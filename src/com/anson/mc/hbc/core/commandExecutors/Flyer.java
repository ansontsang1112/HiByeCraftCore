package com.anson.mc.hbc.core.commandExecutors;

import com.anson.mc.hbc.core.configs.LangManager;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

class Flyer {
    public static String flyerPerm = "hbc.skill.flyer";
    static boolean fly(Player player) {

        if(!Integrate.permissionCheck(player, flyerPerm)) return false;
        boolean fly = !player.getAllowFlight();
        player.setAllowFlight(fly);

        String message = (fly == true) ? "啟用" : "關閉";

        player.sendMessage(LangManager.prefix + " 你已經成功 §l" + message + " §r飛行");
        return true;
    }

    static boolean flySpeed(Player player, String strings) {
        int speed = 0;
        if(!Integrate.permissionCheck(player, flyerPerm)) return false;
        if(player.getGameMode() == GameMode.CREATIVE) {
            player.sendMessage(LangManager.prefix + " 你現在是 " + player.getGameMode() + ", 並不能關閉 FLY");
            return false;
        }
        if(strings.length() == 0) {
            player.sendMessage(LangManager.prefix + LangManager.wrongArg);
            return false;
        }
        try{
            speed = Integer.parseInt(strings);
        } catch (NumberFormatException e) {
            player.sendMessage(LangManager.prefix + " 請輸入數字!");
            return false;
        }
        if(speed > 10 || speed < 0) {
            player.sendMessage(LangManager.prefix + " 飛行速度只限於 0 - 10");
            return false;
        }

        player.sendMessage(LangManager.prefix + " 你已經成功設置飛行速度到 " + speed + " m/s");
        player.setFlySpeed(speed/10);

        return true;
    }
}
