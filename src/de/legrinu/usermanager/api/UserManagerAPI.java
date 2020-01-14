package de.legrinu.usermanager.api;

import org.bukkit.entity.Player;

import de.legrinu.usermanager.raenge.Raenge;
import de.legrinu.usermanager.utils.Manager;

public class UserManagerAPI {
		
	public static void setRang(Player p, String rang){
		Manager.setRang(rang, p);
	}
	
	public static Raenge getRang(Player p){
		return Manager.getRang(p);
	}
	
	public static void setPW(Player p, String pw){
		Manager.setPW(pw, p);
	}
	
	public static String getPW(Player p){
		return Manager.getPW(p);
	}

}
