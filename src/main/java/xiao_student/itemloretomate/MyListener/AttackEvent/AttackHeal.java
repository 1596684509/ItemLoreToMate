package xiao_student.itemloretomate.MyListener.AttackEvent;

import org.bukkit.ChatColor;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import xiao_student.itemloretomate.MyListener.MyListener;
import xiao_student.itemloretomate.MyListener.MyListenerClass;

public class AttackHeal extends MyListenerClass implements MyListener {

    @Override
    public void setEvent(Event event) {

        setEntityDamageByEntityEvent((EntityDamageByEntityEvent) event);

    }

    @Override
    public void run() {

        double heal = playerState.getAttackHeal();

        if((player.getHealth() + heal) >= player.getMaxHealth()) {

            heal = player.getMaxHealth() - player.getHealth();

        }

        player.setHealth(player.getHealth() + heal);

        if(heal > 0) {

            player.sendMessage(ChatColor.RED + "你通过攻击恢复了" + (int)heal + "点生命值");

        }

    }
}
