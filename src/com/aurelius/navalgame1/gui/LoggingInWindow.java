/*
 * Copyright (C) 2012 Oliver Aurelius Ellison

 */
package com.aurelius.navalgame1.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

import com.aurelius.navalgame1.NavalBattle;
import com.aurelius.navalgame1.data.Constants;
import com.aurelius.navalgame1.util.FileUtils;

public class LoggingInWindow extends BaseWindow {
	private static final long serialVersionUID = 1L;
	private Timer timer;
	private int currentImage = 0;
	private int length = 0;
	
	/**
	 * <code>LoggingInWindow</code> constructor.
	 */
	public LoggingInWindow() {
		setUndecorated(true);
		getContentPane().setLayout(null);
		
		JProgressBar progressBar = new JProgressBar();
		final JLabel label = new JLabel("");
		
		progressBar.setBounds(0, 326, 492, 14);
		label.setBounds(0, 0, 492, 340);
		
		progressBar.setIndeterminate(true);
		
		getContentPane().add(progressBar);
		getContentPane().add(label);
		
		ActionListener al = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				length += Constants.SPLASH_DURATION;
				if (currentImage == 0) {
					label.setIcon(new ImageIcon(FileUtils.getResourcePath(("drawable-gui/jpii_title.png"))));
				}
				else if (currentImage == 1) {
					label.setIcon(new ImageIcon(FileUtils.getResourcePath(("drawable-gui/roketgamer_title.png"))));
				}
				else {
					label.setIcon(new ImageIcon(FileUtils.getResourcePath(("drawable-gui/navalbattle_title.png"))));
				}
				if (currentImage == 2)
					currentImage = 0;
				else
					currentImage++;
				
				if(NavalBattle.getGameState().isOffline()) {
					if(length > Constants.SPLASH_SCREEN_TIMEOUT)
						openMenu();
				} else {
					if (length > Constants.SPLASH_SCREEN_TIMEOUT && NavalBattle.getNavalGame().getPlayer().hasLoadedData()) {
						openMenu();
						NavalBattle.getWindowHandler().getToasterManager().showToaster(new ImageIcon(NavalBattle.getNavalGame().getPlayer().getAvatarAsBytes(64)), " Logged in as " + NavalBattle.getNavalGame().getPlayer().getName());
					}
				}
			}
		};
		timer = new Timer(Constants.SPLASH_DURATION,al);
		timer.setInitialDelay(0);
		timer.start();
		
		label.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me) {
				openMenu();
			}
		});
		if(!isVisible())
			timer.stop();
	}
	
	boolean alreadyOpened = false;
	
	/**
	 * Open <code>MainMenuWindow</code>
	 */
	public void openMenu() {
		if (!alreadyOpened) {
			alreadyOpened = true;
			nextWindow("MainMenuWindow");
			donewithMe();
		}
	}

	/**
	 * Set <code>Window</code> visible.
	 */
	public void setVisible(boolean visible){
		super.setVisible(visible);
		if(isVisible()){
			alreadyOpened = false;
			timer.start();
		}
		else{
			if(timer!=null)
				timer.stop();
			length = 0;
		}
	}
}
