package com.github.herbert.gsplugin.events;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;

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
		Player p = event.getPlayer();
		Location loc=event.getBlock().getLocation();
		//Wenn das Event den Grundlegenden Regeln des GS-Plugins nicht entspricht, wird es gecancelt.
		if(!isOnGs(p, loc) && canGsBlockBeChanged(p, loc)) 
			//if(!hasWorldPermission(p))
			{
				event.getPlayer().sendMessage(plugin.convMessage("Dir fehlt die Berechtigung, um hier zu bauen."));
				event.setCancelled(true);
			}
			
		
	}
	//Abfangen des BlockBreakEvents
	@EventHandler
	public void onBlockBreakEvent(BlockBreakEvent event) {
		Player p = event.getPlayer();
		Location loc=event.getBlock().getLocation();
		//Wenn das Event den Grundlegenden Regeln des GS-Plugins nicht entspricht, wird es gecancelt.
		if(!isOnGs(p, loc) && !canGsBlockBeChanged(p, loc)) 
		
			//if(!hasWorldPermission(p))
			{
				event.getPlayer().sendMessage(plugin.convMessage("Dir fehlt die Berechtigung, um hier zu abzubauen."));
				event.setCancelled(true);
			}
			
	}
	//Event zum prüfen der Truhernpermissions
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		if(event.getAction()==Action.RIGHT_CLICK_BLOCK) {
			Block block = event.getClickedBlock();
			Player p = event.getPlayer();
			if( hasContainerPermission(p, block)) {
				
			}
		}
		
	}
	
	
	
	//Hat der <p> für das GS bei <loc> die Permission 8 (bauen/abbauen)?
	private boolean canGsBlockBeChanged(Player p, Location loc) {
		GS gs = plugin.gslist.getGS(loc);
		//Wenn ein GS gefunden wurde, auf dem der Player die Permission 3 hat, dann ist die Aktion erlaubt.
		if(gs.hasPermission(p, (byte) 8)) {
			plugin.getServer().broadcastMessage(p.getName() + " hat die Permissions für das GS" + gs.getCoords().getX() + " / " + gs.getCoords().getZ());
			return true;
		}
		//In allen übrigen Fällen ist die Änderung nicht erlaubt. Der Spieler nimmt Schaden, um spammen zu verhindern.
		p.damage(2);
		return false;
	}
	
	//Liegt <loc> auf einem GS?
	private boolean isOnGs(Player p, Location loc) {
		//Wenn kein GS auf dem Server gelistet ist, ist BlockChange immer erlaubt
		if(!serverHasGs()) {
			plugin.getServer().broadcastMessage("Server hat kein GS");
			return false;
		}
		//Wenn der Block auf der Miningebene liegt
		if(loc.getY()< plugin.getConfigInt("gs.lowestProtectedY"))
			return false;
		
		//Wenn in der GSlist kein GS mit dieser Location eingetragen ist, ist diese BlockChange ebenfalls erlaubt
		if(plugin.gslist.getGS(loc)==null) {
			plugin.getServer().broadcastMessage(p.getName() + " ist auf keinem GS");
			return false;
		}
		//Location ist auf GS
		return true;
	}
	//Hat der Spieler die Rechte, um global auf alle GS Zugriff zu erhalten?
	private boolean hasWorldPermission(Player p) {
		if(p.isOp() || p.hasPermission("gsplugin.buildeverywhere"))
			return true;
		return false;
	}
	
	private boolean hasContainerPermission(Player p, Block b) {
		
		p.damage(2);
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