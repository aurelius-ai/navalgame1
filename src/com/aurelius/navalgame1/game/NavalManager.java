package com.aurelius.navalgame1.game;

import com.aurelius.navalgame1.game.entity.BirdFarm;
import com.aurelius.navalgame1.game.entity.BattleShip;
import com.aurelius.navalgame1.game.entity.Boat;
import com.aurelius.navalgame1.pavo.Game;
import com.aurelius.navalgame1.pavo.PavoHelper;
import com.aurelius.navalgame1.pavo.World;
import com.aurelius.navalgame1.pavo.grid.Asset;
import com.aurelius.navalgame1.pavo.grid.AssetManager;
import com.aurelius.navalgame1.pavo.grid.GridHelper;
import com.aurelius.navalgame1.pavo.grid.GridedEntityTileOrientation;
import com.aurelius.navalgame1.util.FileUtils;

/**
 * The entity manager specified for NavalBattle.
 */
public class NavalManager extends AssetManager {
	private static final long serialVersionUID = 1L;
	public static GridedEntityTileOrientation w1, w2, w3;
	
	/**
	 * Creates a new instance of the NavalManager.
	 * @param w The world to create it from.
	 */
	public NavalManager(World w) {
		super(w);
		battleShipId = new GridedEntityTileOrientation();
		battleShipId.setLeftToRightImage(registerEntity(PavoHelper.imgUtilOutline(
				FileUtils.getImage("drawable-game/battleship/battleship.png"),Game.Settings.GridColor),GridedEntityTileOrientation.ORIENTATION_LEFTTORIGHT));
		battleShipId.setTopToBottomImage(registerEntity(PavoHelper.imgUtilOutline(
				FileUtils.getImage("drawable-game/battleship/battleship_S.png"),Game.Settings.GridColor),GridedEntityTileOrientation.ORIENTATION_TOPTOBOTTOM));
		
		acarrierId = new GridedEntityTileOrientation();
		acarrierId.setLeftToRightImage(registerEntity(PavoHelper.imgUtilOutline(
				FileUtils.getImage("drawable-game/aircraftcarrier/aircraftcarrier.png"),Game.Settings.GridColor),GridedEntityTileOrientation.ORIENTATION_LEFTTORIGHT));
		acarrierId.setTopToBottomImage(registerEntity(PavoHelper.imgUtilOutline(
				FileUtils.getImage("drawable-game/aircraftcarrier/aircraftcarrier_S.png"),Game.Settings.GridColor),GridedEntityTileOrientation.ORIENTATION_TOPTOBOTTOM));
		
		boatId = new GridedEntityTileOrientation();
		boatId.setLeftToRightImage(registerEntity(PavoHelper.imgUtilOutline(
				FileUtils.getImage("drawable-game/submarine/submarine.png"),Game.Settings.GridColor),GridedEntityTileOrientation.ORIENTATION_LEFTTORIGHT));
		boatId.setTopToBottomImage(registerEntity(PavoHelper.imgUtilOutline(
				FileUtils.getImage("drawable-game/submarine/submarine_S.png"),Game.Settings.GridColor),GridedEntityTileOrientation.ORIENTATION_TOPTOBOTTOM));
		
		boatUId = new GridedEntityTileOrientation();
		boatUId.setLeftToRightImage(registerEntity(PavoHelper.imgUtilOutline(
				FileUtils.getImage("drawable-game/submarine/submarineU.png"),Game.Settings.GridColor),GridedEntityTileOrientation.ORIENTATION_LEFTTORIGHT));
		boatUId.setTopToBottomImage(registerEntity(PavoHelper.imgUtilOutline(
				FileUtils.getImage("drawable-game/submarine/submarineU_S.png"),Game.Settings.GridColor),GridedEntityTileOrientation.ORIENTATION_TOPTOBOTTOM));
		
		if (battleShipId != null) {
			BattleShip.BATTLESHIP_ID = battleShipId;
		}
		if (acarrierId != null) {
			BirdFarm.BIRDFARM_ID = acarrierId;
		}
		if (boatId != null) {
			Boat.SUBMARINE_ID = boatId;
			Boat.SUBMARINEU_ID = boatUId;
		}
		else {
		}
		gh = new GridHelper(Game.Settings.rand.nextLong(),this);
	}
	public GridHelper gh;
	
	public void gameDoneGenerating() {
		int w1_ = registerEntity(PavoHelper.imgUtilOutline(FileUtils.getImage("drawable-game/other/whaleleft.png"),Game.Settings.GridColor),GridedEntityTileOrientation.ORIENTATION_LEFTTORIGHT);
		int w2_ = registerEntity(PavoHelper.imgUtilOutline(FileUtils.getImage("drawable-game/other/whalecenter.png"),Game.Settings.GridColor),GridedEntityTileOrientation.ORIENTATION_LEFTTORIGHT);
		int w3_ = registerEntity(PavoHelper.imgUtilOutline(FileUtils.getImage("drawable-game/other/whaleright.png"),Game.Settings.GridColor),GridedEntityTileOrientation.ORIENTATION_LEFTTORIGHT);
		w1 = new GridedEntityTileOrientation();
		w1.setLeftToRightImage(w1_);
		w1.setTopToBottomImage(w1_);
		w2 = new GridedEntityTileOrientation();
		w2.setLeftToRightImage(w2_);
		w2.setTopToBottomImage(w2_);
		w3 = new GridedEntityTileOrientation();
		w3.setLeftToRightImage(w3_);
		w3.setTopToBottomImage(w3_);
		System.out.println("Let me play you the song of my people.");
	}	
	
	public void update(long ticksPassed) {
		for (int c = 0; c < this.getTotalEntities(); c++) {
			Asset e = getEntity(c);
			if (e != null){
				e.onUpdate(ticksPassed);
			}
		}
	}
	
	public NavalGame getGame(){
		return (NavalGame)getWorld().getGame();
	}
	
}
