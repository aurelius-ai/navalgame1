/*
 * Copyright (C) 2012 Oliver Aurelius Ellison

 */
package com.aurelius.navalgame1.gui.listeners;

import java.awt.event.*;

import com.aurelius.navalgame1.data.Constants;
import com.aurelius.navalgame1.gui.BaseWindow;

public class Focus implements FocusListener{
	
	BaseWindow window;
	
	public Focus(BaseWindow classname){
		super();
		window = classname;
	}
	
	@Override
	public void focusGained(FocusEvent e) {
		Constants.keys.add(window);
		Constants.closer.add(window);
	}

	@Override
	public void focusLost(FocusEvent e) {
		Constants.keys.remove(window);
		Constants.closer.remove(window);
	}

}
