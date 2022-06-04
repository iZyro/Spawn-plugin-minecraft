package me.zyro.zjoin;

import java.io.File;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import me.zyro.zjoin.commands.SetSpawn;
import me.zyro.zjoin.commands.Spawn;

public class Main extends JavaPlugin {

    String rutaConfig;

    public void onEnable(){
        registerCommands();
        registerEvents();
        registerConfig();

        System.out.println(ChatColor.translateAlternateColorCodes('&', "&aEl plugin &bzJoin &aha sido habilitado."));

    }

    public void registerCommands(){
        this.getCommand("setspawn").setExecutor(new SetSpawn(this));
        this.getCommand("spawn").setExecutor(new Spawn(this));
    }

    public void registerEvents(){

    }

    public void registerConfig() {
        File config = new File(this.getDataFolder(), "config.yml");
        rutaConfig = config.getPath();
        if (!config.exists()) {
            this.getConfig().options().copyDefaults(true);
            saveConfig();
        }
    }


    private static Main instance;

    public static Main getInstance () {
        return Main.instance;
    }

}