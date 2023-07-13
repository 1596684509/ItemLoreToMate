package xiao_student.itemloretomate;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import xiao_student.itemloretomate.Item.*;

import java.util.List;

public class Commander implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label,  String[] args) {

        if(!(sender instanceof Player)) {

            sender.sendMessage(ChatColor.RED + "此命令仅玩家可用");
            return true;
        }

        if(args.length == 0) {

            return true;

        }

        switch (args[0]) {

            case "gethelp":
                sendHelp(sender);
                break;

            case "state":

                sendPlayerState(sender);
                break;

            case "get":

                getItemCommand(sender);
                break;

            case "damage":
                ((Player) sender).damage(19);
                break;

            case "lore":
                loreAble(args[1], args, sender);
                break;

        }

        return true;
    }

    private void sendPlayerState(CommandSender sender) {

        if(ItemLoreToMate.getPlayerStates().get(sender.getName()) != null) {

            ItemLoreToMate.getPlayerStates().get(sender.getName()).infoState();

        }

    }

    private void sendHelp(CommandSender sender) {


        sender.sendMessage(ChatColor.YELLOW + "/costomitem get <item name>: 获取物品");


    }

    private void getItemCommand(CommandSender sender) {

        if(sender.isOp()) {

            ((Player) sender).getPlayer().getInventory().addItem(new TestSword().getItemStack());
            ((Player) sender).getPlayer().getInventory().addItem(new TestHelmet().getItemStack());
            ((Player) sender).getPlayer().getInventory().addItem(new TestChestoplate().getItemStack());
            ((Player) sender).getPlayer().getInventory().addItem(new TestLeggings().getItemStack());
            ((Player) sender).getPlayer().getInventory().addItem(new TestBoots().getItemStack());

        }else {

            sender.sendMessage(ChatColor.RED + "你没有权限!");

        }

    }

    private void loreAble(String command, String[] args, CommandSender sender) {

        if(!sender.isOp()) {

            sender.sendMessage(ChatColor.RED + "你没有权限!");
            return;

        }

        switch (command) {

            case "add":
                addItemLore(((Player) sender).getPlayer(), args[2]);
                break;

        }

    }

    private void addItemLore(Player plyaer, String addLore) {

        ItemStack itemStack = plyaer.getInventory().getItemInMainHand();
        ItemMeta itemMeta = itemStack.getItemMeta();
        List<String> lore = itemMeta.getLore();
        lore.add(addLore);
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);

    }

}
