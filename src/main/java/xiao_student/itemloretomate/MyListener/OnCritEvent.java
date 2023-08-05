package xiao_student.itemloretomate.MyListener;

import org.bukkit.ChatColor;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import xiao_student.itemloretomate.PlayerState;
import xiao_student.itemloretomate.Util;

public class OnCritEvent extends MyListenerClass implements MyListener{

    private EntityDamageByEntityEvent event;
    private boolean isCrit = false;

    @Override
    public void setEvent(Event event) {

        try {

            this.event = (EntityDamageByEntityEvent) event;

        }catch (ClassCastException e) {

            System.out.print("OnCritEvent: 事件类型错误");
            System.out.print("OnCritEvent: 错误方法 ：setEvent");

        }

    }


    public EntityDamageByEntityEvent getEvent() {
        return event;
    }

    @Override
    public void run() {

        if(event != null) {

            double damage;

            if(Util.getEventBoolean(playerState.getCrit())) {

                damage = playerState.getDamage() * (playerState.getCritDamage() / 100);
                isCrit = true;


                playerState.getPlayer().sendMessage(ChatColor.RED + "你对" + event.getEntity().getName() + "造成了" + damage + "点暴击伤害");

            }else {

                damage = playerState.getDamage();
                isCrit = false;

            }

            event.setDamage(damage);

        }

    }

    public boolean isCrit() {
        return isCrit;
    }
}
