package de.legrinu.usermanager.utils;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import de.legrinu.usermanager.UserManager;

public class EventManager
  implements Listener
{
  @EventHandler
  public void onJoin(PlayerJoinEvent e)
  {
    Player p = e.getPlayer();
    
    //System.out.println("Is Premium: " + p.getName() + " | " + Manager.isPremium(p));
    
    
    if (!p.hasPermission("usermanager.login.bypass")) {
      if (Manager.isRegistered(p)) {
        p.sendMessage(UserManager.prefix() + "Bitte logge dich ein mit /login <Dein Passwort>");
      } else {
        p.sendMessage(UserManager.prefix() + "Bitte registriere dich mit /register <Dein Passwort> <Dein Passwort>");
      }
    }
  }
  
  @EventHandler
  public void onQuit(PlayerQuitEvent e)
  {
    Player p = e.getPlayer();
    if (!p.hasPermission("usermanager.login.bypass")) {
      Manager.logout(p);
    }
  }
  
  @EventHandler
  public void onMove(PlayerMoveEvent e)
  {
    Player p = e.getPlayer();
    if ((!p.hasPermission("usermanager.login.bypass")) && 
      (!Manager.isLoggedIn(p))) {
      p.teleport(e.getFrom());
    }
  }
}
