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
			setSize(405, 205);
		else
			setSize(400, text.length*20+5);
		setText("Tutorial");
		texts=new PText[text.length];
		for(int index=0;index<text.length;index++){
			texts[index] = new PText(this);
			texts[index].setText(text[index]);
			texts[index].setLoc((getWidth()/2)-(texts[index].getWidth()/2), (index+1)*20+5);
			addControl(texts[index]);
		}
	}
}