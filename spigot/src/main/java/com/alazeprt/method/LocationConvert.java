package com.alazeprt.method;

import com.alazeprt.util.Location;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class LocationConvert {
    public static org.bukkit.Location toBukkitLocation(Location location) {
        return new org.bukkit.Location(Bukkit.getWorld(location.getWorld()), location.getX(), location.getY(), location.getZ());
    }

    public static Location toThisLocation(org.bukkit.Location location) {
        return new Location(location.getWorld().getName(), location.getX(), location.getY(), location.getZ());
    }

    public static Location toThisLocation(Player player) {
        return new Location(player.getWorld().getName(), player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ());
    }

    public static List<org.bukkit.Location> toBukkitLocation(List<Location> locationList) {
        List<org.bukkit.Location> list = new ArrayList<>();
        for(Location location : locationList) {
            list.add(toBukkitLocation(location));
        }
        return list;
    }
}
