package xiao_student.itemloretomate.MyEvent.AttackEvent;

import org.bukkit.ChatColor;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import xiao_student.itemloretomate.MyEvent.MyEvent;
import xiao_student.itemloretomate.MyEvent.MyEventClass;
import xiao_student.itemloretomate.Util.Tool;

public class CritEvent extends MyEventClass implements MyEvent {

    private boolean isCrit = false;

    @Override
    public void setEvent(Event event) {

        setEntityDamageByEntityEvent((EntityDamageByEntityEvent) event);

    }


    @Override
    public void run() {

        double damage;

        if (Tool.getEventBoolean(playerState.getCrit())) {

            damage = playerState.getDamage() * (playerState.getCritDamage() / 100);
            entityDamageByEntityEvent.setDamage(damage);
            isCrit = true;


            playerState.getPlayer().sendMessage(ChatColor.RED + "你对" + entityDamageByEntityEvent.getEntity().getName() + "造成了" + damage + "点暴击伤害");

        } else {

            damage = playerState.getDamage();
            entityDamageByEntityEvent.setDamage(damage);
            playerState.getPlayer().sendMessage(ChatColor.RED + "你对" + entityDamageByEntityEvent.getEntity().getName() + "造成了" + damage + "点伤害");
            isCrit = false;

        }

    }

    public boolean isCrit() {
        return isCrit;
    }
}
