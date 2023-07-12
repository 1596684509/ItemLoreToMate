package xiao_student.itemloretomate.Listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import xiao_student.itemloretomate.PlayerState;

public class OnPlayerJoin implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

        Player player = event.getPlayer();

        PlayerState playerState = new PlayerState(player);
        playerState.setPlayerState(player.getInventory().getHeldItemSlot());

        player.setHealth(player.getMaxHealth());


    }

}
