package com.github.herbert.gsplugin.events;


import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class GamemodeThread implements Runnable {
	
	UUID id;
	public GamemodeThread(UUID id) {
		this.id=id;
	}
	
	@Override
	public void run() {
		Player p = Bukkit.getPlayer(id);
		//p.setPlayerTime(18000, false);
		Bukkit.getPlayer(id).setGameMode(GameMode.ADVENTURE);
		p.sendMessage("Eins");
		
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		p.sendMessage("Zwei");
		//p.resetPlayerTime();
		Bukkit.getPlayer(id).setGameMode(GameMode.SURVIVAL);
	}
	
}
