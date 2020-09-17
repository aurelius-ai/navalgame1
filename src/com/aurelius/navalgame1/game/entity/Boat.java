package com.aurelius.navalgame1.game.entity;

import com.aurelius.navalgame1.pavo.Game;
import com.aurelius.navalgame1.pavo.grid.AssetManager;
import com.aurelius.navalgame1.pavo.grid.GridedEntityTileOrientation;
import com.aurelius.navalgame1.pavo.grid.Location;

public class Boat extends MoveableEntity {
	private static final long serialVersionUID = 1L;
	public static GridedEntityTileOrientation SUBMARINE_ID;
	public static GridedEntityTileOrientation SUBMARINEU_ID;
	private boolean submerged=false;
	private boolean canSubmerge=true;
	
	public Boat(AssetManager em, Location loc,byte orientation) {
		super(em, loc, SUBMARINE_ID,orientation);
		imgLocation="drawable-game/submarine/submarine.png";
		Game g = em.getWorld().getGame();
		if (!g.isAClient()) {
			g.getSelfServer().send("submarine:"+loc.getCol()+","+loc.getRow());
		}
		handle = 11;
		maxHealth = 600;
		currentHealth = maxHealth;
		moved = 0;
		maxMovement=7;
		primaryRange = 5;
		secondaryRange = 7;
		gunsAttackOption = true;
		missileAttackOption = true;
	}
	
	public void init() {
		setWidth(2);
	}
	
	public boolean moveTo(Location loc, boolean override) {
		return super.moveTo(loc, override);
	}
	
	public void onUpdate(long timePassed) {
	}
	
	public void onMouseMove(int x, int y) {
	}
	
	public void onMouseDown(int x, int y, boolean leftbutton) {
		super.onMouseDown(x, y, leftbutton);
	}
	public void toggleElevation(){
		if(submerged)
			setId(SUBMARINE_ID);
		else
			setId(SUBMARINEU_ID);
		submerged = !submerged;
	}
	
	public boolean isSumberged(){
		return submerged;
	}
	
	public boolean canSubmerge(){
		return canSubmerge;
	}
	
	public void resetMovement(){
		super.resetMovement();
		canSubmerge=true;
		if(isSumberged()){
			toggleElevation();
		}
	}
	
	public void usePrimary(){
		usedGuns=true;
		canSubmerge=false;
	}
	
	public void useSecondary(){
		missileCount--;
		usedMissiles=true;
		canSubmerge=false;
	}
}
