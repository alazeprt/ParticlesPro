package com.alazeprt;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.nfunk.jep.function.Sum;

import java.util.ArrayList;
import java.util.List;

import static com.alazeprt.ParticlesPro.getPrefix;

public class ParticlesProCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player || sender instanceof BlockCommandSender) {
            boolean isPlayer = false;
            if(sender instanceof Player) {
                isPlayer = true;
            }
            if(args.length == 0) {
                sender.sendMessage(getPrefix() + " §b指令帮助");
                sender.sendMessage("§e/particlex <维度> help  §a- 查看指定维度的指令帮助");
                sender.sendMessage("§6维度有: 1,2,3");
            } else if(args.length == 1 && args[0].equals("help")) {
                sender.sendMessage(getPrefix() + " §b指令帮助");
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
                    sender.sendMessage("§1[" + getPrefix() + "§b] §c未知的维度! 维度有: 1,2,3");
                }
            } else {
                sender.sendMessage("§1[" + getPrefix() + "§b] §c未知的指令用法! 请使用/particlex help 查看指令帮助!");
            }
        } else {
            sender.sendMessage("§1[" + getPrefix() + "§b] §c该命令只能由玩家执行!");
        }
        return false;
    }


    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> list = new ArrayList<>();
        return list;
    }

    private void one(CommandSender player, World world, String[] args) {
        boolean isPlayer = false;
        if(player instanceof Player) {
            isPlayer = true;
        }
        if(args.length == 2 && args[1].equals("help")) {
            player.sendMessage(getPrefix() + " §b一维粒子生成帮助");
            player.sendMessage("§b/particlex 1 vertical <长度> <水平角度> <垂直角度> <粒子名称>  §a- 生成一条直线");
            player.sendMessage("§b/particlex 1 curve <长度> <方向(north, south, east, west)> <曲线两端高度> <每一起伏的长度> <粒子名称>  §a- 生成一条曲线");
            player.sendMessage("§b/particlex 1 cross <长度> <方向(north, south, east, west)> <曲线两端高度> <每一起伏的长度> <粒子名称>  §a- 生成两条相交曲线");
            player.sendMessage("§b/particlex 1 wavy <长度> <水平角度> <垂直角度> <曲线两端高度> <每一起伏的长度> <粒子名称>  §a- 生成一条波浪线");
        } else if(args.length == 5 && args[1].equals("vertical") && isValid.isInteger(args[2], args[3], args[4])) {
            Summon summon;
            if(isPlayer) {
                summon = new Summon((Player) player);
            } else {
                summon = new Summon(((BlockCommandSender) player).getBlock().getLocation());
            }
            List<Location> list = summon.one.vertical(Integer.parseInt(args[2]), Integer.parseInt(args[3]), Integer.parseInt(args[4]));
            player.sendMessage("§1[" + getPrefix() + "§b] §a正在生成粒子中...");
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
        } else if(args.length == 6 && args[1].equals("curve") && isValid.isInteger(args[2], args[4], args[5]) && Direction.isExist(args[3].toUpperCase())) {
            Summon summon;
            if(isPlayer) {
                summon = new Summon((Player) player);
            } else {
                summon = new Summon(((BlockCommandSender) player).getBlock().getLocation());
            }
            List<Location> list = summon.one.curve(Integer.parseInt(args[2]), Direction.valueOf(args[3].toUpperCase()), Integer.parseInt(args[4]), Integer.parseInt(args[5]));
            player.sendMessage("§1[" + getPrefix() + "§b] §a正在生成粒子中...");
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
        } else if(args.length == 6 && args[1].equals("cross") && isValid.isInteger(args[2], args[4], args[5]) && Direction.isExist(args[3].toUpperCase())) {
            Summon summon;
            if(isPlayer) {
                summon = new Summon((Player) player);
            } else {
                summon = new Summon(((BlockCommandSender) player).getBlock().getLocation());
            }
            List<Location> list = summon.one.cross(Integer.parseInt(args[2]), Direction.valueOf(args[3].toUpperCase()), Integer.parseInt(args[4]), Integer.parseInt(args[5]));
            player.sendMessage("§1[" + getPrefix() + "§b] §a正在生成粒子中...");
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
        } else if(args.length == 7 && args[1].equals("wavy") && isValid.isInteger(args[2], args[3], args[4], args[5], args[6])) {
            Summon summon;
            if(isPlayer) {
                summon = new Summon((Player) player);
            } else {
                summon = new Summon(((BlockCommandSender) player).getBlock().getLocation());
            }
            List<Location> list = summon.one.wavy(Integer.parseInt(args[2]), Integer.parseInt(args[3]), Integer.parseInt(args[4]), Integer.parseInt(args[5]), Integer.parseInt(args[6]));
            player.sendMessage("§1[" + getPrefix() + "§b] §a正在生成粒子中...");
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
            player.sendMessage("§1[" + getPrefix() + "§b] §c未知的指令参数或指令参数不正确! 请输入/particlex 1 help 查看粒子生成帮助!");
        }
    }

    private void two(CommandSender player, World world, String[] args) {

    }

    private void three(CommandSender player, World world, String[] args) {

    }
}
