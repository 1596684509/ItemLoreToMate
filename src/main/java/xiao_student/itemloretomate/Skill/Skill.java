package xiao_student.itemloretomate.Skill;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.meta.ItemMeta;
import xiao_student.itemloretomate.ItemLoreToMate;
import xiao_student.itemloretomate.State;
import xiao_student.itemloretomate.Util.EventTimer;
import xiao_student.itemloretomate.Util.Tool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class Skill {

    protected LivingEntity player;
    protected State state;
    protected PlayerInteractEvent playerInteractEvent;
    protected EntityDamageByEntityEvent entityDamageByEntityEvent;
    private int skillCd;
    private EventTimer timer;

    private HashMap<UUID, EventTimer> skillCdListeners;


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
        state = ItemLoreToMate.getPlayerStates().get(player.getUniqueId());

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

    public void createCdListener() {

        if(skillCdListeners.containsKey(player.getUniqueId())) {

            if(skillCdListeners.get(player.getUniqueId()) == null) {

                EventTimer eventTimer = new EventTimer(null);
                eventTimer.setTimer(skillCd);
                setTimer(eventTimer);

                skillCdListeners.put(player.getUniqueId(), getTimer());

            }

        }else {

            EventTimer eventTimer = new EventTimer(null);
            eventTimer.setTimer(skillCd);
            setTimer(eventTimer);
            skillCdListeners.put(player.getUniqueId(), getTimer());

        }

    }

    public void deleteCdListener() {

        if(skillCdListeners.containsKey(player.getUniqueId())) {

            if(skillCdListeners.get(player.getUniqueId()) != null) {

                skillCdListeners.remove(player.getUniqueId());

            }

        }

    }

    public HashMap<UUID, EventTimer> getCdListeners() {
        return skillCdListeners;
    }

    public void setSkillCdListeners(HashMap<UUID, EventTimer> skillCdListeners) {
        this.skillCdListeners = skillCdListeners;
    }
}
