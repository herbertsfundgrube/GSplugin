/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.herbert.gsplugin.GS;

import org.bukkit.Location;

/**
 *
 * @author Leen
 */
public class GSCoords {
    int x1;
    int y1;
    int x2;
    int y2;
    
    
    //Init aus Location
    public GSCoords(Location loc1, Location loc2){
        this.x1=loc1.getBlockX();
        this.y1=loc1.getBlockY();
        this.x2=loc2.getBlockX();
        this.y2=loc2.getBlockY();
    }
    
    //init aus Coords
    public GSCoords(int x, int y){
        this.x1=x;
        this.y1=y;
    }
    public boolean equals(GSCoords c){
        if(c.x1==x1&&c.y1==y1&&c.x2==x2&&c.y2==y2){
            return true;
        }
        return false;
    }
}
