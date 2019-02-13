package com.anson.mc.main;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;

public class LanguageManager {
    public static String prefix, noPerm, noPlayer, wrongArg, startRp, endRp, skillSaiyanSSJ, skillSaiyanSSJB;
    public static Integer rpt;
    private static LanguageManager languageManager;
    private File langFile;
    private FileConfiguration gameLang;

    private LanguageManager(Plugin plugin) {
        langFile = new File(plugin.getDataFolder(), "lang.yml");
        if(!langFile.exists()) plugin.saveResource("lang.yml", true);
        gameLang = YamlConfiguration.loadConfiguration(langFile);
    }

    public static LanguageManager getInstance(Plugin plugin) {
        if (languageManager == null) languageManager = new LanguageManager(plugin);
        return languageManager;
    }

    private String translateString(String path) {
        return ChatColor.translateAlternateColorCodes('&', gameLang.getString(path));
    }

    void loadLang() {
        /* Genetic */
        prefix = translateString("genetic.prefix");
        noPerm = translateString("genetic.no-perm");
        noPlayer = translateString("genetic.not-player");
        wrongArg = translateString("genetic.wrong-arg");
        /* Respawn Protection */
        startRp = translateString("respawn-protection.start-rp");
        endRp = translateString("respawn-protection.end-rp");
        /* Skill - Saiyan */
        skillSaiyanSSJ = translateString("skill.saiyan.ssj");
        skillSaiyanSSJB = translateString("skill.saiyan.ssjb");

        rpt = gameLang.getInt("respawn-protection-time");
    }

    public void reloadLang() {
        gameLang = YamlConfiguration.loadConfiguration(langFile);
        loadLang();
    }
}
