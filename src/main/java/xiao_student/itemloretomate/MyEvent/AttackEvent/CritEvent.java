package xiao_student.itemloretomate.MyEvent.AttackEvent;

import org.bukkit.ChatColor;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import xiao_student.itemloretomate.MyEvent.MyEvent;
import xiao_student.itemloretomate.MyEvent.MyEventClass;
import xiao_student.itemloretomate.MyListener.CirtListener;
import xiao_student.itemloretomate.MyListener.MyListener;
import xiao_student.itemloretomate.MyListener.MyListenerClass;
import xiao_student.itemloretomate.Util.Tool;

public class CritEvent extends MyEventClass implements MyEvent {

    private boolean isCrit = false;
    private CirtListener cirtListener;

    @Override
    public void setEvent(Event event) {

        setEntityDamageByEntityEvent((EntityDamageByEntityEvent) event);

    }

    @Override
    public void registerListener(MyListener myListener) {
        super.registerListener(myListener);
    }

    @Override
    public void run() {

        cirtListener = (CirtListener) getMyListener();

        double damage;

        if (Tool.getEventBoolean(state.getCrit())) {

            cirtListener.onStart();

            if(cirtListener.onRun()) {

                damage = state.getDamage() * (state.getCritDamage() / 100);
                entityDamageByEntityEvent.setDamage(damage);
                isCrit = true;

                state.getLivingEntity().sendMessage(ChatColor.RED + "你对" + entityDamageByEntityEvent.getEntity().getName() + "造成了" + damage + "点暴击伤害");

            }

            cirtListener.onEnd();

        } else {

            damage = state.getDamage();
            entityDamageByEntityEvent.setDamage(damage);
            state.getLivingEntity().sendMessage(ChatColor.RED + "你对" + entityDamageByEntityEvent.getEntity().getName() + "造成了" + damage + "点伤害");
            isCrit = false;

        }

    }

    public boolean isCrit() {
        return isCrit;
    }
}
