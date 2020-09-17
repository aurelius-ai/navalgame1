/*
 * Copyright (C) 2012 Oliver Aurelius Ellison

 */
package com.aurelius.navalgame1.data;

import com.aurelius.navalgame1.gui.*;
import com.jpii.gamekit.toaster.Toaster;

public class WindowHandler extends com.jpii.gamekit.gui.WindowHandler{
	
	private static Toaster toasterManager;
	
	public WindowHandler(int defaultx, int defaulty) {
		super(defaultx, defaulty);
		new LoginWindow();
		new LoggingInWindow();
		new MainMenuWindow();
		new HelpWindow();
		new CreditsWindow();
		new BroadcastWindow();
		new UpdateWindow();
		new GameOverWindow();
		toasterManager = new Toaster();
	}
	
	/**
	 * Returns current instance of <code>Toaster</code>. Used to send desktop notifications.
	 * @return toasterManager
	 */
	public Toaster getToasterManager() {
		return toasterManager;
	}
}
