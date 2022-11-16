package xyz.hiziki.command.sethome

import org.bukkit.ChatColor
import org.bukkit.Sound
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitRunnable
import xyz.hiziki.Main
import xyz.hiziki.config.ConfigFile
import xyz.hiziki.util.Prefix
import xyz.hiziki.util.SaveFile

class SetHomeCommandExecutor : CommandExecutor
{
    private val plugin : JavaPlugin? = Main.plugin

    private val homes : YamlConfiguration? = Main.homes

    private val config = ConfigFile()

    override fun onCommand(sender : CommandSender, command : Command, label : String, args : Array<String>) : Boolean
    {
        if (sender !is Player) //プレイヤーかどうかを確認 - プレイヤーじゃなかったら
        {
            sender.sendMessage("コマンドを実行出来るのはプレイヤーのみです。") //エラーを送信
        }
        else  //プレイヤーだったら
        {
            if (args.isEmpty()) //args が0だったら = サブコマンドが設定されていなかったら
            {
                Prefix(sender, ChatColor.RED.toString() + "サブコマンドが設定されていません。") //エラーをプレイヤーに送信
            }
            else  //サブコマンドが設定されていたら
            {
                val homeNum = args[0].toInt()

                if (homeNum > config.maxHome || homeNum == 0) //サブコマンドが設定されている数を超えている or 0だったら
                {
                    Prefix(sender, ChatColor.RED.toString() + "サブコマンドは 1~" + config.maxHome
                            + " までしかありません。") //エラーをプレイヤーに送信
                }
                else  //サブコマンドが設定されている数以内だったら
                {
                    if (config.enableSetHomeDelay)
                    {
                        setHomeCountDown(sender, homeNum)
                    }
                    else
                    {
                        setHome(sender, homeNum)
                    }
                }
            }
        }
        return true //return false だったら実行されずにチャットとして送信されることになる。
    }

    private fun setHomeCountDown(p : Player, num : Int)
    {
        var count = config.setHomeDelay

        val x = p.location.x
        val y = p.location.y
        val z = p.location.z

        object : BukkitRunnable()
        {
            override fun run()
            {
                if (config.moveCancel)
                {
                    if (x == p.location.x && y == p.location.y && z == p.location.z)
                    {
                        if (count <= 0)
                        {
                            setHome(p, num)
                            cancel()
                            return
                        }
                        count--
                        p.playSound(p.location, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.5f, 0.5f) //再生
                    }
                    else
                    {
                        Prefix(p, ChatColor.RED.toString() + "移動したためホームの設定がキャンセルされました。") //プレイヤーに送信
                        cancel() //スケジューラーから抜ける
                    }
                }
                else
                {
                    if (count <= 0)
                    {
                        setHome(p, num)
                        cancel()
                        return
                    }
                    count--
                    p.playSound(p.location, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.5f, 0.5f) //再生
                }
            }
        }.runTaskTimer(plugin!!, 0, 20)
    }

    private fun setHome(p : Player, num : Int) //ホーム設定メソッド
    {
        homes!!["Homes." + p.uniqueId + "." + num + ".Location"] = p.location //ホームを設定

        if (config.enableSetHomeMessage) //設定ファイルでメッセージがtrueになっていたら
        {
            if (config.setHomeMessage != null) //メッセージがあるかどうかを確認して
            {
                Prefix(p, ChatColor.AQUA.toString() + config.setHomeMessage) //プレイヤーに送信する
            }
        }
        if (config.enableSetHomeSound) //効果音を再生
        {
            p.playSound(p.location, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f) //再生
        }

        SaveFile() //設定したファイルを保存

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