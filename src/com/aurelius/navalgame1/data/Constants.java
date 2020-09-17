/*
 * Copyright (C) 2012 Oliver Aurelius Ellison

 */

package com.aurelius.navalgame1.data;

import com.aurelius.navalgame1.gui.listeners.*;

import maximusvladimir.dagen.Rand;

public class Constants {
	
	/*
	 * General
	 */
	public static final String NAVALBATTLE_VERSION = "1.0";
	public static final String VERSION_CODE = "12";
	public static final String NAVALBATTLE_CODENAME = "Nimitz";
	public static final String NAVALBATTLE_VERSION_TITLE = "NavalBattle " + NAVALBATTLE_VERSION + " (" + NAVALBATTLE_CODENAME + ")";
	public static final String NAVALBATTLE_UPDATE_URL = "https://raw.github.com/aurelius/NavalBattle/master/update.xml";
	
	public static final String CRITICAL_ERROR_HEADER = "NavalBattle encountered a critical error and must close.\n" +
			"Report the error at: https://github.com/aurelius/NavalBattle/issues \n\n";
	
	public static final boolean DEBUG_MODE = true;
	
	public static final int SPLASH_DURATION = 1000;
	public static final int SPLASH_SCREEN_TIMEOUT = 3000;
	
	public static final KeyboardListener keys = new KeyboardListener();
	public static final WindowCloser closer = new WindowCloser();
	
	/*
	 * Game engine
	 */
	public static final int WINDOW_WIDTH = 800;
	public static final int WINDOW_HEIGHT = 600;
	public static final int CHUNK_SIZE = 100;
	public static final int MAIN_SEED = (int)(Math.random() * (256));
	public static final Rand MAIN_RAND = new Rand("34892u8ewdniohrqi3jowd9ehui");
	
	/*
	 * Gameplay
	 */
	public static final int HIT_SHIP_SCORE = 100;
	public static final int SINK_SHIP_SCORE = 400;
	public static final int DEFLECT_SHOT_SCORE = 50;
	public static final int DESTROY_PORT_SCORE = 500;
	
	public static final int BATTLESHIP_DEFLECT_CHANCE = 10;
	public static final int SUBMARINE_DEFLECT_CHANCE = 15;
	public static final int CARRIER_DEFLECT_CHANCE = 5;
	public static final int PORT_DEFLECT_CHANCE = 5;
	
	/*
	 * NavalGame
	 */
	public static final String API_KEY = "35B4531F87C549F23C5444566C7E5";
	public static final NavalGameLogHook ROKETGAMER_LOG_HOOK = new NavalGameLogHook();
	
	/*
	 * GameKit
	 */
	public static final int GAMEKIT_MAX_API_LEVEL = 1;
	public static final int GAMEKIT_MIN_API_LEVEL = 0;
}