package com.customessentials.commands;

import com.customessentials.CustomEssentials;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetSpawnCommand implements CommandExecutor {

    private final CustomEssentials plugin;

    public SetSpawnCommand(CustomEssentials plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("§cТолько игроки.");
            return true;
        }

        if (!player.hasPermission("customessentials.setspawn")) {
            player.sendMessage("§cУ тебя нет прав.");
            return true;
        }

        Location loc = player.getLocation();

        plugin.getConfig().set("spawn.x", loc.getX());
        plugin.getConfig().set("spawn.y", loc.getY());
        plugin.getConfig().set("spawn.z", loc.getZ());
        plugin.getConfig().set("spawn.world", loc.getWorld().getName());
        plugin.saveConfig();

        player.sendMessage("§aТочка спавна установлена на твою текущую позицию.");
        return true;
    }
}