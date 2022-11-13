package xyz.hiziki

import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.plugin.java.JavaPlugin
import xyz.hiziki.command.CommandManager
import xyz.hiziki.config.Config
import xyz.hiziki.util.SaveFile
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
        Config(this)
        CommandManager(this)
        logger.info("プラグインは正常に起動しました。")
    }

    override fun onDisable()
    {
        //プラグインが停止する時
        super.onDisable() //俺もよくわからんけど書いてる(書かないといけない)らしい
        SaveFile() //ファイルを保存する
        logger.info("プラグインは正常に停止しました。")
    }

    companion object
    {
        var plugin : JavaPlugin? = null
            private set
        var homesFile : File? = null
            private set
        var homes : YamlConfiguration? = null
            private set
    }
}