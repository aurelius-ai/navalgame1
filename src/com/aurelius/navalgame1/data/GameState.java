/*
 * Copyright (C) 2012 Oliver Aurelius Ellison

 */
package com.aurelius.navalgame1.data;

import com.aurelius.navalgame1.util.NavalUtils;

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
	 * Set game into Off-line mode.
	 * @param offline
	 */
	public void setOffline(boolean offline) {
		this.offline = offline;
	}
	
	/**
	 * Add points spent.
	 */
	public void addPointsSpent(int points) {
		pointsSpent += points;
		
		if(pointsSpent >= 10000)
			NavalUtils.submitAchievement(NavalGameData.ACHIEVEMENT_BLANK_CHECK);
	}
	
	/**
	 * Get points spent in the current stage.
	 * @return
	 */
	public int getPointsSpent() {
		return pointsSpent;
	}
	
	/**
	 * Called when a ship is destroyed.
	 */
	public void shipDestroyed() {
		shipsDestroyed++;
		
		NavalUtils.submitAchievement(NavalGameData.ACHIEVEMENT_IT_ALL_CHANGED);
		
		if(shipsDestroyed >= 2)
			NavalUtils.submitAchievement(NavalGameData.ACHIEVEMENT_SHOCK_AND_AWE);
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
