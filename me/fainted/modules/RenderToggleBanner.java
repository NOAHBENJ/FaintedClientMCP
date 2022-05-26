package me.fainted.modules;

import java.util.Comparator;

import me.fainted.Client;
import me.fainted.events.listeners.EventRenderGUI;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;

public class RenderToggleBanner {

	public Minecraft mc = Minecraft.getMinecraft();
	
	public void draw(String name) {
		ScaledResolution sr = new ScaledResolution(mc);
		FontRenderer fr = mc.fontRendererObj;
		
		Client.modules.sort(Comparator.comparingInt(m -> 
			mc.fontRendererObj.getStringWidth(((Module)m).name))
				.reversed()
		);
		
		int count = 0;
		
			int offset = count * (fr.FONT_HEIGHT + 6);
			
			Gui.drawRect(100, 100, 100, 100, 0x90d02a07);
			// Gui.drawRect(sr.getScaledWidth() - fr.getStringWidth(name) - 8, offset, sr.getScaledWidth(), 6 + fr.FONT_HEIGHT+ count * (fr.FONT_HEIGHT + 5), 0x90000000);
			// fr.drawStringWithShadow(name, sr.getScaledWidth() - fr.getStringWidth(name) - 4, 4 + count * (fr.FONT_HEIGHT + 5), -1);
			System.out.println("Drew string " + name);
			count++;
		
	
		Client.onEvent(new EventRenderGUI());
	}
	
}
