package com.customessentials.commands;

import com.customessentials.CustomEssentials;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class RtpCommand implements CommandExecutor {

    private final CustomEssentials plugin;
    private final Map<UUID, Long> cooldowns = new HashMap<>();
    private static final long COOLDOWN = 30_000; // 30 секунд

    public RtpCommand(CustomEssentials plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("§cТолько игроки.");
            return true;
        }

        UUID uuid = player.getUniqueId();
        long currentTime = System.currentTimeMillis();

        if (cooldowns.containsKey(uuid)) {
            long lastUse = cooldowns.get(uuid);
            if (currentTime - lastUse < COOLDOWN) {
                long remaining = (COOLDOWN - (currentTime - lastUse)) / 1000;
                player.sendMessage("§cПодожди " + remaining + " секунд перед следующим использованием /rtp.");
                return true;
            }
        }

        // Рандомные координаты в области 10000x10000 (от -5000 до 5000)
        double x = (Math.random() * 10000) - 5000;
        double z = (Math.random() * 10000) - 5000;
        double y = player.getWorld().getHighestBlockYAt((int) x, (int) z) + 1;

        Location loc = new Location(player.getWorld(), x, y, z);
        player.teleport(loc);

        cooldowns.put(uuid, currentTime);
        player.sendMessage("§aТы телепортирован в случайную точку!");
        return true;
    }
}