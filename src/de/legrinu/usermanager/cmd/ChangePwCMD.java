package de.legrinu.usermanager.cmd;

import de.legrinu.usermanager.UserManager;
import de.legrinu.usermanager.utils.Decryption;
import de.legrinu.usermanager.utils.Encryption;
import de.legrinu.usermanager.utils.Manager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class ChangePwCMD
  implements CommandExecutor
{
  public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args)
  {
    if ((sender instanceof Player))
    {
      Player p = (Player)sender;
      if (cmd.getName().equals("changepw")) {
        if (args.length == 3)
        {
          String oldpw = args[0];
          String pw1 = args[1];
          String pw2 = args[2];
          if (Manager.isLoggedIn(p))
          {
            Boolean oldcrypt = null;
            try
            {
              oldcrypt = Decryption.validatePassword(oldpw, Manager.getPW(p));
            }
            catch (NoSuchAlgorithmException | InvalidKeySpecException e)
            {
              e.printStackTrace();
            }

            if ((oldcrypt != null))
            {
              if (oldcrypt)
              {
                if ((pw1.equals(pw2)) && 
                  (!oldpw.equals(pw1)))
                {
                  String crypt = null;
                  try
                  {
                    crypt = Encryption.generateStrongPasswordHash(pw1);
                  }
                  catch (NoSuchAlgorithmException | InvalidKeySpecException e)
                  {
                    e.printStackTrace();
                  }
                  Manager.setPW(crypt, p);
                  p.sendMessage(UserManager.prefix() + "Dein Passwort wurde erfolgreich geändert!");
                  p.sendMessage(UserManager.prefix() + "Rejoine, damit die Änderungen wirken.");
                }
              }
              else {
                p.sendMessage(UserManager.prefix() + "Falsches Passwort!");
              }
            }
            else
            {
              //System.err.println("Error while encrypting! OldPW: " + oldcrypt + " NewPW: " + crypt);
              p.sendMessage(UserManager.prefix() + "Aufgrund eines Fehlers konntest du nicht registriert werden. Melde dich bitte bei einem Teammitglied.");
            }
          }
        }
        else
        {
          sender.sendMessage(UserManager.prefix() + "/changepw <Altes Passwort> <Neues Passwort> <Neues Passwort>");
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
