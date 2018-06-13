package com.github.herbert.worldblocksplugin.datenstruktur;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import com.github.herbert.worldblocksplugin.WorldBlocksPlugin;
import com.github.herbert.worldblocksplugin.GSinteractor.GSinteractor;
import com.github.herbert.worldblocksplugin.GSinteractor.GSinteractorList;
import com.github.herbert.worldblocksplugin.worldblocks.GS;

public class DataHandler {
	private Gslist gslist;
    private GSinteractorList gsintlist;
    WorldBlocksPlugin wbplugin;
    public FileInterface f;
    
    public DataHandler(WorldBlocksPlugin plugin) {
    	wbplugin = plugin;
    	f = new FileInterface(wbplugin);
    }
  //----------------------------------------------------------------------
  //---Prüft, ob ein WorldBlock auf einer bestimmten Location liegt.------
  //---Um NullPointerExceptions auszuschließen wird diese Methode---------
  //---verwendet.---------------------------------------------------------
  //----------------------------------------------------------------------  
	public boolean isOnGs(Location loc) {
		if(gslist==null)
			return false;
		if(gslist.getGS(loc)==null)
			return false;
		return true;
	}
	//----------------------------------------------------------------------
	//----------------------------------------------------------------------
	//----------------------------------------------------------------------
	public void saveData() {
		if(gsintlist!=null)
			f.gsInteractorsSpeichern();
		if(gslist!=null)
			f.GsSpeichern();
		
	}
	public Gslist getGSList() {
		if(gslist==null)
			gslist=new Gslist(null, null);
		return gslist;
	}
	
	public void addGS(GS gs) {
            
		if(gslist==null) {
			gslist = new Gslist(gs, null);
			return;
		}
		if(gslist.getGS(gs.getCoords())==null) {
			gslist.add(gs);
			return;
		}
        Bukkit.getPlayer(gs.getOwner().getMembers()[0].getUUID()).sendMessage(wbplugin.convMessage("Dieser Chunk wurde schon beansprucht."));
		
            
	}
    public void addWorldblockInteractor(GSinteractor interactor) {
            
		if(gsintlist==null) {
			gsintlist = new GSinteractorList(interactor, null);
			return;
		}
        if(gsintlist.getByIdent(interactor.getIdent())==null){
        	gsintlist.add(interactor);
        	return;
        }
        Bukkit.getPlayer(interactor.getMembers()[0].getUUID()).sendMessage(wbplugin.convMessage("Der Gruppenname ist vergeben."));
            
		
            
	}
    
    @Deprecated
    public GSinteractorList getGsInteractorsList() {
    	return gsintlist;
    }
    
    public void loadData() {
        f.gsInteractorsLaden();
        f.GsLaden();
    }
    public boolean hasGsInteractors() {
    	if(gsintlist==null)
    		return false;
    	return true;
    	
    }
    public void removeGS(GS gs) {
    	if(gslist.remove(gs))
    		return;
    	gslist=null;
    }
}
