package xiao_student.itemloretomate.MyListener.AttackEvent;

import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import xiao_student.itemloretomate.MyEvent.EventTimer;
import xiao_student.itemloretomate.MyEvent.Heal;
import xiao_student.itemloretomate.MyListener.MyListener;
import xiao_student.itemloretomate.MyListener.MyListenerClass;

import java.util.Timer;

public class HealEvent extends MyListenerClass implements MyListener {

    EventTimer healEventTimer;
    @Override
    public void setEvent(Event event) {

        setEntityDamageByEntityEvent((EntityDamageByEntityEvent) event);
        healEventTimer = new EventTimer(new Heal(player));

    }

    @Override
    public void run() {

        if(playerState != null) {

            if(healEventTimer == null) {

                healEventTimer = new EventTimer(new Heal(player));

            }


            healEventTimer.setTimer(5);

            if(healEventTimer.isTimerIsOver()) {

                healEventTimer.start();

            }else {

                healEventTimer.setTimer(5);

            }


        }

    }
}
