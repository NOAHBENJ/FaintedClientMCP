package me.fainted.modules.combat;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.lwjgl.input.Keyboard;

import me.fainted.events.Event;
import me.fainted.events.listeners.EventMotion;
import me.fainted.modules.Module;
import me.fainted.util.Timer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.client.C02PacketUseEntity.Action;

public class KillAura extends Module{

	public Timer timer = new Timer();
	
	public KillAura() {
		super("KillAura", Keyboard.KEY_K, Category.COMBAT);
		
	}
	
	public void onDisable() {
		
	}
	
	public void onEvent(Event e) {
		if(e instanceof EventMotion) {
			if (e.isPre()) {
				
				EventMotion event = (EventMotion) e;
				
				List<Entity> targets = mc.theWorld.loadedEntityList.stream()
						.filter(EntityLivingBase.class::isInstance).collect(Collectors.toList());
				
				targets = targets.stream().filter(entity -> entity.getDistanceSqToEntity(mc.thePlayer) < 4 && entity != mc.thePlayer && !entity.isDead)
						.collect(Collectors.toList());
				
				targets.sort(Comparator.comparingDouble(entity -> ((EntityLivingBase)entity).getDistanceToEntity(mc.thePlayer)));
				
				// targets = targets.stream().filter(EntityPlayer.class::isInstance).collect(Collectors.toList());
				
				if(!targets.isEmpty()) {
					Entity target = targets.get(0);
					
					// testing 
					  mc.thePlayer.rotationYaw = (getRotations(target)[0]);
					  mc.thePlayer.rotationPitch = (getRotations(target)[1]); 
					
					// event.setYaw(getRotations(target)[0]);
					// event.setPitch(getRotations(target)[1]);
					
					if(timer.hasTimeElapsed(1000/10, true)) {
						mc.thePlayer.swingItem();
						mc.thePlayer.sendQueue.addToSendQueue(new C02PacketUseEntity(target, Action.ATTACK));
					}
				}
			}
		}
	}
	
	public float[] getRotations(Entity e) {
		double deltaX = e.posX + (e.posX - e.lastTickPosX) - mc.thePlayer.posX, 
			   deltaY = e.posY - 3.5 + e.getEyeHeight() - mc.thePlayer.posY + mc.thePlayer.getEyeHeight(),
			   deltaZ = e.posZ + (e.posZ - e.lastTickPosZ) - mc.thePlayer.posZ,
			   distance = Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaZ, 2));
		
		float yaw = (float) Math.toDegrees(-Math.atan(deltaX / deltaZ)),
				pitch = (float) -Math.toDegrees(Math.atan(deltaY / distance));
			
		if (deltaX < 0 && deltaZ < 0) {
			yaw = (float) (90 + Math.toDegrees(Math.atan(deltaZ - deltaX)));
		} else if (deltaX > 0 && deltaZ < 0) {
			yaw = (float) (-90 + Math.toDegrees(Math.atan(deltaZ - deltaX)));
		}
		
		return new float[] { yaw, pitch };
	}
}
