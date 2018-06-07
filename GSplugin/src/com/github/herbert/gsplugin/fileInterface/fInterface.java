/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.herbert.gsplugin.fileInterface;
import com.github.herbert.gsplugin.GS.*;
import com.github.herbert.gsplugin.GSplugin;
import com.github.herbert.gsplugin.datenstruktur.GSCoords;
import com.github.herbert.gsplugin.datenstruktur.GSinteractor.GSinteractor;
import com.github.herbert.gsplugin.datenstruktur.GSinteractor.GSinteractorList;
import com.github.herbert.gsplugin.datenstruktur.GSinteractor.Member;
import com.github.herbert.gsplugin.datenstruktur.GSinteractor.TempHerbertPlayer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;

/**
 *
 * @author Leen
 */
public class fInterface {
	GSplugin plugin;
	
	public fInterface(GSplugin plugin) {
		this.plugin = plugin;
	}
    public void GsLaden(){
    	
    	if(plugin.gsintlist==null) {
    		plugin.plugin.getLogger().info("GS werden nicht geladen: Kein GSinteractor gefunden.");
    		
    	}
        String rep=null;
        char t1=(char)146;
        char t2=(char)145;
        
        try {
			BufferedReader in = new BufferedReader(new FileReader(plugin.gsfile));
			rep=in.readLine();
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
        if(rep==null)
        	return;
        

    	//DEBUG TODO
    	plugin.plugin.debug("Geladene GS: "+rep);
    	
    	
        char[] c=rep.toCharArray();
        int point=0;
        while (point<c.length){
            if(c[point]=='g'){
                //Normales GS wird geladen
                String ident="";
                point++;
                point++;
                //ident laden
                while(c[point]!=t2){
                    ident=ident+c[point];
                    point++;
                }
                point++;
                //publicpermissions laden
                String pubpermS="";
                byte pubperm;
                while(c[point]!=t2){
                    pubpermS=pubpermS+c[point];
                    point++;
                }
                point++;
                pubperm=Byte.parseByte(pubpermS);
                //coordinaten laden
                String xS="";
                int x=0;
                while(c[point]!=t2){
                    xS=xS+c[point];
                    point++;
                }
                point++;
                x=Integer.parseInt(xS);
                String zS="";
                int z=0;
                while(c[point]!=t2){
                    zS=zS+c[point];
                    point++;
                }
                point++;
                z=Integer.parseInt(zS);
                GSCoords coord=new GSCoords(x,z);
                GSinteractor owner=plugin.gsintlist.getByIdent(ident);
                if(owner==null){
                    plugin.plugin.getLogger().info("GSinteractor "+ident+" Existiert nicht");
                    GSinteractorList test=plugin.gsintlist;
                    plugin.plugin.getLogger().info("Folgende GSinteractor gibt es:");
                    while(test!=null){
                        plugin.plugin.getLogger().info(test.getIdent());
                        test=test.getNext();
                    }
                    return;
                }
                GS gs=new GS(owner,coord,pubperm);
                plugin.addGS(gs);
            }
            else if(c[point]=='l'){
                //Lager wird Geladen
                String ident="";
                point++;
                point++;
                //ident laden
                while(c[point]!=t2){
                    ident=ident+c[point];
                    point++;
                }
                point++;
                //publicpermissions laden
                String pubpermS="";
                byte pubperm;
                while(c[point]!=t2){
                    pubpermS=pubpermS+c[point];
                    point++;
                }
                point++;
                pubperm=Byte.parseByte(pubpermS);
                //coordinaten laden
                String xS="";
                int x=0;
                while(c[point]!=t2){
                    xS=xS+c[point];
                    point++;
                }
                point++;
                x=Integer.parseInt(xS);
                String zS="";
                int z=0;
                while(c[point]!=t2){
                    zS=zS+c[point];
                    point++;
                }
                point++;
                z=Integer.parseInt(zS);
                GSCoords coord=new GSCoords(x,z);
                GSinteractor owner=plugin.gsintlist.getByIdent(ident);
                Lager Lag=new Lager(owner,coord,pubperm);
                plugin.addGS(Lag);
                
            }
            point++;
        }
    }
    public void GsSpeichern(){
    	String out=plugin.getGSList().toString();
    	
    	try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(plugin.gsfile));
			writer.write(out);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

    	//DEBUG TODO
    	plugin.plugin.debug("Gespeicherte GS: "+out);
    	
    	
		//plugin.ymlGS.save(plugin.gsfile);
    }
    public void gsInteractorsLaden(){
        String rep=null;
        try {
			BufferedReader in = new BufferedReader(new FileReader(plugin.gsinteractorsfile));
			rep=in.readLine();
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
        if(rep==null)
        	return;
    	//DEBUG
    	plugin.plugin.debug("GSI list: "+rep);
    	
    	
        char t1=(char)146;
        char t2=(char)145;
        char[] c=rep.toCharArray();
        int point=0;
        while (point<c.length){
            if(c[point]=='h'){
                //der Gsinteractor zum Laden ist ein TempHerbertplayer
                String ident="";
                point++;
                point++;
                //ident laden
                while(c[point]!=t2){
                    ident=ident+c[point];
                    point++;
                }
                point++;
                //Member Laden
                String permsS="";
                int perms=0;
                //permissions laden
                while(c[point]!=t2){
                    permsS=permsS+c[point];
                    point++;
                }
                point++;
                perms=Integer.parseInt(permsS);
                //UUID laden
                String idmS="";
                String idlS="";
                long idm;
                long idl;
                while(c[point]!=t2){
                    idmS=idmS+c[point];
                    point++;
                }
                point++;
                while(c[point]!=t2){
                    idlS=idlS+c[point];
                    point++;
                }
                point++;
                idm=Long.parseLong(idmS);
                idl=Long.parseLong(idlS);
                //Objekte Erstellen
                UUID u=new UUID(idm,idl);
                Member m=new Member(u,perms);
                TempHerbertPlayer p=new TempHerbertPlayer(m,ident);
                plugin.addGSint(p);
            }
            point++;
            
            
        }
        
    }
    public void gsInteractorsSpeichern(){
    	String out=plugin.gsintlist.toString();
    	
    	try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(plugin.gsinteractorsfile));
			writer.write(out);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	//DEBUG
    	plugin.plugin.debug("Gespeicherte GSIlist: " + out);
    	//plugin.ymlgsints.set("derp", out);
    }
    
}
