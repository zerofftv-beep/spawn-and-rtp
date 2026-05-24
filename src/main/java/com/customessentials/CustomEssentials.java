package com.customessentials;

import com.customessentials.commands.*;
import org.bukkit.plugin.java.JavaPlugin;

public class CustomEssentials extends JavaPlugin {

    private static CustomEssentials instance;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();

        // Регистрация команд
        getCommand("spawn").setExecutor(new SpawnCommand(this));
        getCommand("setspawn").setExecutor(new SetSpawnCommand(this));
        getCommand("rtp").setExecutor(new RtpCommand(this));
        getCommand("tppos").setExecutor(new TpPosCommand());
        getCommand("explosion").setExecutor(new ExplosionCommand());

        getLogger().info("CustomEssentials успешно загружен!");
    }

    public static CustomEssentials getInstance() {
        return instance;
    }
}