package xyz.hiziki.simplesethome.config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.hiziki.simplesethome.Main;

/*
*
* ConfigFile
* 作成者：hi_kun_JPN
* 注意点
* 定数の命名はスネークケースで
*
* */

public class ConfigFile
{
    private final JavaPlugin plugin = Main.getPlugin();

    private final FileConfiguration config = plugin.getConfig();

    public final boolean get_ENABLE_SET_HOME_SOUND = config.getBoolean("enable-set-home-sound");

    public final boolean get_ENABLE_SET_HOME_DELAY = config.getBoolean("enable-set-home-delay");

    public final int get_SET_HOME_DELAY = config.getInt("set-home-delay(s)");

    public final boolean get_ENABLE_TELEPORT_SOUND = config.getBoolean("enable-teleport-sound");

    public final boolean get_ENABLE_TELEPORT_DELAY = config.getBoolean("enable-teleport-delay");

    public final int get_TELEPORT_DELAY = config.getInt("teleport-delay(s)");

    public final int get_MAX_HOME = config.getInt("max-home");

    public final boolean get_MOVE_CANCEL = config.getBoolean("move-cancel");
}
