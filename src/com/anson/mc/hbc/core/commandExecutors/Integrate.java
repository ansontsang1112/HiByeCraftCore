package com.anson.mc.hbc.core.commandExecutors;

import com.anson.mc.hbc.core.configs.LangManager;
import org.bukkit.entity.Player;

public class Integrate {
    public static boolean permissionCheck(Player player, String permission) {
        if(!player.hasPermission(permission)) {
            player.sendMessage(LangManager.prefix + LangManager.noPerm);
            return false;
        }
        return true;
    }

    static boolean xpCheck(Player player, int xpNeed) {
        if(player.getLevel() < xpNeed) {
            player.sendMessage(LangManager.prefix + LangManager.noXP);
            return false;
        }
        return true;
    }
}
