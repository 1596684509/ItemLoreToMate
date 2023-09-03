package xiao_student.itemloretomate.Listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;
import xiao_student.itemloretomate.ItemLoreToMate;
import xiao_student.itemloretomate.State;

public class OnPlayerItemHeld implements Listener {

    @EventHandler
    public void onPlayerItemHeld(PlayerItemHeldEvent event) {

        Bukkit.getScheduler().scheduleSyncDelayedTask(ItemLoreToMate.getInstance(), new Runnable() {
            @Override
            public void run() {
                Player player = event.getPlayer();

                State state = new State(player);
                state.setPlayerState();
            }
        }, 0l);

    }

}
