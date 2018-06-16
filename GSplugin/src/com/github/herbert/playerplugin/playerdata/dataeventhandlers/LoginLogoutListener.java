package com.github.herbert.playerplugin.playerdata.dataeventhandlers;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import com.github.herbert.playerplugin.PlayerPlugin;

public class LoginLogoutListener implements Listener {
	
	PlayerPlugin plugin;
	
	public LoginLogoutListener(PlayerPlugin plugin) {
		this.plugin=plugin;
	}
	@EventHandler
	public void onPlayerLogin(PlayerJoinEvent event) {
		plugin.data.loadPlayerFromDisk(event.getPlayer().getUniqueId());
	}
	
	@EventHandler
	public void onPlayerLogout(PlayerQuitEvent event) {
		plugin.data.saveAndUnregisterPlayer(plugin.data.getPlayer(event.getPlayer()));
	}
}
