package com.github.herbert.userinterfaceplugin.gui.buttons;

import java.util.List;

import org.bukkit.Material;

public abstract class GUIButton extends GUIObject {

	public GUIButton(Material appearance, String name, List<String> desc) {
		super(appearance, name, desc);
	}
	
	public void performAction() {
		
	}

}
