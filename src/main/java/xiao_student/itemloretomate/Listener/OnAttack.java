package xiao_student.itemloretomate.Listener;

import org.bukkit.ChatColor;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import xiao_student.itemloretomate.ItemLoreToMate;
import xiao_student.itemloretomate.MyEvent.EventTimer;
import xiao_student.itemloretomate.MyEvent.HealEvent;
import xiao_student.itemloretomate.PlayerState;
import xiao_student.itemloretomate.Util;

public class OnAttack implements Listener {

    private EventTimer healEventTimer;
    @EventHandler
    public void onAttack(EntityDamageByEntityEvent event) {

        if(event.getDamager() instanceof Player) {

            damageByMonster(event);

        }else if(event.getEntity() instanceof Player){

            damageByPlayer(event);

        }


    }

    private void damageByMonster(EntityDamageByEntityEvent event) {

        PlayerState playerState = ItemLoreToMate.getPlayerStates().get(event.getDamager().getName());

        if(playerState != null && event.getEntity() instanceof LivingEntity) {

            Player player = (Player) event.getDamager();
            event.setDamage(onCrit(playerState));
            player.setHealth(player.getHealth() + (attackSuckBlood(playerState, player, event.getDamage())));
            player.setHealth(player.getHealth() + (attackHeal(playerState, player)));
            heal(playerState, player);
            event.getDamager().sendMessage("你对" + event.getEntity().getName() + "造成了" + (int) event.getDamage() + "点伤害");

        }

    }

    //计算防御属性
    private void damageByPlayer(EntityDamageByEntityEvent event) {

        PlayerState playerState = ItemLoreToMate.getPlayerStates().get(event.getEntity().getName());

        if(playerState != null && event.getEntity() instanceof LivingEntity) {

            double defense = playerState.getDefense();
            double damage = event.getDamage();
            double reduceDamge = defense / damage;
            double newDamge = damage - (damage * reduceDamge);
            event.setDamage(newDamge);
            heal(playerState, (Player) event.getEntity());

        }

    }

    private double onCrit(PlayerState playerState) {


        if(Util.getEventBoolean(playerState.getCrit())) {

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

    private double attackHeal(PlayerState playerState, Player player) {

        double heal = playerState.getAttackHeal();

        if((player.getHealth() + heal) >= player.getMaxHealth()) {

            heal = player.getMaxHealth() - player.getHealth();

        }

        if(heal > 0) {

            player.sendMessage(ChatColor.RED + "你通过攻击恢复了" + (int)heal + "点生命值");

        }

        return heal;

    }

    private void heal(PlayerState playerState, Player player) {

        if(playerState != null) {

            if(healEventTimer == null) {

                healEventTimer = new EventTimer(new HealEvent(player));

            }


            healEventTimer.setTimer(5);

            if(healEventTimer.isTimerIsOver()) {

                healEventTimer.start();

            }else {

                healEventTimer.setTimer(5);

            }


        }

    }

}
