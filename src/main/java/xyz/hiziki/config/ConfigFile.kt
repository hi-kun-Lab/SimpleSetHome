package xyz.hiziki.config

import org.bukkit.plugin.java.JavaPlugin
import xyz.hiziki.Main

class ConfigFile
{
    private val plugin : JavaPlugin = Main.plugin!!

    private val config = plugin.config
    val enableSetHomeSound : Boolean
        get() = config.getBoolean("enable-set-home-sound")

    val enableSetHomeMessage : Boolean
        get() = config.getBoolean("enable-set-home-message")

    val setHomeMessage : String?
        get() = config.getString("set-home-message")

    val enableSetHomeDelay : Boolean
        get() = config.getBoolean("enable-set-home-delay")

    val setHomeDelay : Int
        get() = config.getInt("set-home-delay(s)")

    val enableTeleportSound : Boolean
        get() = config.getBoolean("enable-teleport-sound")

    val enableTeleportMessage : Boolean
        get() = config.getBoolean("enable-teleport-message")

    val teleportMessage : String?
        get() = config.getString("teleport-message")

    val enableTeleportDelay : Boolean
        get() = config.getBoolean("enable-teleport-delay")

    val teleportDelay : Int
        get() = config.getInt("teleport-delay(s)")

    val maxHome : Int
        get() = config.getInt("max-home")

    val moveCancel : Boolean
        get() = config.getBoolean("move-cancel")


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