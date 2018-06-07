package com.github.herbert.gsplugin.datenstruktur.GSinteractor;

import com.github.herbert.gsplugin.datenstruktur.GSinteractor.GSinteractor;
import java.util.UUID;


public class TempHerbertPlayer implements GSinteractor{
	Member[] Members=new HerbertPlayer[1];
        String ident;
	public TempHerbertPlayer(UUID id,String ident) {
            this.ident=ident;
		//Alle Rechte außer GS verwalten auf Standart-GS
        Members[0]=new HerbertPlayer(id,(byte) (1+2+4+8+16));
        }
        public TempHerbertPlayer(Member m,String ident) {
            this.ident=ident;
		//Alle Rechte außer GS verwalten auf Standart-GS
            Members[0]=m;
        }
	
	@Override
	public Member[] getMembers() {
		return Members;
	}

        @Override
        public String getIdent() {
            return ident;
        }
        
        @Override
        public String toString(){
            char c=(char)145;
            return ("h"+c+ident+c+Members[0].toString()+c);
        }
}
