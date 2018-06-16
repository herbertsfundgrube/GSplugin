package com.github.herbert.playerplugin.skills.passive;

import com.github.herbert.playerplugin.skills.PassiveSkill;
import com.github.herbert.playerplugin.skills.SkillType;

public class Speech extends PassiveSkill {
	
	public Speech(int lvl, double xp) {
		super(lvl, xp);
	}

	@Override
	public SkillType getType() {
		return SkillType.Speech;
	}
}
