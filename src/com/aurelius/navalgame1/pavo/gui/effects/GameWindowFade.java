package com.aurelius.navalgame1.pavo.gui.effects;

import java.awt.image.BufferedImage;

import com.aurelius.navalgame1.pavo.PavoHelper;

public class GameWindowFade extends GameWindowEffect {
	
	public GameWindowFade() {
		super();
	}
	
	public void setTransparency(float amount) {
		setParameter("transparency", amount);
	}
	
	public BufferedImage ApplyEffect(BufferedImage buffer) {
		if (buffer == null)
			return null;
		
		return PavoHelper.imgUtilAdjustImageTransparency(buffer, (Float)getParameter("transparency"));
	}
	
}
