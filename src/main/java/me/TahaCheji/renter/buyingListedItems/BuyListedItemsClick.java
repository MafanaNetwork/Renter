package me.TahaCheji.renter.buyingListedItems;

import de.tr7zw.nbtapi.NBTItem;
import me.TahaCheji.Main;
import me.TahaCheji.utl.NBTUtils;
import me.TahaCheji.data.list.Listing;
import me.TahaCheji.data.list.ListingData;
import me.TahaCheji.data.loan.LoanedItem;
import me.TahaCheji.renter.listedItems.ListedItemsGui;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.io.IOException;

public class BuyListedItemsClick implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e) throws IOException {
        if (!e.getView().getTitle().contains("Listing")) {
            return;
        }
        if (e.getCurrentItem() == null) {
            return;
        }
        if (e.getCurrentItem().getItemMeta() == null) {
            return;
        }
        e.setCancelled(true);
        Economy economy = Main.getEcon();
        Player player = (Player) e.getWhoClicked();
        if (e.getSlot() == 42) {
            player.openInventory(new ListedItemsGui().getInventory());
            return;
        }
        if (e.getSlot() == 38) {
            if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Accept")) {
                for (Listing listing : ListingData.getAllSavedListing()) {
                    if (new NBTItem(e.getCurrentItem()).getString("ListUUID").contains(listing.getUuid().toString())) {
                        economy.withdrawPlayer(player, listing.getPrice());
                        economy.depositPlayer(listing.getPlayer(), listing.getPrice());
                        ItemStack itemStack = listing.getItem();
                        itemStack = NBTUtils.setBoolean(itemStack, "Loaned", true);
                        itemStack = NBTUtils.setDouble(itemStack, "LoanTime", listing.getLoanTime());
                        player.getInventory().addItem(itemStack);
                        listing.removeListing();
                        player.closeInventory();
                        LoanedItem item = new LoanedItem(listing.getPlayer(), player, itemStack, listing.getLoanTime());
                        item.saveLoanedItem();
                        player.sendMessage(ChatColor.GREEN + "You have rented " + item.getLoanedItem().getItemMeta().getDisplayName() + ChatColor.GREEN + " from " + item.getFrom().getDisplayName() + ChatColor.GREEN + " thank you for using Renter.");
                        //buy history
                        System.out.println("x");
                    }
                }
            }

        }

    }
}
