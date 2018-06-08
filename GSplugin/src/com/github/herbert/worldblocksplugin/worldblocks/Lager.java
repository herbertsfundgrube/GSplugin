/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.herbert.worldblocksplugin.worldblocks;


import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.github.herbert.worldblocksplugin.GSinteractor.GSinteractor;
import com.github.herbert.worldblocksplugin.GSinteractor.Member;
import com.github.herbert.worldblocksplugin.datenstruktur.GSCoords;

/**
 *
 * @author john
 */
public class Lager extends GS{
	
		//Jeder Bit entspricht einer Berechtigung.
		//1 -> Redstone, 2 -> Türen, 4 -> Truhen 8-> Blöcke, 16-> GS verwalten. Falls Int extension: 
		//Exclusiv:32 -> Redstone, 64 -> Türen, 128 -> Truhen 256-> Blöcke, 512-> GS verwalten
        //beispielvergleich von permissions:
        //Hat der spieler mit der Permission "perm" die berechtigung Türen und Blöcke zu verwenden?
        //if((perm&(2+8))==(2+8)) return True;
        //beispielsetzen von permissions:
        //Der Spieler soll die Permissions für Türen, Truhen und Redstone bekommen:
        //perm=1+2+4;
    
    //Konstruktor ohne Permissions
    public Lager(GSinteractor owner, Location loc) {
    	super(owner, loc);
    }
    public Lager(GSinteractor owner, GSCoords coords,byte publicperm) {
    	super(owner, coords,publicperm);
    }
    
    public boolean hasPermission(Player p, byte permission) {
    	
    	//Spezial-Permissions (Mapping) werden abgefragt
    	permission*=32;
    	
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
    
    @Override
    public String toString(){
        
        //Trennzeichen
        char c=(char)145;
        String out="l"+c+owner.getIdent()+c+publicperm+c+coords.toString()+c;
        return out;
    }
}
