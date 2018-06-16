package com.github.herbert.playerplugin.playerdata;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;

import com.github.herbert.playerplugin.skills.CombatSkill;
import com.github.herbert.playerplugin.skills.CraftSkill;
import com.github.herbert.playerplugin.skills.PassiveSkill;
import com.github.herbert.playerplugin.skills.Skill;
import com.github.herbert.playerplugin.skills.SkillType;
import com.github.herbert.worldblocksplugin.GSinteractor.GSinteractor;
import com.github.herbert.worldblocksplugin.GSinteractor.Member;

public class HerbertPlayer implements GSinteractor {

	Player p;
	Map<SkillType, CombatSkill> comSkills = new HashMap<SkillType, CombatSkill>();
	Map<SkillType, CraftSkill> craftSkills = new HashMap<SkillType, CraftSkill>();
	Map<SkillType, PassiveSkill> passiveSkills = new HashMap<SkillType, PassiveSkill>();
	
	public HerbertPlayer(Player player) {
		p=player;
	}
	public HerbertPlayer(Player player, Skill[] skills) {
		p=player;
		for(Skill skill : skills) {
			//CombatSkills einspeichern
			if(skill instanceof CombatSkill)
				comSkills.put(skill.getType(), (CombatSkill) skill);
			//CraftSkill zuweisen
			if(skill instanceof CraftSkill)
				craftSkills.put(skill.getType(), (CraftSkill) skill);
			//Ansonsten muss es sich um einen passiven Skill handeln
			passiveSkills.put(skill.getType(), (PassiveSkill) skill);
		}
	}
	
	public Player getPlayer() {
		return p;
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
