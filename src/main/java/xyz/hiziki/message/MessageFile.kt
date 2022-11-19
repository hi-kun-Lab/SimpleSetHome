package xyz.hiziki.message

import org.bukkit.ChatColor
import xyz.hiziki.config.ConfigFile

class MessageFile
{
    private val config = ConfigFile()

    private val lang = config.language.equals("ja") //日本語ならtrueが返されます

    val emptySubCommand : String =
            if (lang) ChatColor.RED.toString() + "サブコマンドは 1~" + config.maxHome + " までしかありません。"
        else ChatColor.RED.toString() + "There are only 1~" + config.maxHome + "subcommands."

    val overSubCommandRange : String =
        if (lang) ChatColor.RED.toString() + "サブコマンドが設定されていません。"
        else ChatColor.RED.toString() + "Subcommand not set."

    val cancelTeleport : String =
        if (lang) ChatColor.RED.toString() + "移動したためテレポートがキャンセルされました。"
        else ChatColor.RED.toString() + "The teleport was canceled because of the move."

    val teleport : String =
        if (lang) ChatColor.AQUA.toString() + "ホームにテレポートしました。"
        else ChatColor.AQUA.toString() + "Teleported to the home."

    val cancelSetHome : String =
        if (lang) ChatColor.RED.toString() + "移動したためホームの設定がキャンセルされました。"
        else ChatColor.RED.toString() + "The home was canceled because of the move."

    val setHome : String =
        if (lang) ChatColor.AQUA.toString() + "ホームを設定しました。"
        else ChatColor.AQUA.toString() + "Home is set up"

    fun notSetHome(homeNum : String) : String {
        return if (lang) ChatColor.RED.toString() + "ホーム " + homeNum + " は設定されていません。"
        else ChatColor.RED.toString() + "Home" + homeNum + "is not set." }
}