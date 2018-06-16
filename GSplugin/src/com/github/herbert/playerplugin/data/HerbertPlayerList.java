package com.github.herbert.playerplugin.data;

import org.bukkit.entity.Player;

import com.github.herbert.playerplugin.PlayerPlugin;
import com.github.herbert.playerplugin.playerdata.HerbertPlayer;

class HerbertPlayerList {
	public HerbertPlayerList hplist;
	public HerbertPlayer hp;
	PlayerPlugin plugin;
	
	public HerbertPlayerList(PlayerPlugin plugin, HerbertPlayer hp, HerbertPlayerList hpl) {
		this.hp=hp;
		this.hplist=hpl;
		this.plugin=plugin;
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
		hplisttemp.hplist=new HerbertPlayerList(plugin, hp, null);
	}
	
	//Entfernt den Spieler aus der Liste der geladenen PluginPlayers und speichert dessen Werte ab. Üblicherweise beim Logout.
	public void remove(Player p) {
		HerbertPlayerList hplisttemp=this;
		do {
			if(hplisttemp.hplist.hp.getPlayer()==p) {
				hplisttemp.hplist=hplisttemp.hplist.hplist;
			}
			hplisttemp=hplisttemp.hplist;
		} while(hplisttemp!=null);
	}
	
	public void remove(HerbertPlayer hp) {
		HerbertPlayerList hplisttemp=this;
		if(hplist==null)
			return;
				
		do {
			if(hplisttemp.hplist.hp==hp) {
				hplisttemp.hplist=hplisttemp.hplist.hplist;
			}
			hplisttemp=hplisttemp.hplist;
		} while(hplisttemp!=null);
	}
	
	
}
