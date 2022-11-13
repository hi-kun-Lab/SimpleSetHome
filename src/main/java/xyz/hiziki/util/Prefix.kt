package xyz.hiziki.util

import org.bukkit.ChatColor
import org.bukkit.entity.Player

class Prefix(p : Player, msg : String)
{
    init
    {
        p.sendMessage(ChatColor.AQUA.toString() + "[" + ChatColor.GREEN + "SimpleSetHome" + ChatColor.AQUA + "]" + msg)
    }
}