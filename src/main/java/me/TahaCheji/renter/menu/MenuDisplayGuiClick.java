package me.TahaCheji.renter.menu;

import me.TahaCheji.data.stash.StorageStash;
import me.TahaCheji.data.stash.StorageStashData;
import me.TahaCheji.renter.listedItems.ListedItemsGui;
import net.milkbowl.vault.chat.Chat;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class MenuDisplayGuiClick implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        if(!e.getView().getTitle().contains("Renter")) {
            return;
        }
        if(e.getCurrentItem() == null) {
            return;
        }
        if(e.getCurrentItem().getItemMeta() == null) {
            return;
        }
        if (e.getSlot() == 19) {
            player.openInventory(new ListedItemsGui().getInventory());
        }
        if(e.getSlot() == 22) {
            if(!e.getCurrentItem().getItemMeta().getDisplayName().contains("Storage Stash")) {
                return;
            }
            player.closeInventory();
            for(StorageStash storageStash : StorageStashData.getAllSavedStorageStash(player)) {
                player.getInventory().addItem(storageStash.getItem());
                player.sendMessage(ChatColor.GOLD + "You have claimed " + storageStash.getItem().getItemMeta().getDisplayName() + ChatColor.GOLD + " from your Storage Stash");
                storageStash.removeStorageStash();
            }
        }
        if(e.getSlot() == 40) {
            player.closeInventory();
        }
        e.setCancelled(true);
    }


}
