package com.github.herbert.playerplugin.skills;


import org.bukkit.Bukkit;

import com.github.herbert.PluginConfiguration;
import com.github.herbert.playerplugin.PlayerPlugin;
import com.github.herbert.playerplugin.skillevents.MasteryXPEvent;

import net.md_5.bungee.api.ChatColor;

public abstract class Skill {
	private int lvl;
	private double xp;
	private int skillpoints = 0;
	private double nextxp;
	private double freexp;
	public int maxlvl;
	
	public Skill(int lvl, double xp, double freexp) {
		this.lvl=lvl;
		this.xp=xp;
		this.freexp=freexp;
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
			xp = xp * 10;
			xp = Math.round(xp);
			xp = xp/ 10;
			return;
		}
		//XP werden hinzugefügt, wenn keine der Bedingungen abgefangen wurde
		xp+=tempxp;
		//Auf zwei Nachkommastellen
		xp = xp * 10;
		xp = Math.round(xp);
		xp = xp/ 10;
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
		//Auf zwei Nachkommastellen runden.
		freexp = freexp * 10;
		freexp = Math.round(freexp);
		freexp = freexp/ 10;
	}

	public String getInfo() {
		String info = PlayerPlugin.getHeader(getType().displayName());
		info += "\n"+"Fähigkeitslevel: " + ChatColor.DARK_GREEN + getLvl()+ ChatColor.RESET+" / " + ChatColor.GRAY + maxlvl;
		info += "\n"+"Fähigkeitserfahrung: " + ChatColor.DARK_GREEN + getXP() + ChatColor.RESET+" / " + ChatColor.GRAY + nextxp;
		if(freexp > 0)
			info += "\n"+"Freie Fähigkeitserfahrung: " + ChatColor.GOLD+getFreeXP();
		return info;
	}
	public void levelUp() {
		xp=0;
		lvl+=1;
		nextxp=levelCurve(lvl);
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
