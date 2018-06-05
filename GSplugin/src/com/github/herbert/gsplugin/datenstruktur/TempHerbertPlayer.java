package com.github.herbert.gsplugin.datenstruktur;

import org.bukkit.entity.Player;

public class TempHerbertPlayer implements GSinteractor{
	Player bukkplayer;
	Member[] Members=new Member[1];;
	public TempHerbertPlayer(Player p) {
		bukkplayer=p;
        Members[0]=new Member(p,(byte)-1);
        }
	
	@Override
	public Member[] getMembers() {
		return Members;
	}

        @Override
        public String getIdent() {
            return "Herbert";
        }
        
        @Override
        public String toString(){
            return Members[0].toString();
        }
}
