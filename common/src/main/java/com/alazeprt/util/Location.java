package com.alazeprt.util;

import java.util.ArrayList;
import java.util.List;

public class Location {
    private final double x;
    private final double y;
    private final double z;
    private final String world;
    public Location(String world, double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.world = world;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public String getWorld() {
        return world;
    }
}
