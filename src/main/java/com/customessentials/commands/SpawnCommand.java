package com.customessentials.commands;

import com.customessentials.CustomEssentials;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCommand implements CommandExecutor {

    private final CustomEssentials plugin;

    public SpawnCommand(CustomEssentials plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("§cТолько игроки могут использовать эту команду.");
            return true;
        }

        double x = plugin.getConfig().getDouble("spawn.x", 0);
        double y = plugin.getConfig().getDouble("spawn.y", 64);
        double z = plugin.getConfig().getDouble("spawn.z", 0);
        String world = plugin.getConfig().getString("spawn.world", "world");

        Location spawn = new Location(
                org.bukkit.Bukkit.getWorld(world),
                x, y, z
        );

        player.teleport(spawn);
        player.sendMessage("§aТы телепортирован на спавн.");
        return true;
    }
}