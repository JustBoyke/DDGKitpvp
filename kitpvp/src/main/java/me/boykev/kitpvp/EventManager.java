package me.boykev.kitpvp;

import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class EventManager implements Listener{
	
	public EventManager(Main main) {
		this.instance = main;
	}
	private Main instance;
	private ConfigManager cm;
	private MapManager mm;
	
	public void onSignClick(PlayerInteractEvent e) {
		//Hier moet nog wat mee gebeuren, stop met zeuren dat het nog niet gebruikt wordt, i know the fkng issue. Dankje <3
		HashMap<Player, Integer> mapid = instance.mapid;
		cm = new ConfigManager(instance);
		mm = new MapManager(instance);
		
		
		if(e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if(e.getClickedBlock().getType() == Material.SIGN || e.getClickedBlock().getType() == Material.WALL_SIGN) {
				Sign sign = (Sign) e.getClickedBlock().getState();
				String signpref = ChatColor.translateAlternateColorCodes('&', cm.getConfig().getString("Sign-Prefix"));
				Player p = (Player) e.getPlayer();
				if(sign.getLine(0).equalsIgnoreCase(signpref)) {
					
					String mapname = sign.getLine(1).toString().toUpperCase();
					if(mm.getConfig().getConfigurationSection(mapname) == null) {
						p.sendMessage(ChatColor.RED + "Oeps, deze map lijkt niet te bestaan. Neem contact op met een Developer!");
						return;
					}
					
//					mapid.put(p, key); < To be inserted if shit is done
					
					
					//Hier de code die uitgevoerd moet worden als een sign is aangeklikt en de map bestaat (On-Hold voor het ID systeem)
					
				}
				return;
			}
			return;
		}
		return;
	}//end of ClickEvent
	
	public void onSignChange(SignChangeEvent e) {
		cm = new ConfigManager(instance);
		String signpref = ChatColor.translateAlternateColorCodes('&', cm.getConfig().getString("Sign-Prefix"));
		if(!e.getPlayer().hasPermission("kitpvp.sign")) {
			e.getPlayer().sendMessage(ChatColor.RED + "Oeps, je hebt niet de permissies om deze signs te maken!");
			return;
		}
		if(e.getLine(0).equalsIgnoreCase("[kitpvp]")) {
			if(e.getLine(1).isBlank()) {
				e.getPlayer().sendMessage(ChatColor.RED + "Je hebt de sign niet goed opgesteld! Probeer het opnieuw");
				return;
			}
			e.setLine(0, signpref);
			e.setLine(1, e.getLine(1).toString().toUpperCase());
			return;
		}
		
	}
	
}
 