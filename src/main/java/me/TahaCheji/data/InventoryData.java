package me.TahaCheji.data;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class InventoryData {

    public static void saveInventory(Player p) throws IOException {
        File playerData = new File("plugins/Renter/playerData/" + p.getUniqueId() + "/playerInventory.yml");
        FileConfiguration pD = YamlConfiguration.loadConfiguration(playerData);
        if(!(p.getInventory().getContents() == null)) {
            pD.set("inventory.armor", p.getInventory().getArmorContents());
        }
        if(!(p.getInventory().getArmorContents() == null) ){
            pD.set("inventory.content", p.getInventory().getContents());
        }
        pD.save(playerData);
    }

    @SuppressWarnings("unchecked")
    public static void restoreInventory(Player p) throws IOException {
        File playerData = new File("plugins/Renter/playerData/" + p.getUniqueId() + "/playerInventory.yml");
        FileConfiguration pD = YamlConfiguration.loadConfiguration(playerData);
        if(pD.get("inventory.armor") == null || pD.get("inventory.content") == null) {
            saveInventory(p);
            return;
        }
        ItemStack[] content = ((List<ItemStack>) pD.get("inventory.armor")).toArray(new ItemStack[0]);
        p.getInventory().setArmorContents(content);
        content = ((List<ItemStack>) pD.get("inventory.content")).toArray(new ItemStack[0]);
        p.getInventory().setContents(content);
    }

}
