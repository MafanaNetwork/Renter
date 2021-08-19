package me.TahaCheji.data.loan;

import me.TahaCheji.data.ListingData;
import me.TahaCheji.data.stash.StorageStash;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.io.IOException;
import java.util.UUID;

public class LoanedItem {

    private final Player from;
    private final Player to;
    private final ItemStack loanedItem;
    private int loanTime;
    private final UUID uuid;

    public LoanedItem(Player from, Player to, ItemStack loanedItem, int loanTime) {
        this.from = from;
        this.to = to;
        this.loanedItem = loanedItem;
        this.loanTime = loanTime;
        UUID uuid = UUID.randomUUID();
        this.uuid = uuid;
    }

    public LoanedItem(Player from, Player to, ItemStack loanedItem, int loanTime, UUID uuid) {
        this.from = from;
        this.to = to;
        this.loanedItem = loanedItem;
        this.loanTime = loanTime;
        this.uuid = uuid;
    }

    public void saveLoanedItem() throws IOException {
        new LoanedItemData().saveLoanedItem(this);
    }

    public void giveItemsBack(Player from, Player to) throws IOException {
        StorageStash storageStash = new StorageStash(loanedItem, from);
        storageStash.saveStorageStash();
        if(from.isOnline() && to.isOnline()) {
            to.getInventory().removeItem(loanedItem);
            from.sendMessage(ChatColor.GOLD + "The item you have loaned out to " + to.getDisplayName() + ChatColor.GOLD + " has returned back to you.");
            to.sendMessage(ChatColor.RED + "The item you were renting has been returned to " + from.getDisplayName() + ChatColor.RED + " thank you for using Renter.");
        } else if (from.isOnline() && !to.isOnline()) {
            from.sendMessage(ChatColor.GOLD + "The item you have loaned out to " + to.getDisplayName() + ChatColor.GOLD + " has returned back to you.");
            //change there inventory file
         } else if(!from.isOnline() && to.isOnline()) {
            to.getInventory().removeItem(loanedItem);
            to.sendMessage(ChatColor.RED + "The item you were renting has been returned to " + from.getDisplayName() + ChatColor.RED + " thank you for using Renter.");
            //chage there inveotry file
        } else if (!from.isOnline() && to.isOnline()) {
       //both change file
        }
        removeLoanedItem();
    }

    public void changeLogInsTime(int i) {
       this.loanTime = loanTime - i;
    }


    public void removeLoanedItem() {
        LoanedItemData.removeLoanedItem(this);
    }

    public Player getFrom() {
        return from;
    }

    public Player getTo() {
        return to;
    }

    public ItemStack getLoanedItem() {
        return loanedItem;
    }

    public UUID getUuid() {
        return uuid;
    }

    public int getLoanTime() {
        return loanTime;
    }
}
