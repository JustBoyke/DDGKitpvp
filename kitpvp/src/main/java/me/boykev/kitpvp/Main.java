package me.boykev.kitpvp;

import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

public class Main extends JavaPlugin{
	
	public HashMap<Player, Integer> mapid = new HashMap<Player, Integer>();
	public ConfigManager cm;
	
	public void onEnable() {
		cm = new ConfigManager(this);
		cm.LoadDefaults();
		cm.save();
		System.out.println(ChatColor.GREEN + "KitPVP is nu opgestart :)");
	}
	
	public void onDisable() {
		System.out.println(ChatColor.RED + "KitPVP is nu afgesloten :(");
	}
	
	
	//todo:
	// In-Game lobby systeem voor deathspawn -V
		// Instellen door elke game een ID te geven en te koppelen aan de spawn informatie bij join event in hashmap zetten
	// instelbare spawns -V
		// Via SIGNLINK (Sign met naam corrospondeerd aan de locatie in config, instelbaar met commando)
	// bodje om te joinen
	// menu voor instelbare kits
	// opslaan kills / deaths in database
	// scoreboard met kils, deaths en KD ratio me live update
	// *gebruik maven
	
	
	
	
	
}
