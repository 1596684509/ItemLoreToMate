package xiao_student.itemloretomate.MyListener;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import xiao_student.itemloretomate.ItemLoreToMate;
import xiao_student.itemloretomate.PlayerState;

public class MyListenerClass {

    protected Player player;
    protected PlayerState playerState;

    protected EntityDamageByEntityEvent entityDamageByEntityEvent;

    public void setEntityDamageByEntityEvent(EntityDamageByEntityEvent entityDamageByEntityEvent) {
        this.entityDamageByEntityEvent = entityDamageByEntityEvent;
        setPlayer((Player) entityDamageByEntityEvent.getDamager());
        setPlayerState();
    }

    public void setPlayerState() {
        this.playerState = ItemLoreToMate.getPlayerStates().get(player.getName());
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public MyListenerClass getMyListener() {

        return this;

    }

}
