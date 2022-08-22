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
        if (config != null)
        {
            plugin.reloadConfig();
        }

        config = plugin.getConfig();

        if (!config.contains("sethome-message"))
            plugin.getLogger().info("config.yml にエラーが起こっています。");
        else if (!config.isString("sethome-message"))
            plugin.getLogger().info("sethomeのメッセージがString形じゃありません。");

        if (!config.contains("teleport-message"))
            plugin.getLogger().info("config.yml にエラーが起こっています。");
        else if (!config.isString("teleport-message"))
            plugin.getLogger().info("teleportのメッセージがString形じゃありません。");

        if (!config.contains("no-home-message"))
            plugin.getLogger().info("config.yml にエラーが起こっています。");
        else if (!config.isString("no-home-message"))
            plugin.getLogger().info("nohomeのメッセージがString形じゃありません。");
    }
    public Boolean enable(String s)
    {
        return config.getBoolean(s);
    }
    public String message(String s)
    {
        return config.getString(s);
    }
}