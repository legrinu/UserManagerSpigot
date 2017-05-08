package de.legrinu.usermanager.cmd;

import de.legrinu.usermanager.utils.Encryption;
import de.legrinu.usermanager.utils.Manager;
import java.security.NoSuchAlgorithmException;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

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
            String oldcrypt = null;
            try
            {
              oldcrypt = Encryption.encrypt(oldpw);
            }
            catch (NoSuchAlgorithmException e)
            {
              e.printStackTrace();
            }
            String crypt = null;
            try
            {
              crypt = Encryption.encrypt(pw1);
            }
            catch (NoSuchAlgorithmException e)
            {
              e.printStackTrace();
            }
            if ((crypt != null) && (oldcrypt != null))
            {
              if (oldcrypt.equals(Manager.getPW(p)))
              {
                if ((pw1.equals(pw2)) && 
                  (!oldpw.equals(pw1)))
                {
                  Manager.setPW(crypt, p);
                  p.sendMessage("§5[UM]§7 Dein Passwort wurde erfolgreich geändert!");
                  p.sendMessage("§5[UM]§7 Rejoine, damit die Änderungen wirken.");
                }
              }
              else {
                p.sendMessage("§5[UM]§7 Falsches Passwort!");
              }
            }
            else
            {
              System.err.println("Error while encrypting! OldPW: " + oldcrypt + " NewPW: " + crypt);
              p.sendMessage("§5[UM]§7 Aufgrund eines Fehlers konntest du nicht registriert werden. Melde dich bitte bei einem Teammitglied.");
            }
          }
        }
        else
        {
          sender.sendMessage("§5[UM]§7 /changepw <Altes Passwort> <Neues Passwort> <Neues Passwort>");
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
