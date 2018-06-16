package com.github.herbert.playerplugin.data;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import com.github.herbert.playerplugin.PlayerPlugin;
import com.github.herbert.playerplugin.playerdata.HerbertPlayer;

public class DataHandler {
	PlayerPlugin plugin;
	HerbertPlayerList hplist=null;
	FileConfiguration playerFile;
	
	
	public DataHandler(PlayerPlugin plugin) {
		this.plugin=plugin;
		//Ordner für die Daten dieses Plugins erstellen
		File folder = new File(plugin.main.getDataFolder()+"/PlayerPlugin");
		folder.mkdirs();
		
		//Datei für die Spielerdaten erstellen
		File playerfile = new File(folder, "players.yml");
		try {
			playerfile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		playerFile = YamlConfiguration.loadConfiguration(playerfile);
	}
	
	
	
	public HerbertPlayer getPlayer(Player p) {
		if(hplist==null)
			return null;
		return hplist.getHerbertPlayer(p);
	}
	public void removePlayer(Player p) {
		if(hplist!=null)
			hplist.removeAndSave(p);
	}
	
	//Methoden zum Speichern auf die Festplatte
	
	public void savePlayerToDisk(HerbertPlayer hp) {
		//TODO: Save-Methode für Spieler schreiben
	}
	
	public void saveDataToDisk(String path, int key, FileConfiguration file) {
		file.set(path, key);
	}
	
	public void saveDataToDisk(String path, double key, FileConfiguration file) {
		file.set(path, key);
	}
}
