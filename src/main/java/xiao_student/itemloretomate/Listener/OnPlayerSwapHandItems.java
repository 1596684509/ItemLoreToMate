package xiao_student.itemloretomate.Listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import xiao_student.itemloretomate.State;

public class OnPlayerSwapHandItems implements Listener {

    @EventHandler
    public void onPlayerSwapHandItem(PlayerSwapHandItemsEvent event) {

        State state = new State(event.getPlayer());
        state.setPlayerState();

    }

}
