package com.github.herbert.worldblocksplugin;



import com.github.herbert.MainPlugin;
import com.github.herbert.worldblocksplugin.datenstruktur.DataHandler;
import com.github.herbert.worldblocksplugin.fileInterface.fInterface;

import net.md_5.bungee.api.ChatColor;

public class WorldBlocksPlugin {
	
	public DataHandler data;
    public MainPlugin mainplugin;
    public fInterface f;
	
    public WorldBlocksPlugin(MainPlugin plugin) {
    	data = new DataHandler(this);
    	this.mainplugin = plugin;
    	f = new fInterface(this);
        f.gsInteractorsLaden();
        f.GsLaden();
    }
    
    public String convMessage(String input) {
	   return "["+ChatColor.DARK_GRAY + "WorldBlocks"+ ChatColor.RESET + "] " + input;
    }
    public String getHeader() {
    	return ("---------------------"+ChatColor.GRAY+"["+ ChatColor.DARK_AQUA +"WorldBlocks" +ChatColor.GRAY + "]"+ChatColor.RESET+"---------------------");
    }
    
    
}
