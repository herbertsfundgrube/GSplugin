package com.github.herbert.userinterfaceplugin.gui.actions;

import org.bukkit.entity.Player;

public abstract class GUIAction {
	Player p;
	public GUIAction(Player p) {
		this.p=p;
	}
	
	public abstract void performAction();
}
