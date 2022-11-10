package xyz.hiziki;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.hiziki.command.CommandManager;
import xyz.hiziki.config.Config;
import xyz.hiziki.util.SaveFile;

import java.io.File;

public class Main extends JavaPlugin
{
    private static JavaPlugin plugin;

    private static File homesFile;

    private static YamlConfiguration homes;

    @Override
    public void onEnable()
    {
        //プラグインが起動する時

        super.onEnable();

        plugin = this;

        homesFile = new File(getDataFolder(), "Homes.yml");

        homes = YamlConfiguration.loadConfiguration(homesFile);

        new Config(this);

        new CommandManager(this);

        getLogger().info("プラグインは正常に起動しました。");
    }

    @Override
    public void onDisable()
    {
        //プラグインが停止する時

        super.onDisable(); //俺もよくわからんけど書いてる(書かないといけない)らしい

        new SaveFile(); //ファイルを保存する

        getLogger().info("プラグインは正常に停止しました。");
    }

    public static JavaPlugin getPlugin()
    {
        return plugin;
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