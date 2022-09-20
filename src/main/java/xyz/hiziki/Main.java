package xyz.hiziki;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.hiziki.command.CommandManager;

import java.io.File;
import java.io.IOException;

public class Main extends JavaPlugin
{
    public static JavaPlugin plugin;

    public static Config config;

    public static File homesFile;

    public static YamlConfiguration homes;

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
}