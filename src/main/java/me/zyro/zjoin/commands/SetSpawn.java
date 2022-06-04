package me.zyro.zjoin.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import me.zyro.zjoin.Main;

public class SetSpawn implements CommandExecutor{
    
    private Main plugin;
    public SetSpawn(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        FileConfiguration config = plugin.getConfig();
        String onlyPlayer = "messages.only-player";
        String noPermissions = "messages.no-permissions";
        String setSpawn = "messages.set-spawn";

        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString(onlyPlayer)));
            return true;
        }
        
        Player player = (Player) sender;

        if(!player.isOp() || !player.hasPermission("zjoin.setspawn")) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString(noPermissions)));
            return true;
        }

        plugin.getConfig().set("spawn.world", player.getLocation().getWorld().getName());
        plugin.getConfig().set("spawn.x", player.getLocation().getX());
        plugin.getConfig().set("spawn.y", player.getLocation().getY());
        plugin.getConfig().set("spawn.z", player.getLocation().getZ());
        plugin.getConfig().set("spawn.pitch", player.getLocation().getPitch());
        plugin.getConfig().set("spawn.yaw", player.getLocation().getYaw());

        plugin.saveConfig();
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString(setSpawn)));
        return true;
    }

}
