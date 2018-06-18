package com.github.herbert.playerplugin.skills;

import org.bukkit.Bukkit;

import com.github.herbert.PluginConfiguration;
import com.github.herbert.playerplugin.skillevents.MasteryXPEvent;

public abstract class Skill {
	private int lvl;
	private double xp;
	private int skillpoints = 0;
	private double nextxp;
	private double freexp;
	public int maxlvl;
	
	public Skill(int lvl, double xp) {
		this.lvl=lvl;
		this.xp=xp;
		nextxp=levelCurve(lvl);
		maxlvl = PluginConfiguration.getInt("players.maximumSkillLvl");
	}
	public void addXp(double tempxp) {
		//Wenn der Skill über das maximale Level hinaus gelevelt ist, dann wird das Level angepasst und XP zurückerstattet.
		if(lvl > maxlvl) {
			double extraxp = 0;
			while(lvl > maxlvl) {
				lvl-=1;
				extraxp+=levelCurve(lvl);
			}
			Bukkit.getServer().getPluginManager().callEvent(new MasteryXPEvent(this, extraxp));
		}
		//Wenn der Skill das maximale Skilllevel hat, dann werden stattdessen Mastery-XP hinzugefügt.
		if(lvl==PluginConfiguration.getInt("players.maximumSkillLvl")) {
			xp=0;
			Bukkit.getServer().getPluginManager().callEvent(new MasteryXPEvent(this, tempxp));
			return;
		}
		//Wenn nach dem Hinzufügen der tempXP die levelup-Grenze für dieses Level überschritten wird
		if(xp+tempxp>=levelCurve(lvl)) {
			handleXPBorder(tempxp);
			return;
		}
		//XP werden hinzugefügt, wenn keine der Bedingungen abgefangen wurde
		xp+=tempxp;
	}
	/* 
	 * Methode wird aufgerufen, wenn die neuen XP den
	 * Grenzwert zum nächsten Level übersteigen.
	 */
	private void handleXPBorder(double tempxp) {
		//Falls noch XP fehlen, werden diese aufgefüllt
		if(xp<nextxp) {
			tempxp -= nextxp-xp;
			xp=nextxp;
		}
		//XP werden zu den TempXP hinzugefügt
		freexp+=tempxp;
	}
	public void levelUp() {
		xp=0;
		lvl+=1;
	}
	public int getLvl() {
		return lvl;
	}
	public double getXP() {
		return xp;
	}
	public double getFreeXP() {
		return freexp;
	}
	public int getSkillpoints() {
		return skillpoints;
	}
	public double getRequiredXP() {
		return levelCurve(lvl) - xp;
	}
	public double levelCurve(int lvl) {
		return lvl*5040/(lvl+30);
	}
	public abstract SkillType getType();
}
