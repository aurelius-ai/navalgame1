package com.aurelius.navalgame1.game.entity;

import com.aurelius.navalgame1.pavo.Game;
import com.aurelius.navalgame1.pavo.grid.AssetManager;
import com.aurelius.navalgame1.pavo.grid.GridedEntityTileOrientation;
import com.aurelius.navalgame1.pavo.grid.Location;

public class BattleShip extends MoveableEntity {
	private static final long serialVersionUID = 1L;
	public static GridedEntityTileOrientation BATTLESHIP_ID;

	/**
	 * @param em
	 * @param loc
	 * @param superId
	 */
	public BattleShip(AssetManager em, Location loc,byte orientation) {
		super(em, loc, BATTLESHIP_ID,orientation);
		imgLocation="drawable-game/battleship/battleship.png";
		Game g = em.getWorld().getGame();
		if (!g.isAClient()) {
			g.getSelfServer().send("battleship:"+loc.getCol()+","+loc.getRow());
		}
		handle = 31;
		maxHealth = 1200;
		currentHealth = maxHealth;
		moved=0;
		maxMovement = 5;
		primaryRange = 5;
		secondaryRange = 7;
		gunsAttackOption = true;
		missileAttackOption = true;
	}
	
	
	public void init() {
		setWidth(4);
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
}
