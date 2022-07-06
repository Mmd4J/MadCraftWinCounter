package me.madcraft.madcraftwincounter;

import lombok.Getter;
import me.madcraft.madcraftwincounter.database.Database;
import me.madcraft.madcraftwincounter.listener.PlayerWinListener;
import me.madcraft.madcraftwincounter.util.Config;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;


public final class MadCraftWinCounter extends JavaPlugin {
    @Getter
    private static MadCraftWinCounter Instance;

    @Override
    public void onEnable() {
    Instance = this;
        Bukkit.getPluginManager().registerEvents(new PlayerWinListener(),this);
        Config.getConfig("database.yml");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        try {
            Database.getDatabase().getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
