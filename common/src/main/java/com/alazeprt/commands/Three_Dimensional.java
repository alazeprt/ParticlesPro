package com.alazeprt.commands;

import com.alazeprt.util.*;

import java.util.List;

public class Three_Dimensional {
    private final Listener listener;

    public Three_Dimensional(Listener listener) {
        this.listener = listener;
    }

    public void handle(Location location, String[] args) {
        if(args.length == 2 && args[1].equals("help")) {
            listener.sendMessage(location, Prefix.get() + " §b三维粒子生成帮助");
            listener.sendMessage(location, "§b/particlex 3 cube <边长> <水平角度>  §a- 在自己的中心生成一个正方体");
            listener.sendMessage(location, "§b/particlex 3 hollow_cube <边长> <水平角度>  §a- 在自己的中心生成一个空心正方体");
        } else if(args.length == 4 && args[1].equals("soild_cube") && isValid.integer(args[2], args[3])) {
            Summon summon = new Summon(location);
            List<Location> alist = summon.three.soild_cube(Integer.parseInt(args[2]), Integer.parseInt(args[3]));
            listener.summonParticle(alist);
            listener.sendMessage(location, "§1[" + Prefix.get() + "§b] §a正在生成粒子中...");
        } else if(args.length == 4 && args[1].equals("hollow_cube") && isValid.integer(args[2], args[3])) {
            Summon summon = new Summon(location);
            List<Location> alist = summon.three.hollow_cube(Integer.parseInt(args[2]), Integer.parseInt(args[3]));
            listener.summonParticle(alist);
            listener.sendMessage(location, "§1[" + Prefix.get() + "§b] §a正在生成粒子中...");
        }
    }

    public void handle(Location location, String[] args, String player) {
        if(args.length == 2 && args[1].equals("help")) {
            listener.sendPlayerMessage(player, Prefix.get() + " §b三维粒子生成帮助");
            listener.sendPlayerMessage(player, "§e/particlex 3 soild_cube <边长> <水平角度>  §a- 在自己的中心生成一个实心正方体");
            listener.sendPlayerMessage(player, "§b/particlex 3 hollow_cube <边长> <水平角度>  §a- 在自己的中心生成一个空心正方体");
            listener.sendPlayerMessage(player, "§b/particlex 3 sphere <半径> <水平角度>  §a- 在自己的中心生成一个球体");
            listener.sendPlayerMessage(player, "§b/particlex 3 cylinder <半径> <高>  §a- 在自己的中心生成一个圆柱体");
        } else if(args.length == 4 && args[1].equals("soild_cube") && isValid.integer(args[2], args[3])) {
            Summon summon = new Summon(location);
            List<Location> alist = summon.three.soild_cube(Integer.parseInt(args[2]), Integer.parseInt(args[3]));
            listener.summonParticle(alist);
            listener.sendPlayerMessage(player, "§1[" + Prefix.get() + "§b] §a正在生成粒子中...");
        } else if(args.length == 4 && args[1].equals("hollow_cube") && isValid.integer(args[2], args[3])) {
            Summon summon = new Summon(location);
            List<Location> alist = summon.three.hollow_cube(Integer.parseInt(args[2]), Integer.parseInt(args[3]));
            listener.summonParticle(alist);
            listener.sendPlayerMessage(player, "§1[" + Prefix.get() + "§b] §a正在生成粒子中...");
        } else if(args.length == 4 && args[1].equals("sphere") && isValid.integer(args[2], args[3])) {
            Summon summon = new Summon(location);
            List<Location> alist = summon.three.sphere(Integer.parseInt(args[2]), Integer.parseInt(args[3]));
            listener.summonParticle(alist);
            listener.sendPlayerMessage(player, "§1[" + Prefix.get() + "§b] §a正在生成粒子中...");
        } else if(args.length == 4 && args[1].equals("cylinder") && isValid.integer(args[2], args[3])) {
            Summon summon = new Summon(location);
            List<Location> alist = summon.three.cylinder(Integer.parseInt(args[2]), Integer.parseInt(args[3]));
            listener.summonParticle(alist);
            listener.sendPlayerMessage(player, "§1[" + Prefix.get() + "§b] §a正在生成粒子中...");
        }
    }
}
