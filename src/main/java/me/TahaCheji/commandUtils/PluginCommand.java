package me.TahaCheji.commandUtils;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class PluginCommand implements CommandExecutor {
    private final CommandInfo commandInfo;

    public PluginCommand() {
        commandInfo = getClass().getDeclaredAnnotation(CommandInfo.class);
        Objects.requireNonNull(commandInfo, "Command must have CommandInfo annotations");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!commandInfo.permission().isEmpty()) {
            if(!sender.hasPermission(commandInfo.permission())) {
                sender.sendMessage("You dont have permission to execute this command.");
                return true;
            }
        }

        if(commandInfo.requiresPlayer()) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("You must be a player to execute this command");
            }
            execute((Player) sender, args);
            return true;
        }

        execute(sender, args);
        return false;
    }

    public void execute(Player player, String[] args) {}
    public void execute(CommandSender sender, String[] args) {}

    public CommandInfo getCommandInfo() {
        return commandInfo;
    }
}
