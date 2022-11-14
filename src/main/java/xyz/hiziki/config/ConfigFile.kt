package xyz.hiziki.config

import org.bukkit.plugin.java.JavaPlugin
import xyz.hiziki.Main

class ConfigFile
{
    private val plugin : JavaPlugin = Main.plugin!!

    private val config = plugin.config

    val enableSetHomeSound = config.getBoolean("enable-set-home-sound")

    val enableSetHomeMessage = config.getBoolean("enable-set-home-message")

    val setHomeMessage = config.getString("set-home-message")

    val enableSetHomeDelay = config.getBoolean("enable-set-home-delay")

    val setHomeDelay = config.getInt("set-home-delay(s)")

    val enableTeleportSound = config.getBoolean("enable-teleport-sound")

    val enableTeleportMessage = config.getBoolean("enable-teleport-message")

    val teleportMessage = config.getString("teleport-message")

    val enableTeleportDelay = config.getBoolean("enable-teleport-delay")

    val teleportDelay = config.getInt("teleport-delay(s)")

    val maxHome = config.getInt("max-home")

    val moveCancel = config.getBoolean("move-cancel")


    //# --- 設定ファイル --- #
    //
    //# --- ホームを設定した時　--- #
    //enable-set-home-sound: true
    //enable-set-home-message: true
    //set-home-message: 'ホームを設定しました。'
    //enable-set-home-delay: false
    //set-home-delay(s): 5
    //
    //# --- ホームにTPした時 ---#
    //enable-teleport-sound: true
    //enable-teleport-message: true
    //teleport-message: 'ホームにTPしました。'
    //enable-teleport-delay: true
    //teleport-delay(s): 5
    //
    //# --- 最大ホーム数 --- #
    //max-home: 3
    //move-cancel: true
}