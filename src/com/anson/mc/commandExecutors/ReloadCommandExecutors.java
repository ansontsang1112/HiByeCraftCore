package com.anson.mc.commandExecutors;

import com.anson.mc.main.ConfigManager;
import com.anson.mc.main.HiByeCraftCore;
import com.anson.mc.main.LanguageManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReloadCommandExecutors implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        String reloadPerm = "hbcc.reload";
        if(!Permission.check((Player) commandSender, reloadPerm)) return false;

        if(strings.length == 0) {
            commandSender.sendMessage(LanguageManager.prefix + LanguageManager.wrongArg);
            return false;
        }

        if(strings.length > 1) {
            commandSender.sendMessage(LanguageManager.prefix + LanguageManager.wrongArg);
            return false;
        }

        switch (strings[0]) {
            case "lang":
                LanguageManager.getInstance(HiByeCraftCore.plugin).reloadLang();
                commandSender.sendMessage(LanguageManager.prefix + ChatColor.GREEN + " lang.yml Reload Successfully");
                break;
            case "config":
                ConfigManager.getInstance(HiByeCraftCore.plugin).reloadConfig();
                commandSender.sendMessage(LanguageManager.prefix + ChatColor.GREEN + " config.yml Reload Successfully");
                break;
            default:
                commandSender.sendMessage(LanguageManager.prefix + LanguageManager.wrongArg);
                return false;
        }
        return true;
    }
}
