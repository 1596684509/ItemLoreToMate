package xiao_student.itemloretomate.Listener;

import net.md_5.bungee.api.ChatMessageType;
import org.bukkit.ChatColor;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import xiao_student.itemloretomate.ItemLoreToMate;
import xiao_student.itemloretomate.PlayerState;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Random;

public class OnAttack implements Listener {


    @EventHandler
    public void onAttack(EntityDamageByEntityEvent event) {

        if(event.getDamager() instanceof Player) {

            onPlayerDamageByMonster(event);

        }else if(event.getDamager() instanceof Player) {



        }


    }

    private void onPlayerDamageByMonster(EntityDamageByEntityEvent event) {

        PlayerState playerState = ItemLoreToMate.getPlayerStates().get(event.getDamager().getName());

        if(playerState != null && event.getEntity() instanceof LivingEntity) {

            Player player = (Player) event.getDamager();
            event.setDamage(onCrit(playerState));
            player.setHealth(player.getHealth() + (attackSuckBlood(playerState, player, event.getDamage())));
            event.getDamager().sendMessage("你对" + event.getEntity().getName() + "造成了" + (int) event.getDamage() + "点伤害");

        }

    }

    //计算防御属性
    private void monsterDamageByPlayer() {



    }

    private double onCrit(PlayerState playerState) {

        Random random = new Random();
        double critNum = random.nextInt(100)+1;
        if(critNum <= playerState.getCrit()) {

            return playerState.getDamage() * (playerState.getCritDamage() / 100);

        }else {

            return playerState.getDamage();

        }

    }

    private double attackSuckBlood(PlayerState playerState, Player player, double damage) {

        double heal = damage * (playerState.getSuckBlood() / 100);
        if((player.getHealth() + heal) >= player.getMaxHealth()) {

            heal = player.getMaxHealth() - player.getHealth();

        }

        if(heal > 0) {

            player.sendMessage(ChatColor.RED + "你通过吸血回复了" + (int)heal + "点生命值");

        }

        return heal;

    }

}
