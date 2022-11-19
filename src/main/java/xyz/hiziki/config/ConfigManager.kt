package xyz.hiziki.config

import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.plugin.java.JavaPlugin

class ConfigManager(private val plugin : JavaPlugin)
{
    private var config : FileConfiguration? = null

    init
    {
        load()
    }

    private fun load()
    {
        plugin.saveDefaultConfig() // 設定ファイルを保存

        if (config != null) //configファイルがあったら
        {
            plugin.reloadConfig()
        }

        config = plugin.config

        if (!config!!.contains("enable-set-home-sound") || !config!!.contains("enable-teleport-sound") ||
                !config!!.contains("enable-set-home-message") || !config!!.contains("enable-teleport-message") ||
                !config!!.contains("set-home-message") || !config!!.contains("teleport-message") ||
                !config!!.contains("enable-set-home-delay") || !config!!.contains("enable-teleport-delay") ||
                !config!!.contains("set-home-delay(s)") || !config!!.contains("teleport-delay(s)") ||
                !config!!.contains("max-home") || !config!!.contains("move-cancel"))
        {
            plugin.logger.info("Error in config.yml.")
        }
        if (!config!!.isBoolean("enable-set-home-sound"))
        {
            plugin.logger.info("enable-set-home-sound is not in Boolean format.")
        }
        if (!config!!.isBoolean("enable-set-home-message"))
        {
            plugin.logger.info("enable-set-home-message is not in Boolean format.")
        }
        if (!config!!.isString("set-home-message"))
        {
            plugin.logger.info("set-home-message is not in String format.")
        }
        if (!config!!.isBoolean("enable-set-home-delay"))
        {
            plugin.logger.info("enable-set-home-delay is not in Boolean format.")
        }
        if (!config!!.isInt("set-home-delay(s)"))
        {
            plugin.logger.info("set-home-delay(s) is not in Int format.")
        }
        if (!config!!.isBoolean("enable-teleport-sound"))
        {
            plugin.logger.info("enable-teleport-sound is not in Boolean format.")
        }
        if (!config!!.isBoolean("enable-teleport-message"))
        {
            plugin.logger.info("enable-teleport-message is not in Boolean format.")
        }
        if (!config!!.isString("teleport-message"))
        {
            plugin.logger.info("teleport-message is not in String format.")
        }
        if (!config!!.isBoolean("enable-teleport-delay"))
        {
            plugin.logger.info("enable-teleport-delay is not in Boolean format.")
        }
        if (!config!!.isInt("teleport-delay(s)"))
        {
            plugin.logger.info("teleport-delay(s) is not in Int format.")
        }
        if (!config!!.isInt("max-home"))
        {
            plugin.logger.info("max-home is not in Int format.")
        }
        if (!config!!.isBoolean("move-cancel"))
        {
            plugin.logger.info("move-cancel is not in Boolean format.")
        }
    }
}