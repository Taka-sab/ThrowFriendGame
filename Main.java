

package com.qq.q1277832129;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;



public class Main extends JavaPlugin
  implements Listener

{
public static game g;
public static Location loc;
public static Location lobby;
              ArrayList<Player> pl=new ArrayList<Player>();
    @Override
  public void onLoad()
  {
    System.out.println("[会飞的基友插件]插件加载成功");
  }

    @Override
  public void onEnable()
  {
File placesf=new File(getDataFolder(),"places.yml");
FileConfiguration places= load(placesf);
      loc = new Location(getServer().getWorld(places.getString("start-loc")), 
              places.getInt("start-loc-x"), 
              places.getInt("start-loc-y"), 
              places.getInt("start-loc-z")
      );
      lobby = new Location(getServer().getWorld(places.getString("lobby-loc")), 
              places.getInt("lobby-loc-x"), 
              places.getInt("lobby-loc-y"), 
              places.getInt("lobby-loc-z")
      );
    g=new game();
    getServer().getPluginManager().registerEvents(new listener(), this);
    System.out.println("success");
    getCommand("tg").setExecutor(new command(g));
  }

    @Override
  public void onDisable()
  {
    System.out.println("successs");
  }

   public FileConfiguration load(File file)
  {
      if(!file.exists())
	{
          try {
              file.createNewFile();
          } catch (IOException ex) {
              Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
          }
        }
      return YamlConfiguration.loadConfiguration(file);
  }
 
  
}

  
