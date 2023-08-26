package network.hiziki.simplesethome.command;

import network.hiziki.simplesethome.command.home.HomeCommandExecutor;
import network.hiziki.simplesethome.command.home.HomeCommandTabCompleter;
import org.bukkit.plugin.java.JavaPlugin;
import network.hiziki.simplesethome.command.sethome.SetHomeCommandTabCompleter;
import network.hiziki.simplesethome.command.sethome.SetHomeCommandExecutor;

/*
*
* CommandManager
* 作成者：hi_kun_JPN
* コマンドを管理するクラス
* */

public class CommandManager
{
    public CommandManager(JavaPlugin plugin)
    {
        plugin.getCommand("home").setExecutor(new HomeCommandExecutor());
        plugin.getCommand("home").setTabCompleter(new HomeCommandTabCompleter());

        plugin.getCommand("setHome").setExecutor(new SetHomeCommandExecutor());
        plugin.getCommand("setHome").setTabCompleter(new SetHomeCommandTabCompleter());
    }
}
