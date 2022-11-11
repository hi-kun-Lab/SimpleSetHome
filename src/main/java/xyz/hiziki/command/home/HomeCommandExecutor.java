package xyz.hiziki.command.home;

import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.hiziki.Main;
import xyz.hiziki.config.ConfigFile;
import xyz.hiziki.util.Prefix;

import java.util.Objects;

public class HomeCommandExecutor implements CommandExecutor
{
    private final JavaPlugin plugin = Main.getPlugin();
    
    private final YamlConfiguration homes = Main.getHomes();

    private final ConfigFile config = new ConfigFile();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if (!(sender instanceof Player p)) //プレイヤーかどうかを確認 - プレイヤーじゃなかったら
        {
            if (sender instanceof ConsoleCommandSender)
            {
                plugin.getLogger().info("コマンドを実行出来るのはプレイヤーのみです。"); //エラーをコンソールに送信
            }
            else
            {
                sender.sendMessage("コマンドを実行出来るのはプレイヤーのみです。");
            }
            return false;
        }
        else //プレイヤーだったら
        {
            if (args.length == 0) //args が0だったら = サブコマンドが設定されていなかったら
            {
                new Prefix(p, ChatColor.RED + "サブコマンドが設定されていません。"); //エラーをプレイヤーに送信
            }
            else //サブコマンドが設定されていたら
            {
                if (Integer.parseInt(args[0]) > config.getMaxHome()
                        || Integer.parseInt(args[0]) == 0) //サブコマンドが設定されている数を超えている or 0だったら
                {
                    new Prefix(p, ChatColor.RED + "サブコマンドは 1~" + plugin.getConfig().getInt("max-home")
                            + " までしかありません。"); //エラーをプレイヤーに送信
                }
                else //サブコマンドが設定されている数以内だったら
                {
                    if (homes.getString("Homes." + p.getUniqueId() + "." + Integer.parseInt(args[0])) == null)
                    {
                        new Prefix(p, ChatColor.RED + "ホーム " + Integer.parseInt(args[0])
                                + " は設定されていません。"); //ホームが設定されていない場合エラーをプレイヤーに送信
                    }
                    else //ホームが設定されていたら
                    {
                        teleportHome(p, Integer.parseInt(args[0]));

                        if (config.getEnableTeleportMessage()) //設定ファイルでメッセージがtrueになっていたら
                        {
                            if (config.getTeleportMessage() != null) //メッセージがあるかどうかを確認して
                            {
                                new Prefix(p, ChatColor.AQUA
                                        + config.getTeleportMessage()); //プレイヤーに送信する
                            }
                        }
                        if (config.getEnableTeleportSound()) //効果音を送信
                        {
                            p.playSound(p.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);
                        }
                    }
                }
            }
        }
        return true; //return false だったら実行されずにチャットとして送信されることになる。
    }

    private void teleportHome(Player p, int num)
    {
        p.teleport(Objects.requireNonNull(
                homes.getLocation("Homes." + p.getUniqueId() + "." + num + ".Location"))); // ホームにテレポート
    }
}
