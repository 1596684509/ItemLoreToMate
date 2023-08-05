package xiao_student.itemloretomate.MyListener.AttackEvent;

import org.bukkit.ChatColor;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import xiao_student.itemloretomate.MyListener.MyListener;
import xiao_student.itemloretomate.MyListener.MyListenerClass;

public class SuckBloodEvent extends MyListenerClass implements MyListener {


    @Override
    public void setEvent(Event event) {

        setEntityDamageByEntityEvent((EntityDamageByEntityEvent) event);

    }

    @Override
    public void run() {


        double damage = entityDamageByEntityEvent.getDamage();

        double heal = damage * (playerState.getSuckBlood() / 100);
        if((player.getHealth() + heal) >= player.getMaxHealth()) {

            heal = player.getMaxHealth() - player.getHealth();

        }

        player.setHealth(player.getHealth() + heal);

        if(heal > 0) {

            player.sendMessage(ChatColor.RED + "你通过吸血回复了" + (int)heal + "点生命值");

        }

    }


}
