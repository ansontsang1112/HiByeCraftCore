package com.anson.mc.commandExecutors;

import com.anson.mc.main.LanguageManager;
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
            commandSender.sendMessage(LanguageManager.prefix + LanguageManager.wrongArg);
            return false;
        }

        switch(strings[0]) {
            case "saiyan":
                if(strings.length > 2 || strings.length <= 1) {
                    commandSender.sendMessage(LanguageManager.prefix + LanguageManager.wrongArg);
                    return false;
                }
                switch(strings[1]) {
                    case "SSJ":
                        return Saiyan.SSJ(player);
                    case "SSJB":
                        return Saiyan.SSJB(player);
                    default:
                        commandSender.sendMessage(LanguageManager.prefix + LanguageManager.wrongArg);
                        return true;
                }
        }
        return true;
    }
}
