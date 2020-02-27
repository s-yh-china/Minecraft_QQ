package Color_yr.Minecraft_QQ;

import Color_yr.Minecraft_QQ.Command.CommandBukkit;
import Color_yr.Minecraft_QQ.Config.Load;
import Color_yr.Minecraft_QQ.Listener.BukkitEvent;
import Color_yr.Minecraft_QQ.Side.IBukkit;
import Color_yr.Minecraft_QQ.Socket.SocketControl;
import Color_yr.Minecraft_QQ.Utils.logs;
import Color_yr.Minecraft_QQ.bStats.MetricsBukkit;
import com.google.gson.Gson;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.logging.Logger;

public class Minecraft_QQBukkit extends JavaPlugin {

    public static Plugin plugin;
    public static Logger log_b;

    public static void Load()
    {
        try {
            new Load(plugin.getDataFolder(), plugin.getResource("config.json"));
        } catch (Throwable e) {
            log_b.warning("§d[Minecraft_QQ]§c配置文件读取发生错误");
            e.printStackTrace();
        }
    }

    public static void Save() {
        try {
            String data = new Gson().toJson(Minecraft_QQ.Config);
            if (Minecraft_QQ.FileName.exists()) {
                Writer out = new FileWriter(Minecraft_QQ.FileName);
                out.write(data);
                out.close();
            }
        } catch (Exception e) {
            log_b.warning("§d[Minecraft_QQ]§c配置文件保存错误");
            e.printStackTrace();
        }
    }

    @Override
    public void onEnable() {
        plugin = this;
        log_b = getLogger();

        Minecraft_QQ.MinecraftQQ = new IBukkit();

        log_b.info("§d[Minecraft_QQ]§e正在启动，感谢使用，本插件交流群：571239090");

        try {
            new logs(plugin.getDataFolder());
        } catch (IOException e) {
            log_b.warning("§d[Minecraft_QQ]§c日志文件错误");
            e.printStackTrace();
        }
        Load();

        Bukkit.getPluginManager().registerEvents(new BukkitEvent(), this);
        Bukkit.getPluginCommand("qq").setExecutor(new CommandBukkit());
        Bukkit.getPluginCommand("qq").setTabCompleter(new CommandBukkit());

        new MetricsBukkit(this, 6608);

        log_b.info("§d[Minecraft_QQ]§e已启动-" + Minecraft_QQ.Version);
        log_b.info("§d[Minecraft_QQ]§eDebug模式" + Minecraft_QQ.Config.getSystem().isDebug());

        SocketControl socket = new SocketControl();
        socket.Start();
    }

    @Override
    public void onDisable() {
        new SocketControl().Close();
        log_b.info("§d[Minecraft_QQ]§e已停止，感谢使用");
    }
}
