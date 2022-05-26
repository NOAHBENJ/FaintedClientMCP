package me.fainted.modules.movement;

import org.lwjgl.input.Keyboard;

import me.fainted.events.Event;
import me.fainted.events.listeners.EventUpdate;
import me.fainted.modules.Module;

public class Sprint extends Module{

	public Sprint() {
		super("Sprint", Keyboard.KEY_N, Category.MOVEMENT);
		
	}
	
	public void onEnable() {
		
	}
	
	public void onDisable() {
		mc.thePlayer.setSprinting(false);
	}
	
	public void onEvent(Event e) {
		if(e instanceof EventUpdate) {
			if (e.isPre()) {
				if (mc.thePlayer.moveForward > 0 && !mc.thePlayer.isUsingItem() && !mc.thePlayer.isSneaking() && !mc.thePlayer.isCollidedHorizontally) {
					mc.thePlayer.setSprinting(true);
				} else {
					mc.thePlayer.setSprinting(false);
				}
				
			}
		}
	}

}
