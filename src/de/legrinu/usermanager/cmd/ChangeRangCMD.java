package de.legrinu.usermanager.cmd;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.legrinu.usermanager.utils.Manager;

public class ChangeRangCMD implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
		
		if(sender instanceof Player){
			Player p = (Player) sender;
			
				if(cmd.getName().equals("changerang")){
					if(p.hasPermission("usermanager.cmd.changerang")){
					if(args.length == 2){
						
					String player = args[0];
					String rang = args[1];
					
					for(Player op : Bukkit.getOnlinePlayers()){
						if(op.getName().equals(player)){
							Manager.setRang(rang, op);
						}
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

