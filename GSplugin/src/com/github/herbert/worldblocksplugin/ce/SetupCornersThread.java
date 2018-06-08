package com.github.herbert.worldblocksplugin.ce;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.github.herbert.worldblocksplugin.WorldBlocksPlugin;

public class SetupCornersThread extends Thread implements org.bukkit.event.Listener {
	
	Player buyingPlayer;
	Location corner1;
	Location corner2;
	boolean cancelled;
	WorldBlocksPlugin plugin;
	public SetupCornersThread (Player p, WorldBlocksPlugin plugin) {
		buyingPlayer=p;
		this.plugin=plugin;
	}
	
	//IM MOMENT IST DIESE KLASSE REDUNDANT
	//Wird vielleicht später Verwendung finden, um via Chat zwei Eckpunkte anzugeben. Daher Code behalten.
	
	
	
	
	//Der Thread läuft, bis die Schleife unten abbricht. Während der Thread läuft hat player p die Möglichkeit zur Dateneingabe.
	@Override
	public void run() {
		int i = 0;
		do {
			i++;
			try {
				//Eine Sekunde sleep
				Thread.sleep(100);
			} catch (InterruptedException e) {
				buyingPlayer.sendMessage(plugin.convMessage("Fehler 1 (InterruptedException) im GSBuyThread! Bitte melde diesen Vorfall der Serverleitung."));
			}
		//Die Schleife bricht ab, wenn beide Ecken gesetzt sind, wenn der Spieler abbricht, oder wenn 15 Sekunden verstrichen sind.
		} while( (corner1==null || corner2==null)&& i>=15 && cancelled == false);
		
	}
	@EventHandler
	public void onPlayerChatEvent(AsyncPlayerChatEvent event) {
		//Nur der Chat des Spielers, der mit diesem Thread ein GS kaufen will, wird abgefangen.
		if(event.getPlayer().equals(buyingPlayer)) {
			if(event.getMessage().equalsIgnoreCase("ecke1")) {
				corner1=event.getPlayer().getLocation();
			} else if(event.getMessage().equalsIgnoreCase("ecke2")) {
				corner2=event.getPlayer().getLocation();
			} else if(event.getMessage().equalsIgnoreCase("cancel")) {
				cancelled=true;
			}
		}
	}

}
