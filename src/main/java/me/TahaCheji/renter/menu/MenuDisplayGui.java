package me.TahaCheji.renter.menu;

import me.TahaCheji.Utl.NBTUtils;
import me.TahaCheji.data.Listing;
import me.TahaCheji.data.ListingData;
import me.TahaCheji.data.stash.StorageStash;
import me.TahaCheji.data.stash.StorageStashData;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class MenuDisplayGui implements InventoryHolder {

    Inventory gui;

    public ItemStack openListedItems() {
        ItemStack item = new ItemStack(Material.GOLD_BLOCK);
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();
        meta.setDisplayName(ChatColor.GOLD + "All Listings");
        lore.add("--------------------------");
        lore.add(ChatColor.GOLD + "Click to see all listings");
        lore.add("--------------------------");
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack storageStash(Player player) {
        if(StorageStashData.getAllSavedStorageStash(player).size() == 0) {
            ItemStack item = new ItemStack(Material.BARRIER);
            ItemMeta meta = item.getItemMeta();
            List<String> lore = new ArrayList<>();
            meta.setDisplayName(ChatColor.GOLD + "Storage Stash Error");
            lore.add("--------------------------");
            lore.add(ChatColor.RED + "You do not have anything in your stash");
            lore.add("--------------------------");
            meta.setLore(lore);
            item.setItemMeta(meta);
            return item;
        } else {
            ItemStack item = new ItemStack(Material.CHEST);
            ItemMeta meta = item.getItemMeta();
            List<String> lore = new ArrayList<>();
            meta.setDisplayName(ChatColor.GOLD + "Storage Stash");
            lore.add("--------------------------");
            lore.add(ChatColor.GOLD + "Click to claim all your stash");
            for(StorageStash storageStash : StorageStashData.getAllSavedStorageStash(player)) {
                lore.add(ChatColor.DARK_GRAY + "-" + storageStash.getItem().getItemMeta().getDisplayName());
            }
            lore.add("--------------------------");
            meta.setLore(lore);
            item.setItemMeta(meta);
            return item;
        }
    }

    public ItemStack renterInfo() {
        ItemStack item = new ItemStack(Material.PAPER);
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();
        meta.setDisplayName(ChatColor.GOLD + "Renter Info");
        lore.add("--------------------------");
        lore.add(ChatColor.GOLD + "A place where you can rent items to players!");
        lore.add("--------------------------");
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack close() {
        ItemStack item = new ItemStack(Material.BARRIER);
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();
        meta.setDisplayName(ChatColor.RED + "Close");
        lore.add("--------------------------");
        lore.add(ChatColor.GOLD + "Click to close the gui");
        lore.add("--------------------------");
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    public MenuDisplayGui(Player player) {
        gui = Bukkit.createInventory(null, 54, ChatColor.GOLD + "" + ChatColor.BOLD + "Renter");
        List<String> lore = new ArrayList<>();
        ItemStack newItem = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta newmeta = newItem.getItemMeta();
        newmeta.setDisplayName(ChatColor.GRAY + "");
        newmeta.setLore(lore);
        newItem.setItemMeta(newmeta);
        //Set up for the items to look nice
        gui.setItem(19, openListedItems());
        gui.setItem(22, storageStash(player));
        gui.setItem(25, renterInfo());
        gui.setItem(40, close());
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
