package me.fainted.modules;

import java.util.Comparator;

import me.fainted.Client;
import me.fainted.events.Event;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;

public class Module {

	public String name;
	public boolean toggled;
	public int keyCode;
	public Category category;
	public Minecraft mc = Minecraft.getMinecraft();
	
	public Module(String name, int key, Category c) {
		this.name = name;
		this.keyCode = key;
		this.category = c;
	}
	
	public boolean isEnabled() {
		return toggled;
	}
	
	public int getKey() {
		return keyCode;
	}
	
	public void onEvent(Event e) {
		
	}
	
	public void toggle() {
		toggled = !toggled;
		if(toggled) {
			onEnable();
		} else if (!toggled) {
			onDisable();
		}
	}
	
	public void onDisable() {
		
	}
	
	public void onEnable() {
		
	}
	
	public enum Category {
		COMBAT("Combat"), 
		MOVEMENT("Movement"),
		PLAYER("Player"),
		RENDER("Render");
		
		public String name;
		public int moduleIndex;
		
		Category(String name) {
			this.name = name;
		}
	}
	
	/* 

	[Q: 16, W: 17, E: 18, R: 19, T: 20, Y: 21, U: 22, I: 23, O: 24, P: 25]
	[A: 30, S: 31, D: 32, F: 33, G: 34, H: 35, J: 36, K: 37, L:38]
	[Z: 44, X: 45, C: 46, V: 47, B: 48, N: 49, M: 50]
	  
	 */
}
