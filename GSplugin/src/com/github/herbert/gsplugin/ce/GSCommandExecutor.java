package com.github.herbert.gsplugin.ce;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.github.herbert.gsplugin.GSplugin;

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
		if(args[0].equalsIgnoreCase("test2")) {
			sender.sendMessage("Du hast den Befehl /gs test2 eingegeben.");
			return true;
		}
		sender.sendMessage("Da ist etwas schiefgelaufen.");
		return false;
	}
}
