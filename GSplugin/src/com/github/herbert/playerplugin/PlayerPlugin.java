package com.github.herbert.playerplugin;

import com.github.herbert.playerplugin.XP.XPPlayer;
import com.github.herbert.playerplugin.XP.XPPlayerlist;
import org.bukkit.Bukkit;

public class PlayerPlugin {
    public XPPlayerlist list;
    public void addPlayer(XPPlayer p) {
            
		if(list==null) {
			list = new XPPlayerlist(p,null);
		}
                else{
                    list.addPlayer(p);
                }
		
            
	}
}
