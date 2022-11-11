package xyz.hiziki.config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.hiziki.Main;

public class ConfigFile
{
    private final JavaPlugin plugin = Main.getPlugin();

    private final FileConfiguration config = plugin.getConfig();

    public boolean getENABLE_SET_HOME_SOUND()
    {
        return config.getBoolean("enable-set-home-sound");
    }

    public boolean getENABLE_SET_HOME_MESSAGE()
    {
        return config.getBoolean("enable-set-home-message");
    }

    public String getSET_HOME_MESSAGE()
    {
        return config.getString("set-home-message");
    }

    public boolean getENABLE_TELEPORT_SOUND()
    {
        return config.getBoolean("enable-teleport-sound");
    }

    public boolean getENABLE_TELEPORT_MESSAGE()
    {
        return config.getBoolean("enable-teleport-message");
    }

    public String getTELEPORT_MESSAGE()
    {
        return config.getString("teleport-message");
    }

    public int getMAX_HOME()
    {
        return config.getInt("max-home");
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
}
