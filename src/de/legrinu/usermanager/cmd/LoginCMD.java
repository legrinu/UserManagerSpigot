package de.legrinu.usermanager.cmd;

import de.legrinu.usermanager.UserManager;
import de.legrinu.usermanager.utils.Decryption;
import de.legrinu.usermanager.utils.Manager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class LoginCMD
  implements CommandExecutor
{
  public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args)
  {
    if ((sender instanceof Player))
    {
      Player p = (Player)sender;
      if (cmd.getName().equals("login")) {
        if (args.length == 1)
        {
          String pw = args[0];
          Boolean valid = false;
          try
          {
            valid = Decryption.validatePassword(pw, Manager.getPW(p));
          }
          catch (NoSuchAlgorithmException | InvalidKeySpecException e)
          {
            e.printStackTrace();
          }
          if (valid != null)
          {
            if (valid)
            {
              Manager.login(p);
              p.sendMessage(UserManager.prefix() + "Du wurdest erfolgreich eingeloggt und kannst dich nun bewegen.");
            }
            else
            {
              p.sendMessage(UserManager.prefix() + "Falsches Passwort!");
            }
          }
          else
          {
            System.err.println("Error while encrypting! PW: " + valid);
            p.sendMessage(UserManager.prefix() + "Aufgrund eines Fehlers konntest du nicht registriert werden. Melde dich bitte bei einem Teammitglied.");
          }
        }
        else
        {
          sender.sendMessage(UserManager.prefix() + "/login <Dein Passwort>");
        }
      }
    }
    else
    {
      sender.sendMessage("Du musst ein Spieler sein, um dies machen zu dürfen.");
    }
    return true;
  }
}
