package com.qq.q1277832129;

import java.util.TimerTask;
import org.bukkit.ChatColor;

class Taskk extends TimerTask {
    game g;
    int i=0;
    public Taskk(game g){
      this.g=g;
    }
    public void run() {
        if(i>60){ 
            return;
        }
        if(i==60){
            g.start();
            g.tell(ChatColor.GREEN+"游戏开始啦");
        }
        i++;
        
        int time = 60-i;
        
        g.tell(ChatColor.GREEN+"倒计时"+time);
    }
}
