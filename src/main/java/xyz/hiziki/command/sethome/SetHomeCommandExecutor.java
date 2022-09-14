package xyz.hiziki.command.sethome;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.hiziki.Main;

import java.io.IOException;

public class SetHomeCommandExecutor implements CommandExecutor
{
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
                        p.sendMessage(ChatColor.RED + "サブコマンドは 1~" + Main.getConfigFile().maxHome() + " までしかありません。");
                    }
                    else //サブコマンドが設定されている数以内だったら
                    {
                        for (int i = 1; i <= Main.getConfigFile().maxHome(); i++) //forで回して
                        {
                            if (i == Integer.parseInt(args[0])) //ifで確認
                            {
                                setHome(p, i); //sethomeメソッドでhomeを設定し
                                if (Main.getConfigFile().enable("enable-sethome-message")) //設定ファイルでメッセージがtrueになっていたら
                                {
                                    if (Main.getConfigFile().message("sethome-message") != null) //メッセージがあるかどうかを確認して
                                    {
                                        p.sendMessage(ChatColor.AQUA + Main.getConfigFile().message("sethome-message")); //プレイヤーに送信する
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

    private void setHome(Player p, int num) //ホーム設定メソッド
    {
        Main.getHomes().set("Homes." + p.getUniqueId() + "." + num + ".World", p.getWorld().getName());
        Main.getHomes().set("Homes." + p.getUniqueId() + "." + num + ".X", p.getLocation().getX());
        Main.getHomes().set("Homes." + p.getUniqueId() + "." + num + ".Y", p.getLocation().getY());
        Main.getHomes().set("Homes." + p.getUniqueId() + "." + num + ".Z", p.getLocation().getZ());
        Main.getHomes().set("Homes." + p.getUniqueId() + "." + num + ".Yaw", p.getLocation().getYaw());
        Main.getHomes().set("Homes." + p.getUniqueId() + "." + num + ".Pitch", p.getLocation().getPitch());
        saveFile(); //ファイルを保存

        //こんな感じで保存される
        //Homes:
        //  7aa912df-eeed-49d6-814e-5c8994d527f3:
        //    '1':
        //      World: world
        //      X: 1.0
        //      Y: 50.0
        //      Z: 0.0
        //      Yaw: 0.0
        //      Pitch: 0.0
    }

    private void saveFile() //yml保存メソッド
    {
        try
        {
            Main.getHomes().save(Main.getHomesFile()); //ymlファイルを保存する。
        }
        catch(IOException e) //
        {
            e.printStackTrace(); //
        }
    }
}
