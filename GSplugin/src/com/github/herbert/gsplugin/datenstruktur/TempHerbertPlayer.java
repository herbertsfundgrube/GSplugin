package com.github.herbert.gsplugin.datenstruktur;

import org.bukkit.entity.Player;

public class TempHerbertPlayer implements GSinteractor{
	Player bukkplayer;
	Member[] Members=new Member[1];;
    private Member[] Member;
	public TempHerbertPlayer(Player p) {
		bukkplayer=p;
                Member[0]=new Member(p,(byte)-1);
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
	public Member[] getMembers() {
		return Members;
	}
}
