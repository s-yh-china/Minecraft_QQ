package Color_yr.Minecraft_QQ.Log;

import Color_yr.Minecraft_QQ.API.ILog;
import Color_yr.Minecraft_QQ.Bukkit;

public class Log_s implements ILog {
    public void Log_System(String message) {
        Bukkit.log_b.info(message);
    }
}
