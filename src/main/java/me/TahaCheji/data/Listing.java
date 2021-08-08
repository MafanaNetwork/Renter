package me.TahaCheji.data;

import me.TahaCheji.Main;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Listing {

    private final Player player;
    private final ItemStack item;
    private final int price;
    private final int loanTime;

    public Listing(Player player, ItemStack item, int price, int loanTime) {
        this.player = player;
        this.item = item;
        this.price = price;
        this.loanTime = loanTime;
        Main.listedItems.add(this);
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
