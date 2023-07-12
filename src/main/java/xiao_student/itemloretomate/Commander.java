package xiao_student.itemloretomate;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import xiao_student.itemloretomate.Item.*;

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

}
