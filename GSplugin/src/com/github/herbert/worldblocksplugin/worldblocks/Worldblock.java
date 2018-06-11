package com.github.herbert.worldblocksplugin.worldblocks;

import org.bukkit.entity.Player;

import com.github.herbert.worldblocksplugin.GSinteractor.GSinteractor;
import com.github.herbert.worldblocksplugin.datenstruktur.GSCoords;

public interface Worldblock {
	public GSCoords getCoords();
	public GSinteractor getOwner();
	public String[] getInfo();
	public boolean hasPermission(Player p, int i);
	public void setPublicPerms(byte b);
	public String toString();
}
