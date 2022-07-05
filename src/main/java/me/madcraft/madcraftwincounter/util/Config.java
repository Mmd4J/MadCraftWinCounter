package me.madcraft.madcraftwincounter.util;

import lombok.Getter;
import me.madcraft.madcraftwincounter.MadCraftWinCounter;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class Config extends YamlConfiguration
{
    private final MadCraftWinCounter plugin;
    @Getter
    private final File file;

    public Config(File parent, String name) {
        this.plugin = MadCraftWinCounter.getInstance();
        this.file = new File(parent, name);

        if (!file.exists()) {
            options().copyDefaults(true);
            plugin.saveResource(name, false);
        }
        load();
    }

    public Config(String name) {
        this(MadCraftWinCounter.getInstance().getDataFolder(), name);
    }

    public void load() {
        try {
            super.load(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void save() {
        try {
            super.save(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
