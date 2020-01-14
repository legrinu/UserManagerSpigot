package de.legrinu.usermanager.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.legrinu.usermanager.UserManager;

public class UMCMD implements CommandExecutor {
	
	UserManager main;
	
	public UMCMD(UserManager main){
		this.main = main;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
		
		if(sender instanceof Player){
			Player p = (Player) sender;
			
				if(cmd.getName().equals("um")){
					if(args.length == 1){
						if(args[0].equals("rl")){
					if(p.hasPermission("usermanager.cmd.um.rl")){					
						main.reloadConfig();
						p.sendMessage(UserManager.prefix() + "Die Config wurde neu geladen!");
					}
				}
				if(args[0].equals("help")){
					if(p.hasPermission("usermanager.cmd.um.help")){
						
						p.sendMessage("§7-----§5UserManager Hilfe§7-----");
						p.sendMessage("§5- /register <PW> <PW> - Registriert dich mit dem angegebenen Passwort.");
						p.sendMessage("§5- /login <PW> - Loggt dich mit deinem Passwort ein.");
						p.sendMessage("§5- /changepw <AltesPW> <NeuesPW> <NeuesPW> - Ändert dein Passwort zu dem neuen.");
						p.sendMessage("§5- /changerang <Spieler> <Rang> - Ändert den Rang des angegeben Spielers.");
						p.sendMessage("§5- /users - Zeigt die Anzahl der registrierten und aktuellen Online Usern.");
						p.sendMessage("§5- /um rl - Reloaded die Plugin Config.");
						p.sendMessage("§5- /unregister <Player> - Löscht den angegeben Spieler aus der Datenbank. §4[Only Console]");
				}
				}
			}
		}
		}else{
	        sender.sendMessage("Du musst ein Spieler sein, um dies machen zu dürfen.");
       }
		return true;
	}

}
