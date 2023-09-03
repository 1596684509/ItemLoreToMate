package xiao_student.itemloretomate;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import xiao_student.itemloretomate.Listener.*;

import java.util.HashMap;
import java.util.UUID;

public final class ItemLoreToMate extends JavaPlugin {

    private static ItemLoreToMate instance;
    private static HashMap<UUID, State> playerStates;

    @Override
    public void onEnable() {

        instance = this;
        playerStates = new HashMap<>();
        for (Player onlinePlayer : getServer().getOnlinePlayers()) {

            State state = new State(onlinePlayer);
            state.setPlayerState();

        }
        getServer().getPluginManager().registerEvents(new OnPlayerCloseInventory(), this);
        getServer().getPluginManager().registerEvents(new OnDamageByEntity(), this);
        getServer().getPluginManager().registerEvents(new OnPlayerSwapHandItems(), this);
        getServer().getPluginManager().registerEvents(new OnPlayerItemHeld(), this);
        getServer().getPluginManager().registerEvents(new OnPlayerJoin(), this);
        getServer().getPluginManager().registerEvents(new OnRightClick(), this);
        this.getCommand("iltm").setExecutor(new Commander());

    }

    @Override
    public void onDisable() {

        getServer().savePlayers();

    }

    public static void addPlayerStates(UUID uuid, State state) {

        playerStates.put(uuid, state);

    }

    public static HashMap<UUID, State> getPlayerStates() {
        return playerStates;
    }

    public static ItemLoreToMate getInstance() {
        return instance;
    }
}
