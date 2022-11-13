package xyz.hiziki.command.home

import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import xyz.hiziki.config.ConfigFile

class HomeTabCompleterHandler : TabCompleter
{
    private val config = ConfigFile()
    override fun onTabComplete(sender : CommandSender, command : Command, label : String, args : Array<String>) : List<String>?
    {
        val list : MutableList<String> = ArrayList() //リストを作成
        var i = 1
        while (config.maX_HOME >= i)
        {
            list.add(i.toString()) //リストに追加
            i++
        }
        return list //listを返す
    }
}