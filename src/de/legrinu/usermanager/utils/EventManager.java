package de.legrinu.usermanager.utils;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class EventManager
  implements Listener
{
  @EventHandler
  public void onJoin(PlayerJoinEvent e)
  {
    Player p = e.getPlayer();
    if (!p.hasPermission("usermanager.login.bypass")) {
      if (Manager.isRegistered(p)) {
        p.sendMessage("§5[UM]§7 Bitte logge dich ein mit /login <Dein Passwort>");
      } else {
        p.sendMessage("§5[UM]§7 Bitte registriere dich mit /register <Dein Passwort> <Dein Passwort>");
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
