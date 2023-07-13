package xiao_student.itemloretomate;

import org.bukkit.ChatColor;
import org.bukkit.entity.Painting;
import org.bukkit.entity.Player;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PlayerState{
    private Player player;
    private double damage;
    private double health = 20;
    private double crit;
    private double critDamage = 100;
    private double suckBlood;
    private double defense = 0;

    /**
     * 遍历当前玩家的所有部位装备的lore
     * 检测是否拥有指定字符串
     * 获取数值
     * 保存到 ItemLoreToMate 类 的 HashMap
     * @param mainHandSlot 主手的下标
     */
    public void setPlayerState(int mainHandSlot) {

        double nowDamage = 0;
        double nowHeath = 20;
        double nowCrit = 0;
        double nowCritDamage = 100;
        double nowSuckBlood = 0;
        int nowDefense = 0;

        PlayerInventory playerInventory = player.getInventory();
        for (EquipmentSlot value : EquipmentSlot.values()) {

            ItemStack itemStack;
            if(value == EquipmentSlot.HAND) {

                itemStack = playerInventory.getItem(mainHandSlot);

            }else {

                itemStack = playerInventory.getItem(value);

            }

            if(itemStack != null) {

                ItemMeta itemMeta = itemStack.getItemMeta();

                if(itemMeta != null) {

                    List<String> list = itemMeta.getLore();

                    if(list != null) {

                        for (String s : list) {

                            nowDamage += loreToNumber(s, StatePatternEnum.DAMAGE);
                            nowHeath += loreToNumber(s, StatePatternEnum.HEALTH);
                            nowCrit += loreToNumber(s, StatePatternEnum.CRIT);
                            nowCritDamage += loreToNumber(s, StatePatternEnum.CRITDAMAGE);
                            nowSuckBlood += loreToNumber(s, StatePatternEnum.SUCKBLOOD);
                            nowDefense += loreToNumber(s, StatePatternEnum.DEFENSE);


                        }

                    }

                }

            }

        }


        damage = nowDamage;
        health = nowHeath;
        crit = nowCrit;
        critDamage = nowCritDamage;
        suckBlood = nowSuckBlood;
        defense = nowDefense;


        savePlayerState();
        setPlayerStateFromLore();


    }

    private double loreToNumber(String string, StatePatternEnum statePattern) {

        Pattern pattern = Pattern.compile(statePattern.getPattern());
        Matcher matcher = pattern.matcher(string);

        if(matcher.find()) {

            Pattern numFirstPattern = Pattern.compile("[0-9]+%?$");
            Matcher findLastNumber = numFirstPattern.matcher(string);
            findLastNumber.find();

            Pattern clearSignPattern = Pattern.compile("[0-9]+");
            Matcher findNumber = clearSignPattern.matcher(findLastNumber.group());
            findNumber.find();
            return Double.parseDouble(findNumber.group());

        }

        return 0;

    }

    private void savePlayerState() {

        ItemLoreToMate.addPlayerStates(player.getName(), this);
        player.sendMessage("数据保存成功");

    }

    private void setPlayerStateFromLore() {

        PlayerState newPlayerState = ItemLoreToMate.getPlayerStates().get(player.getName());
        player.setMaxHealth(newPlayerState.getHealth());

    }

    public void infoState() {
        player.sendMessage(ChatColor.BOLD + "" + ChatColor.RED + "========[属性]========");
        player.sendMessage(ChatColor.GRAY + "=> 玩家: " + ChatColor.YELLOW + player.getName());
        player.sendMessage(ChatColor.GRAY + "=> 生命值: " + ChatColor.YELLOW + health);
        player.sendMessage(ChatColor.GRAY + "=> 防御力: " + ChatColor.YELLOW + defense);
        player.sendMessage(ChatColor.GRAY + "=> 攻击力: " + ChatColor.YELLOW + damage);
        player.sendMessage(ChatColor.GRAY + "=> 暴击率: " + ChatColor.YELLOW + crit + "%");
        player.sendMessage(ChatColor.GRAY + "=> 暴击伤害: " + ChatColor.YELLOW + critDamage + "%");
        player.sendMessage(ChatColor.GRAY + "=> 吸血: " + ChatColor.YELLOW + suckBlood + "%");

    }

    public PlayerState(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public double getDamage() {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public double getCrit() {
        return crit;
    }

    public void setCrit(double crit) {
        this.crit = crit;
    }

    public double getCritDamage() {
        return critDamage;
    }

    public double getSuckBlood() {
        return suckBlood;
    }

    public void setSuckBlood(double suckBlood) {
        this.suckBlood = suckBlood;
    }

    public void setCritDamage(double critDamage) {
        this.critDamage = critDamage;
    }

    public void setDefense(double defense) {
        this.defense = defense;
    }

    public double getDefense() {
        return defense;
    }
}
