package me.madcraft.madcraftwincounter;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;


public final class MadCraftWinCounter extends JavaPlugin {
    @Getter
    private static MadCraftWinCounter Instance;

    @Override
    public void onEnable() {
    Instance = this;

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
