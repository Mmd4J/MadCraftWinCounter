package me.madcraft.madcraftwincounter.database;

import me.madcraft.madcraftwincounter.util.Config;
import me.madcraft.madcraftwincounter.util.MadPlayer;

public interface Database {
    Config config = new Config("database.yml");
    void insert(MadPlayer player);
    void update(MadPlayer player);
    void getPlayer(MadPlayer player);
    static Database getDatabase(){
        Database database = null;
        switch (config.getString("database.type")){
            case "SQLite":
                database = SQLite.get();
                break;
            case "MySQL":
                database = MySQL.get();
                break;
        }
        return database;
    }
}
