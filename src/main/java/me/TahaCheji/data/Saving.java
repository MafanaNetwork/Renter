package me.TahaCheji.data;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Saving {


    public void saveListing(Listing listing) throws IOException {
        File listingData = new File("plugins/Renting/listings/" + listing.getItem().getItemMeta().getDisplayName() + ".yml");
        FileConfiguration pD = YamlConfiguration.loadConfiguration(listingData);
        try {
            pD.load(listingData);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }

        pD.set("data.item", listing.getItem());
        pD.set("data.loanTime", listing.getLoanTime());
        pD.set("data.player", listing.getPlayer().getUniqueId());
        pD.set("data.price", listing.getPrice());
        pD.save(listingData);


    }

    public static List<Listing> getAllSavedListing() {
        List<Listing> listingArrayList = new ArrayList<>();
        //loop all the yml in the listings folder
        File dataFolder = new File("plugins/Renting/listings");
        File[] files = dataFolder.listFiles();
        for(File file : files) {
            FileConfiguration pD = YamlConfiguration.loadConfiguration(file);
            Listing item = new Listing((Player) pD.getOfflinePlayer("data.player"), pD.getItemStack("data.item"), pD.getInt("data.price"),pD.getInt("data.loanTime"));
            listingArrayList.add(item);
        }
        return listingArrayList;
    }

}
