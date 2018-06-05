package com.github.herbert.gsplugin.events;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import com.github.herbert.gsplugin.GSplugin;
import com.github.herbert.gsplugin.GS.GS;

public class BlockEventListener implements Listener {
	
	GSplugin plugin;
	
	public BlockEventListener(GSplugin p) {
		plugin=p;
	}
	//Abfangen des BlockPlaceEvents
	@EventHandler
	public void onBlockPlaceEvent(BlockPlaceEvent event) {
		//Wenn das Event den Grundlegenden Regeln des GS-Plugins nicht entspricht, wird es gecancelt.
		if(isGsBlockChangePermitted(event.getPlayer(), event.getBlock().getLocation())) 
			event.setCancelled(true);
			
		
	}
	//Abfangen des BlockBreakEvents
	public void onBlockBreakEvent(BlockBreakEvent event) {
		//Wenn das Event den Grundlegenden Regeln des GS-Plugins nicht entspricht, wird es gecancelt.
		if(isGsBlockChangePermitted(event.getPlayer(), event.getBlock().getLocation())) 
			event.setCancelled(true);
			
	}
	
	
	//Ob die Änderung eines Blocks - nach dem grundlegenden GS-Prinzip - gestattet ist.
	//Das heißt: Immer true, wenn der Block entweder auf keinem GS bzw. auf der Mining-Ebene liegt
	//oder wenn der Spieler die Permission "8" (bauen) auf dem GS hat.
	//Der Code würde sich in BlockPlace und BlockBreak doppeln, daher ist er als Methode ausgelagert.
	private boolean isGsBlockChangePermitted(Player p, Location loc) {
		
		//Wenn der Spieler Operator ist oder die entsprechende Permission (Admin) hat
		if(p.isOp() || p.hasPermission("gsplugin.buildeverywhere"))
			return true;
		//Wenn kein GS auf dem Server gelistet ist, ist BlockChange immer erlaubt
		if(!serverHasGs())
			return true;
		//Wenn der Block auf der Miningebene liegt
		if(loc.getY()< plugin.getConfigInt("gs.lowestProtectedY"))
			return true;
		
		GS gs = plugin.gslist.getGS(loc);
		//Wenn in der GSlist kein GS mit dieser Location eingetragen ist, ist diese BlockChange ebenfalls erlaubt
		if(gs==null)
			return true;
		//Wenn ein GS gefunden wurde, auf dem der Player die Permission 3 hat, dann ist die Aktion ebenfalls erlaubt.
		if(gs.hasPermission(p, (byte) 8))
			return true;
		//In allen übrigen Fällen ist die Änderung nicht erlaubt.
		return false;
	}
	
	
	//True, wenn GS auf dem Server gelistet sind
	public boolean serverHasGs() {
		if(plugin.gslist==null) {
			return false;
		}
		return true;
	}
}
