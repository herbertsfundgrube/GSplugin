/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.herbert.gsplugin.datenstruktur;

import com.github.herbert.gsplugin.datenstruktur.GSinteractor.GSinteractor;
import org.bukkit.Location;

import com.github.herbert.gsplugin.GS.GS;
import com.github.herbert.gsplugin.datenstruktur.GSCoords;
import com.github.herbert.gsplugin.datenstruktur.GSinteractor.GSinteractor;
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
    public GS getGS(GSCoords loc){
        if(g.getCoords().equals(loc)){
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
    @Override
    public String toString(){
        //Trennzeichen
        char c=(char)146;
        if(l==null){
            return (g.toString()+c);
        }
        else{
            return (g.toString()+c+l.toString());
        }
    
    }
}
