package com.github.herbert.playerplugin.playerdata;

import org.bukkit.entity.Player;

public class HerbertPlayerList {
	private HerbertPlayerList hplist;
	private HerbertPlayer hp;
	
	public HerbertPlayerList(HerbertPlayer hp, HerbertPlayerList hpl) {
		this.hp=hp;
		this.hplist=hpl;
	}
	
	public HerbertPlayer getHerbertPlayer(Player p) {
		HerbertPlayerList hplisttemp=this;
		do {
			if(hplisttemp.hp.getPlayer()==p)
				return hplisttemp.hp;
			hplisttemp=hplisttemp.hplist;
		} while(hplisttemp!=null);
		
		return null;
	}
	
	public void add(HerbertPlayer hp) {
		HerbertPlayerList hplisttemp=this;
		//Zum letzten Element der Liste gehen
		while(hplisttemp.hplist!=null) {
			hplisttemp=hplisttemp.hplist;
		}
		hplisttemp.hplist=new HerbertPlayerList(hp, null);
	}
	
	public void removeAndSave(Player p) {
		HerbertPlayerList hplisttemp=this;
		do {
			if(hplisttemp.hplist.hp.getPlayer()==p) {
				hplisttemp.hplist=hplisttemp.hplist.hplist;
			}
			hplisttemp=hplisttemp.hplist;
		} while(hplisttemp!=null);
	}
	
	
}
