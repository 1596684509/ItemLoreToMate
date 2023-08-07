package xiao_student.itemloretomate.Skill.AttackEffect;

import org.bukkit.ChatColor;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import xiao_student.itemloretomate.Skill.Skill;
import xiao_student.itemloretomate.Skill.Skillable;
import xiao_student.itemloretomate.Util.EventTimer;

import java.util.HashMap;

public class NaShiZhiHun extends Skill implements Skillable {

    public static final String SKILL_NAME = "纳什之魂";

    @Override
    public void run() {

        double plusDamage = playerState.getApDamage() * 0.35;
        LivingEntity target = (LivingEntity) entityDamageByEntityEvent.getEntity();
        target.damage(plusDamage);
        player.sendMessage(ChatColor.BLUE + "你对" + target.getName() + "造成了" + plusDamage + "点额外伤害");
    }

    @Override
    public void setEvent(Event event) {
        setEntityDamageByEntityEvent((EntityDamageByEntityEvent) event);
    }

    @Override
    public LivingEntity getPlayer() {
        return player;
    }

    @Override
    public EventTimer getTimerTool() {
        return getTimer();
    }

    @Override
    public boolean equal() {
        return super.equal(SKILL_NAME, EquipmentSlot.HAND);
    }

    @Override
    public HashMap getCdListeners() {
        return null;
    }

}
