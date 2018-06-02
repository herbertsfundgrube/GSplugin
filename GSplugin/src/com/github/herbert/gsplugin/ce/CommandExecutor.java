package com.github.herbert.gsplugin.ce;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class CommandExecutor extends JavaPlugin {
	
	//onCommand - Ausgabe "false", wenn ein Fehler beim Ausführen des Befehls aufgetreten ist.
	//
	//
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(args[0].equalsIgnoreCase("hallo")) {
			sender.sendMessage("Hallo auch!");
		}
		return true;
	}
}
