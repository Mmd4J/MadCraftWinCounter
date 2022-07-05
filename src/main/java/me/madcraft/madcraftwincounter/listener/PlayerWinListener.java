package me.madcraft.madcraftwincounter.listener;

import com.andrei1058.bedwars.api.events.gameplay.GameEndEvent;
import com.andrei1058.bedwars.api.events.gameplay.GameStateChangeEvent;
import me.madcraft.madcraftwincounter.database.Database;
import me.madcraft.madcraftwincounter.util.MadPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.List;
import java.util.UUID;

public class PlayerWinListener implements Listener {
    @EventHandler
    public void onPlayerWinEvent(GameEndEvent e){
        List<UUID> winners = e.getWinners();
        for (UUID uuid : winners){
            MadPlayer winner = MadPlayer.getPlayer(uuid);
            winner.addWin();
            Database.getDatabase().update(winner);

         }
    }
}
