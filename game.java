package com.qq.q1277832129;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Timer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class game {
    public static HashMap<Player,Boolean> ingame=new HashMap<Player,Boolean>();
    public Location loc;
    public Location lobby;
    public boolean firstjoin=false;
    boolean hasStart=false;
    public void setLocation(Location loc){
        this.loc=loc;
    }
 
    public void tell(String str) {
        Iterator<Player> it = getPlayers().iterator();
        while (it.hasNext()) {
            Player p = it.next();
            p.sendMessage(str);
        }
    }

    public Boolean hasStart(){
        return hasStart;
    }
    
    public Boolean hasingame(Player p){
        if(!ingame.containsKey(p))return false;
        return ingame.get(p);
    }
    
    public void joingame(Player p){
        if(getPlayers().size()<1) firstjoin=true;
        if(firstjoin){
            firstjoin =false;
            Timer timer= new Timer();
            timer.schedule(new Taskk(this), 0, 1000);
        }
        ingame.put(p, true);
        p.teleport(lobby);
    }
    
    public void leftgame(Player p){
        ingame.remove(p);
        p.teleport(lobby);
        if (getPlayers().size() == 1) {
            Iterator<Player> it = getPlayers().iterator();
            while (it.hasNext()) {
                Player endPlayer= it.next();
                endPlayer.sendMessage(ChatColor.GREEN+"恭喜你获得胜利!!");
                Bukkit.broadcastMessage("玩家"+p.getCustomName()+"获得了扔基友小游戏胜利");
                stop();
            }
        }
    }

    
     public void badleftgame(Player p){
        ingame.remove(p);
        if (getPlayers().size() == 1) {
            Iterator<Player> it = getPlayers().iterator();
            while (it.hasNext()) {
                Player endPlayer= it.next();
                endPlayer.sendMessage(ChatColor.GREEN+"恭喜你获得胜利!!");
            }
        }
    }
    
    public Set<Player> getPlayers() {
        return ingame.keySet();
    }

    public void start() {
        Iterator<Player> it = getPlayers().iterator();
        if (getPlayers().size() < 2) {
            while (it.hasNext()) {
                Player p = it.next();
                p.sendMessage(ChatColor.GREEN+"人数不够");
                stop();
            }
            return;
        }
        while (it.hasNext()) {
            Player p = it.next();
            p.teleport(loc);
        }
        this.hasStart=true;
    }

    public void stop() {
        Iterator<Player> it = getPlayers().iterator();
        while (it.hasNext()) {
            Player p = it.next();
            leftgame(p);
        }
        this.hasStart=false;
        ingame.clear();
    }

    public void tplobby(Player p) {
        p.teleport(lobby);
    }

    
}
