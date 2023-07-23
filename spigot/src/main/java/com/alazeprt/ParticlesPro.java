package com.alazeprt;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class ParticlesPro extends JavaPlugin {
    @Override
    public void onEnable() {
        Objects.requireNonNull(getCommand("particlex")).setExecutor(new ParticlesProCommand());
        getLogger().info("§eParticlesPro v1.1 §a启动!");
    }

    @Override
    public void onDisable() {
        getLogger().info("§eParticlesPro v1.1 §c已卸载!");
    }


}
