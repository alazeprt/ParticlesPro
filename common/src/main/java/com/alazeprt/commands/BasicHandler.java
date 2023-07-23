package com.alazeprt.commands;

import com.alazeprt.util.CommandListener;
import com.alazeprt.util.Listener;
import com.alazeprt.util.Location;
import com.alazeprt.util.Prefix;

public class BasicHandler {
    private final CommandListener commandListener;
    private final Listener listener;
    public BasicHandler(Listener listener, CommandListener commandListener) {
        this.listener = listener;
        this.commandListener = commandListener;
    }

    public void handle(Location location, String[] args) {
        if(args.length == 0) {
            listener.sendMessage(location, Prefix.get() + " §b指令帮助");
            listener.sendMessage(location, "§e/particlex <维度> help  §a- 查看指定维度的指令帮助");
            listener.sendMessage(location, "§6维度有: 1,2,3");
        } else if(args.length == 1 && args[0].equals("help")) {
            listener.sendMessage(location, Prefix.get() + " §b指令帮助");
            listener.sendMessage(location, "§e/particlex <维度> help  §a- 查看指定维度的指令帮助");
            listener.sendMessage(location, "§6维度有: 1,2,3");
        } else if(args.length >= 2) {
            if(args[0].equals("1")){
                commandListener.OneBlockHandle(location, args);
            } else if(args[0].equals("2")) {
                commandListener.TwoBlockHandle(location, args);
            } else if(args[0].equals("3")) {
                commandListener.ThreeBlockHandle(location, args);
            } else {
                listener.sendMessage(location, "§1[" + Prefix.get() + "§b] §c未知的维度! 维度有: 1,2,3");
            }
        } else {
            listener.sendMessage(location, "§1[" + Prefix.get() + "§b] §c未知的指令用法! 请使用/particlex help 查看指令帮助!");
        }
    }

    public void handle(Location location, String[] args, String player) {
        if(args.length == 0) {
            listener.sendPlayerMessage(player, Prefix.get() + " §b指令帮助");
            listener.sendPlayerMessage(player, "§e/particlex <维度> help  §a- 查看指定维度的指令帮助");
            listener.sendPlayerMessage(player, "§6维度有: 1,2,3");
        } else if(args.length == 1 && args[0].equals("help")) {
            listener.sendPlayerMessage(player, Prefix.get() + " §b指令帮助");
            listener.sendPlayerMessage(player, "§e/particlex <维度> help  §a- 查看指定维度的指令帮助");
            listener.sendPlayerMessage(player, "§6维度有: 1,2,3");
        } else if(args.length >= 2) {
            if(args[0].equals("1")){
                commandListener.OnePlayerHandle(location, args, player);
            } else if(args[0].equals("2")) {
                commandListener.TwoPlayerHandle(location, args, player);
            } else if(args[0].equals("3")) {
                commandListener.ThreePlayerHandle(location, args, player);
            } else {
                listener.sendPlayerMessage(player, "§1[" + Prefix.get() + "§b] §c未知的维度! 维度有: 1,2,3");
            }
        } else {
            listener.sendPlayerMessage(player, "§1[" + Prefix.get() + "§b] §c未知的指令用法! 请使用/particlex help 查看指令帮助!");
        }
    }
}
