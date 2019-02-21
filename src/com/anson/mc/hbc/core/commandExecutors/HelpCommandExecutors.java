package com.anson.mc.hbc.core.commandExecutors;

import com.anson.mc.hbc.core.configs.LangManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class HelpCommandExecutors implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(strings.length == 0) {
            commandSender.sendMessage(LangManager.prefix + LangManager.wrongArg);
            return false;
        }

        switch (strings[0]) {
            case "skill":
                if(strings.length == 1) {
                    commandSender.sendMessage(LangManager.prefix + LangManager.helpSkillG);
                    return true;
                }
                if(strings[1] == "type") {
                    commandSender.sendMessage(LangManager.prefix + " 1. Saiyan");
                }
            case "reload":
                if(strings.length == 1) {
                    commandSender.sendMessage(LangManager.prefix + LangManager.helpReload);
                }
        }

        return false;
    }
}
