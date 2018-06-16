package com.github.herbert.playerplugin.playerdata;

import org.bukkit.entity.Player;

import com.github.herbert.worldblocksplugin.GSinteractor.GSinteractor;
import com.github.herbert.worldblocksplugin.GSinteractor.Member;

public class HerbertPlayer implements GSinteractor {

	Player p;
	
	public HerbertPlayer(Player player) {
		p=player;
	}
	
	public Player getPlayer() {
		return p;
	}
	
	@Override
	public String getIdent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Member[] getMembers() {
		//TODO
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return p.getName();
	}
	public String toString() {
		//TODO
		return "";
	}

}
