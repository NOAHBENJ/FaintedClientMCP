package me.fainted.modules.movement;

import org.lwjgl.input.Keyboard;

import me.fainted.events.Event;
import me.fainted.events.listeners.EventUpdate;
import me.fainted.modules.Module;
import me.fainted.modules.RenderToggleBanner;

public class Fly extends Module{

	public Fly() {
		super("Fly", Keyboard.KEY_F, Category.MOVEMENT);
		
	}
	
	public void onEnable() {
		
		
	}
	
	public void onDisable() {
		mc.thePlayer.capabilities.isFlying = false;
	}
	
	public void onEvent(Event e) {
		if(e instanceof EventUpdate) {
			if (e.isPre()) {
				
				mc.thePlayer.capabilities.isFlying = true;
			}
		}
	}

}
