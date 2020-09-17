/*
 * Copyright (C) 2012 Oliver Aurelius Ellison

 */
package com.aurelius.navalgame1.game;

import javax.swing.JFrame;

import com.aurelius.navalgame1.NavalBattle;
import com.aurelius.navalgame1.gui.BaseWindow;
import com.aurelius.navalgame1.pavo.Game;

public class SinglePlayerGame extends BaseWindow {
	private static final long serialVersionUID = 1L;
	
	StageManager sm;
	GameComponent game;
	String playerName;
	
	public SinglePlayerGame(){
		GameComponent.frame = this;
		myHandler.registerWhiteList(this);
		playerName = "";
		if(NavalBattle.getNavalGame().getPlayer()!=null)
			playerName = NavalBattle.getNavalGame().getPlayer().getName();
		sm = new StageManager(playerName);
		game = sm.getGameComponent();
	}
	

	
	public void toggleFullscreen(){
		game.toggleFullscreen();
	}
	
	public NavalGame getGame(){
		return game.getGame();
	}
	
	public StageManager getStageManager(){
		return sm;
	}
}
