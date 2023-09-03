package xiao_student.itemloretomate.MyEvent;

import org.bukkit.entity.Player;
import xiao_student.itemloretomate.ItemLoreToMate;
import xiao_student.itemloretomate.State;

public class Heal implements TimerEventAble{

    Player player;
    State state;

    public Heal(Player player) {
        this.player = player;
        state = ItemLoreToMate.getPlayerStates().get(player.getUniqueId());
    }

    @Override
    public void run() {

        double heal = state.getHeal();

        if((player.getHealth() + heal) > player.getMaxHealth()) {

            heal = player.getMaxHealth() - player.getHealth();

        }
        if(heal > 0) {

            player.setHealth(player.getHealth() + heal);

        }

    }

}
