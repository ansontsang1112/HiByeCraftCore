package com.anson.mc.hbc.core.commandExecutors;

import com.anson.mc.hbc.core.main.LangManager;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

class Saiyan {
    static boolean SSJ(Player player) {
        String permSSJ = "hbcc.skill.saiyan.SSJ";
        if(!Permission.check(player, permSSJ)) return false;
        player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 10240, 10));
        player.sendMessage(LangManager.skillSaiyanSSJ);
        return true;
    }

    static boolean SSJB(Player player) {
        String permSSJB = "hbcc.skill.saiyan.SSJB";
        if(!Permission.check(player, permSSJB)) return false;
        player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 10240, 100));
        player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 10240, 100));
        player.sendMessage(LangManager.skillSaiyanSSJB);
        return true;
    }
}
