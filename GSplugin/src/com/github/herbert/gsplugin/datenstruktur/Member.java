/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.herbert.gsplugin.datenstruktur;

import java.util.UUID;
import org.bukkit.entity.Player;
/**
 *
 * @author Leen
 */
public class Member {
	
	//0 -> Redstone, 1 -> Türen, 2 -> Truhen 3-> Blöcke, 4-> GS verwalten
    byte perms;
    UUID id;
    public Member(Player p,byte perms){
    	this.perms=perms;
        this.id=p.getUniqueId();
    }
    public UUID getUUID(){
        return id;
    }
    public byte getPerms(){
        return perms;
    }
    @Override
    public String toString(){
        //Trennzeichen
        char c=(char)145;
        return (""+perms+c+id.getMostSignificantBits()+c+id.getLeastSignificantBits());
    }
    
}
