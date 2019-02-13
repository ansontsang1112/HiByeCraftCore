package com.anson.mc.commandExecutors;

import com.anson.mc.main.LanguageManager;
import org.bukkit.entity.Player;

class Permission {
    public static boolean check(Player player, String permission) {
        if(!player.hasPermission(permission)) {
            player.sendMessage(LanguageManager.prefix + " " + LanguageManager.noPerm);
        }
        return true;
    }
}
