package xiao_student.itemloretomate.MyEvent;

import org.bukkit.event.Event;
import xiao_student.itemloretomate.MyListener.MyListener;

public interface MyEvent {

    void registerListener(MyListener myListener);
    void setEvent(Event event);
    void run();


}
