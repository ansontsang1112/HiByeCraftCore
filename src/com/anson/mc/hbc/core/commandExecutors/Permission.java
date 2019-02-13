package com.anson.mc.hbc.core.commandExecutors;

import com.anson.mc.hbc.core.main.LangManager;
import org.bukkit.entity.Player;

class Permission {
    public static boolean check(Player player, String permission) {
        if(!player.hasPermission(permission)) {
            player.sendMessage(LangManager.prefix + " " + LangManager.noPerm);
        }
        return true;
    }
}
