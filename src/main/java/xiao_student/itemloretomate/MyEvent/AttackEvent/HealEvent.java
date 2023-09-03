package xiao_student.itemloretomate.MyEvent.AttackEvent;

import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import xiao_student.itemloretomate.Util.EventTimer;
import xiao_student.itemloretomate.MyEvent.Heal;
import xiao_student.itemloretomate.MyEvent.MyEvent;
import xiao_student.itemloretomate.MyEvent.MyEventClass;

public class HealEvent extends MyEventClass implements MyEvent {

    EventTimer healEventTimer;
    @Override
    public void setEvent(Event event) {

        setEntityDamageByEntityEvent((EntityDamageByEntityEvent) event);
        healEventTimer = new EventTimer(new Heal(player));

    }

    @Override
    public void run() {

        if(state != null) {

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
