package com.anson.mc.listeners;

import com.anson.mc.main.ConfigManager;
import com.anson.mc.main.HiByeCraftCore;
import com.anson.mc.main.LanguageManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class RespawnListener implements Listener {
    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent e) {
        Player p = e.getPlayer();
        int duration = LanguageManager.rpt;
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(HiByeCraftCore.plugin, () -> {
            p.setNoDamageTicks(duration * 20);
            p.sendMessage(LanguageManager.prefix + LanguageManager.startRp + duration + " 秒後將會變回正常模式");
        }, 0l);

        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(HiByeCraftCore.plugin, () -> p.sendMessage(LanguageManager.prefix + LanguageManager.endRp), (duration * 20) * 1l);
    }

}
