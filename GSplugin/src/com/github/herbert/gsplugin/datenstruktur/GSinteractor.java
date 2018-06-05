package com.github.herbert.gsplugin.datenstruktur;

public interface GSinteractor {
	public String getIdent();
	
	//Interface für alle Objekte, die mit einem GS interagieren können
	//Spieler oder Stadt oder...
	
	//UUID für Spieler
	//Wenn das Objekt eine Stadt ist, dann muss die UUID als TOWN<Stadtname> oder so gesetzt werden.
	public Member[] getMembers();
	
}
