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

    public static String getPrefix() {
        return "§x§0§0§0§3§f§bP§x§0§0§1§5§f§ba§x§0§0§2§7§f§cr§x§0§0§3§9§f§ct§x§0§0§4§b§f§ci§x§0§0§5§d§f§dc§x§0§0§6§f§f§dl§x§0§0§8§1§f§ee§x§0§0§9§3§f§es§x§0§0§a§5§f§eP§x§0§0§b§7§f§fr§x§0§0§c§9§f§fo";
    }


}
