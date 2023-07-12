package xiao_student.itemloretomate.Listener;

import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.PlayerInventory;
import xiao_student.itemloretomate.ItemLoreToMate;
import xiao_student.itemloretomate.PlayerState;

public class OnPlayerCloseInventory implements Listener {

    @EventHandler
    public void onPlayerOpenInventory(InventoryCloseEvent event) {

        Player player = (Player) event.getView().getPlayer();
        PlayerState playerState = new PlayerState(player);
        playerState.setPlayerState(player.getInventory().getHeldItemSlot());

    }

}
