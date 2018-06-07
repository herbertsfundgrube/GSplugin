package com.github.herbert.gsplugin;

import java.io.File;
import java.io.IOException;

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
    fInterface f;
    //Dateien zum Speichern
    public File gsfile = new File(this.getDataFolder(),"gs.txt");
    public File gsinteractorsfile = new File(this.getDataFolder(),"gsinteractors.txt");
    
    
    
    //Debug-modus ein- bzw aussschalten
    private boolean debug=false;
    
	
    
    
        @Override
	public void onEnable() {
        this.getLogger().info(this.getName()+" wird gestartet");
		registerCommandExecutors();
		registerListeners();
		f=new fInterface(this);
		try {
			gsfile.createNewFile();
			gsinteractorsfile.createNewFile();
		} catch (IOException e) {
			this.getLogger().info("Datei konnte nicht geladen werden:");
			e.printStackTrace();
		}
        f.gsInteractorsLaden();
        f.GsLaden();
	}
	
        @Override
	public void onDisable() {
		this.getLogger().info(this.getName()+" speichert Daten");
		
		saveData();
		
		this.getLogger().info(this.getName()+" deaktiviert!");
	}
	
	private void registerListeners() {
		getServer().getPluginManager().registerEvents(new BlockEventListener(this), this);
	}
	
	private void registerCommandExecutors() {
		//CE für den Befehl /gs
		ce = new GSCommandExecutor(this);
		getCommand("gs").setExecutor(ce);
	}
	private void saveData() {
		if(gsintlist!=null)
			f.gsInteractorsSpeichern();
		if(gslist!=null)
			f.GsSpeichern();
		
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
    public void addGSint(GSinteractor interactor) {
            
		if(gsintlist==null)
			gsintlist = new GSinteractorList(interactor, null);
		else {
                    if(gsintlist.getByIdent(interactor.getIdent())==null){
                    gsintlist.add(interactor);
                    }
                    else{
                        this.getServer().broadcastMessage("Gruppe mit dem Namen existiert bereits");
                    }
            }
            
	}
    	
    public void removeGS(GS gs) {
    	if(gslist.remove(gs))
    		return;
    	gslist=null;
    }
    
    public String convMessage(String input) {
	   return "["+ChatColor.DARK_GRAY + this.getName()+ ChatColor.RESET + "] " + input;
    }
    	
    //TODO
    public String getConfigString(String key) {
    	return null;
    }
    public void debug(String message) {
    	if(debug==true)
    		this.getServer().broadcastMessage(message);
    }
    //TODO
    public int getConfigInt(String key) {
    	
    	//Bis die Config eingebunden ist werden die Werte hardcoded.
    	if(key.equals("gs.lowestProtectedY"))
    		return 10;
    	
    	
    	//Ende Hardcode
    	return 0;
    }
    
}
