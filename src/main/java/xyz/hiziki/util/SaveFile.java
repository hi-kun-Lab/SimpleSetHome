package xyz.hiziki.util;

import org.bukkit.configuration.file.YamlConfiguration;
import xyz.hiziki.Main;

import java.io.File;
import java.io.IOException;

public class SaveFile
{
    public SaveFile() //yml保存
    {
        File homesFile = Main.getHomesFile();
        YamlConfiguration homes = Main.getHomes();

        try
        {
            homes.save(homesFile);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}
