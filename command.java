package com.qq.q1277832129;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


class command implements CommandExecutor {
    game g;
    public command(game g){
        this.g=g;
        this.g.lobby=Main.lobby;
        this.g.loc=Main.loc;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        if(args[0].equalsIgnoreCase("join")){
            if(g.hasStart()){
            p.sendMessage("游戏已经开始啦");
            return true;
            }
            g.joingame(p);
            g.tell(p.getDisplayName()+"加入了游戏");
        }
        if(args[0].equalsIgnoreCase("setlobby")){
            g.lobby=p.getLocation();
        }
        if(args[0].equalsIgnoreCase("setspawn")){
            g.loc=p.getLocation();
        }
        return true;
    }
}
