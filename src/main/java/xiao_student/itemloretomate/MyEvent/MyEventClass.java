package xiao_student.itemloretomate.MyEvent;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import xiao_student.itemloretomate.ItemLoreToMate;
import xiao_student.itemloretomate.MyListener.CirtListener;
import xiao_student.itemloretomate.MyListener.MyListener;
import xiao_student.itemloretomate.MyListener.MyListenerClass;
import xiao_student.itemloretomate.State;

public class MyEventClass {

    protected Player player;
    protected State state;

    protected EntityDamageByEntityEvent entityDamageByEntityEvent;
    private MyListener myListener = new MyListenerClass();

    protected void setEntityDamageByEntityEvent(EntityDamageByEntityEvent entityDamageByEntityEvent) {
        this.entityDamageByEntityEvent = entityDamageByEntityEvent;
        setPlayer((Player) entityDamageByEntityEvent.getDamager());
        setPlayerState();
    }

    public void setPlayerState() {
        this.state = ItemLoreToMate.getPlayerStates().get(player.getUniqueId());
    }


    public void setPlayer(Player player) {
        this.player = player;
    }

    public MyEventClass getEvent() {

        return this;

    }

    public void registerListener(MyListener myListener) {

        this.myListener = myListener;

    }

    public MyListener getMyListener() {

        return myListener;

    }

}
