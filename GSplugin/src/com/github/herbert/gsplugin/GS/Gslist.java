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
    public GS getGS(Location c){
        if(g.getCoords().equals(c)){
            return g;
        }
        else if(l==null){
            return null;
        }
        else{
            return l.getGS(c);
        }
    }
    public void del(Location c){
        if(g.getCoords().equals(c)){
            g=l.g;
            l=l.l;
        }
        else{
            Gslist n=l;
            GSlist p=this;
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
