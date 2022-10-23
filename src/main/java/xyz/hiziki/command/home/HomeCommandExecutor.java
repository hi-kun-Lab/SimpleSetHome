package xyz.hiziki.command.home;

import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.hiziki.Main;
import xyz.hiziki.util.Prefix;

import java.util.Objects;

public class HomeCommandExecutor implements CommandExecutor
{
    private final JavaPlugin plugin = Main.plugin;
    
    private final YamlConfiguration homes = Main.homes;

    private final FileConfiguration config = plugin.getConfig();

    @SuppressWarnings("NullableProblems")
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if (!(sender instanceof Player)) //プレイヤーかどうかを確認 - プレイヤーじゃなかったら
        {
            if (sender instanceof ConsoleCommandSender)
            {
                plugin.getLogger().info("コマンドを実行出来るのはプレイヤーのみです。"); //エラーをコンソールに送信
            }
            else
            {
                sender.sendMessage("コマンドを実行出来るのはプレイヤーのみです。");
            }
        }
        else //プレイヤーだったら
        {
            Player p = (Player) sender; //sender(CommandSender)をPlayer型にキャストし、pという変数名で宣言

            if (args.length == 0) //args が0だったら = サブコマンドが設定されていなかったら
            {
                new Prefix(p, ChatColor.RED + "サブコマンドが設定されていません。"); //エラーをプレイヤーに送信
            }
            else //サブコマンドが設定されていたら
            {
                if (Integer.parseInt(args[0]) > config.getInt("max-home")
                        || Integer.parseInt(args[0]) == 0) //サブコマンドが設定されている数を超えている or 0だったら
                {
                    new Prefix(p, ChatColor.RED + "サブコマンドは 1~" + plugin.getConfig().getInt("max-home")
                            + " までしかありません。"); //エラーをプレイヤーに送信
                }
                else //サブコマンドが設定されている数以内だったら
                {
                    for (int i = 1; i <= config.getInt("max-home"); i++) //forで回して
                    {
                        if (i == Integer.parseInt(args[0])) //ifで確認
                        {
                            if (homes.getString("Homes." + p.getUniqueId() + "." + i) == null) //ホームが設定されていない場合
                            {
                                new Prefix(p, ChatColor.RED + "ホーム " + i
                                        + " は設定されていません。"); //エラーをプレイヤーに送信
                            }
                            else //ホームが設定されていたら
                            {
                                teleportHome(p, i);

                                if (config.getBoolean("enable-teleport-message")) //設定ファイルでメッセージがtrueになっていたら
                                {
                                    if (config.getString("teleport-message") != null) //メッセージがあるかどうかを確認して
                                    {
                                        new Prefix(p, ChatColor.AQUA
                                                + plugin.getConfig().getString("teleport-message")); //プレイヤーに送信する
                                    }
                                }
                                if (config.getBoolean("enable-teleport-sound"))
                                {
                                    p.playSound(p.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);
                                }
                            }
                        }
                    }
                }
            }
        }
        return true; // return true はコマンドが実行されたとして処理するってこと。　false だったら実行されずにチャットとして送信されることになる。
    }

    private void teleportHome(Player p, int num)
    {
        p.teleport(Objects.requireNonNull(
                homes.getLocation("Homes." + p.getUniqueId() + "." + num + ".Location"))); // ホームにテレポート
    }
}
