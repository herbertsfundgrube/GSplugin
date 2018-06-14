/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.herbert.playerplugin.XP;

import java.util.UUID;

/**
 *
 * @author Lumberjack
 */
public class XPPlayerlist {
    XPPlayer p;
    XPPlayerlist n;
    public XPPlayerlist(XPPlayer p,XPPlayerlist n){
        this.p=p;
        this.n=n;
    }
    public void addXP(UUID id,int feld,int menge){
        if(p.id.equals(id)){
            p.incXP(feld, menge);
        }
        else if(n!=null){
            n.addXP(id, feld, menge);
        }
        else{
            XPPlayer t=new XPPlayer(id);
            t.incXP(feld, menge);
            n=new XPPlayerlist(t,null);
        }
    }
    public void addPlayer(XPPlayer p){
        if(n==null){
            n=new XPPlayerlist(p,null);
        }
        else{
            n.addPlayer(p);
        }
    }
    public String getInfo(UUID id, int feld){
        if(p.id.equals(id)){
            return p.getInfo(feld);
        }
        else if(n!=null){
            return n.getInfo(id,feld);
        }
        else{
            return "Fehler, Spieler nicht in der Datenbank";
        }
    }
    @Override
    public String toString(){
        char c=(char)146;
        if(n==null){
            return (p.toString()+c);
        }
        else{
            return (p.toString()+c+n.toString());
        }
    }
}
