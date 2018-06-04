/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.herbert.gsplugin.datenstruktur;

import org.bukkit.Location;

import com.github.herbert.gsplugin.GS.GS;
/**
 *
 * @author Leen
 */
public class Gslist {
    GS g;
    Gslist l;
    public Gslist(GS g,Gslist l){
        this.g=g;
        this.l=l;
    }
    
    public void add(GS u){	
    	if(l==null){
        	
    		l=new Gslist(u,null);
    	}
    	else{
    		l.add(u);
    	}

    }
    
    //Gibt GS für Location, oder null wenn kein GS zur Location gehört.
    public GS getGS(Location loc){
        if(g.getCoords().hasLocation(loc)){
            return g;
        }
        else if(l==null){
            return null;
        }
        else{
            return l.getGS(loc);
        }
    }
    public Gslist getGSByOwner(GSinteractor p){
        Gslist ret =new Gslist(null,null);
        Gslist it =this;
        while(it!=null){
            if(it.g.getOwner().equals(p)){
                if(ret.g==null){
                    ret.g=it.g;
                }
                else{
                    ret.add(it.g);
                }
            }
            it=it.l;
        }
        return ret;
    }
    public void del(GSCoords c){
        if(g.getCoords().equals(c)){
            g=l.g;
            l=l.l;
        }
        else{
            Gslist n=l;
            Gslist p=this;
            while(n!=null){
                if(n.g.getCoords().equals(c)){
                    p.l=n.l;
                    n=null;
                }
                else{
                    p=n;
                    n=n.l;
                }
            }
        }
    }
}
