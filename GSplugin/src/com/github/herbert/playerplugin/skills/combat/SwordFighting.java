package com.github.herbert.playerplugin.skills.combat;

import com.github.herbert.playerplugin.skills.CombatSkill;
import com.github.herbert.playerplugin.skills.SkillType;

public class SwordFighting extends CombatSkill {

	public SwordFighting(int lvl, double xp) {
		super(lvl, xp);
	}

	@Override
	public SkillType getType() {
		return SkillType.SWORD_FIGHTING;
	}

}
