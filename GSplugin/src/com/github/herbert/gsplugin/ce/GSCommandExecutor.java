package com.github.herbert.gsplugin.ce;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.herbert.gsplugin.GSplugin;
import com.github.herbert.gsplugin.GS.GS;
import com.github.herbert.gsplugin.datenstruktur.GSinteractor.TempHerbertPlayer;

public class GSCommandExecutor implements org.bukkit.command.CommandExecutor {
	
	//CommandExecutor für den Befehl /gs
	//Variable für den Zugriff auf die Hauptklasse
	GSplugin plugin;
	
	public GSCommandExecutor(GSplugin plugin) {
		this.plugin = plugin;
	}
	
	//onCommand - Ausgabe "false", wenn ein Fehler beim Ausführen des Befehls aufgetreten ist.
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(args.length == 0)
			return false;
		if(args[0].equalsIgnoreCase("test")) {
			sender.sendMessage(plugin.convMessage("Du hast den Befehl /gs test eingegeben."));
			return true;
		}
		
		//GS kaufen
		if(args[0].equalsIgnoreCase("buy")) {
			//Wenn den Sender kein Spieler ist, abbrechen
			if(! (sender instanceof Player) ) {
				sender.sendMessage(plugin.convMessage("Dies ist ein Spielerbefehl!"));
				return true;
			}
			
			Player p = (Player) sender;
			TempHerbertPlayer tempPlayer = new TempHerbertPlayer(p.getUniqueId(),("Herbert"+p.getLocation().getBlockY()+"/"+p.getEyeLocation().getBlockX()));
                        plugin.addGSint(tempPlayer);
			GS gs = new GS (tempPlayer, p.getLocation());
			plugin.addGS(gs);
			
			return true;
		}
		if(args[0].equalsIgnoreCase("remove")) {
			if(! (sender instanceof Player))
				return true;
			Player p = (Player) sender;
			GS gs = plugin.gslist.getGS(p.getLocation());
			if(gs == null) {
				sender.sendMessage(plugin.convMessage("Du stehst auf keinem Grundstück."));
				return true;
			}
			//Spieler muss auf dem aktuellen GS die Permission "GS verwalten" haben
			if(!gs.hasPermission(p, (byte) 16)) {
				sender.sendMessage(plugin.convMessage("Du hast nicht die notwendige Berechtigung, um dieses GS zu entfernen."));
				return true;
			}
			//GS entfernen.
			plugin.removeGS(gs);
			return true;
			
		}
		
		
		sender.sendMessage(plugin.convMessage("Der eingegebene Befehl ist ungültig."));
		return true;
	}
}
