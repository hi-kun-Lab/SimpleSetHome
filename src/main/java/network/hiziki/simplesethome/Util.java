package network.hiziki.simplesethome;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class Util {
    public void prefix(Player p, String msg) {
        p.sendMessage(ChatColor.AQUA + "[" + ChatColor.GREEN + "SimpleSetHome" + ChatColor.AQUA + "]" + msg); //送信
    }
    public void saveFile() { //保存
        File homesFile = Main.getHomesFile();
        YamlConfiguration homes = Main.getHomes();

        try {
            homes.save(homesFile); //ファイルを保存
        } catch(IOException ex) { //例外が発生したら
            ex.printStackTrace(); //ログに表示
        }
    }
}
