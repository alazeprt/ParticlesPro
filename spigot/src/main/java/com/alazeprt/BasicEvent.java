package com.alazeprt;

import com.alazeprt.method.LocationConvert;
import com.alazeprt.util.Listener;
import com.alazeprt.util.Location;
import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class BasicEvent implements Listener {
    @Override
    public void sendMessage(Location location, String string) {}

    @Override
    public void sendPlayerMessage(String player, String string) {
        Collection<? extends Player> collection = Bukkit.getOnlinePlayers();
        for(Player player1 : collection) {
            player1.sendMessage(string);
        }
    }

    @Override
    public void summonParticle(List<Location> locationList) {
        Thread thread = new Thread(() -> {
            List<org.bukkit.Location> list = LocationConvert.toBukkitLocation(locationList);
            for(org.bukkit.Location location : list) {
                String version = Bukkit.getServer().getVersion();
                String[] versionArray = version.split("\\.");
                int secondVersion = Integer.parseInt(versionArray[1]);
                if(secondVersion >= 13) {
                    Objects.requireNonNull(location.getWorld()).spawnParticle(Particle.END_ROD, location, 2, 0, 0, 0, 0, null, true);
                } else {
                    Objects.requireNonNull(location.getWorld()).spawnParticle(Particle.END_ROD, location, 2, 0, 0, 0, 0);
                }
            }
        });
        thread.start();
    }
}
