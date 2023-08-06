package xiao_student.itemloretomate.Item;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class TestLeggings {

    ItemStack itemStack;

    public TestLeggings(){

        itemStack = new ItemStack(Material.DIAMOND_LEGGINGS);
        ItemMeta itemMeta = itemStack.getItemMeta();
        List<String> lore = new ArrayList<>();

        lore.add(ChatColor.RED + "==> <攻击力> : 7");
        lore.add(ChatColor.RED + "==> <生命值> : 25");
        lore.add(ChatColor.RED + "==> <暴击率> : 7%");
        lore.add(ChatColor.RED + "==> <暴击伤害> : 40%");
        lore.add(ChatColor.RED + "==> <吸血> : 3%");
        itemMeta.setUnbreakable(true);
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);


    }

    public ItemStack getItemStack() {
        return itemStack;
    }

}
