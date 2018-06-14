package com.github.herbert.worldblocksplugin.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.material.Openable;

import com.github.herbert.worldblocksplugin.worldblocks.Worldblock;

public class WorldblockOpenableEvent extends Event implements Cancellable {
	
	public static HandlerList handlers = new HandlerList();
	
	private Cancellable cancel;
	private Openable open;
	private Player p;
	private Worldblock wb;
	
	public WorldblockOpenableEvent(Worldblock wb, Player p, Openable openable, Cancellable cancel) {
		this.cancel=cancel;
		open=openable;
		this.p=p;
		this.wb=wb;
	}
	
	public Openable getOpenable() {
		return open;
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

