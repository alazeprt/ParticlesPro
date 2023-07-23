package com.alazeprt.commands;

import com.alazeprt.util.*;
import org.bukkit.Particle;

import java.util.List;

public class One_Dimensional {
    
    private final Listener listener;
    
    public One_Dimensional(Listener listener) {
        this.listener = listener;
    }
    
    public void handle(Location location, String[] args) {
        if(args.length == 2 && args[1].equals("help")) {
            listener.sendMessage(location, Prefix.get() + " §b一维粒子生成帮助");
            listener.sendMessage(location, "§b/particlex 1 vertical <长度> <水平角度> <垂直角度> <粒子名称>  §a- 生成一条直线");
            listener.sendMessage(location, "§b/particlex 1 curve <长度> <方向(north, south, east, west)> <曲线两端高度> <每一起伏的长度> <粒子名称>  §a- 生成一条曲线");
            listener.sendMessage(location, "§b/particlex 1 cross <长度> <方向(north, south, east, west)> <曲线两端高度> <每一起伏的长度> <粒子名称>  §a- 生成两条相交曲线");
            listener.sendMessage(location, "§b/particlex 1 wavy <长度> <水平角度> <垂直角度> <曲线两端高度> <每一起伏的长度> <粒子名称>  §a- 生成一条波浪线");
        } else if(args.length == 5 && args[1].equals("vertical") && isValid.integer(args[2], args[3], args[4])) {
            Summon summon = new Summon(location);
            List<Location> alist = summon.one.vertical(Integer.parseInt(args[2]), Integer.parseInt(args[3]), Integer.parseInt(args[4]));
            listener.summonParticle(alist);
            listener.sendMessage(location, "§1[" + Prefix.get() + "§b] §a正在生成粒子中...");
        } else if(args.length == 6 && args[1].equals("curve") && isValid.integer(args[2], args[4], args[5]) && Direction.isExist(args[3].toUpperCase())) {
            Summon summon = new Summon(location);
            List<com.alazeprt.util.Location> alist = summon.one.curve(Integer.parseInt(args[2]), Direction.valueOf(args[3].toUpperCase()), Integer.parseInt(args[4]), Integer.parseInt(args[5]));
            listener.summonParticle(alist);
            listener.sendMessage(location, "§1[" + Prefix.get() + "§b] §a正在生成粒子中...");
        } else if(args.length == 6 && args[1].equals("cross") && isValid.integer(args[2], args[4], args[5]) && Direction.isExist(args[3].toUpperCase())) {
            Summon summon = new Summon(location);
            List<com.alazeprt.util.Location> alist = summon.one.cross(Integer.parseInt(args[2]), Direction.valueOf(args[3].toUpperCase()), Integer.parseInt(args[4]), Integer.parseInt(args[5]));
            listener.summonParticle(alist);
            listener.sendMessage(location, "§1[" + Prefix.get() + "§b] §a正在生成粒子中...");
        } else if(args.length == 7 && args[1].equals("wavy") && isValid.integer(args[2], args[3], args[4], args[5], args[6])) {
            Summon summon = new Summon(location);
            List<com.alazeprt.util.Location> alist = summon.one.wavy(Integer.parseInt(args[2]), Integer.parseInt(args[3]), Integer.parseInt(args[4]), Integer.parseInt(args[5]), Integer.parseInt(args[6]));
            listener.summonParticle(alist);
            listener.sendMessage(location, "§1[" + Prefix.get() + "§b] §a正在生成粒子中...");
        } else {
            listener.sendMessage(location, "§1[" + Prefix.get() + "§b] §c未知的指令参数或指令参数不正确! 请输入/particlex 1 help 查看粒子生成帮助!");
        }
    }

    public void handle(Location location, String[] args, String player) {
        if(args.length == 2 && args[1].equals("help")) {
            listener.sendPlayerMessage(player, Prefix.get() + " §b一维粒子生成帮助");
            listener.sendPlayerMessage(player, "§b/particlex 1 vertical <长度> <水平角度> <垂直角度> <粒子名称>  §a- 生成一条直线");
            listener.sendPlayerMessage(player, "§b/particlex 1 curve <长度> <方向(north, south, east, west)> <曲线两端高度> <每一起伏的长度> <粒子名称>  §a- 生成一条曲线");
            listener.sendPlayerMessage(player, "§b/particlex 1 cross <长度> <方向(north, south, east, west)> <曲线两端高度> <每一起伏的长度> <粒子名称>  §a- 生成两条相交曲线");
            listener.sendPlayerMessage(player, "§b/particlex 1 wavy <长度> <水平角度> <垂直角度> <曲线两端高度> <每一起伏的长度> <粒子名称>  §a- 生成一条波浪线");
        } else if(args.length == 5 && args[1].equals("vertical") && isValid.integer(args[2], args[3], args[4])) {
            Summon summon = new Summon(location);
            List<Location> alist = summon.one.vertical(Integer.parseInt(args[2]), Integer.parseInt(args[3]), Integer.parseInt(args[4]));
            listener.summonParticle(alist);
            listener.sendPlayerMessage(player, "§1[" + Prefix.get() + "§b] §a正在生成粒子中...");
        } else if(args.length == 6 && args[1].equals("curve") && isValid.integer(args[2], args[4], args[5]) && Direction.isExist(args[3].toUpperCase())) {
            Summon summon = new Summon(location);
            List<com.alazeprt.util.Location> alist = summon.one.curve(Integer.parseInt(args[2]), Direction.valueOf(args[3].toUpperCase()), Integer.parseInt(args[4]), Integer.parseInt(args[5]));
            listener.summonParticle(alist);
            listener.sendPlayerMessage(player, "§1[" + Prefix.get() + "§b] §a正在生成粒子中...");
        } else if(args.length == 6 && args[1].equals("cross") && isValid.integer(args[2], args[4], args[5]) && Direction.isExist(args[3].toUpperCase())) {
            Summon summon = new Summon(location);
            List<com.alazeprt.util.Location> alist = summon.one.cross(Integer.parseInt(args[2]), Direction.valueOf(args[3].toUpperCase()), Integer.parseInt(args[4]), Integer.parseInt(args[5]));
            listener.summonParticle(alist);
            listener.sendPlayerMessage(player, "§1[" + Prefix.get() + "§b] §a正在生成粒子中...");
        } else if(args.length == 7 && args[1].equals("wavy") && isValid.integer(args[2], args[3], args[4], args[5], args[6])) {
            Summon summon = new Summon(location);
            List<com.alazeprt.util.Location> alist = summon.one.wavy(Integer.parseInt(args[2]), Integer.parseInt(args[3]), Integer.parseInt(args[4]), Integer.parseInt(args[5]), Integer.parseInt(args[6]));
            listener.summonParticle(alist);
            listener.sendPlayerMessage(player, "§1[" + Prefix.get() + "§b] §a正在生成粒子中...");
        } else {
            listener.sendPlayerMessage(player, "§1[" + Prefix.get() + "§b] §c未知的指令参数或指令参数不正确! 请输入/particlex 1 help 查看粒子生成帮助!");
        }
    }
}
