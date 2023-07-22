package com.alazeprt;

import com.alazeprt.method.LocationConvert;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import sun.util.locale.LocaleObjectCache;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class ParticlesProCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if(sender instanceof Player || sender instanceof BlockCommandSender) {
            boolean isPlayer = sender instanceof Player;
            if(args.length == 0) {
                sender.sendMessage(ParticlesPro.getPrefix() + " §b指令帮助");
                sender.sendMessage("§e/particlex <维度> help  §a- 查看指定维度的指令帮助");
                sender.sendMessage("§6维度有: 1,2,3");
            } else if(args.length == 1 && args[0].equals("help")) {
                sender.sendMessage(ParticlesPro.getPrefix() + " §b指令帮助");
                sender.sendMessage("§e/particlex <维度> help  §a- 查看指定维度的指令帮助");
                sender.sendMessage("§6维度有: 1,2,3");
            } else if(args.length >= 2) {
                if(args[0].equals("1")){
                    if(isPlayer) {
                        one(sender, ((Player) sender).getWorld(), args);
                    } else {
                        one(sender, ((BlockCommandSender) sender).getBlock().getWorld(), args);
                    }
                } else if(args[0].equals("2")) {
                    if(isPlayer) {
                        two(sender, ((Player) sender).getWorld(), args);
                    } else {
                        two(sender, ((BlockCommandSender) sender).getBlock().getWorld(), args);
                    }
                } else if(args[0].equals("3")) {
                    if(isPlayer) {
                        three(sender, ((Player) sender).getWorld(), args);
                    } else {
                        three(sender, ((BlockCommandSender) sender).getBlock().getWorld(), args);
                    }
                } else {
                    sender.sendMessage("§1[" + ParticlesPro.getPrefix() + "§b] §c未知的维度! 维度有: 1,2,3");
                }
            } else {
                sender.sendMessage("§1[" + ParticlesPro.getPrefix() + "§b] §c未知的指令用法! 请使用/particlex help 查看指令帮助!");
            }
        } else {
            sender.sendMessage("§1[" + ParticlesPro.getPrefix() + "§b] §c该命令只能由玩家执行!");
        }
        return false;
    }


    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        List<String> list = new ArrayList<>();
        if(args.length == 1) {
            list.add("help");
            list.add("1");
            list.add("2");
            list.add("3");
        } else if(args.length == 2) {
            list.add("help");
            if(args[0].equals("1")) {
                list.add("vertical");
                list.add("curve");
                list.add("cross");
                list.add("wavy");
            }
        } else if(args.length == 4) {
            if(args[1].equals("curve") || args[1].equals("cross")) {
                list.add("NORTH");
                list.add("SOUTH");
                list.add("EAST");
                list.add("WEST");
            }
        }
        return list;
    }

    private void one(CommandSender player, World world, String[] args) {
        boolean isPlayer = player instanceof Player;
        if(args.length == 2 && args[1].equals("help")) {
            player.sendMessage(ParticlesPro.getPrefix() + " §b一维粒子生成帮助");
            player.sendMessage("§b/particlex 1 vertical <长度> <水平角度> <垂直角度> <粒子名称>  §a- 生成一条直线");
            player.sendMessage("§b/particlex 1 curve <长度> <方向(north, south, east, west)> <曲线两端高度> <每一起伏的长度> <粒子名称>  §a- 生成一条曲线");
            player.sendMessage("§b/particlex 1 cross <长度> <方向(north, south, east, west)> <曲线两端高度> <每一起伏的长度> <粒子名称>  §a- 生成两条相交曲线");
            player.sendMessage("§b/particlex 1 wavy <长度> <水平角度> <垂直角度> <曲线两端高度> <每一起伏的长度> <粒子名称>  §a- 生成一条波浪线");
        } else if(args.length == 5 && args[1].equals("vertical") && isValid.integer(args[2], args[3], args[4])) {
            Summon summon;
            if(isPlayer) {
                summon = new Summon(LocationConvert.toThisLocation((Player) player));
            } else {
                summon = new Summon(LocationConvert.toThisLocation(((BlockCommandSender) player).getBlock().getLocation()));
            }
            List<com.alazeprt.Location> alist = summon.one.vertical(Integer.parseInt(args[2]), Integer.parseInt(args[3]), Integer.parseInt(args[4]));
            List<Location> list = LocationConvert.toBukkitLocation(alist);
            player.sendMessage("§1[" + ParticlesPro.getPrefix() + "§b] §a正在生成粒子中...");
            Thread thread = new Thread(() -> {
                for(Location location : list) {
                    String version = Bukkit.getServer().getVersion();
                    String[] versionArray = version.split("\\.");
                    int secondVersion = Integer.parseInt(versionArray[1]);
                    if(secondVersion >= 13) {
                        world.spawnParticle(Particle.END_ROD, location, 2, 0, 0, 0, 0, null, true);
                    } else {
                        world.spawnParticle(Particle.END_ROD, location, 2, 0, 0, 0, 0);
                    }
                }
            });
            thread.start();
        } else if(args.length == 6 && args[1].equals("curve") && isValid.integer(args[2], args[4], args[5]) && Direction.isExist(args[3].toUpperCase())) {
            Summon summon;
            if(isPlayer) {
                summon = new Summon(LocationConvert.toThisLocation((Player) player));
            } else {
                summon = new Summon(LocationConvert.toThisLocation(((BlockCommandSender) player).getBlock().getLocation()));
            }
            List<com.alazeprt.Location> alist = summon.one.curve(Integer.parseInt(args[2]), Direction.valueOf(args[3].toUpperCase()), Integer.parseInt(args[4]), Integer.parseInt(args[5]));
            List<Location> list = LocationConvert.toBukkitLocation(alist);
            player.sendMessage("§1[" + ParticlesPro.getPrefix() + "§b] §a正在生成粒子中...");
            Thread thread = new Thread(() -> {
                for(Location location : list) {
                    String version = Bukkit.getServer().getVersion();
                    String[] versionArray = version.split("\\.");
                    int secondVersion = Integer.parseInt(versionArray[1]);
                    if(secondVersion >= 13) {
                        world.spawnParticle(Particle.END_ROD, location, 2, 0, 0, 0, 0, null, true);
                    } else {
                        world.spawnParticle(Particle.END_ROD, location, 2, 0, 0, 0, 0);
                    }
                }
            });
            thread.start();
        } else if(args.length == 6 && args[1].equals("cross") && isValid.integer(args[2], args[4], args[5]) && Direction.isExist(args[3].toUpperCase())) {
            Summon summon;
            if(isPlayer) {
                summon = new Summon(LocationConvert.toThisLocation((Player) player));
            } else {
                summon = new Summon(LocationConvert.toThisLocation(((BlockCommandSender) player).getBlock().getLocation()));
            }
            List<com.alazeprt.Location> alist = summon.one.cross(Integer.parseInt(args[2]), Direction.valueOf(args[3].toUpperCase()), Integer.parseInt(args[4]), Integer.parseInt(args[5]));
            List<Location> list = LocationConvert.toBukkitLocation(alist);
            player.sendMessage("§1[" + ParticlesPro.getPrefix() + "§b] §a正在生成粒子中...");
            Thread thread = new Thread(() -> {
                for(Location location : list) {
                    String version = Bukkit.getServer().getVersion();
                    String[] versionArray = version.split("\\.");
                    int secondVersion = Integer.parseInt(versionArray[1]);
                    if(secondVersion >= 13) {
                        world.spawnParticle(Particle.END_ROD, location, 2, 0, 0, 0, 0, null, true);
                    } else {
                        world.spawnParticle(Particle.END_ROD, location, 2, 0, 0, 0, 0);
                    }
                }
            });
            thread.start();
        } else if(args.length == 7 && args[1].equals("wavy") && isValid.integer(args[2], args[3], args[4], args[5], args[6])) {
            Summon summon;
            if(isPlayer) {
                summon = new Summon(LocationConvert.toThisLocation((Player) player));
            } else {
                summon = new Summon(LocationConvert.toThisLocation(((BlockCommandSender) player).getBlock().getLocation()));
            }
            List<com.alazeprt.Location> alist = summon.one.wavy(Integer.parseInt(args[2]), Integer.parseInt(args[3]), Integer.parseInt(args[4]), Integer.parseInt(args[5]), Integer.parseInt(args[6]));
            List<Location> list = LocationConvert.toBukkitLocation(alist);
            player.sendMessage("§1[" + ParticlesPro.getPrefix() + "§b] §a正在生成粒子中...");
            Thread thread = new Thread(() -> {
                for(Location location : list) {
                    String version = Bukkit.getServer().getVersion();
                    String[] versionArray = version.split("\\.");
                    int secondVersion = Integer.parseInt(versionArray[1]);
                    if(secondVersion >= 13) {
                        world.spawnParticle(Particle.END_ROD, location, 2, 0, 0, 0, 0, null, true);
                    } else {
                        world.spawnParticle(Particle.END_ROD, location, 2, 0, 0, 0, 0);
                    }
                }
            });
            thread.start();
        } else {
            player.sendMessage("§1[" + ParticlesPro.getPrefix() + "§b] §c未知的指令参数或指令参数不正确! 请输入/particlex 1 help 查看粒子生成帮助!");
        }
    }

    private void two(CommandSender player, World world, String[] args) {
        boolean isPlayer = player instanceof Player;
        if(args.length == 2 && args[1].equals("help")) {
            player.sendMessage(ParticlesPro.getPrefix() + " §b二维粒子生成帮助");
            player.sendMessage("§b/particlex 2 circle <半径>  §a- 在自己的中心生成一个圆形");
            player.sendMessage("§b/particlex 2 square <边长的一半>  §a- 在自己的中心生成一个正方形");
            player.sendMessage("§b/particlex 2 triangle <底(高)的一半>  §a- 在自己的中心生成一个等边三角形");
        } else if(args.length == 3 && args[1].equals("circle") || args[1].equals("square") || args[1].equals("triangle") && isValid.integer(args[2])) {
            Summon summon;
            if(isPlayer) {
                summon = new Summon(LocationConvert.toThisLocation((Player) player));
            } else {
                summon = new Summon(LocationConvert.toThisLocation(((BlockCommandSender) player).getBlock().getLocation()));
            }
            List<com.alazeprt.Location> alist;
            if(args[1].equals("circle")) {
                alist = summon.two.circle(Integer.parseInt(args[2]));
            } else if(args[1].equals("square")) {
                alist = summon.two.square(Integer.parseInt(args[2]));
            } else if(args[1].equals("triangle")) {
                alist = summon.two.triangle(Integer.parseInt(args[2]));
            } else {
                alist = new ArrayList<>();
            }
            List<Location> list = LocationConvert.toBukkitLocation(alist);
            player.sendMessage("§1[" + ParticlesPro.getPrefix() + "§b] §a正在生成粒子中...");
            Thread thread = new Thread(() -> {
                for (Location location : list) {
                    String version = Bukkit.getServer().getVersion();
                    String[] versionArray = version.split("\\.");
                    int secondVersion = Integer.parseInt(versionArray[1]);
                    if (secondVersion >= 13) {
                        world.spawnParticle(Particle.END_ROD, location, 2, 0, 0, 0, 0, null, true);
                    } else {
                        world.spawnParticle(Particle.END_ROD, location, 2, 0, 0, 0, 0);
                    }
                }
            });
            thread.start();
        }
    }

    private void three(CommandSender player, World world, String[] args) {

    }
}
