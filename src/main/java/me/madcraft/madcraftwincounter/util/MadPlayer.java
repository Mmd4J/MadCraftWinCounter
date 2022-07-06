package me.madcraft.madcraftwincounter.util;

import lombok.Data;
import me.madcraft.madcraftwincounter.database.Database;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Data
public class MadPlayer {
    private final UUID uuid;
    private int wins = 0;
    static Map<UUID,MadPlayer> madPlayerMap = new HashMap<>();
    public MadPlayer(UUID uuid){
        this.uuid = uuid;
        Database.getDatabase().insert(this);
        Database.getDatabase().getPlayer(this);

    }
    public static MadPlayer getPlayer(UUID uuid){
    if (!madPlayerMap.containsKey(uuid)) madPlayerMap.put(uuid,new MadPlayer(uuid));
    return madPlayerMap.get(uuid);
    }
    public void addWin(){
        setWins(wins ++);
    }
    public static MadPlayer getPlayer(Player player){
        return getPlayer(player.getUniqueId());
    }
}
