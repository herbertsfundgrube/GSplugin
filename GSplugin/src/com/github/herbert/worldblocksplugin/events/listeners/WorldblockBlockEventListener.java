package com.github.herbert.worldblocksplugin.events.listeners;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.github.herbert.worldblocksplugin.WorldBlocksPlugin;
import com.github.herbert.worldblocksplugin.events.WorldblockBlockEvent;
import com.github.herbert.worldblocksplugin.worldblocks.GS;

public class WorldblockBlockEventListener implements Listener {
	WorldBlocksPlugin plugin;
	public WorldblockBlockEventListener(WorldBlocksPlugin wbplugin) {
		plugin=wbplugin;
	}
	@EventHandler
	public void onBlockChange(WorldblockBlockEvent event) {
		if(canGsBlockBeChanged(event.getPlayer(), event.getBlock().getLocation()))
			event.setCancelled(true);
	}
	
	//Hat der <p> für das GS bei <loc> die Permission 8 (bauen/abbauen)?
	private boolean canGsBlockBeChanged(Player p, Location loc) {
		if(hasWorldPermission(p))
			return true;
		GS gs = plugin.data.getGSList().getGS(loc);
		//Wenn ein GS gefunden wurde, auf dem der Player die Permission 3 hat, dann ist die Aktion erlaubt.
		if(gs.hasPermission(p, (byte) 8)) {
			plugin.getMain().debug(p.getName() + " hat die Permissions für das GS" + gs.getCoords().getX() + " / " + gs.getCoords().getZ());
			return true;
		}
		//In allen übrigen Fällen ist die Änderung nicht erlaubt. Der Spieler nimmt Schaden, um spam zu verhindern.
		p.damage(2);
		return false;
	}

	//Hat der Spieler die Rechte, um global auf alle GS Zugriff zu erhalten?
	private boolean hasWorldPermission(Player p) {
		if(p.isOp() || p.hasPermission("gsplugin.plugin.buildeverywhere"))
			return true;
		return false;
	}
}
