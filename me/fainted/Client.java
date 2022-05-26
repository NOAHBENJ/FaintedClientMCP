package me.fainted;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.lwjgl.opengl.Display;

import me.fainted.events.Event;
import me.fainted.events.listeners.EventKey;
import me.fainted.modules.Module;
import me.fainted.modules.Module.Category;
import me.fainted.modules.combat.KillAura;
import me.fainted.modules.movement.Fly;
import me.fainted.modules.movement.Sprint;
import me.fainted.modules.player.NoFall;
import me.fainted.modules.render.FullBright;
import me.fainted.modules.render.TabGUI;
import me.fainted.ui.HUD;

public class Client {

	public static String name = "Fainted Client", version = "v1.0";
	public static CopyOnWriteArrayList<Module> modules = new CopyOnWriteArrayList<Module>();
	public static HUD hud = new HUD();
	
	public static void Startup() {
		System.out.println("Starting " + name + " " + version);
		Display.setTitle(name + " " + version);
		
		modules.add(new Fly());
		modules.add(new Sprint());
		modules.add(new FullBright());
		modules.add(new NoFall());
		modules.add(new TabGUI());
		modules.add(new KillAura());
	}
	
	public static void onEvent(Event e) {
		for (Module m : modules) {
			if (!m.toggled) { continue; } 
			m.onEvent(e);
		}
		
		
	}
	
	
	
	public static void keyPress(int key) {
		Client.onEvent(new EventKey(key));
		for(Module m : modules) {
			if(m.getKey() == key) {
				m.toggle();
			}
		}
	}
	
	public static List<Module> getModulesByCategory(Category c) {
		List<Module> modules = new ArrayList<Module>();
		
		for(Module m : Client.modules) {
			if (m.category == c) {
				modules.add(m);
			}
		}
		
		return modules;
	}

	
}
