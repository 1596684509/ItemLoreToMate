package xiao_student.itemloretomate.Item;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class TestHelmet {

    ItemStack itemStack;

    public TestHelmet(){

        itemStack = new ItemStack(Material.DIAMOND_HELMET);
        ItemMeta itemMeta = itemStack.getItemMeta();
        List<String> lore = new ArrayList<>();

        lore.add(ChatColor.RED + "==> <攻击力> : 3");
        lore.add(ChatColor.RED + "==> <生命值> : 20");
        lore.add(ChatColor.RED + "==> <暴击率> : 3%");
        lore.add(ChatColor.RED + "==> <暴击伤害> : 35%");
        lore.add(ChatColor.RED + "==> <吸血> : 2%");
        itemMeta.setLore(lore);
        itemMeta.setUnbreakable(true);
        itemStack.setItemMeta(itemMeta);


    }

    public ItemStack getItemStack() {
        return itemStack;
    }


}
