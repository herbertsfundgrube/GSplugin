package com.github.herbert.playerplugin.skillevents;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.github.herbert.playerplugin.skills.SkillType;

public class SkillXPEvent extends Event {
	public static HandlerList handlers = new HandlerList();
	
	private SkillType skill;
	
	public SkillXPEvent(SkillType skill) {
		this.skill=skill;
	}
	
	public SkillType getSkillType() {
		return skill;
	}
	
	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
	public static HandlerList getHandlerList() {
		return handlers;
	}
}
