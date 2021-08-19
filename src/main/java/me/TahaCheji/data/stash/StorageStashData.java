package me.TahaCheji.data.stash;

import me.TahaCheji.Main;
import me.TahaCheji.data.loan.LoanedItem;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class StorageStashData {

    public void saveStash(StorageStash storageStash) throws IOException {
        File listingData = new File(Main.getInstance().getDataFolder(), "storageStash/" + storageStash.getUuid().toString() + ".yml");
        FileConfiguration pD = YamlConfiguration.loadConfiguration(listingData);
        listingData.createNewFile();
        pD.set("data.item", storageStash.getItem());
        pD.set("data.player", storageStash.getPlayer().getUniqueId().toString());
        pD.set("data.uuid", storageStash.getUuid().toString());
        pD.save(listingData);
    }

    public static List<StorageStash> getAllSavedStorageStash(Player player) {
        List<StorageStash> listingArrayList = new ArrayList<>();
        //loop all the yml in the listings folder
        File dataFolder = new File("plugins/Renter/storageStash");
        File[] files = dataFolder.listFiles();
        for (File file : files) {
            FileConfiguration pD = YamlConfiguration.loadConfiguration(file);
            UUID from = UUID.fromString(pD.getString("data.player"));
            Player newPlayer = Bukkit.getPlayer(from);
            if(newPlayer.getUniqueId().toString().contains(player.getUniqueId().toString())) {
                UUID uuid = UUID.fromString(pD.getString("data.uuid"));
                StorageStash storageStash = new StorageStash(pD.getItemStack("data.item"), newPlayer, uuid);
                listingArrayList.add(storageStash);
            }
        }
        return listingArrayList;
    }

    public static void removeAllStorageStash(Player player) {
        File dataFolder = new File("plugins/Renter/storageStash");
        File[] files = dataFolder.listFiles();
        for (File file : files) {
            FileConfiguration pD = YamlConfiguration.loadConfiguration(file);
            UUID from = UUID.fromString(pD.getString("data.player"));
            Player newPlayer = Bukkit.getPlayer(from);
            if(newPlayer.getUniqueId().toString().contains(player.getUniqueId().toString())) {
                file.delete();
            }
        }
    }

    public static void removeAllStorageStash() {
        File dataFolder = new File("plugins/Renter/storageStash");
        File[] files = dataFolder.listFiles();
        for (File file : files) {
            file.delete();
        }
    }

    public static void removeStorageStash(StorageStash storageStash) {
        File dataFolder = new File("plugins/Renter/storageStash");
        File[] files = dataFolder.listFiles();
        for (File file : files) {
            if (file.getName().contains(storageStash.getUuid().toString())) {
                file.delete();
            }
        }
    }

}
