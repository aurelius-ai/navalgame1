package com.aurelius.navalgame1.game.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.aurelius.navalgame1.data.NavalGameData;
import com.aurelius.navalgame1.game.NavalManager;
import com.aurelius.navalgame1.game.turn.TurnManager;
import com.aurelius.navalgame1.pavo.Game;
import com.aurelius.navalgame1.pavo.PavoHelper;
import com.aurelius.navalgame1.pavo.ProceduralLayeredMapGenerator;
import com.aurelius.navalgame1.pavo.grid.EntityManager;
import com.aurelius.navalgame1.pavo.grid.GridHelper;
import com.aurelius.navalgame1.pavo.grid.GridedEntityTileOrientation;
import com.aurelius.navalgame1.pavo.grid.Location;
import com.aurelius.navalgame1.util.NavalUtils;

import maximusvladimir.dagen.Rand;

public class PortEntity extends Animation {
	private static final long serialVersionUID = 1L;
	BufferedImage icon;
	private int maxHealth;
	private int currentHealth;
	
	/**
	 * @param em
	 * @param loc
	 * @param orientation
	 * @param team
	 * @param animationFrameIds
	 */
	public PortEntity(EntityManager em, Location loc, byte orientation) {
		super(em, loc, orientation, generatePort(em,loc));
		icon = em.getImage(em.getTile(loc));
		this.setAlternatingDirection(false);
		handle = 2;
		maxHealth = 2500;
		currentHealth = maxHealth;
	}
	
	public BufferedImage getIcon() {
		return icon;
	}
	
	public void onUpdate(long tickTime) {
		super.onUpdate(tickTime);
		if (tickTime % 6 == 0) {
			updateFrame();
		}
	}
	
	
	private boolean meetsSpawningCondition(int x, int y, int width){
		return (GridHelper.canPlaceInGrid(getManager(), GridedEntityTileOrientation.ORIENTATION_LEFTTORIGHT, y, x, width) ||
				GridHelper.canPlaceInGrid(getManager(), GridedEntityTileOrientation.ORIENTATION_TOPTOBOTTOM, y, x, width) );
	}
	
	public int getPercentHealth(){
		return (int)((double)currentHealth/(double)maxHealth*100.0);
	}
}
