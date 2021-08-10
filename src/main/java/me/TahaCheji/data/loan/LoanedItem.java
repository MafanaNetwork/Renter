package me.TahaCheji.data.loan;

import me.TahaCheji.data.ListingData;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.io.IOException;
import java.util.UUID;

public class LoanedItem {

    private final Player from;
    private final Player to;
    private final ItemStack loanedItem;
    private final int loanTime;
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

    public void giveItemsBack(Player from, Player to) {
        //TODO
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
