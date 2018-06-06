package com.github.herbert.gsplugin;

import org.bukkit.plugin.java.JavaPlugin;

import com.github.herbert.gsplugin.GS.GS;
import com.github.herbert.gsplugin.ce.GSCommandExecutor;
import com.github.herbert.gsplugin.datenstruktur.GSinteractor.GSinteractor;
import com.github.herbert.gsplugin.datenstruktur.GSinteractor.GSinteractorList;
import com.github.herbert.gsplugin.datenstruktur.Gslist;
import com.github.herbert.gsplugin.events.BlockEventListener;
import com.github.herbert.gsplugin.fileInterface.fInterface;

import net.md_5.bungee.api.ChatColor;

public class GSplugin extends JavaPlugin {
	
	private GSCommandExecutor ce;
	public Gslist gslist;
        public GSinteractorList gsintlist;
        fInterface f=new fInterface();
	
        @Override
	public void onEnable() {
		registerCommandExecutors();
		registerListeners();
                f.gsInteractorsLaden(this);
                f.GsLaden(this);
	}
	
        @Override
	public void onDisable() {
		this.getLogger().info("GSplugin deaktiviert");
                f.gsInteractorsSpeichern(gsintlist);
                f.GsSpeichern(gslist);
	}
	
	
	public String convMessage(String input) {
		return "["+ChatColor.DARK_GRAY + this.getName()+ ChatColor.RESET + "] " + input;
	}
	
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
	
	private void registerListeners() {
		getServer().getPluginManager().registerEvents(new BlockEventListener(this), this);
	}
	
	private void registerCommandExecutors() {
		//CE für den Befehl /gs
		ce = new GSCommandExecutor(this);
		getCommand("gs").setExecutor(ce);
	}
	
	public void addGS(GS gs) {
            
		if(gslist==null)
			gslist = new Gslist(gs, null);
		else {
                    if(gslist.getGS(gs.getCoords())==null){
                    gslist.add(gs);
                    }
                    else{
                        this.getServer().broadcastMessage("Auf diesen koordinaten existiert bereits ein GS");
                    }
                }
            
	}
        public void addGSint(GSinteractor gs) {
            
		if(gsintlist==null)
			gsintlist = new GSinteractorList(gs, null);
		else {
                    if(gsintlist.getByIdent(gs.getIdent())==null){
                    gsintlist.add(gs);
                    }
                    else{
                        this.getServer().broadcastMessage("Gruppe mit dem Namen existiert bereits");
                    }
            }
            
	}
}
