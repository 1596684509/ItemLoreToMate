package xiao_student.itemloretomate.Listener;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import xiao_student.itemloretomate.Skill.CDable;
import xiao_student.itemloretomate.Skill.Skillable;
import xiao_student.itemloretomate.Skill.RightClickSkill.TestSkill;

import java.util.ArrayList;

public class OnRightClick implements Listener {

    private ArrayList<Skillable> skills = new ArrayList<>();

    public OnRightClick() {

        registerSkill();

    }

    private void registerSkill() {

        skills.add(new TestSkill());

    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {

        if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {

            if(event.getHand() == EquipmentSlot.HAND) {

                for (Skillable skill : skills) {

                    skill.setEvent(event);

                    //item 判断
                    if(!skill.equal()) {

                        continue;

                    }

                    //CD监视を作る
                    if(skill instanceof CDable) {

                        ((CDable) skill).createCdListener();

                    }

                    //CD　判断
                    if(skill.getCdListeners() != null || !skill.getCdListeners().get(skill.getPlayer().getUniqueId()).isTimerIsOver()) {

                        skill.getPlayer().sendMessage(ChatColor.RED + "[警告]主手武器技能CD还剩 " + skill.getTimerTool().getTimer() + " 秒");
                        continue;

                    }

                    skill.run();
                    skill.getTimerTool().start();

                    //メモリー解放
                    if(skill.getCdListeners() != null || skill.getCdListeners().get(skill.getPlayer().getUniqueId()).isTimerIsOver()) {

                        if(skill instanceof CDable) {

                            ((CDable) skill).deleteCdListener();

                        }

                    }

                }

            }

        }

    }

}
