package xyz.hiziki.command

import org.bukkit.plugin.java.JavaPlugin
import xyz.hiziki.command.home.HomeCommandExecutor
import xyz.hiziki.command.home.HomeTabCompleterHandler
import xyz.hiziki.command.sethome.SetHomeCommandExecutor
import xyz.hiziki.command.sethome.SetHomeTabCompleterHandler

class CommandManager(plugin : JavaPlugin)
{
    init
    {
        plugin.getCommand("home")!!.setExecutor(HomeCommandExecutor())
        plugin.getCommand("home")!!.tabCompleter = HomeTabCompleterHandler()
        plugin.getCommand("setHome")!!.setExecutor(SetHomeCommandExecutor())
        plugin.getCommand("setHome")!!.tabCompleter = SetHomeTabCompleterHandler()
    }
}