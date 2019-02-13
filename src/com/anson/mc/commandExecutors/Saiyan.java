package com.anson.mc.commandExecutors;

import com.anson.mc.main.LanguageManager;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

class Saiyan {
    static boolean SSJ(Player player) {
        String permSSJ = "hbcc.skill.saiyan.SSJ";
        if(!Permission.check(player, permSSJ)) return false;
        player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 10240, 10));
        player.sendMessage(LanguageManager.skillSaiyanSSJ);
        return true;
    }

    static boolean SSJB(Player player) {
        String permSSJB = "hbcc.skill.saiyan.SSJB";
        if(!Permission.check(player, permSSJB)) return false;
        player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 10240, 100));
        player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 10240, 100));
        player.sendMessage(LanguageManager.skillSaiyanSSJB);
        return true;
    }
}
