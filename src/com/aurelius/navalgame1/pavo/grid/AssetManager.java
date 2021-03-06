/*
 * Copyright (C) 2012 Oliver Aurelius Ellison
 */

package com.aurelius.navalgame1.pavo.grid;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;

import com.aurelius.navalgame1.pavo.Chunk;
import com.aurelius.navalgame1.pavo.Game;
import com.aurelius.navalgame1.pavo.PavoHelper;
import com.aurelius.navalgame1.pavo.World;
import com.aurelius.navalgame1.pavo.io.PavoImage;

public class AssetManager implements Serializable {
	private static final long serialVersionUID = 1L;
	private byte[][] tileAccessor;
	private transient World w;
	private ArrayList<Integer> entityRegister;
	private ArrayList<Asset> assets;
	public GridedEntityTileOrientation battleShipId;
	public GridedEntityTileOrientation acarrierId;
	public GridedEntityTileOrientation boatId;
	public GridedEntityTileOrientation boatUId;
	int counter = 0;
	/**
	 * Creates a new entity manager for the desired world.
	 * @param w The world to create the entity manager.
	 */
	public AssetManager(World w) {
		this.w = w;
		tileAccessor = new byte[PavoHelper.getGameWidth(w.getWorldSize())*2][PavoHelper.getGameHeight(w.getWorldSize())*2];
		assets = new ArrayList<Asset>();
		PavoImage grid = new PavoImage(50,50,BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = PavoHelper.createGraphics(grid);
		g.setColor(Game.Settings.GridColor);
		g.drawRect(1,1,49,49);
		try {
			IndexableImage.populateStore(0, grid);
		}
		catch (Exception ex) {}
		g.dispose();
		entityRegister = new ArrayList<Integer>();
		entityRegister.add(0);
	}
	
	public boolean isEntityAnimated() {
		return getWorld().getGame().NOTOUCH_primitivesbeinginvoked();
	}
	
	public boolean isReadyForGen() {
		if (tileAccessor == null)
			return false;
		return true;
	}
	
	public void update(long ticksPassed) {

	}
	
	public Asset getEntity(int index) {
		return assets.get(index);
	}
	
	public void removeEntity(Asset e) {
		assets.remove(e);
	}
	
	public void removeEntity(int index) {
		assets.remove(index);
	}
	/**
	 * DO NOT CALL INDIVIDUALLY
	 * @param e The entity.
	 */
	public void addEntity(Asset e) {
		if (e == null)
			return;
		assets.add(e);
	}
	public int getTotalEntities() {
		return assets.size();
	}
	/**
	 * Gets the chunk associated with the given entity location.
	 * @param r The row of the entity.
	 * @param c The column of the entity.
	 * @return The chunk. Will return null if the desired location is out of bounds.
	 */
	public Chunk getAssociatedChunk(int r, int c) {
		if (c >= PavoHelper.getGameWidth(w.getWorldSize())*2 ||
				r >= PavoHelper.getGameHeight(w.getWorldSize())*2 || c < 0 || r < 0)
			return null;
		
		return w.getChunk(c/2,r/2);
	}
	public Chunk getAssociatedChunk(Location loc) {
		if (loc.getCol() >= PavoHelper.getGameWidth(w.getWorldSize())*2 ||
				loc.getRow() >= PavoHelper.getGameHeight(w.getWorldSize())*2 || loc.getCol() < 0 || loc.getRow() < 0)
			return null;
		
		return w.getChunk(loc.getCol()/2,loc.getRow()/2);
	}
	/**
	 * Sets the entity at the desired location.
	 * @param <T>
	 * @param r The row the entity should be at.
	 * @param c The column the entity should be at.
	 * @param e The entity to replace it with.
	 */
	public void setTile(int r, int c, Tile<Asset> t) {
		if (c >= PavoHelper.getGameWidth(w.getWorldSize())*2 ||
				r >= PavoHelper.getGameHeight(w.getWorldSize())*2 || c < 0 || r < 0)
			return;
		int x = c/2;
		int z = r/2;
		Chunk chunk = w.getChunk(x, z);
		int rx = c % 2;
		int rz = r % 2;
		if (rx == 0 && rz == 0)
			chunk.Tile00 = t;
		else if (rx != 0 && rz == 0)
			chunk.Tile10 = t;
		else if (rx == 0 && rz != 0)
			chunk.Tile01 = t;
		else if (rx != 0 && rz != 0)
			chunk.Tile11 = t;
		chunk.writeBuffer();
	}
	public void setTileOverlay(int r, int c, byte color) {
		if (c >= PavoHelper.getGameWidth(w.getWorldSize())*2 ||
				r >= PavoHelper.getGameHeight(w.getWorldSize())*2 || c < 0 || r < 0)
			return;
		int x = c/2;
		int z = r/2;
		Chunk chunk = w.getChunk(x, z);
		int rx = c % 2;
		int rz = r % 2;
		if (rx == 0 && rz == 0)
			chunk.Overlay00 = color;
		else if (rx != 0 && rz == 0)
			chunk.Overlay10 = color;
		else if (rx == 0 && rz != 0)
			chunk.Overlay01 = color;
		else if (rx != 0 && rz != 0)
			chunk.Overlay11 = color;
		chunk.writeBuffer();
	}
	public Asset findEntity(int r, int c) {
		if (r >= 0 && c >= 0 && c < PavoHelper.getGameWidth(getWorld().getWorldSize()) * 2
				&& r < PavoHelper.getGameHeight(getWorld().getWorldSize()) * 2) {
			Chunk chuck = PavoHelper.convertGridLocationToChunk(getWorld(), new Location(r,c));
			if (chuck == null)
				return null;
//			if (chuck.Tile00 != null && chuck.Tile00.parent != null)
//				return chuck.Tile00.parent;
//			else if (chuck.Tile10 != null && chuck.Tile10.parent != null)
//				return chuck.Tile10.parent;
//			else if (chuck.Tile01 != null && chuck.Tile01.parent != null)
//				return chuck.Tile01.parent;
//			else if (chuck.Tile11 != null && chuck.Tile11.parent != null)
//				return chuck.Tile11.parent;
//			else {
//				return null;
//			}
			int rrr = r % 2;
			int rrc = c % 2;
			if (rrr == 0 && rrc == 0 && chuck.Tile00 != null)
				return chuck.Tile00.parent;
			else if (rrr == 0 && rrc != 0 && chuck.Tile10 != null)
				return chuck.Tile10.parent;
			else if (rrr != 0 && rrc == 0 && chuck.Tile01 != null)
				return chuck.Tile01.parent;
			else if (rrc != 0 && rrr != 0 && chuck.Tile11 != null)
				return chuck.Tile11.parent;
			else
				return null;
		}
		return null;
	}
	public <T> void setTile(Location loc, Tile<Asset> t) {
		setTile(loc.getRow(),loc.getCol(),t);
	}
	public Tile<Asset> getTile(Location loc) {
		return getTile(loc.getRow(),loc.getCol());
	}
	public Tile<Asset> getTile(int r, int c) {
		if (c >= PavoHelper.getGameWidth(w.getWorldSize())*2 ||
				r >= PavoHelper.getGameHeight(w.getWorldSize())*2 || c < 0 || r < 0)
			return null;
		int x = c/2;
		int z = r/2;
		Chunk chunk = w.getChunk(x, z);
		if (chunk == null)
			return null;
		int rx = c % 2;
		int rz = r % 2;
		if (c == 0)
			rx = 0;
		if (c == 1)
			rx = 1;
		if (r == 0)
			rz = 0;
		if (r == 1)
			rz = 1;
		if (rx == 0 && rz == 0)
			return chunk.Tile00;
		else if (rx != 0 && rz == 0)
			return chunk.Tile10;
		else if (rx == 0 && rz != 0)
			return chunk.Tile01;
		else if (rx != 0 && rz != 0)
			return chunk.Tile11;
		return null;
		
	}
	/**
	 * Determines whether the selected tile is filled with water.
	 * @param r The row of the tile.
	 * @param c The column of the tile.
	 * @return
	 */
	public boolean isTileFilledWithWater(int r, int c) {
		if (r < 0 || r >= PavoHelper.getGameHeight(w.getWorldSize())*2 || c < 0 || c >= PavoHelper.getGameWidth(w.getWorldSize())*2)
			return false;
		return tileAccessor[c][r] <= Game.Settings.waterThresholdBarrier;
	}
	/**
	 * Gets the amount of land in the given tile.
	 * @param r The row of the tile.
	 * @param c The column of the tile.
	 * @return
	 */
	public int getTilePercentLand(int r, int c) {
		if (r < 0 || c < 0 || r >= PavoHelper.getGameHeight(getWorld().getWorldSize())*2
				|| c >= PavoHelper.getGameWidth(getWorld().getWorldSize())*2)
			return 0;
		return tileAccessor[c][r];
	}
	public int getTilePercentLand(Location l){
		return getTilePercentLand(l.getRow(), l.getCol());
	}
	
	public static int lastid = 0;
	public <T> int registerEntity(BufferedImage horizontalImage,byte orientation) {
		int swap = lastid + 1;
		if (orientation == GridedEntityTileOrientation.ORIENTATION_LEFTTORIGHT) {
			for (int w = 0; w < horizontalImage.getWidth() / 50; w++) {
				BufferedImage ab = PavoHelper.imgUtilFastCrop(horizontalImage, w * 50, 0, 50, 50);
				IndexableImage.populateStore(new Id(swap,w).getMutexId(), ab);
			}
		}
		else if (orientation == GridedEntityTileOrientation.ORIENTATION_TOPTOBOTTOM) {
			for (int h = 0; h < horizontalImage.getHeight() / 50; h++) {
				BufferedImage ab = PavoHelper.imgUtilFastCrop(horizontalImage,0, h * 50, 50, 50);
				IndexableImage.populateStore(new Id(swap,h).getMutexId(), ab);
			}
		}
		lastid = swap;
		return lastid;
	}
	/**
	 * Finds all non-null assets of a particular type.
	 * Use it like:
	 * <code>
	 * BattleShip[] bs = getEntityManager().<BattleShip>findEntities();
	 * </code>
	 * @return
	 */
	public <T> T[] findEntities() {
		ArrayList<T> ern = new ArrayList<T>();
		for (int c = 0; c < getTotalEntities(); c++) {
			Asset ef = getEntity(c);
			if (ef != null) {
				T tcast = null;
				try {
					tcast = (T)ef;
				}
				catch (Throwable t) {
					
				}
				if (tcast != null)
					ern.add(tcast);
			}
		}
		return (T[])ern.toArray();
	}
	public Asset findEntity(String tag) {
		for (int c = 0; c < getTotalEntities(); c++) {
			Asset ef = getEntity(c);
			if (ef != null && ef.getTag().equals(tag)) {
				return ef;
			}
		}
		return null;
	}
	public Asset[] findEntities(int id) {
		ArrayList<Asset> ferns = new ArrayList<Asset>();
		for (int c = 0; c < getTotalEntities(); c++) {
			Asset ef = getEntity(c);
			if (ef != null && ef.getCurrentId() == id) {
				ferns.add(ef);
			}
		}
		return (Asset[])ferns.toArray();
	}
	public final BufferedImage getImage(Tile<Asset> tile) {
		if (tile == null)
			return IndexableImage.getImage(0);
		return IndexableImage.getImage(tile.getId().getMutexId());
	}
	public void gameDoneGenerating() {
		
	}
	
	/**
	 * Don't play with this.
	 * @param snJMkqmd Don't play with this.
	 * @param cKQK91nm38910JNFEWo Don't play with this.
	 * @param traKQ91 Don't play with this.
	 */
	public void AQms03KampOQ9103nmJMs(int snJMkqmd, int cKQK91nm38910JNFEWo, int traKQ91) {
		byte b = (byte)((traKQ91 * 100)/272);
		if (b > 100)
			b = 100;
		if (b < 0)
			b = 0;
		if (b == 94)
			b = 100;
		if (tileAccessor == null)
			tileAccessor = new byte[PavoHelper.getGameWidth(w.getWorldSize())*2][PavoHelper.getGameHeight(w.getWorldSize())*2];
			
		tileAccessor[cKQK91nm38910JNFEWo][snJMkqmd] = b;
	}
	/**
	 * Get the world instance for the Asset Manager.
	 * @return
	 */
	public World getWorld() {
		return w;
	}
}
