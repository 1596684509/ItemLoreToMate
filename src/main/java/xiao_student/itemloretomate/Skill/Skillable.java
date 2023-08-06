package xiao_student.itemloretomate.Skill;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Event;
import xiao_student.itemloretomate.Util.EventTimer;

import java.util.HashMap;

public interface Skillable {

    void run();
    void setEvent(Event event);

    LivingEntity getPlayer();

    EventTimer getTimerTool();

    boolean equal();

    HashMap<String, EventTimer> getCdListeners();

}
