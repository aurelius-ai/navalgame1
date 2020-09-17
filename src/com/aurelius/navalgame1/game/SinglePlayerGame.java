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
	
	public void setNewGame(){
		if (game != null) {
		getContentPane().remove(game);
		}
		
		game = sm.newGameComponent();
		setSize(Game.Settings.initialWidth,Game.Settings.initialHeight-40);
		setGameVars();
		getContentPane().add(game);
	}
	
	public void setSize(int width, int height) {
		if (game != null) {
			game.setSize(width,height);
		}
		super.setSize(width,height);
	}
	
	public void setGameVars() {
		game.setLocation(0,40);
		setContentPane(game);
		this.getContentPane().setLayout(null);
		setSize(Game.Settings.initialWidth,Game.Settings.initialHeight-40);
		setLocation(0,0);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
	}
	
	public void setVisible(boolean visible){
		super.setVisible(visible);
		if(isVisible()){
			NavalBattle.getWindowHandler().disposeContained();
		}
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
