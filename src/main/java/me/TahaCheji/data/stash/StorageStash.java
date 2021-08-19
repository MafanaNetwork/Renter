package me.TahaCheji.data.stash;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.io.IOException;
import java.util.UUID;

public class StorageStash {

    private final ItemStack item;
    private final Player player;
    private final UUID uuid;

    public StorageStash(ItemStack item, Player player) {
        this.item = item;
        this.player = player;
        UUID uuid = UUID.randomUUID();
        this.uuid = uuid;
    }

    public StorageStash(ItemStack item, Player player, UUID uuid) {
        this.item = item;
        this.player = player;
        this.uuid = uuid;
    }

    public void saveStorageStash() throws IOException {
       new StorageStashData().saveStash(this);
    }

    public void removeStorageStash() {
        StorageStashData.removeStorageStash(this);
    }

    public UUID getUuid() {
        return uuid;
    }

    public ItemStack getItem() {
        return item;
    }

    public Player getPlayer() {
        return player;
    }
}
