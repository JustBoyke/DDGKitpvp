package me.boykev.kitpvp;

import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

public class Main extends JavaPlugin{
	
	public void onEnable() {
		System.out.println(ChatColor.GREEN + "KitPVP is nu opgestart :)");
	}
	
	public void onDisable() {
		System.out.println(ChatColor.RED + "KitPVP is nu afgesloten :(");
	}
	
	
	//todo:
	// In-Game lobby systeem voor deathspawn -V
		// Instellen door elke game een ID te geven en te koppelen aan de spawn informatie
	// instelbare spawns
	// bodje om te joinen
	// menu voor instelbare kits
	// opslaan kills / deaths in database
	// scoreboard met kils, deaths en KD ratio me live update
	// *gebruik maven
	
	
	
	
	
}
