package me.TahaCheji.data;

import me.TahaCheji.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ListingData {


    public void saveListing(Listing listing) throws IOException {
        File listingData = new File(Main.getInstance().getDataFolder(), "listings/" + listing.getUuid() + ".yml");
        if(!listingData.exists()) {
            FileConfiguration pD = YamlConfiguration.loadConfiguration(listingData);
            listingData.createNewFile();
            pD.set("data.item", listing.getItem());
            pD.set("data.player", listing.getPlayer().getUniqueId().toString());
            pD.set("data.price", listing.getPrice());
            pD.set("data.loanTime", listing.getLoanTime());
            pD.set("data.uuid", listing.getUuid().toString());
            pD.save(listingData);
        }
    }

    public static List<Listing> getAllSavedListing() {
        List<Listing> listingArrayList = new ArrayList<>();
        //loop all the yml in the listings folder
        File dataFolder = new File("plugins/Renter/listings");
        File[] files = dataFolder.listFiles();
        for(File file : files) {
            FileConfiguration pD = YamlConfiguration.loadConfiguration(file);
            if(pD.getString("data.player") == null) continue;
            UUID uuid = UUID.fromString(pD.getString("data.player"));
            UUID newUuid = UUID.fromString(pD.getString("data.uuid"));
            Listing item = new Listing(Bukkit.getPlayer(uuid), pD.getItemStack("data.item"), pD.getInt("data.price"),pD.getInt("data.loanTime"), newUuid);
            listingArrayList.add(item);
        }
        return listingArrayList;
    }

    public static List<Listing> getAllSavedListing(Player player) {
        List<Listing> listingArrayList = new ArrayList<>();
        //loop all the yml in the listings folder
        File dataFolder = new File("plugins/Renter/listings");
        File[] files = dataFolder.listFiles();
        for(File file : files) {
            FileConfiguration pD = YamlConfiguration.loadConfiguration(file);
            UUID uuid = UUID.fromString(pD.getString("data.player"));
            if(uuid.toString().contains(player.getUniqueId().toString())) {
                UUID newUuid = UUID.fromString(pD.getString("data.uuid"));
                Listing item = new Listing(Bukkit.getPlayer(uuid), pD.getItemStack("data.item"), pD.getInt("data.price"), pD.getInt("data.loanTime"), newUuid);
                listingArrayList.add(item);
            }
        }
        return listingArrayList;
    }

    public static void removeAllListings() {
        File dataFolder = new File("plugins/Renter/listings");
        File[] files = dataFolder.listFiles();
        for(File file : files) {
            file.delete();
        }
    }

    public static void removeListings(Listing listing) {
        File dataFolder = new File("plugins/Renter/listings");
        File[] files = dataFolder.listFiles();
        for(File file : files) {
            if(file.getName().contains(listing.getUuid().toString())) {
                file.delete();
            }
        }
    }

}
