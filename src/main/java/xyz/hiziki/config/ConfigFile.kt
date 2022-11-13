package xyz.hiziki.config

import org.bukkit.plugin.java.JavaPlugin
import xyz.hiziki.Main

class ConfigFile
{
    private val plugin : JavaPlugin? = Main.Companion.getPlugin()
    private val config = plugin!!.config
    val eNABLE_SET_HOME_SOUND : Boolean
        get() = config.getBoolean("enable-set-home-sound")
    val eNABLE_SET_HOME_MESSAGE : Boolean
        get() = config.getBoolean("enable-set-home-message")
    val sET_HOME_MESSAGE : String?
        get() = config.getString("set-home-message")
    val eNABLE_SET_HOME_DELAY : Boolean
        get() = config.getBoolean("enable-set-home-delay")
    val sET_HOME_DELAY : Int
        get() = config.getInt("set-home-delay(s)")
    val eNABLE_TELEPORT_SOUND : Boolean
        get() = config.getBoolean("enable-teleport-sound")
    val eNABLE_TELEPORT_MESSAGE : Boolean
        get() = config.getBoolean("enable-teleport-message")
    val tELEPORT_MESSAGE : String?
        get() = config.getString("teleport-message")
    val eNABLE_TELEPORT_DELAY : Boolean
        get() = config.getBoolean("enable-teleport-delay")
    val tELEPORT_DELAY : Int
        get() = config.getInt("teleport-delay(s)")
    val mAX_HOME : Int
        get() = config.getInt("max-home")

    //# --- 設定ファイル --- #
    val mOVE_CANCEL : Boolean
        get() = config.getBoolean("move-cancel")
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