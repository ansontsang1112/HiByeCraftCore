package com.anson.mc.hbc.core.commandExecutors;

import com.anson.mc.hbc.core.configs.ConfigManager;
import com.anson.mc.hbc.core.configs.LangManager;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

class Saiyan {
    static boolean SSJ(Player player) {
        String permSSJ = "hbcc.skill.saiyan.SSJ";
        if(!Integrate.xpCheck(player, ConfigManager.xp_saiyan_ssj)) return false;
        if(!Integrate.permissionCheck(player, permSSJ)) return false;
        player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 10240*20, 10));
        player.sendMessage(LangManager.prefix + LangManager.skillSaiyanSSJ);
        return true;
    }

    static boolean SSJB(Player player) {
        String permSSJB = "hbcc.skill.saiyan.SSJB";
        if(!Integrate.xpCheck(player, ConfigManager.xp_saiyan_ssjb)) return false;
        if(!Integrate.permissionCheck(player, permSSJB)) return false;
        player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 10240*20, 100));
        player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 10240*20, 100));
        player.sendMessage(LangManager.prefix + LangManager.skillSaiyanSSJB);
        return true;
    }

    static boolean remove(Player player) {
        String permRemove = "hbc.skill.saiyan.remove";
        if(!Integrate.permissionCheck(player, permRemove)) return false;
        for(PotionEffect effect : player.getActivePotionEffects()) {
            player.removePotionEffect(effect.getType());
        }
        player.sendMessage(LangManager.prefix + LangManager.getSkillSaiyanRemove);
        return true;
    }
}
