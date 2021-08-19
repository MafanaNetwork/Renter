package me.TahaCheji.commands;

import me.TahaCheji.data.Listing;
import me.TahaCheji.renter.listedItems.ListedItemsGui;
import me.TahaCheji.renter.menu.MenuDisplayGui;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.io.IOException;

public class MainCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(label.equalsIgnoreCase("Renting")) {
            if(args.length == 0){
                return true;
            }
            if(args[0].equalsIgnoreCase("menu")) {
                Player player = (Player) sender;
                player.openInventory(new MenuDisplayGui(player).getInventory());
            }
            if(args[0].equalsIgnoreCase("list")){
                if(args.length == 1){
                    return true;
                }
                if(args[1].equalsIgnoreCase("all")) {
                    Player player = (Player) sender;
                    player.openInventory(new ListedItemsGui().getInventory());
                }
                if(args.length == 2){
                    return true;
                }
                Player player = (Player) sender;
                ItemStack item = player.getItemInHand();
                item.setAmount(1);
                int coins = Integer.parseInt(args[1]);
                int time = Integer.parseInt(args[2]);
                if(item.getAmount() == 1) {
                    player.setItemInHand(new ItemStack(Material.AIR));
                } else {
                    ItemStack newItem = item;
                    newItem.setAmount(item.getAmount() - 1);
                    player.setItemInHand(newItem);
                }
                Listing listing =  new Listing(player, item, coins, time);
                try {
                    listing.saveListing();
                    listing.setPlayerListing(player);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }
}
