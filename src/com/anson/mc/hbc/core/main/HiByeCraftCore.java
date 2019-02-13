package com.anson.mc.hbc.core.main;

import com.anson.mc.hbc.core.commandExecutors.ReloadCommandExecutors;
import com.anson.mc.hbc.core.commandExecutors.SkillCommandExecutors;
import com.anson.mc.hbc.core.listeners.RespawnListener;
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
        this.getCommand("hbc").setExecutor(new ReloadCommandExecutors());
        this.getCommand("skill").setExecutor(new SkillCommandExecutors());
        this.getLogger().info(ChatColor.GREEN + "HiByeCraft Core Successfully Loaded !");
    }

    @Override
    public void onDisable() {

    }
}
