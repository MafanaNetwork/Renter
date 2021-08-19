package me.TahaCheji.renter.listedItems;

import me.TahaCheji.Utl.NBTUtils;
import me.TahaCheji.data.Listing;
import me.TahaCheji.data.ListingData;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ListedItemsGui implements InventoryHolder {

    Inventory gui;

    public ListedItemsGui() {
        gui = Bukkit.createInventory(null, 54, ChatColor.GRAY + "" + ChatColor.BOLD + "Listed Weapons");
        List<String> lore = new ArrayList<>();
        ItemStack newItem = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta newmeta = newItem.getItemMeta();
        newmeta.setDisplayName(ChatColor.GRAY + "");
        newmeta.setLore(lore);
        newItem.setItemMeta(newmeta);
        //Set up for the items to look nice
        for(Listing listing : ListingData.getAllSavedListing())  {
            ItemStack item = listing.getItem();
            ItemMeta itemMeta = item.getItemMeta();
            List<String> itemLore = new ArrayList<>();
            for(String string : itemMeta.getLore()){
                itemLore.add(string);
            }
            itemLore.add(ChatColor.DARK_GRAY + "");
            itemLore.add("------------------------");
            itemLore.add(ChatColor.DARK_GRAY + "Price: $" + listing.getPrice());
            itemLore.add(ChatColor.DARK_GRAY + "Login Time: " + listing.getLoanTime() + "x");
            itemLore.add(ChatColor.DARK_GRAY + "Seller: " + listing.getPlayer().getDisplayName());
            itemLore.add(ChatColor.DARK_GRAY + "");
            itemLore.add(ChatColor.DARK_GRAY + "Click to buy!");
            itemMeta.setLore(itemLore);
            item.setItemMeta(itemMeta);
           item = NBTUtils.setBoolean(item, "listed", true);
           item = NBTUtils.setString(item, "ListUUID", listing.getUuid().toString());
            gui.addItem(item);
        }
        for (int emptySlot = 0; emptySlot < gui.getSize(); emptySlot++) {
            if (gui.getItem(emptySlot) == null || gui.getItem(emptySlot).getType().equals(Material.AIR)) {
                gui.setItem(emptySlot, newItem);
            }
        }

    }


    @Override
    public Inventory getInventory() {
        return gui;
    }

}
