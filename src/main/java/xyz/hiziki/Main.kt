package xyz.hiziki

import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.plugin.java.JavaPlugin
import xyz.hiziki.command.CommandManager
import xyz.hiziki.config.ConfigManager
import java.io.File

class Main : JavaPlugin()
{
    override fun onEnable()
    {
        //プラグインが起動する時

        super.onEnable()

        plugin = this

        homesFile = File(dataFolder, "Homes.yml")
        homes = YamlConfiguration.loadConfiguration(homesFile!!)

        ConfigManager(this)
        CommandManager(this)

        logger.info("plugin has been successfully startup.")
    }

    override fun onDisable()
    {
        //プラグインが停止する時

        super.onDisable()

        logger.info("plugin has been successfully shutdown.")
    }

    companion object
    {
        var plugin : JavaPlugin? = null

        var homesFile : File? = null

        var homes : YamlConfiguration? = null
    }
}