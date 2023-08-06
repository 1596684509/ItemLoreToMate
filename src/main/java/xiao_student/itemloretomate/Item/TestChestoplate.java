package xiao_student.itemloretomate.Item;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class TestChestoplate {

    ItemStack itemStack;

    public TestChestoplate(){

        itemStack = new ItemStack(Material.DIAMOND_CHESTPLATE);
        ItemMeta itemMeta = itemStack.getItemMeta();
        List<String> lore = new ArrayList<>();

        lore.add(ChatColor.RED + "==> <攻击力> : 7");
        lore.add(ChatColor.RED + "==> <生命值> : 35");
        lore.add(ChatColor.RED + "==> <暴击率> : 5%");
        lore.add(ChatColor.RED + "==> <暴击伤害> : 50%");
        lore.add(ChatColor.RED + "==> <吸血> : 4%");
        lore.add(ChatColor.RED + "==> <生命恢复> : 3");
        itemMeta.setLore(lore);
        itemMeta.setUnbreakable(true);
        itemStack.setItemMeta(itemMeta);


    }

    public ItemStack getItemStack() {
        return itemStack;
    }

}
