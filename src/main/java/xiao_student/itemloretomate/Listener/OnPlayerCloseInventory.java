package xiao_student.itemloretomate.Listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import xiao_student.itemloretomate.State;

public class OnPlayerCloseInventory implements Listener {

    @EventHandler
    public void onPlayerOpenInventory(InventoryCloseEvent event) {

        Player player = (Player) event.getView().getPlayer();
        State state = new State(player);
        state.setPlayerState();

    }

}
