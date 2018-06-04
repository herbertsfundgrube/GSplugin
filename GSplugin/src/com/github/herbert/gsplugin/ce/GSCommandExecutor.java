package com.github.herbert.gsplugin.ce;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.herbert.gsplugin.GSplugin;
import com.github.herbert.gsplugin.events.BlockEventListener;

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
			sender.sendMessage("Du hast den Befehl /gs test eingegeben.");
			return true;
		}
		
		//GS kaufen
		if(args[0].equalsIgnoreCase("buy")) {
			//Wenn den Sender kein Spieler ist, abbrechen
			if(!(sender instanceof Player)) {
				sender.sendMessage("Dies ist ein Spielerbefehl!");
				return true;
			}
			
			
			GSBuyThread gsbt =new GSBuyThread((Player) sender);
			plugin.getServer().getPluginManager().registerEvents(gsbt, plugin);
			do {
				
			} while(gsbt.isAlive());
			return true;
		}
		
		
		sender.sendMessage("Da ist etwas schiefgelaufen.\nBitte Teil uns mit, was Du gerade getan hast.");
		return false;
	}
}
