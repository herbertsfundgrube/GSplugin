/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.herbert.gsplugin.GS;

import java.util.UUID;
import org.bukkit.entity.Player;
/**
 *
 * @author Leen
 */
public class Member {
	
	//0 -> Redstone, 1 -> Türen, 2 -> Truhen 3-> Blöcke, 4-> GS verwalten
    boolean[] perms;
    UUID id;
    public Member(Player p,byte[] perms){
    	for(byte b : perms) {
    		this.perms[b]=true;
    	}
        this.id=p.getUniqueId();
    }
    public UUID getUUID(){
        return id;
    }
    
    
    public boolean hasPerm(byte perm){
        return perms[perm];
    }
    
}
