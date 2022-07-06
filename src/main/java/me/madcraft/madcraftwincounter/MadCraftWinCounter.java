package me.madcraft.madcraftwincounter;

import lombok.Getter;
import me.madcraft.madcraftwincounter.listener.PlayerWinListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;


public final class MadCraftWinCounter extends JavaPlugin {
    @Getter
    private static MadCraftWinCounter Instance;

    @Override
    public void onEnable() {
    Instance = this;
        Bukkit.getPluginManager().registerEvents(new PlayerWinListener(),this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
