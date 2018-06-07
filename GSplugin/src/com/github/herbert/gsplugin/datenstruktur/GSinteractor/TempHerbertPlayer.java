package com.github.herbert.gsplugin.datenstruktur.GSinteractor;

import com.github.herbert.gsplugin.datenstruktur.GSinteractor.GSinteractor;
import java.util.UUID;

import org.bukkit.entity.Player;


public class TempHerbertPlayer implements GSinteractor{
	
	
	Member[] members=new HerbertPlayer[1];
    String ident;
    int friendsPermissions;
	public TempHerbertPlayer(UUID id,String ident) {
        this.ident=ident;
		//Alle Rechte außer GS verwalten auf Standart-GS
        friendsPermissions=8;
        //Der TempHerbert, dem das GS gehört, hat alle Berechtigungen auf seinen GS.
        members[0]=new HerbertPlayer(id,(byte) (1+2+4+8+16));
        }
        public TempHerbertPlayer(Member m,String ident) {
            this.ident=ident;
            //Alle Rechte außer GS verwalten auf Standart-GS
            members[0]=m;
        }
	
	@Override
	public Member[] getMembers() {
		return members;
	}

    @Override
    public String getIdent() {
    	return ident;
    }
    public void addMember(Player p) {
    	Member temp = new Member(p.getUniqueId(), friendsPermissions);
    	members[members.length] = temp;
    }
    public void removeMember(Player p) {
    	int i = 0;
    	for(Member temp : members) {
    		if(temp.getUUID().equals(p.getUniqueId())) {
    			if(i>0)
    				members[i]=null;
    		}
    		i++;
    	}
    }
        
    @Override
    public String toString(){
    	char c=(char)145;
    	return ("h"+c+ident+c+members[0].toString()+c);
    }
}
