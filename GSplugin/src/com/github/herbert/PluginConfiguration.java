package com.github.herbert;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

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
	
	private static void putDefault(String path, Object value) {
		if(!config.contains(path))
			config.set(path, value);
	}
}
