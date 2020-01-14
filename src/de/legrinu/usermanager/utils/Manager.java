package de.legrinu.usermanager.utils;

import de.legrinu.usermanager.UserManager;
import de.legrinu.usermanager.mysql.MySQL;
import de.legrinu.usermanager.raenge.Raenge;
import org.bukkit.entity.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class Manager {
	
	public static HashMap<String, Boolean> login = new HashMap<>();
	
	public static void login(Player p){
		if(isRegistered(p)){
			if(!isLoggedIn(p)){
		login.put(p.getName(), true);
		System.out.println("Login: " + p.getName());
			}else{
				p.sendMessage(UserManager.prefix() + "Du bist bereist eingeloggt!");
			}
		}else{			
			p.sendMessage(UserManager.prefix() + "Bitte registriere dich zuerst.");
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
	
	public static void register(Player p, String pw, Raenge rang){
		if(isRegistered(p)){
			p.sendMessage(UserManager.prefix() + "Du bist bereist registriert!");
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
	
	public static Raenge getRang(Player p){
		Raenge r = converted(MySQL.getRang(p.getName()));		
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
	
	public static int count(){
		return MySQL.count();
	}
	
	static Raenge converted(String rang){
		Raenge r = null;
		
		if(rang.equals("OWNER")){
			r = Raenge.OWNER;
		}else if(rang.equals("DEV")){
			r = Raenge.DEV;
		}else if(rang.equals("MOD")){
			r = Raenge.MOD;
		}else if(rang.equals("SUP")){
			r = Raenge.SUP;
		}else if(rang.equals("BUILDER")){
			r = Raenge.BUILDER;
		}else if(rang.equals("YT")){
			r = Raenge.YT;
		}else if(rang.equals("SPIELER")){
			r = Raenge.SPIELER;
		}
		
		return r;
	}
	
	static String reconverted(Raenge rang){
		String r = null;
		
		if(rang.equals(Raenge.OWNER)){
			r = "OWNER";
		}else if(rang.equals(Raenge.DEV)){
			r = "DEV";
		}else if(rang.equals(Raenge.MOD)){
			r = "MOD";
		}else if(rang.equals(Raenge.SUP)){
			r = "SUP";
		}else if(rang.equals(Raenge.BUILDER)){
			r = "BUILDER";
		}else if(rang.equals(Raenge.YT)){
			r = "YT";
		}else if(rang.equals(Raenge.SPIELER)){
			r = "SPIELER";
		}
		
		return r;
	}
	
	public static boolean isPremium(Player p){
		boolean premium = false;
		
		try {
			URL url = new URL("https://sessionserver.mojang.com/session/minecraft/hasJoined?username=" + p.getName() + "&serverId=hash");
			String pr = new BufferedReader(new InputStreamReader(url.openStream())).readLine().toUpperCase();
			premium = Boolean.valueOf(pr);
		} catch (MalformedURLException e) {			
			e.printStackTrace();
		} catch (IOException e) {			
			e.printStackTrace();
		}
		
		return premium;
	}

}
