/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.herbert.gsplugin.datenstruktur;

import org.bukkit.Location;

import com.github.herbert.gsplugin.GSplugin;

/**
 *
 * @author Leen
 */
public class GSCoords {
	
	//Anm.: Die Koordinate selbst gehört noch zum GS
    int x1;
    int z1;
    int x2;
    int z2;
    int lowestY;
    
    //Konstruktor für Location
    public GSCoords(Location loc1, Location loc2, GSplugin plugin){
        x1=loc1.getBlockX();
        z1=loc1.getBlockZ();
        x2=loc2.getBlockX();
        z2=loc2.getBlockZ();
        //1. Koordinate ist immer der kleinere Wert
        if(x1>x2) {
        	int i = x1;
        	x2=x1;
        	x1=i;
        }
        if(z1>z2) {
        	int i=z1;
        	z2=z1;
        	z1=i;
        }
    }
    
    //Konstruktor für int-Coords
    public GSCoords(int x, int z, int x2, int z2, GSplugin plugin){
        this.x1=x;
        this.z1=z;
        this.x2=x2;
        this.z2=z2;
        //1. Koordinate ist immer der kleinere Wert
        if(x1>x2) {
        	int i = x1;
        	x2=x1;
        	x1=i;
        }
        if(z1>z2) {
        	int i=z1;
        	z2=z1;
        	z1=i;
        }
        lowestY = plugin.getConfigInt("gs.lowestYBlock");
    }
    
    public boolean equals(GSCoords c){
    	
    	
        if(c.x1==x1&&c.z1==z1){
            return true;
        }
        return false;
    }
    
    //Prüfe, ob eine Location innerhalb eines GS liegt
    public boolean hasLocation(Location loc) {
    	if(x1<=loc.getBlockX() && loc.getBlockZ()<=z1 && x2>=loc.getBlockX() && z2>=loc.getBlockZ())
    		return true;
    	return false;
    }
}
