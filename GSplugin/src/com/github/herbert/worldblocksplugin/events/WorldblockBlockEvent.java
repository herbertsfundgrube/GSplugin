package com.github.herbert.worldblocksplugin.events;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.github.herbert.worldblocksplugin.worldblocks.Worldblock;

public class WorldblockBlockEvent extends Event implements Cancellable {
	
	public static HandlerList handlers = new HandlerList();
	
	private Worldblock worldblock;
	private Player player;
	private Block block;
	private Cancellable cancel;
	public WorldblockBlockEvent(Worldblock wb, Player p, Block b, Cancellable c) {
		this.worldblock=wb;
		this.player=p;
		this.block=b;
		cancel=c;
		
	}

	public Worldblock getWorldblock() {
		return worldblock;
	}
	public Player getPlayer() {
		return player;
	}
	public Block getBlock() {
		return block;
	}

	@Override
	public boolean isCancelled() {
		return cancel.isCancelled();
	}

	@Override
	public void setCancelled(boolean bool) {
		cancel.setCancelled(bool);
		
	}
	
	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
	public static HandlerList getHandlerList() {
		return handlers;
	}
	
}
