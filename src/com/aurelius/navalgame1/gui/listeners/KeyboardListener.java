/*
 * Copyright (C) 2012 Oliver Aurelius Ellison

 */
package com.aurelius.navalgame1.gui.listeners;

import java.awt.event.*;
import java.util.ArrayList;

import com.aurelius.navalgame1.game.SinglePlayerGame;
import com.aurelius.navalgame1.gui.*;

public class KeyboardListener implements KeyListener {
	
	ArrayList<BaseWindow> windows;
	
	public KeyboardListener() {
		super();
		windows = new ArrayList<BaseWindow>();
	}
	
	public void add(BaseWindow classname){
		windows.add(classname);
	}
	
	public void remove(BaseWindow classname){
		windows.remove(classname);
	}
	
	public void keyPressed(KeyEvent k) {	
		if(k.getKeyCode() == KeyEvent.VK_ESCAPE) {
			WindowCloser.close();
		}
		for(int index=0; index<windows.size();index++) {
			BaseWindow window = windows.get(index);
			if(window.name.equals("LoginWindow")) {
				LoginWindow l = (LoginWindow) window;
				if(k.getKeyCode() == KeyEvent.VK_ENTER) {
					l.login();
				}
			}

			if(window.name.equals("SinglePlayerGame")){
				SinglePlayerGame g = (SinglePlayerGame) window;
				if(k.getKeyCode() == KeyEvent.VK_F11){
					g.toggleFullscreen();
				}
				if(k.getKeyCode() == KeyEvent.VK_U){ // used for debug purposes only
					g.setNewGame();
				}
				if(k.getKeyCode() == KeyEvent.VK_H){
					g.getGame().getHud().togglePinable();
				}
				if(k.getKeyCode() == KeyEvent.VK_M){
					g.getGame().getHud().getMid().moveAction();
				}
				if(k.getKeyCode() == KeyEvent.VK_G || k.getKeyCode() == KeyEvent.VK_1){
					g.getGame().getHud().getMid().primaryAction();
				}
				if(k.getKeyCode() == KeyEvent.VK_2 || k.getKeyCode() == KeyEvent.VK_A){
					g.getGame().getHud().getMid().secondaryAction();
				}
				if(k.getKeyCode() == KeyEvent.VK_S){
					g.getGame().getHud().getMid().shopAction();
				}
				if(k.getKeyCode() == KeyEvent.VK_D){
					g.getGame().getHud().getMid().submergeAction();
				}
				if(k.getKeyCode() == KeyEvent.VK_N || k.getKeyCode() == KeyEvent.VK_T){
					g.getGame().getHud().getMid().turnAction();
				}
			}
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}
}
