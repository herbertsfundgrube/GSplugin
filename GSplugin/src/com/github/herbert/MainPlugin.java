package com.github.herbert;


import org.bukkit.plugin.java.JavaPlugin;

import com.github.herbert.playerplugin.PlayerPlugin;
import com.github.herbert.playerplugin.PlayerPluginCommandExecutor;
import com.github.herbert.playerplugin.playerdata.dataeventhandlers.LoginLogoutListener;
import com.github.herbert.worldblocksplugin.WorldBlocksPlugin;
import com.github.herbert.worldblocksplugin.ce.GSCommandExecutor;
import com.github.herbert.worldblocksplugin.events.listeners.BlockEventListener;
import com.github.herbert.worldblocksplugin.events.listeners.PermissionsListener;

public class MainPlugin extends JavaPlugin {
	
	//Debugging aktivieren?
	boolean debug = true;
	
	private GSCommandExecutor gsplugincommandexec;
	private PlayerPluginCommandExecutor playerplugincommandexec;
	public WorldBlocksPlugin worldblocksplugin;
	public PlayerPlugin playerplugin;
	
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
		PluginConfiguration.createConfigFile(this.getDataFolder());
        registerPlugins();
		registerListeners();
		registerCommandExecutors();
		
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
  	//----------Initialisieren--------------
  	//--------------------------------------
  	//--------------------------------------
  	//--------------------------------------
	
	private void registerListeners() {
		getServer().getPluginManager().registerEvents(new BlockEventListener(worldblocksplugin), this);
		getServer().getPluginManager().registerEvents(new PermissionsListener(worldblocksplugin), this);
		getServer().getPluginManager().registerEvents(new LoginLogoutListener(playerplugin), this);
	
	}
	
	private void registerCommandExecutors() {
		gsplugincommandexec = new GSCommandExecutor(worldblocksplugin);
		playerplugincommandexec = new PlayerPluginCommandExecutor(playerplugin);
		this.getCommand("gs").setExecutor(gsplugincommandexec);
		this.getCommand("ich").setExecutor(playerplugincommandexec);
	}
	
	private void registerPlugins() {
		worldblocksplugin = new WorldBlocksPlugin(this);
		playerplugin = new PlayerPlugin(this);
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
