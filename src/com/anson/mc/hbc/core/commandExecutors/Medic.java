package com.anson.mc.hbc.core.commandExecutors;

import com.anson.mc.hbc.core.configs.ConfigManager;
import com.anson.mc.hbc.core.configs.LangManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

class Medic {
    static boolean healOther(Player player, String string) {
        if(!Integrate.xpCheck(player, ConfigManager.xp_medic_heal)) return false;
        Player target = Bukkit.getPlayer(string);
        if(target == null) return false;
        target.setHealth(20.0);
        target.sendMessage(LangManager.prefix + " 你已經被 治療師 " + player.getName() + " 治療");
        player.sendMessage(LangManager.prefix + " 你已經治療 " + target.getName());
        return true;
    }

    static boolean feedOther(Player player, String string) {
        if(!Integrate.xpCheck(player, ConfigManager.xp_medic_feed)) return false;
        Player target = Bukkit.getPlayer(string);
        if(target == null) return false;
        target.setFoodLevel(20);
        target.sendMessage(LangManager.prefix + " 你已經被 治療師 " + player.getName() + " 餵食");
        player.sendMessage(LangManager.prefix + " 你已經餵食 " + target.getName());
        return true;
    }
}
