package xiao_student.itemloretomate.Listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import xiao_student.itemloretomate.PlayerState;

public class OnPlayerSwapHandItems implements Listener {

    @EventHandler
    public void onPlayerSwapHandItem(PlayerSwapHandItemsEvent event) {

        PlayerState playerState = new PlayerState(event.getPlayer());
        playerState.setPlayerState(event.getPlayer().getInventory().getHeldItemSlot());

    }

}
