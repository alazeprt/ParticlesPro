package com.alazeprt.util;

import java.util.List;

public interface Listener {
    void summonParticle(List<Location> locationList);
    void sendMessage(Location location, String string);
    void sendPlayerMessage(String player, String string);
}
