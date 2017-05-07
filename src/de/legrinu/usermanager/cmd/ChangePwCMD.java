package de.legrinu.usermanager.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.legrinu.usermanager.utils.Manager;

public class ChangePwCMD implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
		
		if(sender instanceof Player){
			Player p = (Player) sender;
			
				if(cmd.getName().equals("changepw")){
					if(args.length == 3){
						
					String oldpw = args[0];
					String pw1 = args[1];
					String pw2 = args[2];
					
					if(Manager.isLoggedIn(p)){
					if(oldpw.equals(Manager.getPW(p))){
						if(pw1.equals(pw2)){
							if(!oldpw.equals(pw1)){
							Manager.setPW(pw1, p);
							p.sendMessage("§5[UM]§7 Dein Passwort wurde erfolgreich geändert!");
							p.sendMessage("§5[UM]§7 Rejoine, damit die Änderungen wirken.");
							}
						}
					}else{
						p.sendMessage("§5[UM]§7 Falsches Passwort!");
					}						
					}
				}else{
					sender.sendMessage("§5[UM]§7 /changepw <Altes Passwort> <Neues Passwort> <Neues Passwort>");
				}
			}
		}else{
	sender.sendMessage("Du musst ein Spieler sein, um dies machen zu dürfen.");
}
		return true;
	}

}
