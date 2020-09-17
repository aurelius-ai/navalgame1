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