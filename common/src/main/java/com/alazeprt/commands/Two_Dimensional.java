package com.alazeprt.commands;

import com.alazeprt.util.*;

import java.util.ArrayList;
import java.util.List;

public class Two_Dimensional {
    private final Listener listener;

    public Two_Dimensional(Listener listener) {
        this.listener = listener;
    }

    public void handle(Location location, String[] args) {
        if(args.length == 2 && args[1].equals("help")) {
            listener.sendMessage(location, Prefix.get() + " §b二维粒子生成帮助");
            listener.sendMessage(location, "§b/particlex 2 circle <半径> <垂直角度>  §a- 在自己的中心生成一个圆形");
            listener.sendMessage(location, "§b/particlex 2 square <边长的一半>  §a- 在自己的中心生成一个正方形");
            listener.sendMessage(location, "§b/particlex 2 triangle <底(高)的一半>  §a- 在自己的中心生成一个等边三角形");
        } else if(args.length == 3 && args[1].equals("square") || args[1].equals("triangle") && isValid.integer(args[2])) {
            Summon summon = new Summon(location);
            List<Location> alist;
            if(args[1].equals("square")) {
                alist = summon.two.square(Integer.parseInt(args[2]));
            } else if(args[1].equals("triangle")) {
                alist = summon.two.triangle(Integer.parseInt(args[2]));
            } else {
                alist = new ArrayList<>();
            }
            listener.summonParticle(alist);
            listener.sendMessage(location, "§1[" + Prefix.get() + "§b] §a正在生成粒子中...");
        } else if(args.length == 4 && args[1].equals("circle") && isValid.integer(args[2], args[3])) {
            Summon summon = new Summon(location);
            List<Location> alist = summon.two.circle(Integer.parseInt(args[2]), Integer.parseInt(args[3]));
            listener.summonParticle(alist);
            listener.sendMessage(location, "§1[" + Prefix.get() + "§b] §a正在生成粒子中...");
        }
    }
    
    public void handle(Location location, String[] args, String player) {
        if(args.length == 2 && args[1].equals("help")) {
            listener.sendPlayerMessage(player, Prefix.get() + " §b二维粒子生成帮助");
            listener.sendPlayerMessage(player, "§b/particlex 2 circle <半径> <垂直角度>  §a- 在自己的中心生成一个圆形");
            listener.sendPlayerMessage(player, "§b/particlex 2 square <边长的一半>  §a- 在自己的中心生成一个正方形");
            listener.sendPlayerMessage(player, "§b/particlex 2 triangle <底(高)的一半>  §a- 在自己的中心生成一个等边三角形");
        } else if(args.length == 3 && args[1].equals("square") || args[1].equals("triangle") && isValid.integer(args[2])) {
            Summon summon = new Summon(location);
            List<Location> alist;
            if(args[1].equals("square")) {
                alist = summon.two.square(Integer.parseInt(args[2]));
            } else if(args[1].equals("triangle")) {
                alist = summon.two.triangle(Integer.parseInt(args[2]));
            } else {
                alist = new ArrayList<>();
            }
            listener.summonParticle(alist);
            listener.sendPlayerMessage(player, "§1[" + Prefix.get() + "§b] §a正在生成粒子中...");
        } else if(args.length == 4 && args[1].equals("circle") && isValid.integer(args[2], args[3])) {
            Summon summon = new Summon(location);
            List<Location> alist = summon.two.circle(Integer.parseInt(args[2]), Integer.parseInt(args[3]));
            listener.summonParticle(alist);
            listener.sendPlayerMessage(player, "§1[" + Prefix.get() + "§b] §a正在生成粒子中...");
        }
    }
}
