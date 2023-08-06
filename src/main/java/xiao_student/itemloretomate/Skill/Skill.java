package xiao_student.itemloretomate.Skill;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.meta.ItemMeta;
import xiao_student.itemloretomate.ItemLoreToMate;
import xiao_student.itemloretomate.Listener.OnRightClick;
import xiao_student.itemloretomate.PlayerState;
import xiao_student.itemloretomate.Util.EventTimer;
import xiao_student.itemloretomate.Util.Tool;

import java.util.ArrayList;
import java.util.HashMap;

public class Skill {

    protected LivingEntity player;
    protected PlayerState playerState;
    protected PlayerInteractEvent playerInteractEvent;
    protected EntityDamageByEntityEvent entityDamageByEntityEvent;
    private int skillCd;
    private EventTimer timer;

    protected void setPlayerInteractEvent(PlayerInteractEvent playerInteractEvent) {

        this.playerInteractEvent = playerInteractEvent;
        setPlayerData(playerInteractEvent.getPlayer());

    }

    public void setEntityDamageByEntityEvent(EntityDamageByEntityEvent entityDamageByEntityEvent) {
        this.entityDamageByEntityEvent = entityDamageByEntityEvent;
        setPlayerData((Player) entityDamageByEntityEvent.getDamager());
    }

    private void setPlayerData(Player player) {

        this.player = player;
        playerState = ItemLoreToMate.getPlayerStates().get(player.getName());

    }

    public void setSkillCd(int skillCd) {
        this.skillCd = skillCd;
    }

    public int getSkillCd() {
        return skillCd;
    }

    public void setTimer(EventTimer timer) {
        this.timer = timer;
    }

    public EventTimer getTimer() {
        return timer;
    }

    public boolean equal(String skillName, EquipmentSlot equipmentSlot) {

        ItemMeta itemMeta = ((Player) player).getInventory().getItem(equipmentSlot).getItemMeta();

        ArrayList<String> lore = (ArrayList<String>) itemMeta.getLore();

        for (String s : lore) {

            if(Tool.getFinded(s, skillName)) {

                return true;

            }

        }

        return false;

    }

    protected void createCDListeners(HashMap<String, EventTimer> skillCdListeners) {

        if(skillCdListeners.containsKey(player.getName())) {

            if(skillCdListeners.get(player.getName()) == null) {

                EventTimer eventTimer = new EventTimer(null);
                eventTimer.setTimer(5);
                setTimer(eventTimer);

                skillCdListeners.put(player.getName(), getTimer());

            }

        }else {

            EventTimer eventTimer = new EventTimer(null);
            eventTimer.setTimer(5);
            setTimer(eventTimer);
            skillCdListeners.put(player.getName(), getTimer());

        }

    }

    protected void deleteCDListeners(HashMap<String, EventTimer> skillCdListeners) {

        if(skillCdListeners.containsKey(player.getName())) {

            if(skillCdListeners.get(player.getName()) != null) {

                skillCdListeners.remove(player.getName());

            }

        }

    }

}
