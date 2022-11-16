package xyz.hiziki.command.home

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

class HomeCommandExecutor : CommandExecutor
{
    private val plugin : JavaPlugin? = Main.plugin

    private val homes : YamlConfiguration? = Main.homes

    private val config = ConfigFile()

    private var count = 0

    private var x = 0.0

    private var y = 0.0

    private var z = 0.0

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
                    Prefix(sender, ChatColor.RED.toString() + "サブコマンドは 1~" +
                            config.maxHome + " までしかありません。") //エラーをプレイヤーに送信
                }
                else  //サブコマンドが設定されている数以内だったら
                {
                    if (homes!!.getString("Homes." + sender.uniqueId + "." + homeNum) == null)
                    {
                        Prefix(sender, ChatColor.RED.toString() + "ホーム " + homeNum +
                                " は設定されていません。") //エラーをプレイヤーに送信
                    }
                    else  //ホームが設定されていたら
                    {
                        if (config.enableTeleportDelay)
                        {
                            teleportCountDown(sender, homeNum) //teleportCountDownメソッドに転送
                        }
                        else
                        {
                            teleportHome(sender, homeNum) //teleportHomeメソッドに転送
                        }
                    }
                }
            }
        }
        return true //return false だったら実行されずにチャットとして送信されることになる。
    }

    private fun teleportCountDown(p : Player, num : Int)
    {
        count = config.teleportDelay

        x = p.location.x
        y = p.location.y
        z = p.location.z

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
                            teleportHome(p, num)
                            cancel()
                            return
                        }
                        count--
                        p.playSound(p.location, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.5f, 0.5f)
                    }
                    else
                    {
                        Prefix(p, ChatColor.RED.toString() + "移動したためテレポートがキャンセルされました。") //プレイヤーに送信
                        cancel() //スケジューラーから抜ける
                    }
                }
                else
                {
                    if (count <= 0)
                    {
                        teleportHome(p, num)
                        cancel()
                        return
                    }
                    count--
                    p.playSound(p.location, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.5f, 0.5f)
                }
            }
        }.runTaskTimer(plugin!!, 0, 20)
    }

    private fun teleportHome(p : Player, num : Int)
    {
        p.teleport(homes!!.getLocation("Homes." + p.uniqueId + "." + num + ".Location")!!) // ホームにテレポート

        if (config.enableTeleportMessage) //設定ファイルでメッセージがtrueになっていたら
        {
            if (config.teleportMessage != null) //メッセージがあるかどうかを確認して
            {
                Prefix(p, ChatColor.AQUA.toString() + config.teleportMessage) //プレイヤーに送信する
            }
        }
        if (config.enableTeleportSound) //効果音を送信
        {
            p.playSound(p.location, Sound.ENTITY_ENDERMAN_TELEPORT, 1f, 1f) //再生
        }
    }
}