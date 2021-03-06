/*
 * Copyright (C) 2012 Oliver Aurelius Ellison

 */
package com.aurelius.navalgame1.gui;

import javax.swing.*;

import com.aurelius.navalgame1.NavalBattle;
import com.aurelius.navalgame1.data.Constants;
import com.aurelius.navalgame1.game.SinglePlayerGame;
import com.aurelius.navalgame1.gui.listeners.*;
import com.aurelius.navalgame1.io.NavalBattleIO;
import com.aurelius.navalgame1.renderer.Helper;
import com.aurelius.navalgame1.util.URLUtils;

import java.awt.*;
import java.awt.event.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainMenuWindow extends BaseWindow {
	private static final long serialVersionUID = 1L;
	JButton btnRoketGamer;
	public static SinglePlayerGame spg;

	/**
	 * <code>MainMenuWindow</code> constructor.
	 */
	public MainMenuWindow() {
		super();

		getContentPane().setLayout(null);

		JLabel lblVersion = new JLabel(Constants.NAVALBATTLE_VERSION_TITLE);
		lblVersion.setForeground(Color.WHITE);
		JLabel lblNavalBattle = new JLabel("NavalBattle");
		JButton btnSingleplayer = new JButton("Singleplayer");
		JButton btnHelp = new JButton("Help");
		btnRoketGamer = new JButton("NavalGame");
		JButton btnQuit = new JButton("Quit");
		JButton btnCredits = new JButton("Credits");
		final JButton btnMultiplayer = new JButton("Multiplayer");

		lblNavalBattle.setBounds(10, 13, 466, 51);
		lblVersion.setBounds(10, 287, 238, 14);
		btnSingleplayer.setBounds(194, 74, 100, 30);
		btnHelp.setBounds(194, 141, 100, 30);
		btnRoketGamer.setBounds(194, 175, 100, 30);
		btnQuit.setBounds(194, 209, 100, 30);
		btnCredits.setBounds(375, 267, 100, 30);
		btnMultiplayer.setBounds(194, 107, 100, 30);

		lblNavalBattle.setForeground(Color.WHITE);
		lblNavalBattle.setFont(Helper.GUI_MENU_TITLE_FONT);
		lblNavalBattle.setHorizontalAlignment(SwingConstants.CENTER);

		btnMultiplayer.setEnabled(true);

		getContentPane().add(lblVersion);
		getContentPane().add(lblNavalBattle);
		getContentPane().add(btnSingleplayer);
		getContentPane().add(btnHelp);
		getContentPane().add(btnRoketGamer);
		getContentPane().add(btnQuit);
		getContentPane().add(btnCredits);
		getContentPane().add(btnMultiplayer);

		btnSingleplayer.setFocusable(false);
		btnMultiplayer.setFocusable(false);
		btnHelp.setFocusable(false);
		btnRoketGamer.setFocusable(false);
		btnQuit.setFocusable(false);
		btnCredits.setFocusable(false);
		
		btnMultiplayer.setEnabled(false);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(MainMenuWindow.class.getResource("/com/jpii/navalbattle/res/drawable-gui/menu_background.png")));
		label.setBounds(-83, -62, 569, 374);
		getContentPane().add(label);

		class SPGS implements Runnable {
			public SPGS() {

			}
			public void run() {
				spg = new SinglePlayerGame();
				spg.setGameVars();
				spg.setVisible(true);
				//nextWindow("SinglePlayerGame");
			}
		}

		btnSingleplayer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				//SwingUtilities.invokeLater(new SPGS());
				Thread immediate = new Thread(new SPGS());
				immediate.start();
				try {
					Thread.sleep(200);
				}
				catch (Throwable t) {
					
				}
				NavalBattle.getWindowHandler().disposeContained();
			}
		});	

		btnHelp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// nextWindow("HelpWindow");
				URLUtils.openURL("http://jpii.github.io/NavalBattle/help.html");
			}
		});

		btnRoketGamer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(!NavalBattle.getGameState().isOffline()) {
					URLUtils.openURL("http://roketgamer.com/viewgame.php?id=3");
				}
			}
		});

		btnQuit.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				WindowCloser.close();
			}
		});

		btnCredits.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				nextWindow("CreditsWindow");
			}
		});

		btnMultiplayer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(btnMultiplayer.isEnabled()){
					JOptionPane.showMessageDialog(null,"Warning: Multiplayer is experiemental." +
							"\nProceed with caution.","NavalBattle",JOptionPane.WARNING_MESSAGE);
					spg = new SinglePlayerGame();
					boolean valid = false;
					String ip = NavalBattleIO.getAttribute("lastGoodIP");
					while (!valid) {
						ip = JOptionPane.showInputDialog(null,(Object)"Enter IP address to connect to:",(Object)ip);
						if (ip == null)
							return;
						if (ip.equals(""))
							valid = false;
						else
							valid = validate(ip);
					}
					NavalBattleIO.saveAttribute("lastGoodIP", ip);
					spg.setGameVars();
					nextWindow("SinglePlayerGame");
					NavalBattle.getWindowHandler().disposeContained();
				}
			}
		});
	}

	private static final String PATTERN = 
			"^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
					"([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
					"([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
					"([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";

	public static boolean validate(final String ip) {          
		Pattern pattern = Pattern.compile(PATTERN);
		Matcher matcher = pattern.matcher(ip);
		return matcher.matches();             
	}

	/**
	 * Set visible.
	 */
	public void setVisible(boolean visible){
		super.setVisible(visible);
		if(isVisible()){
			if(NavalBattle.getGameState().isOffline()) {
				btnRoketGamer.setEnabled(false);
			}
		}
	}
}