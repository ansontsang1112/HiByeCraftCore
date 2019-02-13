package com.anson.mc.hbc.core.commandExecutors;

import com.anson.mc.hbc.core.main.ConfigManager;
import com.anson.mc.hbc.core.main.LangManager;
import com.anson.mc.hbc.core.main.HiByeCraftCore;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReloadCommandExecutors implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        String reloadPerm = "hbc.reload";
        if(!Permission.check((Player) commandSender, reloadPerm)) return false;

        if(strings.length == 0) {
            commandSender.sendMessage(LangManager.prefix + " " + LangManager.wrongArg);
            return false;
        }

        if(strings[0].equalsIgnoreCase("reload")) {
            LangManager.getInstance(HiByeCraftCore.plugin).reloadLang();
            ConfigManager.getInstance(HiByeCraftCore.plugin).reloadConfig();
            commandSender.sendMessage(LangManager.prefix + ChatColor.GREEN + " config.yml Reload Successfully");
            commandSender.sendMessage(LangManager.prefix + ChatColor.GREEN + " lang.yml Reload Successfully");
            return true;
        }
        return false;
    }
}
