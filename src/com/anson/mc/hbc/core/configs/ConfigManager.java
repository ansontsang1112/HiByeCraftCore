package com.anson.mc.hbc.core.configs;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;

public class ConfigManager {
    public static int rpt,sql_port,pool_min,pool_max, xp_saiyan_ssj, xp_saiyan_ssjb, xp_medic_heal, xp_medic_feed, xp_flyer, medic_regH, medic_regT, inv_T;
    public static String host,database,table,username,password;
    public static boolean sql_enabled,use_ssl;
    private static ConfigManager configManager;
    private File configFile;
    private FileConfiguration config;

    private ConfigManager(Plugin plugin) {
        configFile = new File(plugin.getDataFolder(), "config.yml");
        if(!configFile.exists()) plugin.saveResource("config.yml", true);
        config = YamlConfiguration.loadConfiguration(configFile);
    }

    public static ConfigManager getInstance(Plugin plugin) {
        if (configManager == null) configManager = new ConfigManager(plugin);
        return configManager;
    }

    public FileConfiguration getConfig() {
        return config;
    }

    public void loadConfig() {
        /* Spawn Protection */
        rpt = config.getInt("respawn-protection-time");
        /*Skill Config */
        xp_saiyan_ssj = config.getInt("skill.xp.saiyan.ssj");
        xp_saiyan_ssjb = config.getInt("skill.xp.saiyan.ssjb");
        xp_medic_heal = config.getInt("skill.xp.medic.heal");
        xp_medic_feed = config.getInt("skill.xp.medic.feed");
        xp_flyer = config.getInt("skill.xp.flyer");
        medic_regH = config.getInt("skill.medic.regen-require");
        medic_regT = config.getInt("skill.medic.regen-time");
        inv_T = config.getInt("skill.inv.inv-time");
        /* SQL Config */
        sql_enabled = config.getBoolean("mysql.enabled");
        host = config.getString("mysql.host");
        sql_port = config.getInt("mysql.port");
        database = config.getString("mysql.database");
        table = config.getString("mysql.table");
        use_ssl = config.getBoolean("mysql.use-ssl");
        pool_min = config.getInt("mysql.pool-size.min");
        pool_max = config.getInt("mysql.pool-size.max");
        password = config.getString("mysql.password");
        username = config.getString("mysql.username");
    }

    public void reloadConfig() {
        config = YamlConfiguration.loadConfiguration(configFile);
        loadConfig();
    }
}
