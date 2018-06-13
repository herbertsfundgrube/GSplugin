package com.github.herbert.worldblocksplugin.events.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import com.github.herbert.worldblocksplugin.WorldBlocksPlugin;
import com.github.herbert.worldblocksplugin.events.WorldblockBlockEvent;
import com.github.herbert.worldblocksplugin.worldblocks.GS;

public class BlockEventListener implements Listener {
	
	WorldBlocksPlugin plugin;
	
	public BlockEventListener(WorldBlocksPlugin plugin) {
		this.plugin=plugin;
	}
	//-------------------------------------------------------------------------------
	//---Serverweit BlockPlace prüfen -> findet es auf einem WorldBlock statt?-------
	//-------------------------------------------------------------------------------
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onBlockPlaceEvent(BlockPlaceEvent event) {
		if(!plugin.hasRegisteredWorldBlocks()) {
			plugin.getMain().debug("Server hat kein GS");
			return;
		}
		Player p = event.getPlayer();
		Location loc=event.getBlock().getLocation();
		GS gs = getGs(p, loc);
		if(gs != null) 
			//if(!hasWorldPermission(p)) -> TODO Später für Permissions mitkompilieren
			Bukkit.getServer().getPluginManager().callEvent(new WorldblockBlockEvent(gs, p, event.getBlock(), event));
			
	}
	
	//-------------------------------------------------------------------------------
	//---Serverweit BlockBreak prüfen -> findet es auf einem WordlBlock statt?-------
	//-------------------------------------------------------------------------------
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onBlockBreakEvent(BlockBreakEvent event) {
		if(!plugin.hasRegisteredWorldBlocks()) {
			plugin.getMain().debug("Server hat kein GS");
			return;
		}
		Player p = event.getPlayer();
		Location loc=event.getBlock().getLocation();
		GS gs = getGs(p, loc);
		if(gs != null)
		{
			Bukkit.getServer().getPluginManager().callEvent(new WorldblockBlockEvent(gs, p, event.getBlock(), event));
		}
			
	}
	//Event wird ausgelöst, wenn Truhen oder ähnliches angeklickt werden.
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		if(!plugin.hasRegisteredWorldBlocks()) {
			plugin.getMain().debug("Server hat kein GS");
			return;
		}
		//if(event.getAction()==Action.RIGHT_CLICK_BLOCK) 
		//{
		//	Block block = event.getClickedBlock();
		//	Player p = event.getPlayer();
		//	if (block.getType().equals(Material.CHEST)) {
		//		
		//	}
		//}
		
	}
	
	
	
	
	
	//GS bei <loc> suchen
	private GS getGs(Player p, Location loc) {
		//Wenn der Block auf der Miningebene liegt
		if(loc.getY()< plugin.getMain().getConfigInt("gs.lowestProtectedY"))
			return null;
		
		//Wenn in der GSlist kein GS mit dieser Location eingetragen ist, ist diese BlockChange ebenfalls erlaubt
		if(plugin.data.getGSList().getGS(loc)==null) {
			plugin.getMain().debug(p.getName() + " ist auf keinem GS");
			return null;
		}
		//Location ist auf GS
		return plugin.data.getGSList().getGS(loc);
	}
}