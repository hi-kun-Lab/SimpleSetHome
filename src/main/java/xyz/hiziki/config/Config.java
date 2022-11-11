package xyz.hiziki.config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Config
{
    private final JavaPlugin plugin;

    private FileConfiguration config;

    public Config(JavaPlugin pl)
    {
        plugin = pl;
        load();
    }

    public void load()
    {
        plugin.saveDefaultConfig(); // 設定ファイルを保存

        if (config != null) //configファイルがあったら
        {
            plugin.reloadConfig();
        }

        config = plugin.getConfig();

        if (!config.contains("enable-set-home-sound") || !config.contains("enable-teleport-sound") ||
                !config.contains("enable-set-home-message") || !config.contains("enable-teleport-message") ||
                !config.contains("set-home-message") || !config.contains("teleport-message") ||
                !config.contains("max-home"))
        {
            plugin.getLogger().info("config.yml にエラーが起こっています。");
        }

        if (!config.isBoolean("enable-set-home-sound"))
        {
            plugin.getLogger().info("enable-set-home-soundがBoolean形じゃありません。");
        }

        if (!config.isBoolean("enable-set-home-message"))
        {
            plugin.getLogger().info("enable-set-home-messageがBoolean形じゃありません。");
        }

        if (!config.isString("set-home-message"))
        {
            plugin.getLogger().info("set-home-messageがString形じゃありません。");
        }

        if (!config.isBoolean("enable-teleport-sound"))
        {
            plugin.getLogger().info("enable-teleport-soundがBoolean形じゃありません。");
        }

        if (!config.isBoolean("enable-teleport-message"))
        {
            plugin.getLogger().info("enable-teleport-messageがBoolean形じゃありません。");
        }

        if (!config.isString("teleport-message"))
        {
            plugin.getLogger().info("teleport-messageがString形じゃありません。");
        }

        if (!config.isInt("max-home"))
        {
            plugin.getLogger().info("max-homeがInt形じゃありません。");
        }
    }
}
//# --- 設定ファイル --- #
//
//# --- ホームを設定した時　--- #
//enable-set-home-sound: true
//enable-set-home-message: true
//set-home-message: 'ホームを設定しました。'
//
//# --- ホームにTPした時 ---#
//enable-teleport-sound: true
//enable-teleport-message: true
//teleport-message: 'ホームにTPしました。'
//
//# --- 最大ホーム数 --- #
//max-home: 3