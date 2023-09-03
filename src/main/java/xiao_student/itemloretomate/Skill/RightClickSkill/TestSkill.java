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
import java.util.UUID;

public class TestSkill extends Skill implements Skillable, CDable {

    public static final String SKILL_NAME = "测试用技能";

    public TestSkill() {

        setSkillCdListeners(new HashMap<>());
        setSkillCd(5);

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


                    ((LivingEntity) entity).damage(state.getDamage());
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

}