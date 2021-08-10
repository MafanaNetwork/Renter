package me.TahaCheji.renter.buyingListedItems;

import me.TahaCheji.Main;
import me.TahaCheji.Utl.NBTUtils;
import me.TahaCheji.data.Listing;
import me.TahaCheji.data.ListingData;
import net.milkbowl.vault.economy.Economy;
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

public class BuyListedItemGui implements InventoryHolder {

    Inventory gui;

    public ItemStack getInfo(Listing listing) {
        ItemStack item = new ItemStack(Material.PAPER);
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();
        meta.setDisplayName("Listing Info");
        lore.add("--------------------------");
        lore.add(ChatColor.GOLD + "Price: $" + listing.getPrice());
        lore.add(ChatColor.GOLD + "Seller: " + listing.getPlayer().getDisplayName());
        lore.add(ChatColor.GOLD + "Loan Time: " + listing.getLoanTime());
        lore.add(ChatColor.GOLD + "Listing UUID: " + listing.getUuid().toString());
        lore.add("--------------------------");
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack getCancelItem() {
        ItemStack item = new ItemStack(Material.BARRIER);
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();
        meta.setDisplayName(ChatColor.RED + "Cancel");
        lore.add("--------------------------");
        lore.add(ChatColor.GOLD + "Click to go back to all listed items");
        lore.add("--------------------------");
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    public BuyListedItemGui(Listing listing, Player player) {
        gui = Bukkit.createInventory(null, 54, ChatColor.GRAY + "" + ChatColor.BOLD + listing.getItem().getItemMeta().getDisplayName() + " Listing");
        List<String> lore1 = new ArrayList<>();
        ItemStack newItem = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta newmeta = newItem.getItemMeta();
        newmeta.setDisplayName(ChatColor.GRAY + "");
        newmeta.setLore(lore1);
        newItem.setItemMeta(newmeta);
        for (int emptySlot = 0; emptySlot < gui.getSize(); emptySlot++) {
            if (gui.getItem(emptySlot) == null || gui.getItem(emptySlot).getType().equals(Material.AIR)) {
                gui.setItem(emptySlot, newItem);
            }
        }

        Economy economy = Main.getEcon();
        gui.setItem(13, listing.getItem());
        gui.setItem(31, getInfo(listing));
        gui.setItem(42, getCancelItem());
        if(economy.getBalance(player) >= listing.getPrice()) {
            ItemStack item = new ItemStack(Material.GREEN_CONCRETE);
            ItemMeta meta = item.getItemMeta();
            List<String> lore = new ArrayList<>();
            meta.setDisplayName(ChatColor.GREEN + "Accept");
            lore.add("--------------------------");
            lore.add(ChatColor.GOLD + "Click to buy this item");
            lore.add("--------------------------");
            meta.setLore(lore);
            item.setItemMeta(meta);
            item = NBTUtils.setString(item, "ListUUID", listing.getUuid().toString());
            gui.setItem(38, item);
        } else  {
            ItemStack item = new ItemStack(Material.RED_CONCRETE);
            ItemMeta meta = item.getItemMeta();
            List<String> lore = new ArrayList<>();
            meta.setDisplayName(ChatColor.RED + "Declined");
            lore.add("--------------------------");
            lore.add(ChatColor.GOLD + "You do not have the coins the buy this item.");
            lore.add("--------------------------");
            meta.setLore(lore);
            item.setItemMeta(meta);
            gui.setItem(38, item);
        }
    }


    @Override
    public Inventory getInventory() {
        return gui;
    }


}
