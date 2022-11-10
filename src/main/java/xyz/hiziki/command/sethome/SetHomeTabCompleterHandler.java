package xyz.hiziki.command.sethome;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import xyz.hiziki.config.ConfigFile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SetHomeTabCompleterHandler implements TabCompleter
{
    private final ConfigFile config = new ConfigFile();

    @SuppressWarnings("NullableProblems")
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args)
    {
        List<String> list = new ArrayList<>();

        if (command.getName().equalsIgnoreCase("setHome"))
        {
            for (int i = 1; config.getMaxHome() >= i; i++)
            {
                list.add(String.valueOf(i));
            }
            Collections.sort(list);
        }
        return list;
    }
}
