package me.fainted.ui;

import java.util.Comparator;

import me.fainted.Client;
import me.fainted.events.listeners.EventRenderGUI;
import me.fainted.modules.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;

public class HUD {

	public Minecraft mc  = Minecraft.getMinecraft();
		
	public void draw() {
		ScaledResolution sr = new ScaledResolution(mc);
		FontRenderer fr = mc.fontRendererObj;
		
		Client.modules.sort(Comparator.comparingInt(m -> 
			mc.fontRendererObj.getStringWidth(((Module)m).name))
				.reversed()
		);
		
		//GlStateManager.pushMatrix();
		
		//GlStateManager.translate(4, 4, 0);
		//GlStateManager.scale(2, 2, 1);
		//GlStateManager.translate(-4, -4, 0);
		
		//GlStateManager.popMatrix();
		// Leaving this here as a note for later
		
		int count = 0;
		for(Module m : Client.modules) {
			if (!m.toggled || m.name.equals("TabGUI")) { continue; }
			
			int offset = count * (fr.FONT_HEIGHT + 5);
			
			Gui.drawRect(sr.getScaledWidth() - fr.getStringWidth(m.name) - 10, offset, sr.getScaledWidth() - fr.getStringWidth(m.name) - 8, 6 + fr.FONT_HEIGHT+ count * (fr.FONT_HEIGHT + 5), 0x90d02a07);
			Gui.drawRect(sr.getScaledWidth() - fr.getStringWidth(m.name) - 8, offset, sr.getScaledWidth(), 6 + fr.FONT_HEIGHT+ count * (fr.FONT_HEIGHT + 5), 0x90000000);
			fr.drawStringWithShadow(m.name, sr.getScaledWidth() - fr.getStringWidth(m.name) - 4, 4 + count * (fr.FONT_HEIGHT + 5), -1);
			
			count++;
		}
	
		Client.onEvent(new EventRenderGUI());
	}

}
