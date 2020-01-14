package de.legrinu.usermanager;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import de.legrinu.usermanager.cmd.ChangePwCMD;
import de.legrinu.usermanager.cmd.ChangeRangCMD;
import de.legrinu.usermanager.cmd.GetUsersCMD;
import de.legrinu.usermanager.cmd.LoginCMD;
import de.legrinu.usermanager.cmd.RegisterCMD;
import de.legrinu.usermanager.cmd.UMCMD;
import de.legrinu.usermanager.cmd.UnregisterCMD;
import de.legrinu.usermanager.mysql.MySQL;
import de.legrinu.usermanager.utils.EventManager;

public class UserManager extends JavaPlugin{
	
	static String pr;
		
	@Override
	public void onEnable(){
		loadConfig();
		
		MySQL.connect();		
		MySQL.update("CREATE TABLE IF NOT EXISTS user (NAME VARCHAR(100), PASSWORT VARCHAR(256), RANG VARCHAR(50))");
		
		System.out.println("[UM] Userdaten werden erfasst.");
		
		Bukkit.getServer().getPluginManager().registerEvents(new EventManager(),  this);
		regCMD();
	}
	
	@Override
	public void onDisable(){
		
		if(MySQL.isConnected()){
			MySQL.disconnect();
		}
		
		System.out.println("[UM] Erfassen von Userdaten beendet.");
	}
	
	private void regCMD(){
		this.getCommand("login").setExecutor(new LoginCMD());
		this.getCommand("register").setExecutor(new RegisterCMD());
		this.getCommand("changepw").setExecutor(new ChangePwCMD());
		this.getCommand("unregister").setExecutor(new UnregisterCMD());
		this.getCommand("changerang").setExecutor(new ChangeRangCMD());
		this.getCommand("users").setExecutor(new GetUsersCMD());
		this.getCommand("um").setExecutor(new UMCMD(this));
	}
	
	private void loadConfig(){		
		getConfig().addDefault("--: MySQL", "--");
		getConfig().addDefault("Host", "localhost");
		getConfig().addDefault("Port", 3306);
		getConfig().addDefault("Database", "ExampleDatabase");
		getConfig().addDefault("Username", "ExampleUsername");
		getConfig().addDefault("Passwort", "ExamplePasswort");	
		getConfig().addDefault("--: Plugin", "--");
		getConfig().addDefault("Prefix", "UM");
						
		
		getConfig().options().copyDefaults(true);
		saveConfig();
	}	
	
	public static String prefix(){
		return pr = "§5[" + UserManager.getPlugin(UserManager.class).getConfig().getString("Prefix") + "]§7 ";
	}

}
