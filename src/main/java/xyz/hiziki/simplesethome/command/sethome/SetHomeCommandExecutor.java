package xyz.hiziki.simplesethome.command.sethome;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;
import xyz.hiziki.simplesethome.Main;
import xyz.hiziki.simplesethome.config.ConfigFile;
import xyz.hiziki.simplesethome.util.Message;
import xyz.hiziki.simplesethome.util.Prefix;
import xyz.hiziki.simplesethome.util.SaveFile;

/*
 *
 * SetHomeCommandExecutor
 * 作成者：hi_kun_JPN
 * コマンドを処理するクラス
 *
 * */

public class SetHomeCommandExecutor implements CommandExecutor
{
    private final JavaPlugin plugin = Main.getPlugin(); //JavaPlugin

    private final YamlConfiguration homes = Main.getHomes(); //ホームファイル

    private final ConfigFile config = new ConfigFile(); //設定

    private final Message message = new Message(); //メッセージ

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label,
                             @NotNull String[] args)
    {
        if (!(sender instanceof Player p)) //プレイヤーじゃなかったら
        {
            sender.sendMessage("コマンドを実行できるこはプレイヤーのみです。"); //エラーを送信
        }
        else //プレイヤーだったら
        {
            if (args.length == 0) //サブコマンドが設定されていなかったら
            {
                new Prefix(p, message.get_EMPTY_SUB_COMMAND); //プレイヤーにメッセージを送信
            }
            else //サブコマンドが設定されていたら
            {
                var homeNum = Integer.parseInt(args[0]); //args[0]を数字に変換

                if (homeNum > config.get_MAX_HOME || homeNum == 0) //サブコマンドが設定されている数を超えている or 0だったら
                {
                    new Prefix(p, message.get_OVER_SUB_COMMAND_RANGE); //送信
                }
                else //サブコマンドが正常な場合
                {
                    if (config.get_ENABLE_SET_HOME_DELAY) //遅延がありだったら
                    {
                        setHomeCountDown(p, homeNum); //setHomeCountDownメソッドに転送
                    }
                    else
                    {
                        setHome(p, homeNum); //setHomeメソッドに転送
                    }
                }
            }
        }
        return true; //コマンドが実行されたとして処理
    }

    private int count = config.get_SET_HOME_DELAY; //カウント用変数

    private void setHomeCountDown(Player p, int num) //テレポート遅延用メソッド
    {
        var x = p.getLocation().getX(); //ロケーションを保存
        var y = p.getLocation().getY();
        var z = p.getLocation().getZ();

        new BukkitRunnable() //スケジューラー
        {
            @Override
            public void run() //この中が回される
            {
                if (config.get_MOVE_CANCEL) //移動したらキャンセルされる設定になっていたら
                {
                    if (x == p.getLocation().getX() && y == p.getLocation().getY() && z == p.getLocation().getZ()) //比較
                    {
                        if (count <= 0) //カウントが0になったら
                        {
                            setHome(p, num); //setHomeメソッドに転送
                            cancel(); //スケジューラーをキャンセルする
                            return; //メソッドから抜ける
                        }
                        count--; //カウントを1引く
                        p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.5f, 0.5f); //再生
                    }
                    else //プレイヤーがカウント中に動いたら
                    {
                        new Prefix(p, message.get_CANCEL_SET_HOME); //プレイヤーに送信
                        cancel(); //スケジューラーから抜ける
                    }
                }
                else //移動してもキャンセルされない設定になっていたら
                {
                    if (count <= 0) //カウントが0になったら
                    {
                        setHome(p, num); //setHomeメソッドに転送
                        cancel(); //スケジューラーをキャンセルする
                        return; //メソッドから抜ける
                    }
                    count--; //カウントを1引く
                    p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.5f, 0.5f); //再生
                }
            }
        }.runTaskTimer(plugin, 0, 20); //20Tick(20tick = 1秒) 1秒ごとに回す
    }

    private void setHome(Player p, int num) //ホーム設定メソッド
    {
        homes.set("Homes." + p.getUniqueId() + "." + num + ".Location", p.getLocation()); //ホームを設定

        new Prefix(p, ChatColor.AQUA + message.get_SET_HOME); //プレイヤーに送信する

        if (config.get_ENABLE_SET_HOME_SOUND) //効果音を再生
        {
            p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1); //再生
        }

        new SaveFile(); //設定したファイルを保存

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
