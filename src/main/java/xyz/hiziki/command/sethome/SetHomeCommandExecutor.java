package xyz.hiziki.command.sethome;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.hiziki.Main;
import xyz.hiziki.config.ConfigFile;
import xyz.hiziki.util.Prefix;
import xyz.hiziki.util.SaveFile;

public class SetHomeCommandExecutor implements CommandExecutor
{
    private final JavaPlugin plugin = Main.getPlugin();

    private final YamlConfiguration homes = Main.getHomes();

    private final ConfigFile config = new ConfigFile();

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
                if (Integer.parseInt(args[0]) > config.getMaxHome() || Integer.parseInt(args[0]) == 0) //サブコマンドが設定されている数を超えている or 0だったら
                {
                    new Prefix(p, ChatColor.RED + "サブコマンドは 1~" + config.getMaxHome() + " までしかありません。");
                }
                else //サブコマンドが設定されている数以内だったら
                {
                    for (int i = 1; i <= config.getMaxHome(); i++) //forで回して
                    {
                        if (i == Integer.parseInt(args[0])) //ifで確認
                        {
                            setHome(p, i); //setHomeメソッドでhomeを設定し

                            if (config.getEnableSetHomeMessage()) //設定ファイルでメッセージがtrueになっていたら
                            {
                                if (config.getSetHomeMessage() != null) //メッセージがあるかどうかを確認して
                                {
                                    new Prefix(p, ChatColor.AQUA + config.getSetHomeMessage()); //プレイヤーに送信する
                                }
                            }
                            if (config.getEnableSetHomeSound())
                            {
                                p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
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
        homes.set("Homes." + p.getUniqueId() + "." + num + ".Location", p.getLocation());

        new SaveFile(); //ファイルを保存

        //こんな感じで保存される
        //Homes:
        //  7aa912df-eeed-49d6-814e-5c8994d527f3:
        //    '1':
        //    location:
        //      ==: org.bukkit.Location
        //      world: world
        //      x: 214.30000001192093
        //      y: 65.0
        //      z: -95.69999998807907
        //      pitch: 16.300346
        //      yaw: -186.54565
    }
}