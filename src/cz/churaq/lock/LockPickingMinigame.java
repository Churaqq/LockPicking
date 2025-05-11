package cz.churaq.lock;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class LockPickingMinigame {

    private final Main plugin;

    public LockPickingMinigame(Main plugin) {
        this.plugin = plugin;
    }

    public void startMinigame(Player player, int tier) {
        if (tier == 1) {
            player.sendMessage("Začínáš snadnou minihrou na odemknutí zámku!");
            initiateGame(player, 10, 1); // Snadná minihra
        } else if (tier == 2) {
            player.sendMessage("Začínáš středně těžkou minihrou na odemknutí zámku!");
            initiateGame(player, 8, 2); // Střední minihra
        } else if (tier == 3) {
            player.sendMessage("Začínáš těžkou minihrou na odemknutí zámku!");
            initiateGame(player, 5, 3); // Těžká minihra
        }
    }

    private void initiateGame(Player player, int maxtime, int speed) {
        int correctClicks = 0;
        int[] wheelPosition = {0, 0, 0}; // Výchozí pozice kol
        int[] correctPosition = {2, 2, 2}; // Cílové pozice (správné)
        int timeLeft = maxtime;

        // Spuštění otáčení kol
        for (int i = 0; i < 3; i++) {
            final int index = i; // Uložení i do finální proměnné index
            new BukkitRunnable() {
                @Override
                public void run() {
                    wheelPosition[index] = (wheelPosition[index] + 1) % 3; // Otáčení kola
                }
            }.runTaskTimer(plugin, 0, speed);
        }

        // Odpočítávání času
        new BukkitRunnable() {
            @Override
            public void run() {
                if (correctClicks == 3) {
                    player.sendMessage("Úspěšně otevřeno!"); // Výhra
                } else {
                    player.sendMessage("Neúspěšně, zámek se nezdařil."); // Prohra
                }
            }
        }.runTaskLater(plugin, maxtime * 20); // Po uplynutí času
    }
}
