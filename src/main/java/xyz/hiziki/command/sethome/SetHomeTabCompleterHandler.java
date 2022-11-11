package xyz.hiziki.command.sethome;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import xyz.hiziki.config.ConfigFile;

import java.util.ArrayList;
import java.util.List;

public class SetHomeTabCompleterHandler implements TabCompleter
{
    private final ConfigFile config = new ConfigFile();

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args)
    {
        List<String> list = new ArrayList<>(); //リストを作成

        for (int i = 1; config.getMAX_HOME() >= i; i++) //ホームの最大値までforで回し
        {
            list.add(String.valueOf(i)); //リストに追加
        }
        return list; //listを返す
    }
}
