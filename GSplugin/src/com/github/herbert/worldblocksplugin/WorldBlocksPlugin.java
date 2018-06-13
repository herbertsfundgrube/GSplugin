package com.github.herbert.worldblocksplugin;



import com.github.herbert.MainPlugin;
import com.github.herbert.worldblocksplugin.datenstruktur.DataHandler;

import net.md_5.bungee.api.ChatColor;

public class WorldBlocksPlugin {
	
	public DataHandler data;
    private MainPlugin mainplugin;
	
    public WorldBlocksPlugin(MainPlugin plugin) {
    	this.mainplugin = plugin;
    	data = new DataHandler(this);
    	data.loadData();
    }
    
    public MainPlugin getMain() {
    	return mainplugin;
    }
    public String convMessage(String input) {
	   return "["+ChatColor.DARK_GRAY + "WorldBlocks"+ ChatColor.RESET + "] " + input;
    }
    public String getHeader() {
    	return ("---------------------"+ChatColor.GRAY+"["+ ChatColor.DARK_AQUA +"WorldBlocks" +ChatColor.GRAY + "]"+ChatColor.RESET+"---------------------");
    }
    
    public boolean hasRegisteredWorldBlocks() {
    	if(data.getGSList()==null)
    		return false;
    	return true;
    }
    
}
