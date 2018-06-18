package com.github.herbert.playerplugin;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.herbert.playerplugin.playerdata.HerbertPlayer;

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
			HerbertPlayer hp = plugin.data.getPlayer((Player)sender);
			if(args.length == 1) {
				sender.sendMessage(hp.getPlayerInfo());
				return true;
			}
			if(args.length>1) {
				sender.sendMessage(hp.getSkillInfo(args[1]));
				return true;
			}
			
		}
		
		
		if(args.length>1) {
			sender.sendMessage(showHelp());
			return true;
		}
		sender.sendMessage(showHelp());
		return true;
	}
	
	public String showHelp() {
		String help = PlayerPlugin.getHeader();
		help+="\n"+ChatColor.AQUA+"/player stats "+ChatColor.RESET +"- zeigt Spielerstatistiken";
		help+="\n"+ChatColor.AQUA+"/player stats [Fähigkeit] "+ChatColor.RESET +"- zeigt Fähigkeitsstatistiken";
		return help;
	}

}
