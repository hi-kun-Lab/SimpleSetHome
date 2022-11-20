package xyz.hiziki.simplesethome.config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

/*
*
* ConfigManager
* 作成者：hi_kun_JPN
* 読みにくいです
*
* */

public class ConfigManager
{
    public ConfigManager(JavaPlugin plugin)
    {
        FileConfiguration config = plugin.getConfig();

        plugin.saveDefaultConfig();

        plugin.reloadConfig();

        if (!config.contains("enable-set-home-sound") || !config.contains("enable-teleport-sound") ||
            !config.contains("enable-set-home-delay") || !config.contains("enable-teleport-delay") ||
            !config.contains("set-home-delay(s)") || !config.contains("teleport-delay(s)") ||
            !config.contains("max-home") || !config.contains("move-cancel"))
        {
            plugin.getLogger().info("Error in config.yml.");
        }
        if (!config.isBoolean("enable-set-home-sound"))
        {
            plugin.getLogger().info("enable-set-home-sound is not in Boolean format.");
        }
        if (!config.isBoolean("enable-set-home-delay"))
        {
            plugin.getLogger().info("enable-set-home-delay is not in Boolean format.");
        }
        if (!config.isInt("set-home-delay(s)"))
        {
            plugin.getLogger().info("set-home-delay(s) is not in Int format.");
        }
        if (!config.isBoolean("enable-teleport-sound"))
        {
            plugin.getLogger().info("enable-teleport-sound is not in Boolean format.");
        }
        if (!config.isBoolean("enable-teleport-delay"))
        {
            plugin.getLogger().info("enable-teleport-delay is not in Boolean format.");
        }
        if (!config.isInt("teleport-delay(s)"))
        {
            plugin.getLogger().info("teleport-delay(s) is not in Int format.");
        }
        if (!config.isInt("max-home"))
        {
            plugin.getLogger().info("max-home is not in Int format.");
        }
        if (!config.isBoolean("move-cancel"))
        {
            plugin.getLogger().info("move-cancel is not in Boolean format.");
        }
    }
}
