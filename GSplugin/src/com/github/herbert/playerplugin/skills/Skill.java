package com.github.herbert.playerplugin.skills;

import org.bukkit.Bukkit;

import com.github.herbert.playerplugin.skillevents.MasteryXPEvent;
import com.github.herbert.playerplugin.skillevents.SkillLevelUpEvent;

public abstract class Skill {
	private int lvl;
	private double xp;
	
	public Skill(int lvl, double xp) {
		this.lvl=lvl;
		this.xp=xp;
	}
	public void addXp(double xp) {
		if(lvl>=100) {
			Bukkit.getServer().getPluginManager().callEvent(new MasteryXPEvent(this, xp));
			return;
		}
		//TODO: Level-Kurve einbinden, Levelups einbinden
		this.xp+=xp;
		//Zu Testzwecken werden 100 XP benötigt.
		if(this.xp>=100) {
			this.xp-=100;
			lvl+=1;
			//Auf lvl 100 werden keine Skill-XP mehr gesammelt. Daher werden sie auf 0 gesetzt.
			if(lvl==100)
				this.xp=0;
			Bukkit.getServer().getPluginManager().callEvent(new SkillLevelUpEvent(this));
		}
	}
	public int getLvl() {
		return lvl;
	}
	public double getXP() {
		return xp;
	}
	public abstract SkillType getType();
}
