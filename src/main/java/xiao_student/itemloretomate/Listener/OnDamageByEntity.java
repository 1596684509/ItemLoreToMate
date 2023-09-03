package xiao_student.itemloretomate.Listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import xiao_student.itemloretomate.ItemLoreToMate;
import xiao_student.itemloretomate.MyEvent.AttackEvent.AttackHeal;
import xiao_student.itemloretomate.MyEvent.AttackEvent.HealEvent;
import xiao_student.itemloretomate.MyEvent.AttackEvent.SuckBloodEvent;
import xiao_student.itemloretomate.MyEvent.MyEvent;
import xiao_student.itemloretomate.MyEvent.AttackEvent.CritEvent;
import xiao_student.itemloretomate.State;
import xiao_student.itemloretomate.Skill.AttackEffect.NaShiZhiHun;
import xiao_student.itemloretomate.Skill.Skillable;

import java.util.ArrayList;

public class OnDamageByEntity implements Listener {

    private ArrayList<MyEvent> publicListeners = new ArrayList<>();

    private ArrayList<MyEvent> onAttackListeners = new ArrayList<>();
    private ArrayList<Skillable> attackEffect = new ArrayList<>();

    public OnDamageByEntity() {

        registerPublicListener();
        registerAttackListener();
        registerAttackEffect();

    }

    private void registerAttackEffect() {

        attackEffect.add(new NaShiZhiHun());

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

        for (MyEvent publicEvent : publicListeners) {

            publicEvent.setEvent(event);
            publicEvent.run();

        }

        for (MyEvent myEvent : onAttackListeners) {

            myEvent.setEvent(event);
            myEvent.run();

        }

        for (Skillable skillable : attackEffect) {

            skillable.setEvent(event);

            if(skillable.equal()) {

                skillable.run();

            }

        }

    }

    //计算防御属性
    private void damageByPlayer(EntityDamageByEntityEvent event) {

        State state = ItemLoreToMate.getPlayerStates().get(event.getEntity().getUniqueId());

        double defense = state.getDefense();
        double damage = event.getDamage();
        double reduceDamge = defense / damage;
        double newDamge = damage - (damage * reduceDamge);
        event.setDamage(newDamge);

    }

}
