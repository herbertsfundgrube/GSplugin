package com.github.herbert.gsplugin;

import org.bukkit.plugin.java.JavaPlugin;

import com.github.herbert.gsplugin.ce.GSCommandExecutor;
import com.github.herbert.gsplugin.datenstruktur.Gslist;
import com.github.herbert.gsplugin.events.BlockEventListener;

import net.md_5.bungee.api.ChatColor;

public class GSplugin extends JavaPlugin {
	
	private GSCommandExecutor ce;
	public Gslist gslist;
	
	public void onEnable() {
		registerCommandExecutors();
		registerListeners();
	}
	
	public void onDisable() {
		this.getLogger().info("GSplugin deaktiviert");
	}
	
	
	public String convMessage(String input) {
		return "["+ChatColor.DARK_GRAY + this.getName()+ ChatColor.RESET + "] " + input;
	}
	
	//TODO
	public String getConfigString(String key) {
		return null;
	}
	//TODO
	public int getConfigInt(String key) {
		
		//Bis die Config eingebunden ist werden die Werte hardcoded.
		if(key.equals("gs.lowestProtectedY"))
			return 10;
		
		
		//Ende Hardcode
		return 0;
	}
	
	private void registerListeners() {
		getServer().getPluginManager().registerEvents(new BlockEventListener(this), this);
	}
	
	private void registerCommandExecutors() {
		//CE für den Befehl /gs
		ce = new GSCommandExecutor(this);
		getCommand("gs").setExecutor(ce);
	}
}
