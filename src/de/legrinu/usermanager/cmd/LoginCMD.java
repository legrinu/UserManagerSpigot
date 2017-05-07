package de.legrinu.usermanager.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.legrinu.usermanager.utils.Manager;

public class LoginCMD implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
		
		if(sender instanceof Player){
			Player p = (Player) sender;
		
				if(cmd.getName().equals("login")){
					if(args.length == 1){
						
					String pw = args[0];
					
					if(pw.equals(Manager.getPW(p))){
						Manager.login(p);
						p.sendMessage("§5[UM]§7 Du wurdest erfolgreich eingeloggt und kannst dich nun bewegen.");
					}else{
						p.sendMessage("§5[UM]§7 Falsches Passwort!");
					}
				}else{
					sender.sendMessage("§5[UM]§7 /login <Dein Passwort>");
				}
			}
		}else{
	sender.sendMessage("Du musst ein Spieler sein, um dies machen zu dürfen.");
}
		return true;
	}

}
