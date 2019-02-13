package com.anson.mc.main;

import com.anson.mc.commandExecutors.ReloadCommandExecutors;
import com.anson.mc.commandExecutors.SkillCommandExecutors;
import com.anson.mc.listeners.RespawnListener;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class HiByeCraftCore extends JavaPlugin {
    public static Plugin plugin;

    @Override
    public void onEnable() {
        plugin = this;
        ConfigManager.getInstance(this).loadConfig();
        LanguageManager.getInstance(this).loadLang();
        this.getServer().getPluginManager().registerEvents(new RespawnListener(), this);
        this.getCommand("hbccreload").setExecutor(new ReloadCommandExecutors());
        this.getCommand("skill").setExecutor(new SkillCommandExecutors());
        this.getLogger().info(ChatColor.GREEN + "HiByeCraft Core Successfully Loaded !");
    }

    @Override
    public void onDisable() {

    }
}
