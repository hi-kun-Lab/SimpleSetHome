package xyz.hiziki.command.sethome;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import xyz.hiziki.Main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SetHomeTabCompleterHandler implements TabCompleter
{
    @SuppressWarnings("NullableProblems")
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args)
    {
        List<String> list = new ArrayList<>();

        if (sender instanceof Player)
        {
            if (command.getName().equalsIgnoreCase("sethome"))
            {
                for (int i = 1; Main.getConfigFile().maxHome() >= i; i++)
                {
                    list.add(String.valueOf(i));
                }
                Collections.sort(list);
            }
        }
        return list;
    }
}
