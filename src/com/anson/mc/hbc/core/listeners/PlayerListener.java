package com.anson.mc.hbc.core.listeners;

import com.anson.mc.hbc.core.commandExecutors.Integrate;
import com.anson.mc.hbc.core.configs.ConfigManager;
import com.anson.mc.hbc.core.configs.LangManager;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PlayerListener implements Listener {
    @EventHandler
    public void onHealthDecrease(EntityDamageEvent e) {
        Entity target = e.getEntity();
        if(!(target instanceof Player)) {
            return;
        }
        Player player = (Player) target;
        if(!player.hasPermission("hbc.skill.medic")) return;
        if(!(player.getHealth() > ConfigManager.medic_regH)) {
            return;
        }
        player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, ConfigManager.medic_regT*20, 2));
        player.sendMessage(LangManager.prefix + " 回復技能自動發動，將會在 " + ConfigManager.medic_regT + "s 後結束。");
    }

    public void skillInvisible(EntityDamageEvent e) {
        Entity target = e.getEntity();
        if(!(target instanceof Player)) return;
        Player player = (Player) target;
        if(!player.hasPermission("hbc.skill.inv")) return;
        player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, ConfigManager.inv_T*20, 2));
        player.sendMessage(LangManager.prefix + " 隱身技能自動發動，將會在 " + ConfigManager.medic_regT + "s 後結束。");
    }
}