package com.github.herbert.playerplugin.skillevents;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.github.herbert.playerplugin.skills.Skill;

public class MasteryXPEvent extends Event {
public static HandlerList handlers = new HandlerList();
	
	private Skill skill;
	private double xp;
	
	public MasteryXPEvent(Skill skill, double xp) {
		this.skill=skill;
	}
	
	public Skill getSkill() {
		return skill;
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
