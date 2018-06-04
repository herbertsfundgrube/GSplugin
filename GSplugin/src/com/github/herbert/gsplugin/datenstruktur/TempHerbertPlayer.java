package com.github.herbert.gsplugin.datenstruktur;

import org.bukkit.entity.Player;

public class TempHerbertPlayer implements GSinteractor{
	Player bukkplayer;
	Member[] globalGsFriends;
	public TempHerbertPlayer(Player p) {
		bukkplayer=p;
	}
	
	public Player getBukkitPlayer() {
		return bukkplayer;
	}

	@Override
	public boolean isPlayer() {
		return true;
	}

	@Override
	public boolean isTown() {
		return false;
	}
	
	@Override
	public Member[] getGlobalGsFriends() {
		return globalGsFriends;
	}

	@Override
	public boolean hasGlobalGsFriends() {
		if(getGlobalGsFriends()==null)
			return false;
		return true;
	}
}
