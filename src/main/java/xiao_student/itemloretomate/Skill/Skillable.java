package xiao_student.itemloretomate.Skill;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Event;
import xiao_student.itemloretomate.Util.EventTimer;

import java.util.HashMap;
import java.util.UUID;

public interface Skillable {

    void run();
    void setEvent(Event event);

    LivingEntity getPlayer();

    EventTimer getTimerTool();

    boolean equal();

    HashMap<UUID, EventTimer> getCdListeners();

}
