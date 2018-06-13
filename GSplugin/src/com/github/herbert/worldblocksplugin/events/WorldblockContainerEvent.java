package com.github.herbert.worldblocksplugin.events;

import org.bukkit.block.Container;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.github.herbert.worldblocksplugin.worldblocks.Worldblock;

public class WorldblockContainerEvent extends Event implements Cancellable {
	
	public static HandlerList handlers = new HandlerList();
	
	private Cancellable cancel;
	private Container cont;
	private Player p;
	private Worldblock wb;
	
	public WorldblockContainerEvent(Worldblock wb, Player p, Container cont, Cancellable cancel) {
		this.cancel=cancel;
		this.cont=cont;
		this.p=p;
		this.wb=wb;
	}
	
	public Container getContainer() {
		return cont;
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
