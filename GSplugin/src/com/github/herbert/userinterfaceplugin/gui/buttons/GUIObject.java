package com.github.herbert.userinterfaceplugin.gui.buttons;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class GUIObject extends ItemStack {
	
	public GUIObject(Material appearance, String name, List<String> desc) {
		super(appearance);
		this.getItemMeta().setDisplayName(name);
		this.getItemMeta().setLore(desc);
	}
}
