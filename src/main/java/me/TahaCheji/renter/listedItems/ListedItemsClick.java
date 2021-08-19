package me.TahaCheji.renter.listedItems;

import de.tr7zw.nbtapi.NBTItem;
import me.TahaCheji.data.Listing;
import me.TahaCheji.data.ListingData;
import me.TahaCheji.renter.buyingListedItems.BuyListedItemGui;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ListedItemsClick implements Listener {


    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if(!e.getView().getTitle().contains("Listed Weapons")) {
            return;
        }
        if(e.getCurrentItem() == null){
            return;
        }
        if(e.getCurrentItem().getItemMeta() == null) {
            return;
        }
        if(!new NBTItem(e.getCurrentItem()).getBoolean("listed")) {
            e.setCancelled(true);
            return;
        }
        for(Listing listing : ListingData.getAllSavedListing()) {
            if(new NBTItem(e.getCurrentItem()).getString("ListUUID").contains(listing.getUuid().toString())){
                Player player = (Player) e.getWhoClicked();
                player.openInventory(new BuyListedItemGui(listing, player).getInventory());
            } else {
                //todo
            }
        }
    }


}
