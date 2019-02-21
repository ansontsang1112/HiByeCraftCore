package com.anson.mc.hbc.core.listeners;

import com.anson.mc.hbc.core.main.HiByeCraftCore;
import com.anson.mc.hbc.core.mysql.PlayerDataManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.HashMap;

public class PlayerDataListener implements Listener {

    private HashMap<Player,Long> playerLoginTime = new HashMap<>();
    private PlayerDataManager playerDataManager;

    public PlayerDataListener(){
        playerDataManager = PlayerDataManager.getInstance();
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        Player player = e.getPlayer();
        long loginTime = Timestamp.from(Instant.now()).getTime();
        playerLoginTime.put(player,loginTime);
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e){
        Player player = e.getPlayer();
        String ip = player.getAddress().getAddress().getHostAddress();
        if (!playerLoginTime.containsKey(player))return;
        long lastLogin = playerLoginTime.get(player);
        Bukkit.getScheduler().runTaskAsynchronously(HiByeCraftCore.plugin,()->playerDataManager.updateTable(player,ip,lastLogin));
    }
}
