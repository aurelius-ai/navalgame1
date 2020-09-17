package com.aurelius.navalgame1.game.entity;

import com.aurelius.navalgame1.pavo.Game;
import com.aurelius.navalgame1.pavo.grid.EntityManager;
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
	public BattleShip(EntityManager em, Location loc,byte orientation) {
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
	
	

}
