package com.github.herbert.gsplugin;

import org.bukkit.plugin.java.JavaPlugin;

import com.github.herbert.gsplugin.ce.GSCommandExecutor;

public class GSplugin extends JavaPlugin {
	
	private GSCommandExecutor ce;
	
	public void onEnable() {
		ce = new GSCommandExecutor(this);
		getCommand("gs").setExecutor(ce); //CommandExecutor für Befehl "/gs" setzen
	}
	
	public void onDisable() {
		this.getLogger().info("GSplugin deaktiviert");
	}
	
	
	//TODO
	public String getConfigString(String key) {
		return null;
	}
	//TODO
	public int getConfigInt(String key) {
		
		//Bis die Config eingebunden ist werden die Werte hardcoded.
		if(key.equals("gs.lowestBlockInt"))
			return 10;
		
		
		//Ende Hardcode
		return 0;
	}
}
