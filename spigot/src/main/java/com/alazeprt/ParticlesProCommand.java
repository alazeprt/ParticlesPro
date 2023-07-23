package com.alazeprt;

import com.alazeprt.commands.BasicHandler;
import com.alazeprt.method.LocationConvert;
import com.alazeprt.util.Prefix;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ParticlesProCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            BasicEvent event = new BasicEvent();
            CommandEvent cevent = new CommandEvent();
            BasicHandler handler = new BasicHandler(event, cevent);
            handler.handle(LocationConvert.toThisLocation(player), args);
        } else if(sender instanceof BlockCommandSender) {
            BlockCommandSender commandSender = (BlockCommandSender) sender;
            BasicEvent event = new BasicEvent();
            CommandEvent cevent = new CommandEvent();
            BasicHandler handler = new BasicHandler(event, cevent);
            handler.handle(LocationConvert.toThisLocation(commandSender.getBlock().getLocation()), args);
        } else {
            sender.sendMessage("§1[" + Prefix.get() + "§b] §c该命令只能由玩家执行!");
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
}
