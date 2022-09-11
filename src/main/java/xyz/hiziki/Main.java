package xyz.hiziki;

import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class Main extends JavaPlugin
{
    private final File homesFile = new File(getDataFolder(), "Homes.yml");
    private final YamlConfiguration homes = YamlConfiguration.loadConfiguration(homesFile);
    private Config config;

    @Override
    public void onEnable()
    {
        //プラグインが起動する時

        super.onEnable();

        config = new Config(this);

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

    @SuppressWarnings("NullableProblems")
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) //コマンドが実行された時
    {
        if (command.getName().equals("sethome")) //実行されたコマンドが「/sethome」だったら
        {
            if (!(sender instanceof Player)) //プレイヤーかどうかを確認 - プレイヤーじゃなかったら
            {
                getLogger().info("コマンドを実行出来るのはプレイヤーのみです。"); //エラーをコマンド実行者に送信
            }
            else //プレイヤーだったら
            {
                Player p = (Player) sender; //sender(CommandSender)をPlayer型にキャストし、pという変数名で宣言

                if (args.length == 0) //args が0だったら = サブコマンドが設定されていなかったら
                {
                    p.sendMessage(ChatColor.RED + "サブコマンドが設定されていません。"); //エラーをプレイヤーに送信
                }
                else //サブコマンドが設定されていたら
                {
                    if (!(isNum(args[0]))) //サブコマンドが数字じゃなかったら
                    {
                        p.sendMessage(ChatColor.RED + "サブコマンドが数値ではありません。"); //エラーをプレイヤーに送信
                    }
                    else //サブコマンドが数字だったら
                    {
                        if (Integer.parseInt(args[0]) > config.maxHome() || Integer.parseInt(args[0]) == 0) //サブコマンドが設定されている数を超えている or 0だったら
                        {
                            p.sendMessage(ChatColor.RED + "サブコマンドは 1~" + config.maxHome() + " までしかありません。");
                        }
                        else //サブコマンドが設定されている数以内だったら
                        {
                            for (int i = 1; i <= config.maxHome(); i++) //forで回して
                            {
                                if (i == Integer.parseInt(args[0])) //ifで確認
                                {
                                    setHome(p, i); //sethomeメソッドでhomeを設定し
                                    if (config.enable("enable-sethome-message")) //設定ファイルでメッセージがtrueになっていたら
                                    {
                                        if (config.message("sethome-message") != null) //メッセージがあるかどうかを確認して
                                        {
                                            p.sendMessage(ChatColor.AQUA + config.message("sethome-message")); //プレイヤーに送信する
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return true; //return true はコマンドが実行されたとして処理するってこと。　false だったら実行されずにチャットとして送信されることになる。
        }
        if (command.getName().equals("home")) //実行されたコマンドが「/home」だったら
        {
            if (!(sender instanceof Player)) //プレイヤーかどうかを確認 - プレイヤーじゃなかったら
            {
                getLogger().info("コマンドを実行出来るのはプレイヤーのみです。"); //エラーをコマンド実行者に送信
            }
            else //プレイヤーだったら
            {
                Player p = (Player) sender; //sender(CommandSender)をPlayer型にキャストし、pという変数名で宣言

                if (args.length == 0) //args が0だったら = サブコマンドが設定されていなかったら
                {
                    p.sendMessage(ChatColor.RED + "サブコマンドが設定されていません。"); //エラーをプレイヤーに送信
                }
                else //サブコマンドが設定されていたら
                {
                    if (!(isNum(args[0]))) //サブコマンドが数字じゃなかったら
                    {
                        p.sendMessage(ChatColor.RED + "サブコマンドが数値ではありません。"); //エラーをプレイヤーに送信
                    }
                    else //サブコマンドが数字だったら
                    {
                        if (Integer.parseInt(args[0]) > config.maxHome() || Integer.parseInt(args[0]) == 0) //サブコマンドが設定されている数を超えている or 0だったら
                        {
                            p.sendMessage(ChatColor.RED + "サブコマンドは 1~" + config.maxHome() + " までしかありません。"); //エラーをプレイヤーに送信
                        }
                        else //サブコマンドが設定されている数以内だったら
                        {
                            for (int i = 1; i <= config.maxHome(); i++) //forで回して
                            {
                                if (i == Integer.parseInt(args[0])) //ifで確認
                                {
                                    if (homes.getString("Homes." + p.getUniqueId() + "." + i) == null) //ホームが設定されていなかったら
                                    {
                                        p.sendMessage(ChatColor.RED + "ホーム " + i + " は設定されていません。"); //エラーをプレイヤーに送信
                                    }
                                    else //ホームが設定されていたら
                                    {
                                        teleportHome(p, i);
                                        if (config.enable("enable-teleport-message")) //設定ファイルでメッセージがtrueになっていたら
                                        {
                                            if (config.message("teleport-message") != null) //メッセージがあるかどうかを確認して
                                            {
                                                p.sendMessage(ChatColor.AQUA + config.message("teleport-message")); //プレイヤーに送信する
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return true; //return true はコマンドが実行されたとして処理するってこと。　false だったら実行されずにチャットとして送信されることになる。
        }
        return false;
    }

    private Boolean isNum(String str) //数字確認メソッド
    {
        try
        {
            Integer.parseInt(str); //
        }
        catch (NumberFormatException e) //
        {
            return false; //falseとして返す→ifでつかったらそのifは実行されない
        }
        return true; //trueとして返す→ifで使ったらそのifが実行される
    }

    private void setHome(Player p, int num) //ホーム設定メソッド
    {
        homes.set("Homes." + p.getUniqueId() + "." + num + ".World", p.getWorld().getName());
        homes.set("Homes." + p.getUniqueId() + "." + num + ".X", p.getLocation().getX());
        homes.set("Homes." + p.getUniqueId() + "." + num + ".Y", p.getLocation().getY());
        homes.set("Homes." + p.getUniqueId() + "." + num + ".Z", p.getLocation().getZ());
        homes.set("Homes." + p.getUniqueId() + "." + num + ".Yaw", p.getLocation().getYaw());
        homes.set("Homes." + p.getUniqueId() + "." + num + ".Pitch", p.getLocation().getPitch());
        saveFile(); //ファイルを保存

        //こんな感じで保存される
        //Homes:
        //  7aa912df-eeed-49d6-814e-5c8994d527f3:
        //    '1':
        //      World: world
        //      X: 1.0
        //      Y: 50.0
        //      Z: 0.0
        //      Yaw: 0.0
        //      Pitch: 0.0
    }

    private void teleportHome(Player p, int num)
    {
        World world = Bukkit.getWorld(Objects.requireNonNull(homes.getString("Homes." + p.getUniqueId() + "." + num + ".World")));
        double x = homes.getDouble("Homes." + p.getUniqueId() + "." + num + ".X");
        double y = homes.getDouble("Homes." + p.getUniqueId() + "." + num + ".Y");
        double z = homes.getDouble("Homes." + p.getUniqueId() + "." + num + ".Z");
        float yaw = homes.getLong("Homes." + p.getUniqueId() + "." + num + ".Yaw");
        float pitch = homes.getLong("Homes." + p.getUniqueId() + "." + num + ".Pitch");
        p.teleport(new Location(world, x, y, z, yaw, pitch)); //ホームにテレポート
        p.playSound(new Location(world, x, y, z), Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);
    }
}