package me.TahaCheji.events;

import me.TahaCheji.data.InventoryData;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.io.IOException;

public class PlayerQuit implements Listener {


    @EventHandler
    public void onQuit(PlayerQuitEvent e) throws IOException {
        InventoryData.saveInventory(e.getPlayer());
    }

}
