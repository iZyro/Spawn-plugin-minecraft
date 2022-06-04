package me.zyro.zjoin.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import me.zyro.zjoin.Main;

public class Spawn implements CommandExecutor{
    
    
    private Main plugin;
    public Spawn(Main plugin) {
        this.plugin = plugin;
    }
    

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        
        FileConfiguration config = plugin.getConfig();
        String onlyPlayer = "messages.only-player";
        String spawnNotSet = "messages.spawn-not-set";
        String teleportSpawn = "messages.teleport-spawn";

        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString(onlyPlayer)));
            return true;
        }
        Player player = (Player) sender;

        if (!(config.contains("spawn.world"))) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString(spawnNotSet)));
            return true;
        }
        World world = Bukkit.getServer().getWorld(plugin.getConfig().getString("spawn.world"));
        double x = plugin.getConfig().getDouble("spawn.x");
        double y = plugin.getConfig().getDouble("spawn.y");
        double z = plugin.getConfig().getDouble("spawn.z");
        float pitch = (float) plugin.getConfig().getDouble("spawn.pitch");
        float yaw = (float) plugin.getConfig().getDouble("spawn.yaw");

        player.teleport(new Location(world, x, y, z, yaw, pitch));
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString(teleportSpawn)));
        return true;   
    }
}
