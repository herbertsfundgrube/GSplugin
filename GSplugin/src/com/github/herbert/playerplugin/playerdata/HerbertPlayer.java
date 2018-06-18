package com.github.herbert.playerplugin.playerdata;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.github.herbert.playerplugin.skillevents.MasteryXPEvent;
import com.github.herbert.playerplugin.skills.Skill;
import com.github.herbert.playerplugin.skills.SkillType;
import com.github.herbert.worldblocksplugin.GSinteractor.Member;

public class HerbertPlayer  implements Listener {

	private Player p;
	private Map<SkillType, Skill> playerSkills = new HashMap<SkillType, Skill>();
	private Member[] members = new Member[1];
	
	private int playerlvl;
	private int playerxp;
	private double masteryxp;
	//Konstruktor, der alle Fähigkeiten neu erstellt (Lvl 1)
	public HerbertPlayer(Player player) {
		p=player;
		for(SkillType skilltype : SkillType.values()) {
			
			playerSkills.put(skilltype, SkillType.newSkill(skilltype));
		}
		members[0]=new Member(player.getUniqueId(), 31);
	}
	//Konstruktor, der vorhandene Skills lädt und die Restlichen neu erstellt (Lvl 1)
	public HerbertPlayer(Player player, Skill[] skills) {
		p=player;
		for(Skill skill : skills) {
			playerSkills.put(skill.getType(), skill);
		}
		for(SkillType skilltype : SkillType.values()) {
			if(playerSkills.containsKey(skilltype))
				playerSkills.put(skilltype, SkillType.newSkill(skilltype));
		}
		members[0]=new Member(player.getUniqueId(), 31);
	}
	@EventHandler
	public void onMasteryXP(MasteryXPEvent event) {
		if(playerSkills.get(event.getSkill().getType()).equals(event.getSkill())) {
			masteryxp+=event.getXP();
		}
	}
	
	public void addXP(SkillType type, double xp) {
		playerSkills.get(type).addXp(xp);
	}
	public void addPlayerXP(int xp) {
		if(playerxp+xp==2) {
			playerxp=0;
			playerlvl+=1;
		}
		playerxp+=xp;
	}
	
	public Player getPlayer() {
		return p;
	}
	public int getPlayerLvl() {
		return playerlvl;
	}
	public Map<SkillType, Skill> getSkillsMap() {
		return playerSkills;
	}
	public String toString() {
		return p.getUniqueId().toString();
	}

}
