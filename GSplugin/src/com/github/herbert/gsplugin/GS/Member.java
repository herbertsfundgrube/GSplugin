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
    int permlvl;
    UUID id;
    public Member(Player p,int perm){
        this.permlvl=perm;
        this.id=p.getUniqueId();
    }
    public UUID getUUID(){
        return id;
    }
    public int getperm(){
        return permlvl;
    }
    
}
