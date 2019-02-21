package com.anson.mc.hbc.core.commandExecutors;

import com.anson.mc.hbc.core.configs.LangManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SkillCommandExecutors implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(!(commandSender instanceof Player)) return false;

        Player player = (Player) commandSender;

        if(strings.length == 0) {
            commandSender.sendMessage(LangManager.prefix + LangManager.wrongArg);
            return false;
        }

        if("saiyan".equals(strings[0])) {
            if (strings.length > 2 || strings.length <= 1) {
                commandSender.sendMessage(LangManager.prefix + LangManager.wrongArg);
                return false;
            }
            switch (strings[1]) {
                case "SSJ":
                    return Saiyan.SSJ(player);
                case "SSJB":
                    return Saiyan.SSJB(player);
                case "remove":
                    return Saiyan.remove(player);
                default:
                    commandSender.sendMessage(LangManager.prefix + LangManager.wrongArg);
                    return true;
            }
        }

        if("flyer".equals(strings[0])) {
            if (strings.length > 3 || strings.length <= 1) {
                commandSender.sendMessage(LangManager.prefix + LangManager.wrongArg);
                return false;
            }
            switch (strings[1]) {
                case "switch":
                    return Flyer.fly(player);
                case "speed":
                    return Flyer.flySpeed(player, strings[2]);
                default:
                    commandSender.sendMessage(LangManager.prefix + LangManager.wrongArg);
                    return true;
            }
        }

        if("medic".equals(strings[0])) {
            String permission = "hbc.skill.medic";
            if(!Integrate.permissionCheck(player, permission)) return false;
            if (strings.length > 3 || strings.length <= 1) {
                commandSender.sendMessage(LangManager.prefix + LangManager.wrongArg);
                return false;
            }
            switch (strings[1]) {
                case "heal":
                    return Medic.healOther(player, strings[2]);
                case "feed":
                    return Medic.feedOther(player, strings[2]);
                default:
                    commandSender.sendMessage(LangManager.prefix + LangManager.wrongArg);
                    return true;
            }

        }
        return true;
    }
}
