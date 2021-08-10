package me.TahaCheji.data;

import me.TahaCheji.Main;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.io.IOException;
import java.util.UUID;

public class Listing {

    private final Player player;
    private final ItemStack item;
    private final int price;
    private final int loanTime;
    private final UUID uuid;

    public Listing(Player player, ItemStack item, int price, int loanTime) {
        this.player = player;
        this.item = item;
        this.price = price;
        this.loanTime = loanTime;
        UUID uuid = UUID.randomUUID();
        this.uuid = uuid;
        // Main.listedItems.add(this);
    }

    public Listing(Player player, ItemStack item, int price, int loanTime, UUID uuid) {
        this.player = player;
        this.item = item;
        this.price = price;
        this.loanTime = loanTime;
        this.uuid = uuid;
        // Main.listedItems.add(this);
    }

    public void saveListing() throws IOException {
        new ListingData().saveListing(this);
    }

    public void setPlayerListing(Player player) {
        player.sendMessage(ChatColor.GOLD + "You have listed " + item.getItemMeta().getDisplayName() + ChatColor.GOLD  + " for rent at a price of $" + price);
    }

    public void removeListing() {
       ListingData.removeListings(this);
    }

    public UUID getUuid() {
        return uuid;
    }

    public Player getPlayer() {
        return player;
    }

    public ItemStack getItem() {
        return item;
    }

    public int getPrice() {
        return price;
    }

    public int getLoanTime() {
        return loanTime;
    }
}
