package xiao_student.itemloretomate.Listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;
import xiao_student.itemloretomate.PlayerState;

public class OnPlayerItemHeld implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerItemHeld(PlayerItemHeldEvent event) {

        PlayerState playerState = new PlayerState(event.getPlayer());
        playerState.setPlayerState(event.getNewSlot());

    }

}
