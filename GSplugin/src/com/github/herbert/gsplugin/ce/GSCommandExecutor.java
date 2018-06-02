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
		sender.sendMessage("Du hast den Befehl /gs eingegeben, vielleicht mit irgendwelchen Argumenten.");
		return false;
	}
}
