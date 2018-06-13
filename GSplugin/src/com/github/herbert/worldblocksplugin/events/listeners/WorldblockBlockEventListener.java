package com.github.herbert.worldblocksplugin.events.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.github.herbert.worldblocksplugin.WorldBlocksPlugin;
import com.github.herbert.worldblocksplugin.events.WorldblockBlockEvent;
import com.github.herbert.worldblocksplugin.worldblocks.Worldblock;

import net.md_5.bungee.api.ChatColor;

public class WorldblockBlockEventListener implements Listener {
	WorldBlocksPlugin plugin;
	public WorldblockBlockEventListener(WorldBlocksPlugin wbplugin) {
		plugin=wbplugin;
	}
	//-------------------------------------------------------------------------------
	//-------------------------------------------------------------------------------
	//---Listener für Blockänderungen auf WorldBlocks--------------------------------
	//-------------------------------------------------------------------------------
	//-------------------------------------------------------------------------------
	@EventHandler
	public void onBlockChange(WorldblockBlockEvent event) {
		if(!checkBlockPermission(event.getPlayer(), event.getWorldblock()))
			event.setCancelled(true);
	}
	
	
	
	//-------------------------------------------------------------------------------
	//---Auslagerung: Hat der <p> für den <worldblock> die Permission 8 -------------
	//---(bauen/abbauen)?------------------------------------------------------------
	//-------------------------------------------------------------------------------
		private boolean checkBlockPermission(Player p, Worldblock wb) {
			if(hasWorldPermission(p))
				return true;
			if(wb.hasPermission(p, (byte) 8)) {
				plugin.getMain().debug(p.getName() + " hat die Permissions für das GS" + wb.getCoords().getX() + " / " + wb.getCoords().getZ());
				return true;
			}
			//In allen übrigen Fällen ist die Änderung nicht erlaubt. Der Spieler nimmt Schaden, um spam zu verhindern.
			p.sendMessage(plugin.convMessage(ChatColor.RED + "Ihr dürft hier nicht bauen!"));
			p.damage(2);
			return false;
		}
	//-------------------------------------------------------------------------------
	//---Auslagerung: Überprüfen von Mod- und Operatorpermissions--------------------
	//-------------------------------------------------------------------------------
		private boolean hasWorldPermission(Player p) {
			if(p.isOp() || p.hasPermission("gsplugin.plugin.buildeverywhere"))
				return true;
			return false;
		}
}
