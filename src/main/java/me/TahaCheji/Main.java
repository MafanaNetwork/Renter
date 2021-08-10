package me.TahaCheji;

import me.TahaCheji.Utl.Files;
import me.TahaCheji.commands.MainCommand;
import me.TahaCheji.data.Listing;
import me.TahaCheji.data.ListingData;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.event.Listener;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.reflections.Reflections;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public final class Main extends JavaPlugin {


    private static Main instance;
    private static Economy econ = null;
    public static List<Listing> listedItems = ListingData.getAllSavedListing();


    @Override
    public void onEnable() {
        System.out.println(ChatColor.GREEN + "[Starting] Renter Plugin");
        instance = this;

        String packageName = getClass().getPackage().getName();
        for (Class<?> clazz : new Reflections(packageName, ".listeners").getSubTypesOf(Listener.class)) {
            try {
                Listener listener = (Listener) clazz.getDeclaredConstructor().newInstance();
                getServer().getPluginManager().registerEvents(listener, this);
            } catch (InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
                e.printStackTrace();
            }
        }


        getCommand("Renting").setExecutor(new MainCommand());

        if (!setupEconomy()) {
            System.out.print("No econ plugin found.");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        try {
            Files.initFiles();
        } catch (IOException | InvalidConfigurationException e2) {
            e2.printStackTrace();
        }
    }

    @Override
    public void onDisable() {
        System.out.println(ChatColor.DARK_RED + "[Stopping] Renter Plugin");

        for (Listing listing : listedItems) {
            try {
                new ListingData().saveListing(listing);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }


    public static Economy getEcon() {
        return econ;
    }


    public static Main getInstance() {
        return instance;
    }
}
