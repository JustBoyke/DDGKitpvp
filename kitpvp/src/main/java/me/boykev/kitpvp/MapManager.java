package me.boykev.kitpvp;

import java.io.File;
import java.io.IOException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class MapManager
{
  private File configFile;
  private FileConfiguration config;
  
  public MapManager(Main basement)
  {
    this.configFile = new File(basement.getDataFolder(), "maps.yml");
    this.config = YamlConfiguration.loadConfiguration(this.configFile);
  }
  
  public void save()
  {
    try
    {
      this.config.save(this.configFile);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
  
  public FileConfiguration getConfig()
  {
    return this.config;
  }

  public boolean reloadConfig() {
      try {
          config = YamlConfiguration.loadConfiguration(configFile);
          return true;
      } catch (Exception erorr) {
          return false;
      }
  }
public void LoadDefaults() {
    config.addDefault("PleaseDoNot", "EDIT");
    config.options().copyDefaults(true);
    save();
	
}

public FileConfiguration editConfig() {
	return config;
}
}