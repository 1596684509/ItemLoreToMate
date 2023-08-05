package xiao_student.itemloretomate.Listener;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.checkerframework.checker.units.qual.A;
import xiao_student.itemloretomate.ItemLoreToMate;
import xiao_student.itemloretomate.MyEvent.EventTimer;
import xiao_student.itemloretomate.MyListener.AttackEvent.AttackHeal;
import xiao_student.itemloretomate.MyListener.AttackEvent.HealEvent;
import xiao_student.itemloretomate.MyListener.AttackEvent.SuckBloodEvent;
import xiao_student.itemloretomate.MyListener.MyListener;
import xiao_student.itemloretomate.MyListener.AttackEvent.CritEvent;
import xiao_student.itemloretomate.PlayerState;

import java.util.ArrayList;
import java.util.HashMap;

public class OnAttack implements Listener {

    private ArrayList<MyListener> publicListeners = new ArrayList<>();

    private ArrayList<MyListener> onAttackListeners = new ArrayList<>();

    public OnAttack() {

        registerPublicListener();
        registerAttackListener();

    }

    private void registerPublicListener() {

        publicListeners.add(new HealEvent());

    }

    private void registerAttackListener() {

        onAttackListeners.add(new CritEvent());
        onAttackListeners.add(new SuckBloodEvent());
        onAttackListeners.add(new AttackHeal());

    }
    @EventHandler
    public void onAttack(EntityDamageByEntityEvent event) {


        if(event.getDamager() instanceof Player) {

            damageByMonster(event);

        }else if(event.getEntity() instanceof Player){

            damageByPlayer(event);

        }


    }

    private void damageByMonster(EntityDamageByEntityEvent event) {

        for (MyListener publicEvent : publicListeners) {

            publicEvent.setEvent(event);
            publicEvent.run();

        }


        for (MyListener myEvent : onAttackListeners) {

            myEvent.setEvent(event);
            myEvent.run();

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


        }

    }

}
