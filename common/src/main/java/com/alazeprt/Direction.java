package com.alazeprt;

public enum Direction {
    SOUTH,
    NORTH,
    EAST,
    WEST;

    public static boolean isExist(String direction) {
        return SOUTH.toString().equals(direction) || NORTH.toString().equals(direction) || EAST.toString().equals(direction) || WEST.toString().equals(direction);
    }
}
