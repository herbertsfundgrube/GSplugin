package com.github.herbert.playerplugin.skillevents;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.github.herbert.playerplugin.skills.Skill;

public class SkillLevelUpEvent extends Event {

	public static HandlerList handlers = new HandlerList();
	
	private Skill skill;
	
	public SkillLevelUpEvent(Skill skill) {
		this.skill=skill;
	}
	
	public Skill getSkill() {
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
