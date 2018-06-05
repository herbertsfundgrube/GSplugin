/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.herbert.gsplugin.datenstruktur;

import org.bukkit.Location;


/**
 *
 * @author Leen
 */
public class GSCoords {
	
	//Anm.: Die Koordinate selbst gehört noch zum GS
    int x;
    int z;
    
    //Konstruktor für Location
    public GSCoords(int x ,int z){
        this.x=x;
        this.z=z;
    }
    public GSCoords(Location loc) {
    	x=loc.getChunk().getX();
    	z=loc.getChunk().getZ();
    }
    
    public boolean equals(GSCoords c){
        if(c.x==x&&c.z==z){
            return true;
        }
        return false;
    }
    
    //Prüfe, ob eine Location innerhalb eines GS liegt
    public boolean hasLocation(Location loc) {
    	if(loc.getChunk().getX()==x&&loc.getChunk().getZ()==z) return true;
        return false;
    }
    
    public int getX() {
    	return x;
    }
    public int getZ() {
    	return z;
    }
}
