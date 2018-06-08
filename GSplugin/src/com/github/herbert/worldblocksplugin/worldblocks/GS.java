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
public class GS {
	GSinteractor owner;
	GSCoords coords;
	
	//Jeder Bit entspricht einer Berechtigung.
	//1 -> Redstone, 2 -> Türen, 4 -> Truhen 8-> Blöcke, 16-> GS verwalten 
        //falls Int extension: Exclusiv:32 -> Redstone, 64 -> Türen, 128 -> Truhen 256-> Blöcke, 512-> GS verwalten
        //beispielvergleich von permissions:
        //Hat der spieler mit der Permission "perm" die berechtigung Türen und Blöcke zu verwenden?
        //if((perm&(2+8))==(2+8)) return True;
        //beispielsetzen von permissions:
        //Der Spieler soll die Permissions für Türen, Truhen und Redstone bekommen:
        //perm=1+2+4;
    byte publicperm=0;
    
    //Konstruktor ohne Permissions
    public GS(GSinteractor owner, Location loc) {
        this.owner=owner;
        Member[] omembers = owner.getMembers();
        //Owner bekommt alle Permissions
        omembers[0].addPerm(31);
        coords = new GSCoords(loc);
    }
    public GS(GSinteractor owner, GSCoords coords,byte publicperm) {
        this.owner=owner;
        this.coords=coords;
        this.publicperm=publicperm;
    }
    
    //Koordinaten des GS ausgeben
    public GSCoords getCoords() {
    	
    	return coords;
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
    
        @Override
    public String toString(){
        
        //Trennzeichen
        char c=(char)145;
        String out="g"+c+owner.getIdent()+c+publicperm+c+coords.toString()+c;
        return out;
    }
}
