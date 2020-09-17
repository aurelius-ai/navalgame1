/*
 * Copyright (C) 2012 Oliver Aurelius Ellison

 */
package com.aurelius.navalgame1.data;

import com.aurelius.navalgame1.util.RoketUtils;

public class GameState {
	
	private boolean offline = true;
	private int pointsSpent, shipsDestroyed = 0;
	
	/**
	 * Get if game is in off-line mode.
	 * @return off-line
	 */
	public boolean isOffline() {
		return offline;
	}
	
	/**
	 * Get number of ships destroyed this turn.
	 * @return
	 */
	public int getShipsDestroyed() {
		return shipsDestroyed;
	}
	
	/**
	 * Called at the end of a stage to reset all stage-related data.
	 */
	public void endStage() {
		pointsSpent = 0;
		shipsDestroyed = 0;
	}
}
