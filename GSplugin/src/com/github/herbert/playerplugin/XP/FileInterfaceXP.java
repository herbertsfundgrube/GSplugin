/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.herbert.playerplugin.XP;
import com.github.herbert.playerplugin.*;


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
public class FileInterfaceXP {
	
	
	PlayerPlugin playerplugin;
	public File file;
	
	public FileInterfaceXP(PlayerPlugin plugin) {
		this.playerplugin = plugin;
	}
    public void Laden(){
        
        String rep=null;
        //todo: Load File from String
        if(rep!=null){
        char t1=(char)146;
        char t2=(char)145;
        char[] c=rep.toCharArray();
        int point=0;
        while (point<c.length){
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
                UUID u=new UUID(idm,idl);
                //XP werte Laden
                int[] XP =new int[8];
                int[] Level =new int[8];
                int[] MaxLevel =new int[8];
                for(int i=0;i<XP.length;i++){
                    String sXP="";
                    String sLevel="";
                    String sMaxLevel="";
                    while(c[point]!=t2){
                        sXP=sXP+c[point];
                        point++;
                    }
                    point++;
                    while(c[point]!=t2){
                        sLevel=sLevel+c[point];
                        point++;
                    }
                    point++;
                    while(c[point]!=t2){
                        sMaxLevel=sMaxLevel+c[point];
                        point++;
                    }
                    point++;
                    XP[i]=Integer.parseInt(sXP);
                    Level[i]=Integer.parseInt(sLevel);
                    MaxLevel[i]=Integer.parseInt(sMaxLevel);
                }
                
                String sskillp="";
                //skillpoints laden
                while(c[point]!=t2){
                    sskillp=sskillp+c[point];
                    point++;
                }
                point++;
                int skillp=Integer.parseInt(sskillp);
                String sMXP="";
                //skillpoints laden
                while(c[point]!=t2){
                    sMXP=sMXP+c[point];
                    point++;
                }
                point++;
                long MXP=Long.parseLong(sMXP);
                //Objekterstellung
                XPPlayer p=new XPPlayer(u,XP,Level,MaxLevel,skillp,MXP);
                point++;
                
                
                
        }
        }
        
    }
    public void Speichern(){
        //todo: Save String into file
        String out=playerplugin.list.toString();
        
    }
    
}
