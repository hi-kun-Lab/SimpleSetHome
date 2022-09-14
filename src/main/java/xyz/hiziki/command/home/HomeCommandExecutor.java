package xyz.hiziki.command.home;

import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.hiziki.Main;

import java.util.Objects;

public class HomeCommandExecutor implements CommandExecutor
{

    @SuppressWarnings("NullableProblems")
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if (!(sender instanceof Player)) //プレイヤーかどうかを確認 - プレイヤーじゃなかったら
        {
           Main.getPlugin().getLogger().info("コマンドを実行出来るのはプレイヤーのみです。"); //エラーをコマンド実行者に送信
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
                if (!(isNum(args[0]))) //サブコマンドが数字じゃなかったら
                {
                    p.sendMessage(ChatColor.RED + "サブコマンドが数値ではありません。"); //エラーをプレイヤーに送信
                }
                else //サブコマンドが数字だったら
                {
                    if (Integer.parseInt(args[0]) > Main.getConfigFile().maxHome() || Integer.parseInt(args[0]) == 0) //サブコマンドが設定されている数を超えている or 0だったら
                    {
                        p.sendMessage(ChatColor.RED + "サブコマンドは 1~" + Main.getConfigFile().maxHome() + " までしかありません。"); //エラーをプレイヤーに送信
                    }
                    else //サブコマンドが設定されている数以内だったら
                    {
                        for (int i = 1; i <= Main.getConfigFile().maxHome(); i++) //forで回して
                        {
                            if (i == Integer.parseInt(args[0])) //ifで確認
                            {
                                if (Main.getHomes().getString("Homes." + p.getUniqueId() + "." + i) == null) //ホームが設定されていなかったら
                                {
                                    p.sendMessage(ChatColor.RED + "ホーム " + i + " は設定されていません。"); //エラーをプレイヤーに送信
                                }
                                else //ホームが設定されていたら
                                {
                                    teleportHome(p, i);

                                    if (Main.getConfigFile().enable("enable-teleport-message")) //設定ファイルでメッセージがtrueになっていたら
                                    {
                                        if (Main.getConfigFile().message("teleport-message") != null) //メッセージがあるかどうかを確認して
                                        {
                                            p.sendMessage(ChatColor.AQUA + Main.getConfigFile().message("teleport-message")); //プレイヤーに送信する
                                        }
                                    }

                                    if (Main.getConfigFile().enable("enable-teleport-sound"))
                                    {
                                        p.playSound(p.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return true; //return true はコマンドが実行されたとして処理するってこと。　false だったら実行されずにチャットとして送信されることになる。
    }

    private Boolean isNum(String str) //数字確認メソッド
    {
        try
        {
            Integer.parseInt(str); //
        }
        catch (NumberFormatException e) //
        {
            return false; //falseとして返す→ifでつかったらそのifは実行されない
        }
        return true; //trueとして返す→ifで使ったらそのifが実行される
    }

    private void teleportHome(Player p, int num)
    {
        World world = Bukkit.getWorld(Objects.requireNonNull(Main.getHomes().getString("Homes." + p.getUniqueId() + "." + num + ".World")));
        double x = Main.getHomes().getDouble("Homes." + p.getUniqueId() + "." + num + ".X");
        double y = Main.getHomes().getDouble("Homes." + p.getUniqueId() + "." + num + ".Y");
        double z = Main.getHomes().getDouble("Homes." + p.getUniqueId() + "." + num + ".Z");
        float yaw = Main.getHomes().getLong("Homes." + p.getUniqueId() + "." + num + ".Yaw");
        float pitch = Main.getHomes().getLong("Homes." + p.getUniqueId() + "." + num + ".Pitch");
        p.teleport(new Location(world, x, y, z, yaw, pitch)); //ホームにテレポート
    }
}
