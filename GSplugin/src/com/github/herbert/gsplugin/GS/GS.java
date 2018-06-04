/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.herbert.gsplugin.GS;


import org.bukkit.entity.Player;

import com.github.herbert.gsplugin.datenstruktur.GSCoords;
import com.github.herbert.gsplugin.datenstruktur.GSinteractor;
import com.github.herbert.gsplugin.datenstruktur.Member;

/**
 *
 * @author john
 */
public class GS {
	GSinteractor owner;
	
	//Jeder Bit entspricht einer Berechtigung.
	//1 -> Redstone, 2 -> Türen, 4 -> Truhen 8-> Blöcke, 16-> GS verwalten
        //beispielvergleich von permissions:
        //Hat der spieler mit der Permission "perm" die berechtigung Türen und Blöcke zu verwenden?
        //if((perm&(2+8))==(2+8)) return True;
        //beispielsetzen von permissions:
        //Der Spieler soll die Permissions für Türen, Truhen und Redstone bekommen:
        //perm=1+2+4;
    byte publicperm=0;
    
    //Konstruktor ohne Permissions
    public GS(GSinteractor owner) {
        this.owner=owner;
    }
    
    //Konstruktor: Mit bestimmten Public-Permissions initialisieren
    public GS(GSinteractor owner, byte[] enabledPublicPerms) {
        this.owner=owner;
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
                if((m.getPerms()&permission)==permission){
                    return true;
                }
            }
        }
        //Public Section
        if((publicperm&permission)==permission){
                    return true;
        }
        return false;
    }
    
    //Öffentliche Permissions ändern
    public void setPublicPerms(byte permissons) {
    	publicperm=permissons;
    }
    
    public GSinteractor getOwner() {
    	return owner;
    }
}
