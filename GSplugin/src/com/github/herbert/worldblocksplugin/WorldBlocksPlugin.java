package com.github.herbert.worldblocksplugin;


import org.bukkit.Bukkit;
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
    public MainPlugin mainplugin;
    
    
    
	
    public WorldBlocksPlugin(MainPlugin plugin) {
    	this.mainplugin = plugin;
    	f = new fInterface(this);
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
                    	Bukkit.getPlayer(gs.getOwner().getMembers()[0].getUUID()).sendMessage(convMessage("Dieser Chunk wurde schon beansprucht."));
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
                        Bukkit.getPlayer(interactor.getMembers()[0].getUUID()).sendMessage(convMessage("Der Gruppenname ist vergeben."));
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
