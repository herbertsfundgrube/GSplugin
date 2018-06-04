package com.github.herbert.gsplugin;

import org.bukkit.plugin.java.JavaPlugin;

import com.github.herbert.gsplugin.ce.GSCommandExecutor;
import com.github.herbert.gsplugin.datenstruktur.Gslist;
import com.github.herbert.gsplugin.events.BlockEventListener;

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
	
	private void registerListeners() {
		getServer().getPluginManager().registerEvents(new BlockEventListener(this), this);
	}
	
	private void registerCommandExecutors() {
		//CE für den Befehl /gs
		ce = new GSCommandExecutor(this);
		getCommand("gs").setExecutor(ce);
	}
}
