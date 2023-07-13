package xiao_student.itemloretomate.Item;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class TestBoots {

    ItemStack itemStack;

    public TestBoots(){

        itemStack = new ItemStack(Material.DIAMOND_BOOTS);
        ItemMeta itemMeta = itemStack.getItemMeta();
        List<String> lore = new ArrayList<>();

        lore.add(ChatColor.RED + "==> <攻击力> : 10");
        lore.add(ChatColor.RED + "==> <生命值> : 5");
        lore.add(ChatColor.RED + "==> <暴击率> : 7%");
        lore.add(ChatColor.RED + "==> <暴击伤害> : 50%");
        lore.add(ChatColor.RED + "==> <吸血> : 3%");
        lore.add(ChatColor.RED + "==> <速度加成> : 50%");
        itemMeta.setLore(lore);
        itemMeta.setAttributeModifiers(null);
        itemStack.setItemMeta(itemMeta);


    }

    public ItemStack getItemStack() {
        return itemStack;
    }

}
