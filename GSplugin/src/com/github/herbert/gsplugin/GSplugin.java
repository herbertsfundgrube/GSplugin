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
	
	public String getConfigEntry(String key) {
		return null;
	}
}
