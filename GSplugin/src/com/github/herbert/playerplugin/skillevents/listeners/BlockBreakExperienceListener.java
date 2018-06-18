package com.github.herbert.playerplugin.skillevents.listeners;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import com.github.herbert.PluginConfiguration;
import com.github.herbert.playerplugin.PlayerPlugin;
import com.github.herbert.playerplugin.playerdata.HerbertPlayer;
import com.github.herbert.playerplugin.skills.SkillType;

public class BlockBreakExperienceListener implements Listener {
	private PlayerPlugin plugin;
	public BlockBreakExperienceListener(PlayerPlugin p) {
		plugin=p;
	}
	
	@EventHandler
	public void onBlockBreakEvent(BlockBreakEvent event) {
		if(getXPMap(event.getBlock().getType())!=null) {
			HerbertPlayer hp = plugin.data.getPlayer(event.getPlayer());
			for(Map.Entry<SkillType, Double> me : getXPMap(event.getBlock().getType()).entrySet()) {
				hp.addSkillXP(me.getKey(), me.getValue());
			}
		}
	}
	
	private HashMap<SkillType, Double> getXPMap(Material mat) {
		return PluginConfiguration.getBlockBreakXP(mat);
	}
}
