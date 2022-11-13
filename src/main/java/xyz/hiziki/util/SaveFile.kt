package xyz.hiziki.util

import org.bukkit.configuration.file.YamlConfiguration
import xyz.hiziki.Main
import java.io.File
import java.io.IOException

class SaveFile
{
    init  //yml保存
    {
        val homesFile : File = Main.homesFile!!

        val homes : YamlConfiguration = Main.homes!!

        try
        {
            homes.save(homesFile)
        }
        catch (e : IOException)
        {
            e.printStackTrace()
        }
    }
}