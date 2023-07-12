package xiao_student.itemloretomate;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import xiao_student.itemloretomate.Listener.*;

import java.util.HashMap;

public final class ItemLoreToMate extends JavaPlugin {

    private static ItemLoreToMate instance;
    private static HashMap<String, PlayerState> playerStates;

    @Override
    public void onEnable() {

        instance = this;
        playerStates = new HashMap<>();
        for (Player onlinePlayer : getServer().getOnlinePlayers()) {

            PlayerState playerState = new PlayerState(onlinePlayer);
            playerState.setPlayerState(onlinePlayer.getInventory().getHeldItemSlot());

        }
        getServer().getPluginManager().registerEvents(new OnPlayerCloseInventory(), this);
        getServer().getPluginManager().registerEvents(new OnAttack(), this);
        getServer().getPluginManager().registerEvents(new OnPlayerSwapHandItems(), this);
        getServer().getPluginManager().registerEvents(new OnPlayerItemHeld(), this);
        getServer().getPluginManager().registerEvents(new OnPlayerJoin(), this);
        this.getCommand("iltm").setExecutor(new Commander());

    }

    @Override
    public void onDisable() {

        getServer().savePlayers();

    }

    public static void addPlayerStates(String name, PlayerState state) {

        playerStates.put(name, state);

    }

    public static HashMap<String, PlayerState> getPlayerStates() {
        return playerStates;
    }

    public static ItemLoreToMate getInstance() {
        return instance;
    }
}
