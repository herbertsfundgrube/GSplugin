package com.github.herbert.worldblocksplugin.ce;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.herbert.worldblocksplugin.WorldBlocksPlugin;
import com.github.herbert.worldblocksplugin.GSinteractor.TempHerbertPlayer;
import com.github.herbert.worldblocksplugin.worldblocks.GS;

import net.md_5.bungee.api.ChatColor;

public class GSCommandExecutor implements org.bukkit.command.CommandExecutor {
	
	//-----------------------------------
	//-----------------------------------
	//-------CE für Befehl /gs-----------
	//-----------------------------------
	//-----------------------------------
	WorldBlocksPlugin plugin;
	
	public GSCommandExecutor(WorldBlocksPlugin plugin) {
		this.plugin = plugin;
	}
	
	
	//-----------------------------------
	//-----------------------------------
	//----Befehlsmethode für /gs---------
	//-----------------------------------
	//-----------------------------------
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		//-----------------------------------
		//----Bei Verwendung ohne args-------
		//----wird die Hilfe angezeigt-------
		//-----------------------------------
		if(args.length == 0) {
			showHelp(sender);
			return true;
		}
		
		//-----------------------------------
		//---/gs buy bewirkt, dass das-------
		//---aktuelle GS als Spieler-GS------
		//---gekauft wird. Der Spieler-------
		//---wird als TempHerbertPlayer------
		//---betrachtet Repräsentiert--------
		//---einzelnen Spieler)--------------
		//-----------------------------------
		if(args[0].equalsIgnoreCase("buy")) {
			//Wenn den Sender kein Spieler ist, abbrechen
			if(! (sender instanceof Player) ) {
				sender.sendMessage(plugin.convMessage("Dies ist ein Spielerbefehl!"));
				return true;
			}
			
			Player p = (Player) sender;
			TempHerbertPlayer tempPlayer = new TempHerbertPlayer(p.getUniqueId(),("Herbert"+p.getLocation().getBlockY()+"/"+p.getEyeLocation().getBlockX()));
                        plugin.data.addWorldblockInteractor(tempPlayer);
			GS gs = new GS (tempPlayer, p.getLocation());
			plugin.data.addGS(gs);
			
			return true;
		}
		//-----------------------------------
		//----Mit /gs remove wird das--------
		//----aktuelle GS gelöscht,----------
		//----wenn der Spieler die ----------
		//----nötigen Rechte hat.------------
		//-----------------------------------
		if(args[0].equalsIgnoreCase("remove")) {
			if(! (sender instanceof Player))
				return true;
			
			Player p = (Player) sender;
			GS gs = plugin.data.getGSList().getGS(p.getLocation());
			//-----------------------------------
			//----Spielerbefehl------------------
			//-----------------------------------
			if(gs == null) {
				sender.sendMessage(plugin.convMessage("Du stehst auf keinem Grundstück."));
				return true;}
			//-----------------------------------
			//----Prüfen der Berechtigungen------
			//----(GS-interne perms oder---------
			//----Operator-Rechte----------------
			//-----------------------------------
			if(!gs.hasPermission(p, (byte) 16) && !p.isOp()) {
				sender.sendMessage(plugin.convMessage("Du hast nicht die notwendige Berechtigung, um dieses GS zu entfernen."));
				return true;
			}
			//-----------------------------------
			//----Alle Bedingungen erfüllt,------
			//----GS kann gelöscht werden.-------
			//-----------------------------------
			plugin.data.removeGS(gs);
			return true;
			
		}
		
		//-----------------------------------
		//---/gs info zeigt Informationen----
		//---über das aktuelle GS an, wenn---
		//---vorhanden.----------------------
		//-----------------------------------
		if(args[0].equalsIgnoreCase("info")) {
			//-----------------------------------
			//---Spielerbefehl.------------------
			//-----------------------------------
			if(!(sender instanceof Player))
				return true;
			
			//-----------------------------------
			//---Prüfen der Bedingung:-----------
			//---Dort, wo der Spieler------------
			//---steht, muss ein GS sein---------
			//-----------------------------------
			Player p = (Player)sender;
			if(!plugin.data.isOnGs(p.getLocation())) {
				sender.sendMessage(plugin.convMessage("Du stehst auf keinem Grundstück."));
				return true;
			}
			//-----------------------------------
			//---Dem Spieler werden die----------
			//---Überschrift des GSPlugins-------
			//---sowie alle Informationen--------
			//---über das GS ausgegeben.---------
			//---Die Infos sind in einem---------
			//---Array gespeichert.--------------
			//-----------------------------------
			GS gs = plugin.data.getGSList().getGS(p.getLocation());
			sender.sendMessage(plugin.getHeader());
			for(String inf : gs.getInfo()) {
				sender.sendMessage(inf);
			}
			return true;
		}
		//-----------------------------------
		//---Alle übrigen Befehle mit--------
		//---einem einzigen Argument---------
		//---sind ungültig, daher wird-------
		//---in diesem Fall die Hilfe--------
		//---angezeigt.----------------------
		//-----------------------------------
		if(args.length == 1) {
			showHelp(sender);
			return true;
		}
		//Hilfe anzeigen
		showHelp(sender);
		return true;
	}
	//-----------------------------------
	//---Anzeige der Hilfe. Wird---------
	//---hardcoded und muss für----------
	//---jeden neuen Befehl in-----------
	//---dieser Methode angepasst--------
	//---werden.-------------------------
	//-----------------------------------
	private void showHelp(CommandSender sender) {
		sender.sendMessage(plugin.getHeader());
		sender.sendMessage(helpFormat("buy","Aktuellen Chunk erwerben"));
		sender.sendMessage(helpFormat("sell","Aktuellen Chunk verkaufen"));
		sender.sendMessage(helpFormat("info","Informationen über den aktuellen Chunk"));
	}
	//-----------------------------------
	//---Formatiert die Hilfe zu---------
	//---einem Befehl--------------------
	//-----------------------------------
	private String helpFormat(String args, String helptext) {
		return ChatColor.AQUA+"/gs "+args+ChatColor.RESET+" -"+helptext;
	}
}
