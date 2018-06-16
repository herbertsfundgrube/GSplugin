package com.github.herbert.playerplugin.playerdata;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;

import com.github.herbert.playerplugin.skills.Skill;
import com.github.herbert.playerplugin.skills.SkillType;
import com.github.herbert.worldblocksplugin.GSinteractor.GSinteractor;
import com.github.herbert.worldblocksplugin.GSinteractor.Member;

public class HerbertPlayer implements GSinteractor {

	private Player p;
	private Map<SkillType, Skill> playerSkills = new HashMap<SkillType, Skill>();
	
	//Konstruktor, der alle Fähigkeiten neu erstellt (Lvl 1)
	public HerbertPlayer(Player player) {
		p=player;
		for(SkillType skilltype : SkillType.values()) {
			
			playerSkills.put(skilltype, SkillType.newSkill(skilltype));
		}
	}
	//Konstruktor, der vorhandene Skills lädt und die restlichen neu erstellt (Lvl 1)
	public HerbertPlayer(Player player, Skill[] skills) {
		p=player;
		for(Skill skill : skills) {
			playerSkills.put(skill.getType(), skill);
		}
		for(SkillType skilltype : SkillType.values()) {
			if(playerSkills.containsKey(skilltype))
				playerSkills.put(skilltype, SkillType.newSkill(skilltype));
		}
	}
	public void addXP(SkillType type, double xp) {
		playerSkills.get(type).addXp(xp);
	}
	public Player getPlayer() {
		return p;
	}
	
	public Map<SkillType, Skill> getSkillsMap() {
		return playerSkills;
	}
	@Override
	public String getIdent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Member[] getMembers() {
		//TODO
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return p.getName();
	}
	public String toString() {
		//TODO
		return "";
	}

}
