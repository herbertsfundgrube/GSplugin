package com.github.herbert.playerplugin.playerdata;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.github.herbert.playerplugin.PlayerPlugin;
import com.github.herbert.playerplugin.skillevents.MasteryXPEvent;
import com.github.herbert.playerplugin.skills.Skill;
import com.github.herbert.playerplugin.skills.SkillType;
import com.github.herbert.worldblocksplugin.GSinteractor.Member;

import net.md_5.bungee.api.ChatColor;

public class HerbertPlayer  implements Listener {

	private Player p;
	private Map<SkillType, Skill> playerSkills = new HashMap<SkillType, Skill>();
	private Member[] members = new Member[1];
	
	private int playerlvl;
	private double playerxp;
	private double masteryxp;
	//Konstruktor, der alle Fähigkeiten neu erstellt (Lvl 1)
	public HerbertPlayer(Player player) {
		p=player;
		for(SkillType skilltype : SkillType.values()) {
			
			playerSkills.put(skilltype, SkillType.newSkill(skilltype));
		}
		members[0]=new Member(player.getUniqueId(), 31);
		playerlvl = 1;
	}
	//Konstruktor, der vorhandene Skills lädt und die Restlichen neu erstellt (Lvl 1)
	public HerbertPlayer(Player player, Skill[] skills, int level, double xp, double masteryxp) {
		p=player;
		for(Skill skill : skills) {
			System.out.println("es wird geputtet: "+skill.getType()+" für den Skilltyp, "+skill.getXP()+" für die XP im Skill.");
			playerSkills.put(skill.getType(), skill);
		}
		for(SkillType skilltype : SkillType.values()) {
			if(!playerSkills.containsKey(skilltype)) {
				playerSkills.put(skilltype, SkillType.newSkill(skilltype));
				System.out.println("es wird geputtet: "+skilltype+" für den Skilltyp und ein neuer Skill dazu.");
			}
		}
		members[0]=new Member(player.getUniqueId(), 31);
		
		playerlvl = level;
		playerxp = xp;
		this.masteryxp=masteryxp;
	}
	@EventHandler
	public void onMasteryXP(MasteryXPEvent event) {
		if(playerSkills.get(event.getSkill().getType()).equals(event.getSkill())) {
			masteryxp+=event.getXP();
		}
	}
	
	public void addSkillXP(SkillType type, double xp) {
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
	public double getPlayerXP() {
		return playerxp;
	}
	public double getMasteryXP() {
		return masteryxp;
	}
	public Map<SkillType, Skill> getSkillsMap() {
		return playerSkills;
	}
	public String toString() {
		return p.getUniqueId().toString();
	}
	public String getPlayerInfo() {
		String info = PlayerPlugin.getHeader(getPlayer().getName());
		if(playerlvl < 30) {
			info += "\n"+"Spielerlevel: " + ChatColor.DARK_GREEN + playerlvl;
		} else
			info += "\n"+"Spielerlevel: " + ChatColor.GOLD + playerlvl;
		info += "\n"+"Spielererfahrung: "+ ChatColor.DARK_GREEN + playerxp+ChatColor.RESET+" / "+ChatColor.GRAY+levelCurve(playerlvl);
		if(masteryxp > 0)
			info += "/n"+"Meisterschafts-XP: " + ChatColor.GOLD + masteryxp;
		
		return info;
	}
	public String getSkillInfo(String skill) {
		SkillType type = SkillType.getByDisplayName(skill);
		if(type==null) {
			String info = PlayerPlugin.getHeader();
			info += "\nEs konnte keine Fähigkeit Namens "+skill+" gefunden werden.";
			info += "\nFolgende Fähigkeiten gibt es: " +  ChatColor.YELLOW;
			for(SkillType t : SkillType.values()) {
				info += "\n"+t.displayName();
			}
			return info;
		}
		Skill s = playerSkills.get(type);
		return s.getInfo();
	}

	public double levelCurve(int lvl) {
		return lvl*5040/(lvl+30);
	}

}
