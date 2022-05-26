package me.fainted.modules.render;

import org.lwjgl.input.Keyboard;

import me.fainted.events.Event;
import me.fainted.events.listeners.EventUpdate;
import me.fainted.modules.Module;

public class FullBright extends Module{
	
	public FullBright() {
		super("FullBright", Keyboard.KEY_O, Category.RENDER);
		
	}
	
	public void onEnable() {
		mc.gameSettings.gammaSetting = 100;
	}
	
	public void onDisable() {
		mc.gameSettings.gammaSetting = 1;
	}
	
	public void onEvent(Event e) {
		if(e instanceof EventUpdate) {
			if (e.isPre()) {
				
			}
		}
	}

}
