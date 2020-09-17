/*
 * Copyright (C) 2012 Oliver Aurelius Ellison

 */
package com.aurelius.navalgame1.io;

import java.io.File;

import com.aurelius.navalgame1.NavalBattle;
import com.aurelius.navalgame1.io.SettingsAttribute;
import com.aurelius.navalgame1.pavo.Game;
import com.aurelius.navalgame1.pavo.grid.Asset;
import com.aurelius.navalgame1.util.FileUtils;

import ellison.oliver.Rand;

/**
 * NavalBattle IO manager.
 */
public class NavalBattleIO {
	
	private static SettingsIO settings;
	private static boolean inited = false;
	/**
	 * Starts the NavalBattleIO service.
	 */
	public static void run() {
		inited = true;
		if (isFirstRun()) {
			NavalBattle.getDebugWindow().printInfo("Writing default config file");
			String settingsPath = getSettingsPath();
			settings = new SettingsIO(settingsPath);
			boolean res = settings.setAttribute(new SettingsAttribute("lastGoodUserName",""));
			res = settings.setAttribute(new SettingsAttribute("announcementId", "-2"));
			if (!res)
				NavalBattle.getDebugWindow().printError("failed to write initial attributes");
		}
		else {
			NavalBattle.getDebugWindow().printInfo("Loading config file");	
			try {
				settings = new SettingsIO(getSettingsPath());
			} catch (Exception e) { 
				NavalBattle.getDebugWindow().printError("Error while reading config file");
			}
		}
		settings.refresh();
	}
	
	/**
	 * Gets a specific attribute from the NavalBattle settings file.
	 * @param name The name of the attribute to get. Should only contain alpha-numeric characters.
	 * @return The value of the attribute (if any).
	 */
	public static String getAttribute(String name) {
		if (!inited)
			run();
		return settings.readAttribute(name);
	}
	
	/**
	 * Gets a specific attribute from the NavalBattle settings file.
	 * @param a The name and value store of the attribute to get. Should only contain alpha-numeric characters.
	 * @return The value of the attribute (if any).
	 */
	public static String getAttribute(SettingsAttribute a) {
		if (!inited)
			run();
		return settings.readAttribute(a);
	}
	
	/**
	 * Saves an attribute to the NavalBattle settings file.
	 * @param attribute The attribute to save to the file.
	 */
	public static void saveAttribute(SettingsAttribute attribute) {
		if (!inited)
			run();
		settings.setAttribute(attribute);
	}
	
	/**
	 * Saves an attribute to the NavalBattle settings file.
	 * @param name The name to save to the file.
	 * @param value The value of the attribute.
	 */
	public static void saveAttribute(String name, String value) {
		if (!inited)
			run();
		settings.setAttribute(new SettingsAttribute(name,value));
	}
	
	/**
	 * Determines if this is the first time the game has ran.
	 * @return
	 */
	public static boolean isFirstRun() {
		return !new File(getSettingsPath()).exists();
	}
	
	/**
	 * Gets the path of the settings file.
	 * @return
	 */
	public static String getSettingsPath() {
		return (FileUtils.getSavingDirectory().getAbsolutePath()+"\\settings.ini");
	}
	
	public static boolean saveGame(Game g, String name) {
		String ultimatePath = FileUtils.getSavingDirectory().getAbsolutePath() + "\\saves\\" + name + "\\" + new Rand(name).nextString(10,15) + ".inf";
		String entitycomposition = "";
		for (int c = 0; c < g.getWorld().getEntityManager().getTotalEntities(); c++) {
			Asset ent = g.getWorld().getEntityManager().getEntity(c);
			if (ent != null) {
				entitycomposition += "(" + ent.getLocation().getRow() + "," +
						ent.getLocation().getCol() + "," + ent.getCurrentId() + ")";
			}
			if (c != g.getWorld().getEntityManager().getTotalEntities() - 1)
				entitycomposition += " & ";
		}
		//String recordData =
			//	"seed: " + Game.Settings.seed +
				//"enty: " + entitycomposition +
				//"vsgn: " + com.jpii.navalbattle.data.Constants.NAVALBATTLE_VERSION +
				//"rokt: " + (NavalBattle.getRoketGamer().getStatus() == AuthStatus.OFFLINE ? "offline" : "online") +
				//"estr: " + Boolean.toString(NavalBattle.getGameState().isOffline());
		try {
			File del = new File(FileUtils.getSavingDirectory().getAbsolutePath() + "\\saves\\" + name + "\\");
			del.delete();
		}
		catch (Throwable t) {
			
		}
		try {
			new File(FileUtils.getSavingDirectory().getAbsolutePath() + "\\saves\\" + name + "\\").mkdirs();
		}
		catch (Throwable t) {
			return false;
		}
		SettingsIO tmp = new SettingsIO(ultimatePath);
		tmp.setAttribute(new SettingsAttribute("seed",Game.Settings.seed+""));
		tmp.setAttribute(new SettingsAttribute("enty",entitycomposition));
		tmp.setAttribute(new SettingsAttribute("vsgn",com.aurelius.navalgame1.data.Constants.NAVALBATTLE_VERSION));
		//tmp.setAttribute(new SettingsAttribute("rokt",(NavalBattle.getRoketGamer().getStatus() == AuthStatus.OFFLINE ? "offline" : "online")));
		tmp.setAttribute(new SettingsAttribute("estr",Boolean.toString(NavalBattle.getGameState().isOffline())));
		tmp.refresh();
		tmp = null;
		return true;
	}
}