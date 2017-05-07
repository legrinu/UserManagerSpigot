package de.legrinu.usermanager.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import de.legrinu.usermanager.UserManager;


public class MySQL {
	
	private static String host = UserManager.getPlugin(UserManager.class).getConfig().getString("Host");
	private static int port = UserManager.getPlugin(UserManager.class).getConfig().getInt("Port");
	private static String database = UserManager.getPlugin(UserManager.class).getConfig().getString("Database");
	private static String username = UserManager.getPlugin(UserManager.class).getConfig().getString("Username");
	private static String pw = UserManager.getPlugin(UserManager.class).getConfig().getString("Passwort");
	
	private static Connection con;
	
	public static boolean connect(){
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, username, pw);
			System.out.println("[MySQL] Connected.");
			return true;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		
		return false;
	}
	
	public static Connection getConnection(){
		return con;
	}
	
	public static boolean isConnected(){
		return con != null;
	}
	
public static void update(String s){
		
		try {
			PreparedStatement ps = con.prepareStatement(s);
			ps.executeUpdate();
		} catch (SQLException e) {			
			e.printStackTrace();
		}
	}
	
	public static boolean existsUser(String name){
		
		if(!isConnected()){				
			if(!connect()){					
				return false;
			}	
		}
					
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM user WHERE NAME = ?");				
			ps.setString(1, name);
			
			ResultSet rs = ps.executeQuery();	
			
			return rs.next();
			
		} catch (SQLException e) {			
			e.printStackTrace();			
		}		
				
		return false;
	}
	
	public static boolean createUser(String name, String pw, String rang){
		
		if(!isConnected()){
			if(!connect()){
				return false;
			}
		}
			
			try {
				PreparedStatement ps = con.prepareStatement("INSERT INTO user (NAME,PASSWORT,RANG) VALUES (?,?,?)");
											
				ps.setString(1, name);
				ps.setString(2, pw);
				ps.setString(3, rang);
				
				ps.executeUpdate();
				
			} catch (SQLException e) {				
				e.printStackTrace();
			}			
		
		
		return false;
	}
	
public static void updatePW(String name, String pw){
		
		if(!isConnected())
			if(!connect())
				return;
			
			
			if(!existsUser(name))
				if(!createUser(name, pw, null))
					return;
			
			try {
				PreparedStatement ps = con.prepareStatement("UPDATE user SET PASSWORT = ? WHERE NAME = ?");
				ps.setString(1, pw);
				ps.setString(2, name);
				
				ps.executeUpdate();
			} catch (SQLException e) {				
				e.printStackTrace();
			}
	}

public static void updateRang(String name, String rang){
	
	if(!isConnected())
		if(!connect())
			return;
		
		
		if(!existsUser(name))
			if(!createUser(name, null, rang))
				return;
		
		try {
			PreparedStatement ps = con.prepareStatement("UPDATE user SET RANG = ? WHERE NAME = ?");
			ps.setString(1, rang);
			ps.setString(2, name);
			
			ps.executeUpdate();
		} catch (SQLException e) {				
			e.printStackTrace();
		}
}

public static String getPW(String name){
	
	if(!isConnected()){				
		if(!connect()){					
			return null;
		}	
	}
				
	try {
		PreparedStatement ps = con.prepareStatement("SELECT PASSWORT FROM user WHERE NAME = ?");				
		ps.setString(1, name);
		
		ResultSet rs = ps.executeQuery();
		rs.first();
		
		return rs.getString("PASSWORT");
		
	} catch (SQLException e) {			
		e.printStackTrace();			
	}		
			
	return null;
}

public static String getRang(String name){
	
	if(!isConnected()){				
		if(!connect()){					
			return null;
		}	
	}
				
	try {
		PreparedStatement ps = con.prepareStatement("SELECT RANG FROM user WHERE NAME = ?");				
		ps.setString(1, name);
		
		ResultSet rs = ps.executeQuery();
		rs.first();
		
		return rs.getString("RANG");
		
	} catch (SQLException e) {			
		e.printStackTrace();			
	}		
			
	return null;
}

public static boolean removeUser(String name){
	
	if(!isConnected()){				
		if(!connect()){					
			return false;
		}	
	}
	
	if(!existsUser(name)){
		return false;
	}
	

	try {
		PreparedStatement ps = con.prepareStatement("DELETE FROM user WHERE NAME = ?");
									
		ps.setString(1, name);
		
		ps.executeUpdate();			
				
	} catch (SQLException e) {				
		e.printStackTrace();
	}		
	
	return false;
}
	
	public static void disconnect() {
		try {
			con.close();
		} catch (SQLException e) {			
			e.printStackTrace();
		}		
	}

}
