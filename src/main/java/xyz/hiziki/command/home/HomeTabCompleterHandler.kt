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
        return if (config.maxHome > 1)
        {
            val list : MutableList<String> = ArrayList() //リストを作成

            for (i in 1..config.maxHome)
            {
                list.add(i.toString()) //リストに追加
            }
            list //listを返す
        }
        else
        {
            null
        }
    }
}