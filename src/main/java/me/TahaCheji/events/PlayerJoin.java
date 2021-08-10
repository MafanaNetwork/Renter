package me.TahaCheji.events;

import me.TahaCheji.data.InventoryData;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.IOException;

public class PlayerJoin implements Listener {


    @EventHandler
    public void onJoin(PlayerJoinEvent e) throws IOException {
        InventoryData.restoreInventory(e.getPlayer());
    }

}
