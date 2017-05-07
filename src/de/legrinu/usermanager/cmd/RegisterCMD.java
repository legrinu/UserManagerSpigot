package de.legrinu.usermanager.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.legrinu.usermanager.utils.Manager;
import de.legrinu.usermanager.utils.Rang;

public class RegisterCMD implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
		
		if(sender instanceof Player){
			Player p = (Player) sender;			
			
				if(cmd.getName().equals("register")){
					if(args.length == 2){
					String pw1 = args[0];
					String pw2 = args[1];
					p.sendMessage(pw1 + "|" + pw2);
					
					if(pw1.equals(pw2)){
						Manager.register(p, pw1, Rang.SPIELER);
						p.sendMessage("§5[UM]§7 Du wurdest erfolgreich registriert!");
						p.sendMessage("§5[UM]§7 Bitte logge dich mit /login <Dein Passwort> ein!");
					}
				}else{
					sender.sendMessage("§5[UM]§7 /register <Dein Passwort> <Dein Passwort>");
				}
				}
				}else{
	sender.sendMessage("Du musst ein Spieler sein, um dies machen zu dürfen.");
}
		return true;
	}

}
