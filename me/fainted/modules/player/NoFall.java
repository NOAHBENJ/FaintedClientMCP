package me.fainted.modules.player;

import org.lwjgl.input.Keyboard;

import me.fainted.events.Event;
import me.fainted.events.listeners.EventUpdate;
import me.fainted.modules.Module;
import net.minecraft.network.play.client.C03PacketPlayer;

public class NoFall extends Module{
	
	public NoFall() {
		super("No Fall", Keyboard.KEY_Z, Category.PLAYER);
		
	}
	
	public void onEvent(Event e) {
		if(e instanceof EventUpdate) {
			if (e.isPre()) {
				if(mc.thePlayer.fallDistance > 2) {
					mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer(true));
				}
			}
		}
	}

}
