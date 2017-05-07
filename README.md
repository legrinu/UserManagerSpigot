Dieses Plugin ist ein Login Plugin für cracked Server, um ihre User besser überblicken zu können.
Der Spieler muss sich beim ersten Join mit /register <Passwort> <Passwort> registrieren.
Danach und bei allem darauffolgendem Betreten des Servers kann sich der Spieler mit /login <Passwort> einloggen.

Alle Befehle:

Befehl - Beschreibung - Permission

/login <Passwort> - Mit diesem Befehl wird man eingelogged. - keine

/register <Passwort> <Passwort> - Mit diesem Befehl registriert man sich. - keine

/changepw <altes Passwort> <neues Passwort> <neues Passwort> - Mit diesem Befehl ändert man sein Passwort. - keine

/changerang <Spieler> <Rang> - Mit diesem Befehl ändert man den Rang des angegeben Spielers. - usermanager.cmd.changerang

/unregister <Spieler> - Mit diesem Befehl, den man nur von der Konsole ausführen kann, löscht man einen Spieler aus der Datenbank - keine 

Folgende Ränge gibt es:

OWNER, DEV, MOD, SUP, BUILDER, YT, SPIELER
[Die Ränge werden in weiteren Versionen eine Funktion erhalten.]

Das Plugin sendet immer in die Konsole, sobald sich ein Spieler registriert, einlogged, auslogged, sein Passwort ändert und sein Rang geändert wird.

Das gesamte Plugin funktioniert nur zusammen mit MySQL. Die Daten dazu trägt man in der Config ein, die beim ersten Starten des Servers mit dem Plugin erstellt wird.

Standard config.yml:

'--: MySQL': --

Host: localhost

Port: 3306

Database: ExampleDatabase

Username: ExampleUsername

Passwort: ExamplePasswort
