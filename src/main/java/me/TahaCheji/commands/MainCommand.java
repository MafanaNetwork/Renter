package me.TahaCheji.commands;

import me.TahaCheji.commandUtils.CommandInfo;
import me.TahaCheji.commandUtils.PluginCommand;
import me.TahaCheji.data.Listing;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

@CommandInfo(name = "Renting", requiresPlayer = false)
public class MainCommand extends PluginCommand {

    @Override
    public void execute(Player player, String[] args) {
        if(args.length == 0){
            return;
        }
        if(args[0].equalsIgnoreCase("list")){
            if(args.length == 1){
                return;
            }
            if(args.length == 2){
                return;
            }
            ItemStack item = player.getItemInHand();
            int coins = Integer.parseInt(args[1]);
            int time = Integer.parseInt(args[2]);
            Listing listing =  new Listing(player, item, coins, time);
        }
    }
}
