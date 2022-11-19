package xyz.hiziki.simplesethome.command.sethome;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.hiziki.simplesethome.config.ConfigFile;

import java.util.ArrayList;
import java.util.List;

/*
 *
 * SetHomeCommandTabCompleter
 * 作成者：hi_kun_JPN
 * コマンドのtab保管を行うクラス
 *
 * */

public class SetHomeCommandTabCompleter implements TabCompleter
{
    public final ConfigFile config = new ConfigFile();

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command,
                                                @NotNull String alias, @NotNull String[] args)
    {
        List<String> list = new ArrayList<>();

        for (int i = 1; config.get_MAX_HOME >= i; i++)
        {
            list.add(String.valueOf(i));
        }
        return list;
    }
}
