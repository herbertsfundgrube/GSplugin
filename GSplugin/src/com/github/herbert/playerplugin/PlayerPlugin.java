package com.github.herbert.playerplugin;

import com.github.herbert.MainPlugin;
import com.github.herbert.playerplugin.data.DataHandler;
import com.github.herbert.playerplugin.playerdata.dataeventhandlers.LoginLogoutListener;
import com.github.herbert.zredundant.XPPlayer;
import com.github.herbert.zredundant.XPPlayerlist;

import net.md_5.bungee.api.ChatColor;

public class PlayerPlugin {
    public XPPlayerlist list;
    public DataHandler data;
    public MainPlugin main;
    public LoginLogoutListener loglistener;
    
    public PlayerPlugin(MainPlugin main) {
    	this.main=main;
    	data=new DataHandler(this);
    	loglistener = new LoginLogoutListener(this);
    }
    
    public static String convMessage(String input) {
 	   return "["+ChatColor.DARK_GRAY + "Spielerplugin"+ ChatColor.RESET + "] " + input;
    }
    public static String getHeader() {
    	return ("---------------------"+ChatColor.GRAY+"["+ ChatColor.DARK_AQUA +"Spielerplugin" +ChatColor.GRAY + "]"+ChatColor.RESET+"---------------------");
    }
    public static String getHeader(String s) {
    	return ("---------------------"+ChatColor.GRAY+"["+ ChatColor.YELLOW+ s +ChatColor.GRAY + "]"+ChatColor.RESET+"---------------------");
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
