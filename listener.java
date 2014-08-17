package com.qq.q1277832129;

import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.util.Vector;

public class listener implements Listener {
    
    @EventHandler
    public void onRightClickPlayer(PlayerInteractEntityEvent e) {
        Player p = e.getPlayer();
        if (!Main.g.hasingame(p)) {
            return;
        }
        Entity ent = e.getRightClicked();
        if (!(ent instanceof Player)) {
            return;
        }
        p.setPassenger(ent);
    }
    
    @EventHandler
    public void onDead(PlayerDeathEvent e) {
        Player p = e.getEntity();
        if (!Main.g.hasingame(p)) {
            return;
        }
        Main.g.leftgame(p);
        p.sendMessage(ChatColor.GREEN+"你死亡了");
    }
    
    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        if (!Main.g.hasingame(p)) {
            return;
        }
        Main.g.badleftgame(p);
        p.sendMessage(ChatColor.GREEN+"你死亡了");
    }
    
    @EventHandler
    public void onThrow(PlayerInteractEvent e) {
        if (e.getAction() == Action.LEFT_CLICK_AIR
                || e.getAction() == Action.LEFT_CLICK_BLOCK) {
            
            Player p = e.getPlayer();
            if (!Main.g.hasingame(p)) {
                return;
            }
            if (p.getPassenger() == null) {
                return;
            }
            Entity ent = p.getPassenger();
            if (!(p.getPassenger() instanceof Player)) {
                return;
            }
            
            p.eject();
            
            Vector vector = ent.getLocation().getDirection();
            vector.multiply(2);
            ent.setVelocity(vector);
        }
    }
}
