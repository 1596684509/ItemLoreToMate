package xiao_student.itemloretomate.Skill.RightClickSkill;

import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import xiao_student.itemloretomate.Skill.CDable;
import xiao_student.itemloretomate.Skill.Skillable;
import xiao_student.itemloretomate.Skill.Skill;
import xiao_student.itemloretomate.Util.EventTimer;

import java.util.HashMap;
import java.util.List;

public class TextSkill extends Skill implements Skillable, CDable {

    public  static HashMap<String, EventTimer> skillCdListeners = new HashMap<>();
    public static final String SKILL_NAME = "测试用技能";

    @Override
    public void createCdListener() {

        super.createCDListeners(skillCdListeners);

    }

    @Override
    public void deleteCdListener() {

        super.deleteCDListeners(skillCdListeners);

    }

    @Override
    public void setEvent(Event event) {
        setPlayerInteractEvent((PlayerInteractEvent) event);
    }

    @Override
    public void run() {

        Entity playerEntity = player;
        List<Entity> entities = playerEntity.getNearbyEntities(5, 5, 5);

        for (Entity entity : entities) {

            if (entity != player) {

                if (entity instanceof LivingEntity) {


                    ((LivingEntity) entity).damage(playerState.getDamage());
                    player.sendMessage(ChatColor.YELLOW + "发动技能对附近的" + entity.getName() + "造成" + ((LivingEntity) entity).getLastDamage() + "伤害");

                }

            }
        }

    }

    @Override
    public LivingEntity getPlayer() {
        return player;
    }

    @Override
    public EventTimer getTimerTool() {
        return super.getTimer();
    }

    @Override
    public boolean equal() {
        return super.equal(SKILL_NAME, EquipmentSlot.HAND);
    }

    @Override
    public HashMap getCdListeners() {
        return skillCdListeners;
    }
}