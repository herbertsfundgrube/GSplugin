package com.github.herbert.playerplugin;

import com.github.herbert.MainPlugin;
import com.github.herbert.playerplugin.XP.XPPlayer;
import com.github.herbert.playerplugin.XP.XPPlayerlist;
import com.github.herbert.playerplugin.data.DataHandler;

public class PlayerPlugin {
    public XPPlayerlist list;
    public DataHandler data;
    public MainPlugin main;
    
    public PlayerPlugin(MainPlugin main) {
    	this.main=main;
    	this.data=new DataHandler(this);
    }
    
    
    public void addPlayer(XPPlayer p) {
            
		if(list==null) {
			list = new XPPlayerlist(p,null);
		}
                else{
                    list.addPlayer(p);
                }
		
            
	}
}
