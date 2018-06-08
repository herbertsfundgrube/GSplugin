package com.github.herbert;


import org.bukkit.plugin.java.JavaPlugin;

import com.github.herbert.worldblocksplugin.WorldBlocksPlugin;
import com.github.herbert.worldblocksplugin.ce.GSCommandExecutor;
import com.github.herbert.worldblocksplugin.events.BlockEventListener;

public class MainPlugin extends JavaPlugin {
	
	//Debugging aktivieren?
	boolean debug = false;
	

	
	private GSCommandExecutor gsplugincommandexec;
	public WorldBlocksPlugin worldblocksplugin;
	
	//--------------------------------------
	//--------------------------------------
	//--------------------------------------
	//------------Pluginstart---------------
	//--------------------------------------
	//--------------------------------------
	//--------------------------------------
	
	@Override
	public void onEnable() {
        this.getLogger().info(this.getName()+" wird gestartet");
        registerPlugins();
		registerCommandExecutors();
		registerListeners();
		
	}
	
	//--------------------------------------
	//--------------------------------------
	//--------------------------------------
	//------------Pluginstopp---------------
	//--------------------------------------
	//--------------------------------------
	//--------------------------------------
	
	@Override
	public void onDisable() {
		this.getLogger().info(this.getName()+" speichert Daten für GSplugin");
		worldblocksplugin.data.saveData();
		this.getLogger().info(this.getName()+" deaktiviert!");
	}
	
	//--------------------------------------
	//--------------------------------------
	//--------------------------------------
	//----------Config Section--------------
	//--------------------------------------
	//--------------------------------------
	//--------------------------------------
	
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
    
    
    //--------------------------------------
  	//--------------------------------------
  	//--------------------------------------
  	//----------Initialisieren--------------
  	//--------------------------------------
  	//--------------------------------------
  	//--------------------------------------
	
	private void registerListeners() {
		getServer().getPluginManager().registerEvents(new BlockEventListener(worldblocksplugin), this);
	}
	
	private void registerCommandExecutors() {
		//CE für den Befehl /gs
		gsplugincommandexec = new GSCommandExecutor(worldblocksplugin);
		getCommand("gs").setExecutor(gsplugincommandexec);
	}
	
	private void registerPlugins() {
		worldblocksplugin = new WorldBlocksPlugin(this);
	}
	
    //--------------------------------------
  	//--------------------------------------
  	//--------------------------------------
  	//-------------Debugging----------------
  	//--------------------------------------
  	//--------------------------------------
  	//--------------------------------------
    public void debug(String message) {
    	if(debug==true)
    		this.getServer().broadcastMessage(message);
    }
}
