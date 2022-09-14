package xyz.hiziki.util;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Prefix
{
    public Prefix(Player p, String msg)
    {
        p.sendMessage(ChatColor.AQUA + "[" + ChatColor.GREEN + "SimpleSetHome" + ChatColor.AQUA + "]" + msg);
    }
}
