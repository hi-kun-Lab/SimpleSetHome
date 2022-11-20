package xyz.hiziki.simplesethome.util;

import org.bukkit.ChatColor;
import xyz.hiziki.simplesethome.config.ConfigFile;

/*
*
* Message
* 作成者：hi_kun_JPN
* 注意点
* 定数の命名はスネークケースで
* 絶対もっといい方法ある
*
* */

public class Message
{
    private final ConfigFile config = new ConfigFile();

    public String get_OVER_SUB_COMMAND_RANGE = config.get_LANGUAGE.equals("ja") ?
            ChatColor.RED + "サブコマンドは 1~" + config.get_MAX_HOME + " までしかありません。" :
            ChatColor.RED + "There are only 1~" + config.get_MAX_HOME + "subcommands.";

    public String get_EMPTY_SUB_COMMAND = config.get_LANGUAGE.equals("ja") ?
            ChatColor.RED + "サブコマンドが設定されていません。" :
            ChatColor.RED + "Subcommand not set." ;
    
    public String get_CANCEL_TELEPORT = config.get_LANGUAGE.equals("ja") ?
            ChatColor.RED + "移動したためテレポートがキャンセルされました。" :
            ChatColor.RED + "The teleport was canceled because of the move.";
    
    public String get_TELEPORT = config.get_LANGUAGE.equals("ja") ?
            ChatColor.AQUA + "ホームにテレポートしました。" :
            ChatColor.AQUA + "Teleported to the home.";

    public String get_CANCEL_SET_HOME = config.get_LANGUAGE.equals("ja") ?
            ChatColor.RED + "移動したためホームの設定がキャンセルされました。" :
            ChatColor.RED + "The home was canceled because of the move.";

    public String get_SET_HOME = config.get_LANGUAGE.equals("ja") ?
            ChatColor.AQUA + "ホームを設定しました。" :
            ChatColor.AQUA + "Home is set up";

    public String get_NOT_SET_HOME(int homeNum)
    {
        return config.get_LANGUAGE.equals("ja") ?
                ChatColor.RED + "ホーム " + homeNum + " は設定されていません。" :
                ChatColor.RED + "Home" + homeNum + "is not set.";
    }
}
