package com.github.herbert.worldblocksplugin.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.material.Redstone;

import com.github.herbert.worldblocksplugin.worldblocks.Worldblock;

public class WorldblockRedstoneEvent extends Event implements Cancellable {
	
	public static HandlerList handlers = new HandlerList();
	
	private Cancellable cancel;
	private Redstone reds;
	private Player p;
	private Worldblock wb;
	
	public WorldblockRedstoneEvent(Worldblock wb, Player p, Redstone reds, Cancellable cancel) {
		this.cancel=cancel;
		this.reds=reds;
		this.p=p;
		this.wb=wb;
	}
	
	public Redstone getRedstone() {
		return reds;
	}
	public Player getPlayer() {
		return p;
	}
	public Worldblock getWorldblock() {
		return wb;
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
