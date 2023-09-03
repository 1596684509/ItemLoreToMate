package xiao_student.itemloretomate;

import org.bukkit.ChatColor;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class State {
    private LivingEntity livingEntity;
    private double damage;
    private double apDamage;
    private double health = 20;
    private double crit;
    private double critDamage = 100;
    private double suckBlood;
    private double defense;
    private double speed;
    private double attackHeal;
    private double heal;

    /**
     * 遍历当前玩家的所有部位装备的lore
     * 检测是否拥有指定字符串
     * 获取数值
     * 保存到 ItemLoreToMate 类 的 HashMap
     */
    public void setPlayerState() {

        double nowDamage = 0;
        double nowApDamage = 0;
        double nowHeath = 20;
        double nowCrit = 0;
        double nowCritDamage = 100;
        double nowSuckBlood = 0;
        int nowDefense = 0;
        double nowSpeed = 0;
        double nowAttackHeal = 0;
        double nowHeal = 0;

        //PlayerInventory playerInventory = livingEntity.getInventory();
        for (EquipmentSlot value : EquipmentSlot.values()) {

            ItemStack itemStack;

            itemStack = livingEntity.getEquipment().getItem(value);

//            if(value == EquipmentSlot.HAND) {
//
//                itemStack = playerInventory.getItem(mainHandSlot);
//
//            }else {
//
//                itemStack = playerInventory.getItem(value);
//
//            }

            if(itemStack != null) {

                ItemMeta itemMeta = itemStack.getItemMeta();

                if(itemMeta != null) {

                    List<String> list = itemMeta.getLore();

                    if(list != null) {

                        for (String s : list) {

                            nowDamage += loreToNumber(s, StatePatternEnum.DAMAGE);
                            nowApDamage += loreToNumber(s, StatePatternEnum.APDAMAGE);
                            nowHeath += loreToNumber(s, StatePatternEnum.HEALTH);
                            nowCrit += loreToNumber(s, StatePatternEnum.CRIT);
                            nowCritDamage += loreToNumber(s, StatePatternEnum.CRITDAMAGE);
                            nowSuckBlood += loreToNumber(s, StatePatternEnum.SUCKBLOOD);
                            nowDefense += loreToNumber(s, StatePatternEnum.DEFENSE);
                            nowSpeed += loreToNumber(s, StatePatternEnum.SPEED);
                            nowAttackHeal += loreToNumber(s, StatePatternEnum.ATTACKHEAL);
                            nowHeal += loreToNumber(s, StatePatternEnum.HEAL);

                        }

                    }

                }

            }

        }


        damage = nowDamage;
        apDamage = nowApDamage;
        health = nowHeath;
        crit = nowCrit;
        critDamage = nowCritDamage;
        suckBlood = nowSuckBlood;
        defense = nowDefense;
        speed = nowSpeed;
        attackHeal = nowAttackHeal;
        heal = nowHeal;


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

        ItemLoreToMate.addPlayerStates(livingEntity.getUniqueId(), this);

    }

    private void setPlayerStateFromLore() {

        State state = ItemLoreToMate.getPlayerStates().get(livingEntity.getUniqueId());
        livingEntity.setMaxHealth(state.getHealth());
        double newPlayerSpeed = 0.2 + (0.2 * (state.getSpeed() / 100));

        if(newPlayerSpeed > 1) {

            newPlayerSpeed = 1;

        }else if(newPlayerSpeed < -1) {

            newPlayerSpeed = -1;

        }

        if(livingEntity instanceof Player) {

            ((Player)livingEntity).setWalkSpeed((float) newPlayerSpeed);

        }


    }

    public void infoState() {
        livingEntity.sendMessage(ChatColor.BOLD + "" + ChatColor.RED + "========[属性]========");
        livingEntity.sendMessage(ChatColor.GRAY + "=> 玩家: " + ChatColor.YELLOW + livingEntity.getName());
        livingEntity.sendMessage(ChatColor.GRAY + "=> 生命值: " + ChatColor.YELLOW + health);
        livingEntity.sendMessage(ChatColor.GRAY + "=> 防御力: " + ChatColor.YELLOW + defense);
        livingEntity.sendMessage(ChatColor.GRAY + "=> 攻击力: " + ChatColor.YELLOW + damage);
        livingEntity.sendMessage(ChatColor.GRAY + "=> 法术强度: " + ChatColor.YELLOW + apDamage);
        livingEntity.sendMessage(ChatColor.GRAY + "=> 暴击率: " + ChatColor.YELLOW + crit + "%");
        livingEntity.sendMessage(ChatColor.GRAY + "=> 暴击伤害: " + ChatColor.YELLOW + critDamage + "%");
        livingEntity.sendMessage(ChatColor.GRAY + "=> 吸血: " + ChatColor.YELLOW + suckBlood + "%");
        livingEntity.sendMessage(ChatColor.GRAY + "=> 攻击恢复: " + ChatColor.YELLOW + damage);
        livingEntity.sendMessage(ChatColor.GRAY + "=> 生命恢复: " + ChatColor.YELLOW + heal);
        livingEntity.sendMessage(ChatColor.GRAY + "=> 速度加成: " + ChatColor.YELLOW + speed + "%");

    }

    public State(Player player) {
        this.livingEntity = player;
    }

    public LivingEntity getLivingEntity() {
        return livingEntity;
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

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void setAttackHeal(double attackHeal) {
        this.attackHeal = attackHeal;
    }

    public double getAttackHeal() {
        return attackHeal;
    }

    public void setHeal(double heal) {
        this.heal = heal;
    }

    public double getHeal() {
        return heal;
    }

    public void setApDamage(double apDamage) {
        this.apDamage = apDamage;
    }

    public double getApDamage() {
        return apDamage;
    }
}
