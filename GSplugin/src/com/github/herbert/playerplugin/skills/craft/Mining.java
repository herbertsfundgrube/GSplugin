package com.github.herbert.playerplugin.skills.craft;

import com.github.herbert.playerplugin.skills.CraftSkill;
import com.github.herbert.playerplugin.skills.SkillType;

public class Mining extends CraftSkill {

	public Mining(int lvl, double xp) {
		super(lvl, xp);
	}

	@Override
	public SkillType getType() {
		return SkillType.MINING;
	}

}
