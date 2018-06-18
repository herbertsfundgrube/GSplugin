package com.github.herbert.playerplugin.skills;
import com.github.herbert.playerplugin.skills.passive.*;
import com.github.herbert.playerplugin.skills.craft.*;
import com.github.herbert.playerplugin.skills.combat.*;

import java.util.HashMap;

import org.bukkit.Material;

public enum SkillType {
	SWORD_FIGHTING("Schwertkampf"), MINING("Bergbau"), SPEECH("Redekunst");
	private String displayName;

    SkillType(String displayName) {
        this.displayName = displayName;
    }
	public static Skill newSkill(SkillType type) {
		if(type.equals(SkillType.SPEECH))
			return new Speech(1, 0, 0);
		if(type.equals(SkillType.MINING))
			return new Mining(1, 0, 0);
		if(type.equals(SkillType.SWORD_FIGHTING))
			return new SwordFighting(1, 0, 0);
		return null;
	}
	public static Skill newSkill(SkillType type, int lvl, double xp, double freexp) {
		if(type.equals(SkillType.SPEECH))
			return new Speech(lvl, xp, freexp);
		if(type.equals(SkillType.MINING))
			return new Mining(lvl, xp, freexp);
		if(type.equals(SkillType.SWORD_FIGHTING))
			return new SwordFighting(lvl, xp, freexp);
		return null;
		
	}
	
	public static SkillType getByDisplayName(String name) {
		if(name.equalsIgnoreCase(SPEECH.displayName))
			return SkillType.SPEECH;
		if(name.equalsIgnoreCase(MINING.displayName))
			return SkillType.MINING;
		if(name.equalsIgnoreCase(SWORD_FIGHTING.displayName))
			return SkillType.SWORD_FIGHTING;
		
		return null;
	}
	public String displayName() {
		return displayName;
	}
	public static HashMap<Material, Double> getBlockXP(SkillType type) {
		HashMap<Material, Double> friedhelm = new HashMap<Material, Double>();
		return friedhelm;
	}
}
