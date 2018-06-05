package com.github.herbert.gsplugin.datenstruktur;

import org.bukkit.entity.Player;

public class TempHerbertPlayer implements GSinteractor{
	Member[] Members=new Member[1];;
	public TempHerbertPlayer(Player p) {
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
            char c=(char)145;
            return ("h"+c+Members[0].toString());
        }
}
