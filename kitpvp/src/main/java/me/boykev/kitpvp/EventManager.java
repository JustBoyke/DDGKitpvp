package me.boykev.kitpvp;

import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class EventManager implements Listener{
	
	public EventManager(Main main) {
		this.instance = main;
	}
	private Main instance;
	private ConfigManager cm;
	
	public void onSignClick(PlayerInteractEvent e) {
		HashMap<Player, Integer> mapid = instance.mapid;
		cm = new ConfigManager(instance);
		
		if(e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if(e.getClickedBlock().getType() == Material.SIGN || e.getClickedBlock().getType() == Material.WALL_SIGN) {
				Sign sign = (Sign) e.getClickedBlock().getState();
				String signpref = ChatColor.translateAlternateColorCodes('&', cm.getConfig().getString("Sign-Prefix"));
				if(sign.getLine(0).equalsIgnoreCase(signpref)) {
					
					//Hier de code die uitgevoerd moet worden als een sign is aangeklikt (On-Hold voor het ID systeem)
					
				}
				return;
			}
			return;
		}
		return;
		
	}
	
}
