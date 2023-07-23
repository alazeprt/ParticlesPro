package com.alazeprt.util;

public interface CommandListener {
    void OneBlockHandle(Location location, String[] args);
    void OnePlayerHandle(Location location, String[] args, String player);
    void TwoBlockHandle(Location location, String[] args);
    void TwoPlayerHandle(Location location, String[] args, String player);
}
