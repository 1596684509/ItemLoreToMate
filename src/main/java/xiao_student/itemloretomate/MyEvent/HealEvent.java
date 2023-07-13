package xiao_student.itemloretomate.MyEvent;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import xiao_student.itemloretomate.ItemLoreToMate;
import xiao_student.itemloretomate.PlayerState;

public class HealEvent implements TimerEventAble{

    Player player;
    PlayerState playerState;

    public HealEvent(Player player) {
        this.player = player;
        playerState = ItemLoreToMate.getPlayerStates().get(player.getName());
    }

    @Override
    public void onEvent() {

        double heal = 10;

        if((player.getHealth() + heal) >= player.getMaxHealth()) {

            heal = player.getMaxHealth() - player.getHealth();

        }

        player.setHealth(playerState.getHealth() + heal);

    }

}
