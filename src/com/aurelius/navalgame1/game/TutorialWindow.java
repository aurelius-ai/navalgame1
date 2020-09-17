package com.aurelius.navalgame1.game;

import com.aurelius.navalgame1.pavo.gui.NewWindowManager;
import com.aurelius.navalgame1.pavo.gui.controls.PText;
import com.aurelius.navalgame1.pavo.gui.controls.PWindow;

public class TutorialWindow extends PWindow {

	PText[] texts;
	
	public TutorialWindow(NewWindowManager parent,String... text) {
		super(parent);
		parent.add(this);
		setLoc(127, 153);
		setVisible(true);
		if(text.length<=9)
			setSize(400, 200);
	
		}
	}
