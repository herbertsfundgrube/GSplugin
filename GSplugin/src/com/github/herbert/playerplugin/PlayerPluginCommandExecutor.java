package com.github.herbert.playerplugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PlayerPluginCommandExecutor implements CommandExecutor {
	
	private PlayerPlugin plugin;
	public PlayerPluginCommandExecutor(PlayerPlugin plugin) {
		this.plugin = plugin;
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if( ! (sender instanceof Player))
			return true;
		if(args.length==0) {
			sender.sendMessage(showHelp());
			return true;
		}
		
		if(args[0].equalsIgnoreCase("stats")) {
			plugin.data.getPlayer((Player)sender);
		}
		
		
		if(args.length>1) {
			sender.sendMessage(showHelp());
			return true;
		}
		return false;
	}
	
	public String showHelp() {
		return "Hilfetext";
	}
	
	public String formatStats(String stat) {
		String form = "";
		
		return form;
	}

}
