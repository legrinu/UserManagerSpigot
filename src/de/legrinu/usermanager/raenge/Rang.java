package de.legrinu.usermanager.raenge;

import java.util.ArrayList;

public class Rang {
	
	Raenge rang;
	ArrayList<String> perms;
	
	public Rang(Raenge rang, ArrayList<String> perms){
		this.rang = rang;
		this.perms = perms;
	}
	
	public void addPerm(String perm){
		if(!perms.contains(perm)){
		   perms.add(perm);
		}
	}
	
	public String getPerms(){
		
		for(int i= 0; i < perms.size();){
			return perms.get(i);
		}
		
		return null;
	}

}
