package com.github.herbert.playerplugin.skillevents;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.github.herbert.playerplugin.skills.SkillType;

public class CraftSkillEvent extends Event {

	public static HandlerList handlers = new HandlerList();
	
	private SkillType skilltype;
	private double xp;
	
	public CraftSkillEvent(SkillType type, double xp) {
		skilltype=type;
		this.xp=xp;
	}
	
	public SkillType getSkillType() {
		return skilltype;
	}
	public double getXP() {
		return xp;
	}
	
	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
	public static HandlerList getHandlerList() {
		return handlers;
	}

}
