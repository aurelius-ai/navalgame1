package com.aurelius.navalgame1.data;

import com.aurelius.navalgame1.NavalBattle;
import com.navalgame.log.LoggerHook;

public class NavalGameLogHook extends LoggerHook {

	/**
	 * Informative message.
	 * @param message
	 */
	public void printInfo(String message) {
		NavalBattle.getDebugWindow().printInfo(message);
	}

	/**
	 * Warning message.
	 * @param message
	 */
	public void printWarning(String message) {
		NavalBattle.getDebugWindow().printWarning(message);
	}

	/**
	 * Error message.
	 * @param message
	 */
	public void printError(String message) {
		NavalBattle.getDebugWindow().printError(message);
	}

	/**
	 * Other messages.
	 * @param message
	 */
	public void printOther(String message) {
		NavalBattle.getDebugWindow().printOther(message);
	}
}
