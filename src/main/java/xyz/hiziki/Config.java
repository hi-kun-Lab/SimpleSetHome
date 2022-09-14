package xyz.hiziki;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

public class Config
{
    private final Plugin plugin;
    private FileConfiguration config = null;

    public Config(Plugin pl)
    {
        this.plugin = pl;
        load();
    }

    public void load()
    {
        plugin.saveDefaultConfig(); // 設定ファイルを保存
        if (config != null) //configファイルがなかったら
        {
            plugin.reloadConfig();
        }

        config = plugin.getConfig();

        if (!config.contains("sethome-message")) //config.yml ファイルの sethome-message
        {
            plugin.getLogger().info("config.yml にエラーが起こっています。");
        }
        else if (!config.isString("sethome-message"))
        {
            plugin.getLogger().info("sethomeのメッセージがString形じゃありません。");
        }

        if (!config.contains("sethome-message")) //config.yml ファイルの sethome-message
        {
            plugin.getLogger().info("config.yml にエラーが起こっています。");
        }
        else if (!config.isString("teleport-message"))
        {
            plugin.getLogger().info("teleportのメッセージがString形じゃありません。");
        }

        if (!config.contains("max-home")) //config.yml ファイルの max-home
        {
            plugin.getLogger().info("config.yml にエラーが起こっています。");
        }
        else if (!config.isInt("max-home"))
        {
            plugin.getLogger().info("max-homeの最大数が数値じゃありません。");
        }
    }
    public Boolean enable(String enable)
    {
        return config.getBoolean(enable);
    }
    public String message(String msg)
    {
        return config.getString(msg);
    }
    public int maxHome()
    {
        return config.getInt("max-home");
    }
}