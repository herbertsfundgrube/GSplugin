package com.github.herbert.gsplugin.datenstruktur;

public interface GSinteractor {
	
	
	//Interface für alle Objekte, die mit einem GS interagieren können
	//Spieler oder Stadt oder...
	public boolean isPlayer();
	public boolean isTown();
	
	//UUID für Spieler
	//Wenn das Objekt eine Stadt ist, dann muss die UUID als TOWN<Stadtname> oder so gesetzt werden.
	public Member[] getGlobalGsFriends();
	public boolean hasGlobalGsFriends();
	
}
