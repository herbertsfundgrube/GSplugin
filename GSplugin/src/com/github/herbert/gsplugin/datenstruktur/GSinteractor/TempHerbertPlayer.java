package com.github.herbert.gsplugin.datenstruktur.GSinteractor;

import com.github.herbert.gsplugin.datenstruktur.GSinteractor.GSinteractor;
import java.util.UUID;


public class TempHerbertPlayer implements GSinteractor{
	Member[] Members=new HerbertPlayerFriend[1];;
	public TempHerbertPlayer(UUID id) {
		//Alle Rechte außer GS verwalten auf Standart-GS
        Members[0]=new HerbertPlayerFriend(id,(byte) (1+2+4+8));
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
