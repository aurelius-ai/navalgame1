package com.aurelius.navalgame1.game;

import com.aurelius.navalgame1.game.entity.BattleShip;
import com.aurelius.navalgame1.pavo.Game;
import com.aurelius.navalgame1.pavo.grid.GridedEntityTileOrientation;
import com.aurelius.navalgame1.pavo.grid.Location;
import com.aurelius.navalgame1.pavo.io.PavoClient;

public class NavalClient extends PavoClient {
	long seed = Long.MIN_VALUE;
	Game game;
	/**
	 * @param ipaddress
	 */
	public NavalClient(Game game,String ipaddress) {
		super(ipaddress);
		this.game = game;
	}
	public void onMessageRecieved(String message) {
		if (message.startsWith("SEED:")) {
			String part = message.replace("SEED:","");
			seed = Long.parseLong(part);
		}
	
		else if (message.startsWith("bounds:")) {
			String part = message.replace("bounds:","");
			String col = part.substring(0, part.indexOf(","));
			String row = part.substring(part.indexOf(",")+1);
			
			int x = Integer.parseInt(col);
			int y = Integer.parseInt(row);
			NavalGame gn = (NavalGame)game;
			gn.getMap().setMultiplayer(x, y);
		}
		else
			super.onMessageRecieved(message);
	}
	public long getSeed() {
		return seed;
	}
}
