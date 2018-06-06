/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.herbert.gsplugin.datenstruktur.GSinteractor;

import java.util.UUID;
/**
 *
 * @author Leen
 */
public class Member {
	
	//0 -> Redstone, 1 -> Türen, 2 -> Truhen 3-> Blöcke, 4-> GS verwalten
    int perms;
    UUID id;
    public Member(UUID id,int perms){
    	this.perms=perms;
        this.id=id;
    }
    public UUID getUUID(){
        return id;
    }
    public int getPerms(){
        return perms;
    }
    @Override
    public String toString(){
        //Trennzeichen
        char c=(char)145;
        return (""+perms+c+id.getMostSignificantBits()+c+id.getLeastSignificantBits()+c);
    }
    
}
