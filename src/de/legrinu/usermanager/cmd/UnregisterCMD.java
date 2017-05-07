package de.legrinu.usermanager.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

import de.legrinu.usermanager.utils.Manager;

public class UnregisterCMD implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
		
		if(sender instanceof ConsoleCommandSender){
			if(cmd.getName().equals("unregister")){
				if(args.length == 1){
					Manager.unregister(args[0]);
				}
			}
		}
		
		return true;
	}

}
