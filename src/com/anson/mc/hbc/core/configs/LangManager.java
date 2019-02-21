package com.anson.mc.hbc.core.configs;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;

public class LangManager {
    public static String prefix, noPerm, noPlayer, noXP, wrongArg, startRp, endRp, skillSaiyanSSJ, skillSaiyanSSJB, getSkillSaiyanRemove, helpG,helpSkillG, helpReload;
    private static LangManager langManager;
    private File langFile;
    private FileConfiguration lang;

    private LangManager(Plugin plugin) {
        langFile = new File(plugin.getDataFolder(), "lang.yml");
        if(!langFile.exists()) plugin.saveResource("lang.yml", true);
        lang = YamlConfiguration.loadConfiguration(langFile);
    }

    public static LangManager getInstance(Plugin plugin) {
        if (langManager == null) langManager = new LangManager(plugin);
        return langManager;
    }


    private String translateString(String path) {
        return ChatColor.translateAlternateColorCodes('&', lang.getString(path));
    }

    public void loadLang() {
        /* Genetic */
        prefix = translateString("genetic.prefix");
        noPerm = translateString("genetic.no-perm");
        noPlayer = translateString("genetic.not-player");
        wrongArg = translateString("genetic.wrong-arg");
        noXP = translateString("genetic.no-xp");
        /* Respawn Protection */
        startRp = translateString("respawn-protection.start-rp");
        endRp = translateString("respawn-protection.end-rp");
        /* Skill - Saiyan */
        skillSaiyanSSJ = translateString("skill.saiyan.ssj");
        skillSaiyanSSJB = translateString("skill.saiyan.ssjb");
        getSkillSaiyanRemove = translateString("skill.saiyan.removeal");
        /* Help */
        helpG = translateString("help.error");
        helpSkillG = translateString("help.skill.general");
        helpReload = translateString("help.reload");
    }

    public void reloadLang() {
        lang = YamlConfiguration.loadConfiguration(langFile);
        loadLang();
    }
}
