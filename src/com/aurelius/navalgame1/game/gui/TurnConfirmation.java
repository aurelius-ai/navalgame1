package com.aurelius.navalgame1.game.gui;

import com.aurelius.navalgame1.game.NavalGame;
import com.aurelius.navalgame1.pavo.gui.NewWindowManager;
import com.aurelius.navalgame1.pavo.gui.controls.*;
import com.aurelius.navalgame1.pavo.gui.events.PMouseEvent;

public class TurnConfirmation extends PWindow {
	
	public static boolean viewed;
	
	public TurnConfirmation(NewWindowManager parent,final NavalGame ng) {
		super(parent);
		parent.add(this);
		setLoc(228, 136);
		setSize(200, 150);
		setText("End Turn");
		PText text0 = new PText(this);
		text0.setText("Are you sure you want to");
		text0.setLoc(32, 30);
		addControl(text0);
		PText text1 = new PText(this);
		text1.setText("end your turn.");
		text1.setLoc(61, 50);
		addControl(text1);
		PButton button2 = new PButton(this);
		button2.setText("Yes");
		button2.setLoc(83, 75);
		addControl(button2);
		PButton button3 = new PButton(this);
		button3.setText("No");
		button3.setLoc(86, 101);
		addControl(button3);
		viewed = true;
		
		button3.addMouseListener(new PMouseEvent(){
			public void mouseDown(int x, int y, int buttonid) {
				close();
			}
		});
		
		button2.addMouseListener(new PMouseEvent(){
			public void mouseDown(int x, int y, int buttonid) {
				ng.getTurnManager().nextTurn();
				close();
			}
		});
	}
	
	public void onClose(){
		super.onClose();
		viewed = false;
	}

}
