/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.herbert.gsplugin.GS;


import org.bukkit.entity.Player;

/**
 *
 * @author john
 */
public class GS {
	GSinteractor owner;
        
        
    public GS(GSinteractor owner) {
        this.owner=owner;
    }
    
    public GSCoords getCoords() {
    	
    	return null;
    	//TODO
    }
    
    public boolean hasPermission(Player p, GSpermissions perm) {
    	
    	//TODO: Spieler mit GSpermissions listen
        for(Member i:owner.getMembers()){
            if(i.getUUID().equals(p.getUniqueId()))
                return true;
        }
    	
    	//if(coownerslist.hasEntry(p)) ...
    	//Vielleich eine HashMap?
    	//Berechtigungsgruppen (Coowner / Mieter...?)
    	
    	return false;
    }
}
