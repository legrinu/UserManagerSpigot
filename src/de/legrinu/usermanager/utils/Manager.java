package de.legrinu.usermanager.utils;

import java.util.HashMap;

import org.bukkit.entity.Player;

import de.legrinu.usermanager.mysql.MySQL;

public class Manager {
	
	public static HashMap<String, Boolean> login = new HashMap<>();
	
	public static void login(Player p){
		if(isRegistered(p)){
			if(!isLoggedIn(p)){
		login.put(p.getName(), true);
		System.out.println("Login: " + p.getName());
			}else{
				p.sendMessage("§5[UM]§7 Du bist bereist eingeloggt!");
			}
		}else{			
			p.sendMessage("§5[UM]§7 Bitte registriere dich zuerst.");
		}
	}
	
	public static void logout(Player p){
		if(login.containsKey(p.getName())){
			login.remove(p.getName());
			System.out.println("Logout: " + p.getName());
		}		
	}
	
	public static boolean isLoggedIn(Player p){
		if(login.containsKey(p.getName())){
		   return login.get(p.getName());
		}		
		return false;
	}
	
	public static void unregister(String p){
		if(MySQL.existsUser(p)){
		  MySQL.removeUser(p);
		  System.out.println("Unregister: " + p);	
		}else{
			System.out.println("User is not registered: " + p);
		}
	}
	
	public static void register(Player p, String pw, Rang rang){
		if(isRegistered(p)){
			p.sendMessage("§5[UM]§7 Du bist bereist registriert!");
		return;
		}else{
		MySQL.createUser(p.getName(), pw, reconverted(rang));
		login.put(p.getName(), false);
		System.out.println("Registered: " + p.getName());
		}			
	}
	
	public static void setPW(String pw, Player p){
		MySQL.updatePW(p.getName(), pw);
		System.out.println("PW changed: " + p.getName());
	}
	
	public static void setRang(String rang, Player p){		
		MySQL.updateRang(p.getName(), rang);
		System.out.println("Rang changed: " + p.getName());
	}
	
	public static Rang getRang(Player p){
		Rang r = converted(MySQL.getRang(p.getName()));		
		return r;
	}
	
	public static String getPW(Player p){
		if(isRegistered(p)){
		String pw = MySQL.getPW(p.getName());
		return pw;
		}
		return null;
	}
	
	public static boolean isRegistered(Player p){
		return MySQL.existsUser(p.getName());
	}
	
	static Rang converted(String rang){
		Rang r = null;
		
		if(rang.equals("OWNER")){
			r = Rang.OWNER;
		}else if(rang.equals("DEV")){
			r = Rang.DEV;
		}else if(rang.equals("MOD")){
			r = Rang.MOD;
		}else if(rang.equals("SUP")){
			r = Rang.SUP;
		}else if(rang.equals("BUILDER")){
			r = Rang.BUILDER;
		}else if(rang.equals("YT")){
			r = Rang.YT;
		}else if(rang.equals("SPIELER")){
			r = Rang.SPIELER;
		}
		
		return r;
	}
	
	static String reconverted(Rang rang){
		String r = null;
		
		if(rang.equals(Rang.OWNER)){
			r = "OWNER";
		}else if(rang.equals(Rang.DEV)){
			r = "DEV";
		}else if(rang.equals(Rang.MOD)){
			r = "MOD";
		}else if(rang.equals(Rang.SUP)){
			r = "SUP";
		}else if(rang.equals(Rang.BUILDER)){
			r = "BUILDER";
		}else if(rang.equals(Rang.YT)){
			r = "YT";
		}else if(rang.equals(Rang.SPIELER)){
			r = "SPIELER";
		}
		
		return r;
	}

}
