package com.aurelius.navalgame1.game.entity;

import com.aurelius.navalgame1.pavo.grid.Asset;
import com.aurelius.navalgame1.pavo.grid.AssetManager;
import com.aurelius.navalgame1.pavo.grid.GridedEntityTileOrientation;
import com.aurelius.navalgame1.pavo.grid.Location;

public class Animation extends Asset {
	private static final long serialVersionUID = 1L;
	private GridedEntityTileOrientation[] animationids;

	/**
	 * @param em The EnitityManager.
	 * @param loc The Location of the Asset.
	 * @param animationFrameIds The animation frame sequences.
	 */
	public Animation(AssetManager em, Location loc,byte orientation, GridedEntityTileOrientation... animationFrameIds) {
		super(em, loc, animationFrameIds[0],orientation);
		animationids = animationFrameIds;
		handle = 3;
	}
	
	public void setCurrentFrame(int index) {
		if (index >= getTotalFrames())
			throw new java.lang.ArrayIndexOutOfBoundsException("The indicated frame is not in the given range of frames.");
		setId(animationids[index]);
	}
	
	public int getTotalFrames() {
		return animationids.length;
	}
	
	int nextIndex = 0;
	boolean direction = true;
	boolean alternate = true;
	
	public void setAlternatingDirection(boolean t) {
		alternate = t;
	}
	
	public boolean isAlternatingDirections() {
		return alternate;
	}
	
	public void updateFrame() {
		if (isHidden()) {
			return;
		}
		setCurrentFrame(nextIndex);
		if (alternate) {
		if (direction)
			nextIndex++;
		else
			nextIndex--;
		if (nextIndex >= getTotalFrames()) {
			direction = false;
			nextIndex--;
		}
		if (nextIndex == -1) {
			direction = true;
			nextIndex = 0;
		}
		}
		else {
			if (nextIndex >= getTotalFrames() - 1) {
				nextIndex = 0;
			}
			else
				nextIndex++;
		}
	}
}
