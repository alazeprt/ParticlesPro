package com.alazeprt;

import com.alazeprt.commands.One_Dimensional;
import com.alazeprt.commands.Three_Dimensional;
import com.alazeprt.commands.Two_Dimensional;
import com.alazeprt.util.CommandListener;
import com.alazeprt.util.Location;

public class CommandEvent implements CommandListener {
    @Override
    public void OneBlockHandle(Location location, String[] args) {
        BasicEvent event = new BasicEvent();
        One_Dimensional dimensional = new One_Dimensional(event);
        dimensional.handle(location, args);
    }

    @Override
    public void OnePlayerHandle(Location location, String[] args, String player) {
        BasicEvent event = new BasicEvent();
        One_Dimensional dimensional = new One_Dimensional(event);
        dimensional.handle(location, args, player);
    }

    @Override
    public void TwoBlockHandle(Location location, String[] args) {
        BasicEvent event = new BasicEvent();
        Two_Dimensional dimensional = new Two_Dimensional(event);
        dimensional.handle(location, args);
    }

    @Override
    public void TwoPlayerHandle(Location location, String[] args, String player) {
        BasicEvent event = new BasicEvent();
        Two_Dimensional dimensional = new Two_Dimensional(event);
        dimensional.handle(location, args, player);
    }

    @Override
    public void ThreeBlockHandle(Location location, String[] args) {
        BasicEvent event = new BasicEvent();
        Three_Dimensional dimensional = new Three_Dimensional(event);
        dimensional.handle(location, args);
    }

    @Override
    public void ThreePlayerHandle(Location location, String[] args, String player) {
        BasicEvent event = new BasicEvent();
        Three_Dimensional dimensional = new Three_Dimensional(event);
        dimensional.handle(location, args, player);
    }
}
