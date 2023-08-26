package network.hiziki.simplesethome.util;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/*
*
* Prefix
* 作成者：hi_kun_JPN
*
* */

public class Prefix {
    public Prefix(Player p, String msg) {
        p.sendMessage(ChatColor.AQUA + "[" + ChatColor.GREEN + "SimpleSetHome" + ChatColor.AQUA + "]" + msg); //送信
    }
}
