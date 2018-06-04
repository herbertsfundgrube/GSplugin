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
	
	//Jeder Index entspricht einer Berechtigung.
	//0 -> Redstone, 1 -> Türen, 2 -> Truhen 3-> Blöcke, 4-> GS verwalten
    boolean[] publicperms = new boolean[5];
    
    //Konstruktor ohne Permissions
    public GS(GSinteractor owner) {
        this.owner=owner;
    }
    
    //Konstruktor: Mit bestimmten Public-Permissions initialisieren
    public GS(GSinteractor owner, byte[] enabledPublicPerms) {
        this.owner=owner;
        setPublicPermissions(enabledPublicPerms, true);
    }
    
    //Koordinaten des GS ausgeben
    public GSCoords getCoords() {
    	
    	return null;
    	//TODO
    }
    
    public boolean hasPermission(Player p, byte permission) {
    	//Member section
        for(Member m:owner.getMembers()){
            if(m.getUUID().equals(p.getUniqueId())){
                return m.hasPerm(permission);
            }
                
        }
        //Public Section
        return publicperms[permission];
    }
    
    public void setPublicPermissions(byte[] perms, boolean setting) {
    	for(byte b:perms) {
        	publicperms[b]=setting;
        }
    }
}
