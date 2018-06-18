package com.github.herbert;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import com.github.herbert.playerplugin.skills.SkillType;

public class PluginConfiguration {
	private static File configFile;
	private static FileConfiguration config;
	
	public static void createConfigFile(File folder) {
		folder.mkdirs();
		configFile = new File(folder, "config.yml");
		try {
			configFile.createNewFile();
		} catch (IOException e) {
			System.out.println("config.yml konnte nicht erstellt werden! Details:");
			e.printStackTrace();
			return;
		}
		config = YamlConfiguration.loadConfiguration(configFile);
		setConfigDefaults();
		try {
			config.save(configFile);
		} catch (IOException e) {
			System.out.println("config.yml konnte nicht gespeichert werden! Details:");
			e.printStackTrace();
		}
	}
	
	/*
	 * 
	 */
	private static void setConfigDefaults() {
		//Adminplugin
		
		//Craftingplugin
		
		//Groupplugin
		
		//Itemplugin
		
		//NPCplugin
		
		//PlayerPlugin
		putDefault("players.maximumSkillLvl", 100);
		
			//XP-Werte für CraftSkills - BlockBreakEvents
			putDefaultBlockBreakXP(Material.STONE,SkillType.MINING,  0.7);
		//UserInterfacePlugin
		
		//WorldBlocksPlugin
		putDefault("gs.lowestProtectedY", 10);
	}
	
	public static int getInt(String path) {
		return config.getInt(path);
	}
	public static String getString(String path) {
		return config.getString(path);
	}
	public static double getDouble(String path) {
		return config.getDouble(path);
	}
	private static void putDefaultBlockBreakXP(Material mat, SkillType skilltype, double xp) {
		if(config.contains("players.skills.xpvalues.blockbreak."+mat.name()+"."+skilltype.displayName()))
			return;
		config.set("players.skills.xpvalues.blockbreak."+mat.name()+"."+skilltype.displayName(), xp);
	}
	
	public static HashMap<SkillType, Double> getBlockBreakXP(Material mat) {
		if(!config.contains("players.skills.xpvalues.blockbreak."+mat.name()))
			return null;
		ConfigurationSection materialSection = config.getConfigurationSection("players.skills.xpvalues.blockbreak."+mat.name());
		HashMap<SkillType, Double> xpmap = new HashMap<SkillType, Double>();
		for(String s : materialSection.getKeys(false)) {
			xpmap.put(SkillType.getByDisplayName(s), config.getDouble(materialSection.getCurrentPath()+"."+s));
		}
		return xpmap;
	}
	
	private static void putDefault(String path, Object value) {
		if(!config.contains(path))
			config.set(path, value);
	}
}
