package com.github.herbert.userinterfaceplugin.gui.buttons;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.event.Listener;

import com.github.herbert.PluginConfiguration;

public class GUIFillingObject extends GUIObject implements Listener {

	public GUIFillingObject(String name, List<String> desc) {
		super(Material.getMaterial(PluginConfiguration.getString("ui.gui.fillingobjectmaterial")), name, desc);
	}
	
}
