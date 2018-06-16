package com.github.herbert.playerplugin.playerdata;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.github.herbert.playerplugin.skillevents.SkillLevelUpEvent;
import com.github.herbert.playerplugin.skills.Skill;
import com.github.herbert.playerplugin.skills.SkillType;
import com.github.herbert.worldblocksplugin.GSinteractor.GSinteractor;
import com.github.herbert.worldblocksplugin.GSinteractor.Member;

public class HerbertPlayer  implements GSinteractor, Listener {

	private Player p;
	private Map<SkillType, Skill> playerSkills = new HashMap<SkillType, Skill>();
	private Member[] members = new Member[1];
	
	private int playerlvl;
	private int playerxp;
	//Konstruktor, der alle Fähigkeiten neu erstellt (Lvl 1)
	public HerbertPlayer(Player player) {
		p=player;
		for(SkillType skilltype : SkillType.values()) {
			
			playerSkills.put(skilltype, SkillType.newSkill(skilltype));
		}
		members[0]=new Member(player.getUniqueId(), 31);
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
		members[0]=new Member(player.getUniqueId(), 31);
	}
	@EventHandler
	public void onSkillLevelUp(SkillLevelUpEvent event) {
		//Wenn der gelevelte Skill zu diesem Spieler gehört
		if(playerSkills.get(event.getSkill().getType()).equals(event.getSkill())) {
			addPlayerXP(1);
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
	@Override
	public String getIdent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Member[] getMembers() {
		return members;
	}

	@Override
	public String getName() {
		return p.getName();
	}
	public String toString() {
		return p.getUniqueId().toString();
	}

}
