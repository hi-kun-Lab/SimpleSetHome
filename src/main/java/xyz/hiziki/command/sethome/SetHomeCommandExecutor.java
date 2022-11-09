package xyz.hiziki.command.sethome;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.hiziki.Main;
import xyz.hiziki.util.Prefix;

import java.io.File;
import java.io.IOException;

public class SetHomeCommandExecutor implements CommandExecutor
{
    private final JavaPlugin plugin = Main.getPlugin();

    private final YamlConfiguration homes = Main.getHomes();

    private final File homesFile = Main.getHomesFile();

    private final FileConfiguration config = plugin.getConfig();

    @SuppressWarnings("NullableProblems")
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if (!(sender instanceof Player p)) //プレイヤーかどうかを確認 - プレイヤーじゃなかったら
        {
            plugin.getLogger().info("コマンドを実行出来るのはプレイヤーのみです。"); //エラーをコマンド実行者に送信
        }
        else //プレイヤーだったら
        {
            if (args.length == 0) //args が0だったら = サブコマンドが設定されていなかったら
            {
                new Prefix(p, ChatColor.RED + "サブコマンドが設定されていません。"); //エラーをプレイヤーに送信
            }
            else //サブコマンドが設定されていたら
            {
                if (Integer.parseInt(args[0]) > config.getInt("max-home") || Integer.parseInt(args[0]) == 0) //サブコマンドが設定されている数を超えている or 0だったら
                {
                    new Prefix(p, ChatColor.RED + "サブコマンドは 1~" + config.getInt("max-home") + " までしかありません。");
                }
                else //サブコマンドが設定されている数以内だったら
                {
                    for (int i = 1; i <= config.getInt("max-home"); i++) //forで回して
                    {
                        if (i == Integer.parseInt(args[0])) //ifで確認
                        {
                            setHome(p, i); //sethomeメソッドでhomeを設定し

                            if (config.getBoolean("enable-sethome-message")) //設定ファイルでメッセージがtrueになっていたら
                            {
                                if (config.getString("sethome-message") != null) //メッセージがあるかどうかを確認して
                                {
                                    new Prefix(p, ChatColor.AQUA + config.getString("sethome-message")); //プレイヤーに送信する
                                }
                            }
                        }
                    }
                }
            }
        }
        return true; //return false だったら実行されずにチャットとして送信されることになる。
    }

    private void setHome(Player p, int num) //ホーム設定メソッド
    {
        homes.set("Homes." + p.getUniqueId() + "." + num + ".World", p.getLocation());

        saveFile(); //ファイルを保存

        //こんな感じで保存される
        //Homes:
        //  7aa912df-eeed-49d6-814e-5c8994d527f3:
        //    '1':
        //    location:
    }

    private void saveFile() //yml保存メソッド
    {
        try
        {
            homes.save(homesFile); //ymlファイルを保存する。
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}