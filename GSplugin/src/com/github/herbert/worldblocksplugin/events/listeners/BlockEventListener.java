package com.github.herbert.worldblocksplugin.events.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Container;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.material.Openable;
import org.bukkit.material.Redstone;

import com.github.herbert.worldblocksplugin.WorldBlocksPlugin;
import com.github.herbert.worldblocksplugin.events.WorldblockBlockEvent;
import com.github.herbert.worldblocksplugin.events.WorldblockContainerEvent;
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
	@EventHandler(priority=EventPriority.HIGHEST)
	public void onPlayerInteract(PlayerInteractEvent event) {
		if(!plugin.hasRegisteredWorldBlocks()) {
			plugin.getMain().debug("Server hat kein GS");
			return;
		}
		//Nur Rechtsklick-Events werden behandelt (Truhen, Redstone, Türen)
		if(!(event.getAction()==Action.RIGHT_CLICK_BLOCK)) 
			return;
		
		BlockState blockstate = event.getClickedBlock().getState();
		
		if(blockstate instanceof Container)  {
			plugin.getMain().debug("Der Block ist ein Container!");
			Container cont = (Container) blockstate;
			GS gs = getGs(event.getPlayer(), cont.getLocation());
			if(gs != null)  {
				Bukkit.getServer().getPluginManager().callEvent(new WorldblockContainerEvent(gs, event.getPlayer(), cont, event));
				return;
			}
		}
		if(blockstate instanceof Redstone) {
			plugin.getMain().debug("Der Block ist eine Redstoneinstanz!");
			Redstone reds = (Redstone) blockstate;
			GS gs = getGs(event.getPlayer(), blockstate.getLocation());
			if(gs != null) {
				//TODO: WorldblockRedstoneEvent schreiben
				return;
			}
		}
		if(blockstate instanceof Openable) {
			plugin.getMain().debug("Der Block ist eine Openableinstanz!");
			Openable open = (Openable) blockstate;
			GS gs = getGs(event.getPlayer(), blockstate.getLocation());
			if(gs != null) {
				//TODO: WorldblockOpenableEvent schreiben
				return;
			}
		}
		
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