package network.hiziki.simplesethome.util;

import org.bukkit.configuration.file.YamlConfiguration;
import network.hiziki.simplesethome.Main;

import java.io.File;
import java.io.IOException;

/*
*
* SaveFile
* 作成者：hi_kun_JPN
*
* */

public class SaveFile {
    public SaveFile() { //保存
        File homesFile = Main.getHomesFile();
        YamlConfiguration homes = Main.getHomes();

        try {
            homes.save(homesFile); //ファイルを保存
        }
        catch(IOException ex) { //例外が発生したら
            ex.printStackTrace(); //ログに表示
        }
    }
}
