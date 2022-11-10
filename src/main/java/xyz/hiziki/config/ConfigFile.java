package xyz.hiziki.config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.hiziki.Main;

public class ConfigFile
{
    private final JavaPlugin plugin = Main.getPlugin();

    private final FileConfiguration config = plugin.getConfig();

    public boolean getEnableSetHomeSound()
    {
        return config.getBoolean("enable-set-home-sound");
    }

    public boolean getEnableSetHomeMessage()
    {
        return config.getBoolean("enable-set-home-message");
    }

    public String getSetHomeMessage()
    {
        return config.getString("set-home-message");
    }

    public boolean getEnableTeleportSound()
    {
        return config.getBoolean("enable-teleport-sound");
    }

    public boolean getEnableTeleportMessage()
    {
        return config.getBoolean("enable-teleport-message");
    }

    public String getTeleportMessage()
    {
        return config.getString("teleport-message");
    }

    public int getMaxHome()
    {
        return config.getInt("max-home");
    }
}
