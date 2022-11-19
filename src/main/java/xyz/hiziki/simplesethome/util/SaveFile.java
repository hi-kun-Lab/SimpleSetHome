package xyz.hiziki.simplesethome.util;

import org.bukkit.configuration.file.YamlConfiguration;
import xyz.hiziki.simplesethome.Main;

import java.io.File;
import java.io.IOException;

/*
*
* SaveFile
* 作成者：hi_kun_JPN
*
* */

public class SaveFile
{
    public SaveFile() //yml保存
    {
        File homesFile = Main.getHomesFile();

        YamlConfiguration homes = Main.getHomes();

        try
        {
            homes.save(homesFile); //ファイルを保存
        }
        catch(IOException ex) //IOExceptionが起こったら
        {
            ex.printStackTrace(); //ログに表示
        }
    }
}
