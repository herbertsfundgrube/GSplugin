package com.github.herbert.worldblocksplugin;

import java.io.File;
import java.io.IOException;

import org.bukkit.Location;

import com.github.herbert.MainPlugin;
import com.github.herbert.worldblocksplugin.GSinteractor.GSinteractor;
import com.github.herbert.worldblocksplugin.GSinteractor.GSinteractorList;
import com.github.herbert.worldblocksplugin.datenstruktur.Gslist;
import com.github.herbert.worldblocksplugin.fileInterface.fInterface;
import com.github.herbert.worldblocksplugin.worldblocks.GS;

import net.md_5.bungee.api.ChatColor;

public class WorldBlocksPlugin {
	
	
	private Gslist gslist;
    public GSinteractorList gsintlist;
    fInterface f;
    public MainPlugin plugin;

    public File gsfile;
    public File gsinteractorsfile;
    
    
    
	
    public WorldBlocksPlugin(MainPlugin plugin) {
    	this.plugin = plugin;
    	gsfile = new File(plugin.getDataFolder(),"gs.txt");
    	gsinteractorsfile = new File(plugin.getDataFolder(),"gsinteractors.txt");
		f=new fInterface(this);
		try {
			gsfile.createNewFile();
			gsinteractorsfile.createNewFile();
		} catch (IOException e) {
			plugin.getLogger().info("Datei konnte nicht geladen werden:");
			e.printStackTrace();
		}
        f.gsInteractorsLaden();
        f.GsLaden();
    }
	public void saveData() {
		if(gsintlist!=null)
			f.gsInteractorsSpeichern();
		if(gslist!=null)
			f.GsSpeichern();
		
	}
	
	public boolean isOnGs(Location loc) {
		if(gslist==null)
			return false;
		if(gslist.getGS(loc)==null)
			return false;
		return true;
	}
	public Gslist getGSList() {
		if(gslist==null)
			gslist=new Gslist(null, null);
		return gslist;
	}
	
	public void addGS(GS gs) {
            
		if(gslist==null)
			gslist = new Gslist(gs, null);
		else {
                    if(gslist.getGS(gs.getCoords())==null){
                    gslist.add(gs);
                    }
                    else{
                        plugin.getServer().broadcastMessage("Auf diesen koordinaten existiert bereits ein GS");
                    }
                }
            
	}
    public void addGSint(GSinteractor interactor) {
            
		if(gsintlist==null)
			gsintlist = new GSinteractorList(interactor, null);
		else {
            if(gsintlist.getByIdent(interactor.getIdent())==null){
                    gsintlist.add(interactor);
                    }
                    else{
                        plugin.getServer().broadcastMessage("Gruppe mit dem Namen existiert bereits");
                    }
            }
            
	}
    	
    public void removeGS(GS gs) {
    	if(gslist.remove(gs))
    		return;
    	gslist=null;
    }
    
    public String convMessage(String input) {
	   return "["+ChatColor.DARK_GRAY + "WorldBlocks"+ ChatColor.RESET + "] " + input;
    }
    public String getHeader() {
    	return ("---------------------"+ChatColor.GRAY+"["+ ChatColor.DARK_AQUA +"WorldBlocks" +ChatColor.GRAY + "]"+ChatColor.RESET+"---------------------");
    }
    
}
