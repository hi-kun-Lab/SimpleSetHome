package xyz.hiziki;

import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.hiziki.command.CommandManager;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class Main extends JavaPlugin
{

    private static JavaPlugin plugin;

    private static Config config;

    private static File homesFile;

    private static YamlConfiguration homes;

    @Override
    public void onEnable()
    {
        //プラグインが起動する時

        super.onEnable();

        plugin = this;

        config = new Config(this);

        homesFile = new File(getDataFolder(), "Homes.yml");

        homes = YamlConfiguration.loadConfiguration(homesFile);

        new CommandManager();

        getLogger().info("プラグインは正常に起動しました。");
    }

    @Override
    public void onDisable()
    {
        //プラグインが停止する時

        super.onDisable(); //俺もよくわからんけど書いてる(書かないといけない)らしい

        saveFile(); //ファイルを保存する

        getLogger().info("プラグインは正常に停止しました。");
    }

    private void saveFile() //yml保存メソッド
    {
        try
        {
            homes.save(homesFile); //ymlファイルを保存する。
        }
        catch(IOException e) //
        {
            e.printStackTrace(); //
        }
    }



    public static JavaPlugin getPlugin()
    {
        return plugin;
    }

    public static Config getConfigFile()
    {
        return config;
    }

    public static File getHomesFile()
    {
        return homesFile;
    }

    public static YamlConfiguration getHomes()
    {
        return homes;
    }
}