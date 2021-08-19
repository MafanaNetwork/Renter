package me.TahaCheji.data.loan;

import me.TahaCheji.Main;
import me.TahaCheji.data.Listing;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class LoanedItemData {


    public void saveLoanedItem(LoanedItem loanedItem) throws IOException {
        File listingData = new File(Main.getInstance().getDataFolder(), "loanedItems/" + loanedItem.getUuid().toString() + ".yml");
        FileConfiguration pD = YamlConfiguration.loadConfiguration(listingData);
        listingData.createNewFile();
        pD.set("data.item", loanedItem.getLoanedItem());
        pD.set("data.from", loanedItem.getFrom().getUniqueId().toString());
        pD.set("data.to", loanedItem.getTo().getUniqueId().toString());
        pD.set("data.loanTime", loanedItem.getLoanTime());
        pD.set("data.uuid", loanedItem.getUuid().toString());
        pD.save(listingData);
    }

    public static List<LoanedItem> getAllSavedLoanedItem() {
        List<LoanedItem> listingArrayList = new ArrayList<>();
        //loop all the yml in the listings folder
        File dataFolder = new File("plugins/Renter/loanedItems");
        File[] files = dataFolder.listFiles();
        for (File file : files) {
            FileConfiguration pD = YamlConfiguration.loadConfiguration(file);
            UUID from = UUID.fromString(pD.getString("data.from"));
            UUID to = UUID.fromString(pD.getString("data.to"));
            UUID uuid = UUID.fromString(pD.getString("data.uuid"));
            LoanedItem item = new LoanedItem(Bukkit.getPlayer(from), Bukkit.getPlayer(to), pD.getItemStack("data.item"), pD.getInt("data.loanTime"), uuid);
            listingArrayList.add(item);
        }
        return listingArrayList;
    }

    public void startCounting(Player player, int i) throws IOException {
        for (LoanedItem loanedItem : getAllSavedLoanedItem()) {
            if (loanedItem.getTo().getUniqueId().toString().contains(player.getUniqueId().toString())) {
                if (loanedItem.getLoanTime() <= 0) {
                    loanedItem.giveItemsBack(loanedItem.getFrom(), loanedItem.getTo());
                } else {
                    loanedItem.changeLogInsTime(i);
                    loanedItem.saveLoanedItem();
                }
            }
        }
    }

    public static void removeAllLoanedItems() {
        File dataFolder = new File("plugins/Renter/loanedItems");
        File[] files = dataFolder.listFiles();
        for (File file : files) {
            file.delete();
        }
    }

    public static void removeLoanedItem(LoanedItem loanedItem) {
        File dataFolder = new File("plugins/Renter/loanedItems");
        File[] files = dataFolder.listFiles();
        for (File file : files) {
            if (file.getName().contains(loanedItem.getUuid().toString())) {
                file.delete();
            }
        }
    }

}
