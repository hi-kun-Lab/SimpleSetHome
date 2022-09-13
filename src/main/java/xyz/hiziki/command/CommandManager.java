package xyz.hiziki.command;

import org.bukkit.plugin.java.JavaPlugin;
import xyz.hiziki.Main;
import xyz.hiziki.command.home.HomeCommandExecutor;
//import xyz.hiziki.command.home.HomeTabCompleterHandler;
import xyz.hiziki.command.sethome.SetHomeCommandExecutor;
//import xyz.hiziki.command.sethome.SetHomeTabCompleterHandler;

public class CommandManager
{
    public CommandManager()
    {
        Main.getPlugin().getCommand("home").setExecutor(new HomeCommandExecutor());
//        Main.getPlugin().getCommand("home").setTabCompleter(new HomeTabCompleterHandler());

        Main.getPlugin().getCommand("setHome").setExecutor(new SetHomeCommandExecutor());
//        Main.getPlugin().getCommand("sethome").setTabCompleter(new SetHomeTabCompleterHandler());
    }
}
