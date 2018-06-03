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
	
	
	//TODO:
	//Folgende Configeinträge müssen hinzugefügt werden:
	//gs.lowestYBlock (int)
	//
	public String getConfigString(String key) {
		return null;
	}
	//TODO
	public int getConfigInt(String key) {
		return 0;
	}
}
