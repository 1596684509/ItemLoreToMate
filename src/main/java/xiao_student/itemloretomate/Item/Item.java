package xiao_student.itemloretomate.Item;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public abstract class Item {

    private ItemStack itemStack;
    private double attackDamage;
    private double health;
    private String disPlay;
    private List<String> lore;
    private ItemMeta itemMeta;


    public Item(ItemStack itemStack, double attackDamage, double health) {
        this.itemStack = itemStack;
        this.attackDamage = attackDamage;
        this.health= health;
        itemMeta = itemStack.getItemMeta();

    }

    public void setDisPlay(String disPlay) {
        this.disPlay = disPlay;
        itemMeta.setDisplayName(disPlay);
    }

    public void setLore(List<String> lore) {
        this.lore = lore;
        itemMeta.setLore(lore);
    }
}
