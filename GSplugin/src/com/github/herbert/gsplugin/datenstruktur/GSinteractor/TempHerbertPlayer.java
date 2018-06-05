package com.github.herbert.gsplugin.datenstruktur.GSinteractor;

import com.github.herbert.gsplugin.datenstruktur.GSinteractor.GSinteractor;
import com.github.herbert.gsplugin.datenstruktur.Member;
import java.util.UUID;


public class TempHerbertPlayer implements GSinteractor{
	Member[] Members=new Member[1];;
	public TempHerbertPlayer(UUID id) {
        Members[0]=new Member(id,(byte)-1);
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
