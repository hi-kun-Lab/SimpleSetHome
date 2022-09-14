package xyz.hiziki.command.home;

import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.hiziki.Config;
import xyz.hiziki.Main;

import java.util.Objects;

public class HomeCommandExecutor implements CommandExecutor
{
    private final JavaPlugin plugin = Main.getPlugin();

    private final YamlConfiguration homes = Main.getHomes();

    private final Config config = Main.getConfigFile();

    @SuppressWarnings("NullableProblems")
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if (!(sender instanceof Player)) //プレイヤーかどうかを確認 - プレイヤーじゃなかったら
        {
           plugin.getLogger().info("コマンドを実行出来るのはプレイヤーのみです。"); //エラーをコマンド実行者に送信
        }
        else //プレイヤーだったら
        {
            Player p = (Player) sender; //sender(CommandSender)をPlayer型にキャストし、pという変数名で宣言

            if (args.length == 0) //args が0だったら = サブコマンドが設定されていなかったら
            {
                p.sendMessage(ChatColor.RED + "サブコマンドが設定されていません。"); //エラーをプレイヤーに送信
            }
            else //サブコマンドが設定されていたら
            {
                if (Integer.parseInt(args[0]) > config.maxHome() || Integer.parseInt(args[0]) == 0) //サブコマンドが設定されている数を超えている or 0だったら
                {
                    p.sendMessage(ChatColor.RED + "サブコマンドは 1~" + config.maxHome() + " までしかありません。"); //エラーをプレイヤーに送信
                }
                else //サブコマンドが設定されている数以内だったら
                {
                    for (int i = 1; i <= config.maxHome(); i++) //forで回して
                    {
                        if (i == Integer.parseInt(args[0])) //ifで確認
                        {
                            if (homes.getString("Homes." + p.getUniqueId() + "." + i) == null) //ホームが設定されていなかったら
                            {
                                p.sendMessage(ChatColor.RED + "ホーム " + i + " は設定されていません。"); //エラーをプレイヤーに送信
                            }
                            else //ホームが設定されていたら
                            {
                                teleportHome(p, i);

                                if (config.enable("enable-teleport-message")) //設定ファイルでメッセージがtrueになっていたら
                                {
                                    if (config.message("teleport-message") != null) //メッセージがあるかどうかを確認して
                                    {
                                        p.sendMessage(ChatColor.AQUA + config.message("teleport-message")); //プレイヤーに送信する
                                    }
                                }
                                if (config.enable("enable-teleport-sound"))
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
        World world = Bukkit.getWorld(Objects.requireNonNull(homes.getString("Homes." + p.getUniqueId() + "." + num + ".World")));
        double x = homes.getDouble("Homes." + p.getUniqueId() + "." + num + ".X");
        double y = homes.getDouble("Homes." + p.getUniqueId() + "." + num + ".Y");
        double z = homes.getDouble("Homes." + p.getUniqueId() + "." + num + ".Z");
        float yaw = homes.getLong("Homes." + p.getUniqueId() + "." + num + ".Yaw");
        float pitch = homes.getLong("Homes." + p.getUniqueId() + "." + num + ".Pitch");
        p.teleport(new Location(world, x, y, z, yaw, pitch)); // ホームにテレポート
    }
}
