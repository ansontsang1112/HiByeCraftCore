package com.anson.mc.hbc.core.listeners;

import com.anson.mc.hbc.core.configs.ConfigManager;
import com.anson.mc.hbc.core.configs.LangManager;
import com.anson.mc.hbc.core.main.HiByeCraftCore;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class RespawnListener implements Listener {

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent e) {
        Player p = e.getPlayer();
        int duration = ConfigManager.rpt;
        Bukkit.getServer().getScheduler().runTask(HiByeCraftCore.plugin, () -> {
            p.setNoDamageTicks(duration * 20);
            p.sendMessage(LangManager.prefix + LangManager.startRp + duration + " 秒後將會變回正常模式");
        });
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(HiByeCraftCore.plugin, () -> p.sendMessage(LangManager.prefix + LangManager.endRp), (long) (duration * 20));
    }



}
