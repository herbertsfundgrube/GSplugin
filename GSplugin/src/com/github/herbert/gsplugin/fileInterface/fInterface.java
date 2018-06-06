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
import com.github.herbert.gsplugin.datenstruktur.Gslist;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.UUID;
/**
 *
 * @author Leen
 */
public class fInterface {
	
	
    public void GsLaden(GSplugin plugin){
        String rep="";
        char t1=(char)146;
        char t2=(char)145;
        try{
            //einlesen des die Gslist repräsentierenden Strings
            FileReader f=new FileReader("GS.txt");
            BufferedReader r=new BufferedReader(f);
            rep=r.readLine();
            r.close();
            f.close();
        }
        catch(IOException i){
            //Fehler beim Dateizugriff
        }
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
    public void GsSpeichern(Gslist l){
        try{
            //schreiben des die Gslist repräsentierenden Strings
            FileWriter f=new FileWriter("/GSplugin/GS.txt");
            BufferedWriter w=new BufferedWriter(f);
            w.write(l.toString());
            w.close();
            f.close();
        }
        catch(IOException i){
            //Fehler beim Dateizugriff
        }
    }
    public void gsInteractorsLaden(GSplugin plugin){
        String rep="";
        char t1=(char)146;
        char t2=(char)145;
        try{
            //einlesen des die Gruppenliste repräsentierenden Strings
            FileReader f=new FileReader("/GSplugin/GSinteractors.txt");
            BufferedReader r=new BufferedReader(f);
            rep=r.readLine();
            r.close();
            f.close();
        }
        catch(IOException i){
            //Fehler beim Dateizugriff
        }
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
    public void gsInteractorsSpeichern(GSinteractorList l){
        try{
            //schreiben des die Gruppenliste repräsentierenden Strings
            FileWriter f=new FileWriter("/GSplugin/GSinteractors.txt");
            BufferedWriter w=new BufferedWriter(f);
            w.write(l.toString());
            w.close();
            f.close();
        }
        catch(IOException i){
            //Fehler beim Dateizugriff
        }
    }
    
}
