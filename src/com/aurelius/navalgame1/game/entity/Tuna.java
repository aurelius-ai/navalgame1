package com.aurelius.navalgame1.game.entity;

import com.aurelius.navalgame1.pavo.Game;
import com.aurelius.navalgame1.pavo.PavoHelper;
import com.aurelius.navalgame1.pavo.grid.EntityManager;
import com.aurelius.navalgame1.pavo.grid.GridedEntityTileOrientation;
import com.aurelius.navalgame1.pavo.grid.Location;

public class Tuna extends Animation {

	private static final long serialVersionUID = 1L;
	public static int[] animationFramesForDaWhale;
	
	/**
	 * @param em
	 * @param loc
	 * @param animationFrameIds
	 */
	public Tuna(EntityManager em, Location loc,byte orientation, GridedEntityTileOrientation... animationFrameIds) {
		super(em, loc, orientation, animationFrameIds);
		nextIndex = Game.Settings.rand.nextInt(0,3);
		imgLocation="drawable-game/other/whaleright.png";
	}
	
	
	boolean speedy = false;
	public void onUpdate(long tickTime) {
		super.onUpdate(tickTime);
		if (speedy && tickTime % 2 == 0) {
			updateFrame();
		}
		else if (tickTime % 6 == 0) {
			updateFrame();
		}
	
	
	}
	
	public void onMouseDown(int x, int y, boolean leftClick) {
		//this.animatedMoveTo(/*new Location(3,3)*/((NavalManager)getManager()).gh.pollNearLocation(getLocation()), 0.25f);
		//getManager().getWorld().animatedSetLoc(Game.Settings.rand.nextInt(-2000, 2000),Game.Settings.rand.nextInt(1000, 2000));
		//Location loc2 = Location.random(getManager().getWorld(),Game.Settings.rand);
		//getManager().getWorld().animatedSetLoc(loc2,0.0556666662f);
	}
	


}
