package com.customessentials.commands;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.flags.Flags;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ExplosionCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("§cТолько игроки.");
            return true;
        }

        if (!player.hasPermission("customessentials.explosion")) {
            player.sendMessage("§cНет прав.");
            return true;
        }

        if (args.length != 1) {
            player.sendMessage("§cИспользование: /explosion <on|off>");
            return true;
        }

        // Получаем регион, в котором стоит игрок (первый регион)
        RegionManager regionManager = WorldGuard.getInstance()
                .getPlatform()
                .getRegionContainer()
                .get(BukkitAdapter.adapt(player.getWorld()));

        if (regionManager == null) {
            player.sendMessage("§cWorldGuard не загружен.");
            return true;
        }

        ProtectedRegion region = null;
        for (ProtectedRegion r : regionManager.getApplicableRegions(BukkitAdapter.asBlockVector(player.getLocation()))) {
            region = r;
            break;
        }

        if (region == null) {
            player.sendMessage("§cТы не находишься в WorldGuard регионе.");
            return true;
        }

        boolean denyBreak = args[0].equalsIgnoreCase("off");

        if (denyBreak) {
            region.setFlag(Flags.BLOCK_BREAK, com.sk89q.worldguard.protection.flags.StateFlag.State.DENY);
            player.sendMessage("§aЛомание блоков §cзапрещено §aв регионе §e" + region.getId());
        } else {
            region.setFlag(Flags.BLOCK_BREAK, null); // сброс
            player.sendMessage("§aЛомание блоков §2разрешено §aв регионе §e" + region.getId());
        }

        return true;
    }
}