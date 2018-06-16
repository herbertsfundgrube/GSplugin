package com.github.herbert.playerplugin.skills;
import com.github.herbert.playerplugin.skills.passive.*;
import com.github.herbert.playerplugin.skills.craft.*;
import com.github.herbert.playerplugin.skills.combat.*;

public enum SkillType {
	SWORD_FIGHTING, MINING, SPEECH;
	
	public static Skill newSkill(SkillType type) {
		if(type.equals(SkillType.SPEECH))
			return new Speech(1, 0);
		if(type.equals(SkillType.MINING))
			return new Mining(1, 0);
		if(type.equals(SkillType.SWORD_FIGHTING))
			return new SwordFighting(1, 0);
		return null;
	}
	public static Skill newSkill(SkillType type, int lvl, double xp) {
		if(type.equals(SkillType.SPEECH))
			return new Speech(lvl, xp);
		if(type.equals(SkillType.MINING))
			return new Mining(lvl, xp);
		if(type.equals(SkillType.SWORD_FIGHTING))
			return new SwordFighting(lvl, xp);
		return null;
	}
}
