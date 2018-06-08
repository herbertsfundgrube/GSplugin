/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.herbert.worldblocksplugin.GSinteractor;

import java.util.UUID;
/**
 *
 * @author Leen
 */
public class Member {
	
	//1 -> Redstone, 2 -> Türen, 4 -> Truhen 8-> Blöcke, 16-> GS verwalten 
        //falls Int extension: Exclusiv:32 -> Redstone, 64 -> Türen, 128 -> Truhen 256-> Blöcke, 512-> GS verwalten
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
        return (""+perms+c+id.getMostSignificantBits()+c+id.getLeastSignificantBits());
    }
    public void addPerm(int perm) {
    	if((perms&perm)==perm){
            return; 
         }
    	perms+=perm;
    }
    public void removePerm(int perm) {
    	if((perms&perm)==perm){
        	perms-=perm;
         }
    }
    
}
