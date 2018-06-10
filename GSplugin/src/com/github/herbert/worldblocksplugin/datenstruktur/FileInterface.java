/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.herbert.worldblocksplugin.datenstruktur;
import com.github.herbert.worldblocksplugin.WorldBlocksPlugin;
import com.github.herbert.worldblocksplugin.GSinteractor.GSinteractor;
import com.github.herbert.worldblocksplugin.GSinteractor.GSinteractorList;
import com.github.herbert.worldblocksplugin.GSinteractor.Member;
import com.github.herbert.worldblocksplugin.GSinteractor.TempHerbertPlayer;
import com.github.herbert.worldblocksplugin.worldblocks.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;

/**
 *
 * @author Leen
 */
public class FileInterface {
	
	
	WorldBlocksPlugin worldblocksplugin;
	public File gsfile;
	public File gsinteractorsfile;
	
	public FileInterface(WorldBlocksPlugin plugin) {
		this.worldblocksplugin = plugin;
		String pluginDir = worldblocksplugin.getMain().getDataFolder().getAbsolutePath();
		gsfile = new File(pluginDir,"gs.txt");
		gsinteractorsfile = new File(pluginDir,"gsinteractors.txt");
		//Speicher für GSfiles erstellen
		try {
			gsfile.getParentFile().mkdir();
			gsfile.createNewFile();
			gsinteractorsfile.createNewFile();
			plugin.getMain().getLogger().info("Dateien sollten erstellt sein.");
		} catch (IOException e) {
			
			plugin.getMain().getLogger().info("Eine Datei konnte nicht gespeichert werden.");
			//Dateipfade vollständig ausgeben
			plugin.getMain().getLogger().info("Dateipfade: " + gsfile.getAbsolutePath()+", " + gsinteractorsfile.getAbsolutePath());
			e.printStackTrace();
		}
	}
    public void GsLaden(){
    	
    	if(worldblocksplugin.data.hasGsInteractors()) {
    		worldblocksplugin.getMain().getLogger().info("GS werden nicht geladen: Kein GSinteractor gefunden.");
    		
    	}
        String rep=null;
        char t1=(char)146;
        char t2=(char)145;
        
        try {
			BufferedReader in = new BufferedReader(new FileReader(gsfile));
			rep=in.readLine();
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
        if(rep==null)
        	return;
        

    	//DEBUG TODO
    	worldblocksplugin.getMain().debug("Geladene GS: "+rep);
    	
    	
        char[] c=rep.toCharArray();
        int point=0;
        while (point<c.length){
            if(c[point]=='g'){
                //Normales GS wirdbbb geladen
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
                GSinteractor owner=worldblocksplugin.data.getGsInteractorsList().getByIdent(ident);
                if(owner==null){
                    worldblocksplugin.getMain().getLogger().info("GSinteractor "+ident+" Existiert nicht");
                    GSinteractorList test=worldblocksplugin.data.getGsInteractorsList();
                    worldblocksplugin.getMain().getLogger().info("Folgende GSinteractor gibt es:");
                    while(test!=null){
                        worldblocksplugin.getMain().getLogger().info(test.getIdent());
                        test=test.getNext();
                    }
                    return;
                }
                GS gs=new GS(owner,coord,pubperm);
                worldblocksplugin.data.addGS(gs);
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
                GSinteractor owner=worldblocksplugin.data.getGsInteractorsList().getByIdent(ident);
                Lager Lag=new Lager(owner,coord,pubperm);
                worldblocksplugin.data.addGS(Lag);
                
            }
            point++;
        }
    }
    public void GsSpeichern(){
    	String out=worldblocksplugin.data.getGSList().toString();
    	
    	try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(gsfile));
			writer.write(out);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

    	//DEBUG TODO
    	worldblocksplugin.getMain().debug("Gespeicherte GS: "+out);
    	
    	
		//plugin.ymlGS.save(plugin.gsfile);
    }
    public void gsInteractorsLaden(){
        String rep=null;
        try {
			BufferedReader in = new BufferedReader(new FileReader(gsinteractorsfile));
			rep=in.readLine();
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
        if(rep==null)
        	return;
    	//DEBUG
    	worldblocksplugin.getMain().debug("GSI list: "+rep);
    	
    	
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
                worldblocksplugin.data.addWorldblockInteractor(p);
            }
            point++;
            
            
        }
        
    }
    public void gsInteractorsSpeichern(){
    	String out=worldblocksplugin.data.getGsInteractorsList().toString();
    	
    	try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(gsinteractorsfile));
			writer.write(out);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	//DEBUG
    	worldblocksplugin.getMain().debug("Gespeicherte GSIlist: " + out);
    }
    
}
