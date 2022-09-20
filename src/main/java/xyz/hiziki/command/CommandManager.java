package xyz.hiziki.command;

import org.bukkit.plugin.java.JavaPlugin;
import xyz.hiziki.Main;
import xyz.hiziki.command.home.HomeCommandExecutor;
import xyz.hiziki.command.home.HomeTabCompleterHandler;
import xyz.hiziki.command.sethome.SetHomeCommandExecutor;
import xyz.hiziki.command.sethome.SetHomeTabCompleterHandler;

import java.util.Objects;

public class CommandManager
{
    private final JavaPlugin plugin = Main.plugin;

    public CommandManager()
    {
        Objects.requireNonNull(plugin.getCommand("home")).setExecutor(new HomeCommandExecutor());
        Objects.requireNonNull(plugin.getCommand("home")).setTabCompleter(new HomeTabCompleterHandler());

        Objects.requireNonNull(plugin.getCommand("setHome")).setExecutor(new SetHomeCommandExecutor());
        Objects.requireNonNull(plugin.getCommand("sethome")).setTabCompleter(new SetHomeTabCompleterHandler());
    }
}
