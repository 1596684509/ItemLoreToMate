package xiao_student.itemloretomate.Listener;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import xiao_student.itemloretomate.Skill.CDable;
import xiao_student.itemloretomate.Skill.Skillable;
import xiao_student.itemloretomate.Skill.RightClickSkill.TextSkill;

import java.util.ArrayList;

public class OnRightClick implements Listener {

    private ArrayList<Skillable> skills = new ArrayList<>();

    public OnRightClick() {

        registerSkill();

    }

    private void registerSkill() {

        skills.add(new TextSkill());

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

                    //创建CD监视器
                    if(skill instanceof CDable) {

                        ((CDable) skill).createCdListener();

                    }

                    //CD　判断
                    if(!skill.getCdListeners().get(skill.getPlayer().getName()).isTimerIsOver()) {

                        skill.getPlayer().sendMessage(ChatColor.RED + "[警告]主手武器技能CD还剩 " + skill.getTimerTool().getTimer() + " 秒");
                        return;

                    }

                    skill.run();
                    skill.getTimerTool().start();

                    //释放内存
                    if(skill.getCdListeners().get(skill.getPlayer().getName()).isTimerIsOver()) {

                        if(skill instanceof CDable) {

                            ((CDable) skill).deleteCdListener();

                        }

                    }

                }

            }

        }

    }

}
