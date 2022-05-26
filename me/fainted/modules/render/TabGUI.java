package me.fainted.modules.render;

import java.util.List;

import org.lwjgl.input.Keyboard;

import me.fainted.Client;
import me.fainted.events.Event;
import me.fainted.events.listeners.EventKey;
import me.fainted.events.listeners.EventRenderGUI;
import me.fainted.modules.Module;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;

public class TabGUI extends Module{
	
	public int currentTab;
	public boolean expanded;
	
	public TabGUI() {
		super("TabGUI", Keyboard.KEY_NONE, Category.RENDER);
		toggled = true;
	}
	
	public void onEvent(Event e) {
		if(e instanceof EventRenderGUI) {
			FontRenderer fr = mc.fontRendererObj;
			Gui.drawRect(5, 3, 69, 30 + Module.Category.values().length * 9 + 5, 0x90000000);
			Gui.drawRect(7, 5 + currentTab * 16, 6 + 61, 8 + currentTab * 16 + 12, 0x90d02a07); // 5, 5 + currentTab * 16, 8, 8 + currentTab * 16 + 10, 0x90d02a07
		
			int count = 0;
			for (Category c : Module.Category.values()) {
				fr.drawStringWithShadow(c.name, 10, 10 + count * 16, -1);
				
				
				count++;
			}
			
			if (expanded) {
				Category category = Module.Category.values()[currentTab];
				List<Module> modules = Client.getModulesByCategory(category);
				
				if (modules.size() == 0) {
					return;
				}
				
				
				Gui.drawRect(72, 3, 68 + 68, 30 + Module.Category.values().length * 9 + 5, 0x90000000);
				Gui.drawRect(134, 5 + category.moduleIndex * 16, 13 + 61, 8 + category.moduleIndex * 16 + 12, 0x90d02a07); // 5, 5 + currentTab * 16, 8, 8 + currentTab * 16 + 10, 0x90d02a07
			
				count = 0;
				for (Module m : modules) {
					fr.drawStringWithShadow(m.name, 75, 10 + count * 16, -1);
					
					count++;
				}
			}
		}
		
		if (e instanceof EventKey) {
			int code = ((EventKey)e).code;
			Category category = Module.Category.values()[currentTab];
			List<Module> modules = Client.getModulesByCategory(category);
			
			
			
			if(code == Keyboard.KEY_UP) {
				if (expanded) {
					if (category.moduleIndex <= modules.size() - 1) {
						category.moduleIndex = 0;
					} else {
						category.moduleIndex++;
					}
				} else {
					if (currentTab <= 0) {
						currentTab = Module.Category.values().length - 1;
					} else {
						currentTab--;
					}
				}
			}
			
			if(code == Keyboard.KEY_DOWN) {
				if (expanded) {
					if (category.moduleIndex >= modules.size() - 1) {
						category.moduleIndex = 0;
					} else {
						category.moduleIndex++;
					}
				} else {
					if (currentTab >= Module.Category.values().length) {
						currentTab = 0;
					} else {
						currentTab++;
					}
				}
			}
			
			if(code == Keyboard.KEY_RIGHT) {
				if (expanded && modules.size() != 0) {
					Module module = modules.get(category.moduleIndex);
					if(!module.name.equals("TabGUI")) {
						module.toggle();
					}
					
					
				} else {
					expanded = true;
				}
			}
			if(code == Keyboard.KEY_LEFT) {
				expanded = false;
			}
		}
		
	}

}
