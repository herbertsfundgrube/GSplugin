package com.github.herbert.playerplugin.data;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import com.github.herbert.playerplugin.PlayerPlugin;
import com.github.herbert.playerplugin.playerdata.HerbertPlayer;
import com.github.herbert.playerplugin.skills.Skill;
import com.github.herbert.playerplugin.skills.SkillType;

public class DataHandler {
	PlayerPlugin plugin;
	HerbertPlayerList hplist=null;
	FileConfiguration playerFile;
	File playerdiskfile;
	
	
	public DataHandler(PlayerPlugin plugin) {
		this.plugin=plugin;
		//Ordner für die Daten dieses Plugins erstellen
		File folder = new File(plugin.main.getDataFolder()+"/PlayerPlugin");
		folder.mkdirs();
		
		//Datei für die Spielerdaten erstellen
		playerdiskfile = new File(folder, "players.yml");
		try {
			playerdiskfile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		playerFile = YamlConfiguration.loadConfiguration(playerdiskfile);
	}
	
	//Neuen HerbertPlayer in der HerbertPlayerList registrieren
	public void registerPlayer(HerbertPlayer hp) {
		plugin.main.debug("Registriere den Spieler " + hp.getPlayer().getName());
		plugin.main.getServer().getPluginManager().registerEvents(hp, plugin.main);
		if(hplist == null) {
			hplist = new HerbertPlayerList(plugin, hp, null);
			return;
		}
		hplist.add(hp);
			
	}
	public HerbertPlayer getPlayer(Player p) {
		if(hplist==null)
			return null;
		return hplist.getHerbertPlayer(p);
	}
	public void removePlayer(Player p) {
		
		
	}
	
	//Versucht, einen Spieler aus der Datei vorhandener Spieler zu laden.
	public void loadPlayerFromDisk(UUID id) {
		HerbertPlayer loadedplayer = null;
		//Wenn der Spieler nicht in der Datei gespeichert ist, wird er neu erstellt.
		if(!playerFile.contains(id.toString())) {
			plugin.main.debug("Keine Daten für den Spieler " + id + " vorhanden. Erstelle neu...");
			loadedplayer = new HerbertPlayer(Bukkit.getPlayer(id));
		}
		//Sonst: Wenn keine Skills für den Spieler gespeichert sind, wird er neu erstellt.
		else if(!playerFile.contains(id.toString() + ".skills")) {
			plugin.main.debug("Keine Skill-Daten für den Spieler " + id + " vorhanden. erstelle neu...");
			loadedplayer = new HerbertPlayer(Bukkit.getPlayer(id));
		//Sonst: Die Skills werden aus der Datei ausgelesen und ins Plugin eingespeist.
		} else {
			plugin.main.debug("Daten für den Spieler " + id + " werden geladen...");
			int playerlevel=1;
			double playerxp=0;
			double masteryxp=0;
			if(playerFile.contains(id.toString() + ".playerxp"))
				playerlevel=playerFile.getInt(id.toString() + ".playerxp");
			if(playerFile.contains(id.toString() + ".playerlevel"))
				playerlevel=playerFile.getInt(id.toString() + ".playerlevel");
			if(playerFile.contains(id.toString() + ".playermasteryxp"))
				playerlevel=playerFile.getInt(id.toString() + ".playermasteryxp");
			
			//Im Array werden die eingelesenen Skills zwischengespeichert. <i> ist dabei der Index.
			Skill[] skills = new Skill[playerFile.getConfigurationSection(id.toString()+".skills").getKeys(false).size()];
			int i = 0;
			//Schleife, die durch jeden Skill iteriert
			ConfigurationSection sec = playerFile.getConfigurationSection(id.toString()+".skills");
			for(String skillSection : sec.getKeys(false)) {
				int lvl = playerFile.getInt(sec.getCurrentPath()+"."+skillSection + ".lvl");
				double xp = playerFile.getDouble(sec.getCurrentPath()+"."+skillSection + ".xp");
				double freexp = playerFile.getDouble(sec.getCurrentPath()+"."+skillSection + ".freexp");
				//String in SkillType umwandeln (substring, um die ConfigurationSection abzuschneiden)
				SkillType type = SkillType.valueOf(skillSection);
				//Geladenen Skill im Array zwischenspeichern
				skills[i]=SkillType.newSkill(type, lvl, xp, freexp);
				i++;
			}
			loadedplayer = new HerbertPlayer(Bukkit.getPlayer(id), skills, playerlevel, playerxp, masteryxp);
		}
		
		registerPlayer(loadedplayer);
		try {
			playerFile.save(playerdiskfile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//Methoden zum Speichern auf die Festplatte
	
	public void saveAndUnregisterPlayer(HerbertPlayer hp) {
		UUID id = hp.getPlayer().getUniqueId();
		plugin.main.debug("Speichert Daten für " + hp.getPlayer().getName());
		int playerlevel=hp.getPlayerLvl();
		double playerxp=hp.getPlayerXP();
		double masteryxp=hp.getMasteryXP();
		playerFile.set(id.toString() +".playerxp", playerxp);
		playerFile.set(id.toString() +".playerlevel", playerlevel);
		playerFile.set(id.toString() +".playermasteryxp", masteryxp);
		//Schleife, um Skills abzuspeichern. Loopt durch Einträge in der HashMap für Skills.
		for(Map.Entry<SkillType, Skill> me : hp.getSkillsMap().entrySet()) {
			int lvl = me.getValue().getLvl();
			double xp = me.getValue().getXP();
			double freexp = me.getValue().getFreeXP();
			playerFile.set(id.toString() + ".skills."+me.getKey().name()+".lvl", lvl);
			playerFile.set(id.toString() + ".skills."+me.getKey().name()+".xp", xp);
			playerFile.set(id.toString() + ".skills."+me.getKey().name()+".freexp", freexp);
		}
		if(hplist.hplist==null) {
			hplist=null;
		} else
			hplist.remove(hp);

		try {
			playerFile.save(playerdiskfile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void SaveFile() {
		try {
			playerFile.save(playerdiskfile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
