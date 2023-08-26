package network.hiziki.simplesethome.command.home;

import network.hiziki.simplesethome.config.ConfigFile;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;


import java.util.ArrayList;
import java.util.List;

/*
 *
 * HomeCommandTabCompleter
 * 作成者：hi_kun_JPN
 * コマンドのtab保管を行うクラス
 *
 * */

public class HomeCommandTabCompleter implements TabCompleter {
    public final ConfigFile config = new ConfigFile();

    @Override
    public  List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> list = new ArrayList<>();

        for (int i = 1; config.get_MAX_HOME >= i; i++) {
            list.add(String.valueOf(i));
        }
        return list;
    }
}
