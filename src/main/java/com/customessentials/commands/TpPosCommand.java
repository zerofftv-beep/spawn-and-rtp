package com.customessentials.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TpPosCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("§cТолько игроки.");
            return true;
        }

        if (!player.hasPermission("customessentials.tppos")) {
            player.sendMessage("§cУ тебя нет прав на эту команду.");
            return true;
        }

        if (args.length != 3) {
            player.sendMessage("§cИспользование: /tppos <x> <y> <z>");
            return true;
        }

        try {
            double x = Double.parseDouble(args[0]);
            double y = Double.parseDouble(args[1]);
            double z = Double.parseDouble(args[2]);

            Location loc = new Location(player.getWorld(), x, y, z);
            player.teleport(loc);
            player.sendMessage("§aТелепортировано на §e" + x + " " + y + " " + z);
        } catch (NumberFormatException e) {
            player.sendMessage("§cКоординаты должны быть числами!");
        }
        return true;
    }
}