package xyz.hiziki.command;

import xyz.hiziki.Main;
import xyz.hiziki.command.home.HomeCommandExecutor;
import xyz.hiziki.command.home.HomeTabCompleterHandler;
import xyz.hiziki.command.sethome.SetHomeCommandExecutor;
import xyz.hiziki.command.sethome.SetHomeTabCompleterHandler;

import java.util.Objects;

public class CommandManager
{
    public CommandManager()
    {
        Objects.requireNonNull(Main.getPlugin().getCommand("home")).setExecutor(new HomeCommandExecutor());
        Objects.requireNonNull(Main.getPlugin().getCommand("home")).setTabCompleter(new HomeTabCompleterHandler());

        Objects.requireNonNull(Main.getPlugin().getCommand("setHome")).setExecutor(new SetHomeCommandExecutor());
        Objects.requireNonNull(Main.getPlugin().getCommand("sethome")).setTabCompleter(new SetHomeTabCompleterHandler());
    }
}
