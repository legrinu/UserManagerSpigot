package de.legrinu.usermanager.cmd;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.legrinu.usermanager.UserManager;
import de.legrinu.usermanager.utils.Manager;

public class GetUsersCMD implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
		
		if(sender instanceof Player){
			Player p = (Player) sender;
			
				if(cmd.getName().equals("users")){
					if(p.hasPermission("usermanager.cmd.users")){
					if(args.length == 0){
						
					p.sendMessage(UserManager.prefix() + "Aktuell registrierte Benutzer: §6" + Manager.count());
					p.sendMessage(UserManager.prefix() + "Aktuelle Onlinezahl: §6" + Bukkit.getOnlinePlayers().size());
			}
		}
		}else{
	        sender.sendMessage("Du musst ein Spieler sein, um dies machen zu dürfen.");
       }
		}
		return true;
	}

}
