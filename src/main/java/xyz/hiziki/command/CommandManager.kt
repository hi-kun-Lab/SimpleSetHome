package xyz.hiziki.command;

import org.bukkit.plugin.java.JavaPlugin;
import xyz.hiziki.command.home.HomeCommandExecutor;
import xyz.hiziki.command.home.HomeTabCompleterHandler;
import xyz.hiziki.command.sethome.SetHomeCommandExecutor;
import xyz.hiziki.command.sethome.SetHomeTabCompleterHandler;

import java.util.Objects;

public class CommandManager
{
    public CommandManager(JavaPlugin plugin)
    {
        Objects.requireNonNull(plugin.getCommand("home")).setExecutor(new HomeCommandExecutor());
        Objects.requireNonNull(plugin.getCommand("home")).setTabCompleter(new HomeTabCompleterHandler());

        Objects.requireNonNull(plugin.getCommand("setHome")).setExecutor(new SetHomeCommandExecutor());
        Objects.requireNonNull(plugin.getCommand("setHome")).setTabCompleter(new SetHomeTabCompleterHandler());
    }
}
