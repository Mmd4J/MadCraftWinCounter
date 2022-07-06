package me.madcraft.madcraftwincounter.util;

import lombok.Getter;
import me.madcraft.madcraftwincounter.MadCraftWinCounter;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Config extends YamlConfiguration
{
    private final MadCraftWinCounter plugin;
    @Getter
    private final File file;
    private static Map<String,Config> configMap = new HashMap<>();

    private Config(File parent, String name) {
        this.plugin = MadCraftWinCounter.getInstance();
        this.file = new File(parent, name);

        if (!file.exists()) {
            options().copyDefaults(true);
            plugin.saveResource(name, false);
        }
        load();
    }

    private Config(String name) {
        this(MadCraftWinCounter.getInstance().getDataFolder(), name);
    }

    public void load() {
        try {
            super.load(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Config getConfig(String name){
        if (!configMap.containsKey(name)) configMap.put(name,new Config(name));
        return configMap.get(name);
    }

    public void save() {
        try {
            super.save(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
