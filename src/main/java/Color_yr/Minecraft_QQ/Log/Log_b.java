package Color_yr.Minecraft_QQ.Log;

import Color_yr.Minecraft_QQ.API.ILog;
import Color_yr.Minecraft_QQ.BungeeCord;

public class Log_b implements ILog {
    public void Log_System(String message) {
        BungeeCord.log_b.info(message);
    }
}
