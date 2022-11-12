package xyz.hiziki.command.home;

import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import xyz.hiziki.Main;
import xyz.hiziki.config.ConfigFile;
import xyz.hiziki.util.Prefix;

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
            sender.sendMessage("コマンドを実行出来るのはプレイヤーのみです。"); //エラーを送信
        }
        else //プレイヤーだったら
        {
            if (args.length == 0) //args が0だったら = サブコマンドが設定されていなかったら
            {
                new Prefix(p, ChatColor.RED + "サブコマンドが設定されていません。"); //エラーをプレイヤーに送信
            }
            else //サブコマンドが設定されていたら
            {
                int homeNum = Integer.parseInt(args[0]);

                //サブコマンドが設定されている数を超えている or 0だったら
                if (homeNum > config.getMAX_HOME() || homeNum == 0)
                {
                    new Prefix(p, ChatColor.RED + "サブコマンドは 1~" + config.getMAX_HOME()
                            + " までしかありません。"); //エラーをプレイヤーに送信
                }
                else //サブコマンドが設定されている数以内だったら
                {
                    if (homes.getString("Homes." + p.getUniqueId() + "." + homeNum) == null)
                    {
                        new Prefix(p, ChatColor.RED + "ホーム " + homeNum + " は設定されていません。"); //エラーを送信
                    }
                    else //ホームが設定されていたら
                    {
                        if (config.getENABLE_TELEPORT_DELAY())
                        {
                            teleportCountDown(p, homeNum);
                        }
                        else
                        {
                            teleportHome(p, homeNum); //teleportHomeメソッドに転送
                        }
                    }
                }
            }
        }
        return true; //return false だったら実行されずにチャットとして送信されることになる。
    }

    private void teleportCountDown(Player p, int num)
    {
        new BukkitRunnable()
        {
            @Override
            public void run()
            {
                teleportHome(p, num);
            }
        }.runTaskLater(plugin, 20L * config.getTELEPORT_DELAY());
    }

    private void teleportHome(Player p, int num)
    {
        p.teleport(homes.getLocation("Homes." + p.getUniqueId() + "." + num + ".Location")); // ホームにテレポート

        if (config.getENABLE_TELEPORT_MESSAGE()) //設定ファイルでメッセージがtrueになっていたら
        {
            if (config.getTELEPORT_MESSAGE() != null) //メッセージがあるかどうかを確認して
            {
                new Prefix(p, ChatColor.AQUA + config.getTELEPORT_MESSAGE()); //プレイヤーに送信する
            }
        }
        if (config.getENABLE_TELEPORT_SOUND()) //効果音を送信
        {
            p.playSound(p.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1); //再生
        }
    }
}
