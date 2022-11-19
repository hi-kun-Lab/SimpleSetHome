package xyz.hiziki.config

import org.bukkit.plugin.java.JavaPlugin
import xyz.hiziki.Main

class ConfigFile
{
    private val plugin : JavaPlugin = Main.plugin!!

    private val config = plugin.config

    val enableSetHomeSound = config.getBoolean("enable-set-home-sound")

    val enableSetHomeDelay = config.getBoolean("enable-set-home-delay")

    val setHomeDelay = config.getInt("set-home-delay(s)")

    val enableTeleportSound = config.getBoolean("enable-teleport-sound")

    val enableTeleportDelay = config.getBoolean("enable-teleport-delay")

    val teleportDelay = config.getInt("teleport-delay(s)")

    val language = config.getString("language(ja, en)")

    val maxHome = config.getInt("max-home")

    val moveCancel = config.getBoolean("move-cancel")


    //# --- configuration --- #
    //
    //# --- set to the home　--- #
    //enable-set-home-sound: true
    //enable-set-home-delay: false
    //set-home-delay(s): 5
    //
    //# --- teleport to the home ---#
    //enable-teleport-sound: true
    //enable-teleport-delay: true
    //teleport-delay(s): 5
    //
    //# --- generic setting --- #
    //language(ja, en): en
    //max-home: 3
    //move-cancel: true
}