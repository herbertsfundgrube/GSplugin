package com.github.herbert.playerplugin.skills;

public abstract class Skill {
	private int lvl;
	private double xp;
	
	public Skill(int lvl, double xp) {
		this.lvl=lvl;
		this.xp=xp;
	}
	public void addXp(double xp) {
		//TODO: Level-Kurve einbinden, Levelups einbinden
		this.xp*=xp;
	}
	public int getLvl() {
		return lvl;
	}
	public double getXP() {
		return xp;
	}
	public abstract SkillType getType();
}
