package de.legrinu.usermanager.raenge;

import java.util.ArrayList;

public class ADMIN {
	
	static ArrayList<String> perms = new ArrayList<>();
	
	public static void createRang(){
		Rang owner = new Rang(Raenge.OWNER, perms);
		
		owner.addPerm("um.cmd.um.rl");
		owner.addPerm("um.cmd.um.help");
		owner.addPerm("um.cmd.users");
		owner.addPerm("um.cmd.um.changerang");
		owner.addPerm("um.login.bypass");
		
	}

}
