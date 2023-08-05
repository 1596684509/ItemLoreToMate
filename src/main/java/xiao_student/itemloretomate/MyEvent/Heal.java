package xiao_student.itemloretomate.MyEvent;

import org.bukkit.entity.Player;
import xiao_student.itemloretomate.ItemLoreToMate;
import xiao_student.itemloretomate.PlayerState;

public class Heal implements TimerEventAble{

    Player player;
    PlayerState playerState;

    public Heal(Player player) {
        this.player = player;
        playerState = ItemLoreToMate.getPlayerStates().get(player.getName());
    }

    @Override
    public void onEvent() {

        double heal = playerState.getHeal();

        if((player.getHealth() + heal) > player.getMaxHealth()) {

            heal = player.getMaxHealth() - player.getHealth();

        }
        if(heal > 0) {

            player.setHealth(player.getHealth() + heal);

        }

    }

}
