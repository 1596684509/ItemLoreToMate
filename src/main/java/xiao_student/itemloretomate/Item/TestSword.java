package xiao_student.itemloretomate.Item;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import xiao_student.itemloretomate.Skill.AttackEffect.NaShiZhiHun;
import xiao_student.itemloretomate.Skill.RightClickSkill.TextSkill;

import java.util.ArrayList;

public class TestSword {

    private ItemStack itemStack;

    public TestSword() {

        itemStack = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta itemMeta = itemStack.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.RED + "==> <攻击力> : 20");
        lore.add(ChatColor.RED + "==> <生命值> : 15");
        lore.add(ChatColor.RED + "==> <暴击率> : 15%");
        lore.add(ChatColor.RED + "==> <暴击伤害> : 100%");
        lore.add(ChatColor.RED + "==> <吸血> : 5%");
        lore.add(ChatColor.RED + "==> <攻击恢复> : 20");
        lore.add(ChatColor.YELLOW + "技能： " + TextSkill.SKILL_NAME);
        itemMeta.setUnbreakable(true);
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);

    }

    public ItemStack getItemStack() {
        return itemStack;
    }


}
