package com.github.herbert.worldblocksplugin.ce;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.herbert.worldblocksplugin.WorldBlocksPlugin;
import com.github.herbert.worldblocksplugin.GSinteractor.TempHerbertPlayer;
import com.github.herbert.worldblocksplugin.worldblocks.GS;

import net.md_5.bungee.api.ChatColor;

public class GSCommandExecutor implements org.bukkit.command.CommandExecutor {
	
	//CommandExecutor für den Befehl /gs
	//Variable für den Zugriff auf die Hauptklasse
	WorldBlocksPlugin plugin;
	
	public GSCommandExecutor(WorldBlocksPlugin plugin) {
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
		//gs löschen
		if(args[0].equalsIgnoreCase("remove")) {
			if(! (sender instanceof Player))
				return true;
			
			Player p = (Player) sender;
			GS gs = plugin.getGSList().getGS(p.getLocation());
			if(gs == null) {
				sender.sendMessage(plugin.convMessage("Du stehst auf keinem Grundstück."));
				return true;
			}
			//Spieler muss auf dem aktuellen GS die Permission "GS verwalten" haben
			if(!gs.hasPermission(p, (byte) 16) && !p.isOp()) {
				sender.sendMessage(plugin.convMessage("Du hast nicht die notwendige Berechtigung, um dieses GS zu entfernen."));
				return true;
			}
			//GS entfernen.
			plugin.removeGS(gs);
			return true;
			
		}
		
		//gs info
		if(args[0].equalsIgnoreCase("info")) {
			if(!(sender instanceof Player))
				return true;
			
			Player p = (Player)sender;
			if(!plugin.isOnGs(p.getLocation())) {
				sender.sendMessage(plugin.convMessage("Du stehst auf keinem Grundstück."));
				return true;
			}
			GS gs = plugin.getGSList().getGS(p.getLocation());
			sender.sendMessage(plugin.getHeader());
			for(String inf : gs.getInfo()) {
				sender.sendMessage(inf);
			}
			return true;
		}
		
		//Hilfe anzeigen
		sender.sendMessage(plugin.getHeader());
		sender.sendMessage(ChatColor.AQUA+"/gs buy " + ChatColor.RESET + " - aktuellen Chunk erwerben");
		sender.sendMessage(ChatColor.AQUA+"/gs sell " + ChatColor.RESET + " - aktuellen Chunk verkaufen");
		sender.sendMessage(ChatColor.AQUA+"/gs info " + ChatColor.RESET + " - Informationen zum aktuellen Chunk anzeigen");
		return true;
	}
}
