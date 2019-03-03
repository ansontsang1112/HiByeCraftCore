package com.anson.mc.hbc.core.main;

import com.anson.mc.hbc.core.commandExecutors.HelpCommandExecutors;
import com.anson.mc.hbc.core.commandExecutors.ReloadCommandExecutors;
import com.anson.mc.hbc.core.commandExecutors.SkillCommandExecutors;
import com.anson.mc.hbc.core.configs.ConfigManager;
import com.anson.mc.hbc.core.configs.LangManager;
import com.anson.mc.hbc.core.listeners.PlayerDataListener;
import com.anson.mc.hbc.core.listeners.PlayerListener;
import com.anson.mc.hbc.core.listeners.PlayerRightClickListener;
import com.anson.mc.hbc.core.listeners.RespawnListener;
import com.anson.mc.hbc.core.mysql.PlayerDataManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class HiByeCraftCore extends JavaPlugin {
    public static Plugin plugin;

    @Override
    public void onEnable() {
        plugin = this;
        LangManager.getInstance(this).loadLang();
        ConfigManager.getInstance(this).loadConfig();
        this.getServer().getPluginManager().registerEvents(new RespawnListener(), this);
        this.getServer().getPluginManager().registerEvents(new PlayerListener(), this);
        this.getServer().getPluginManager().registerEvents(new PlayerRightClickListener(), this);
        this.getCommand("hbc").setExecutor(new ReloadCommandExecutors());
        this.getCommand("skill").setExecutor(new SkillCommandExecutors());
        this.getCommand("help").setExecutor(new HelpCommandExecutors());
        this.getLogger().info(ChatColor.GREEN + "HiByeCraft Core Successfully Loaded !");
        if (ConfigManager.sql_enabled) {
            Bukkit.getScheduler().runTaskAsynchronously(this,()-> PlayerDataManager.getInstance().createTable());
            this.getServer().getPluginManager().registerEvents(new PlayerDataListener(),this);
        }

    }

    @Override
    public void onDisable() {

    }
}
