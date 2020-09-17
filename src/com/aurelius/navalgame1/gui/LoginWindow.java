/*
 * Copyright (C) 2012 Oliver Aurelius Ellison

 */

package com.aurelius.navalgame1.gui;

import javax.swing.*;
import java.awt.event.*;

import com.aurelius.navalgame1.NavalBattle;
import com.aurelius.navalgame1.data.Constants;
import com.aurelius.navalgame1.gui.listeners.Focus;
import com.aurelius.navalgame1.io.NavalBattleIO;
import com.aurelius.navalgame1.io.SettingsAttribute;
import com.aurelius.navalgame1.util.URLUtils;
import com.navalgame.Player;
import com.navalgame.rauth.*;

public class LoginWindow extends BaseWindow {
	private static final long serialVersionUID = 1L;
	JButton loginButton, updateButton, announcementButton;
	JLabel usernameLabel, passwordLabel, lblCheckingForUpdate;
	JTextField usernameField;
	JPasswordField passwordField;

	/**
	 * <code>LoginWindow</code> constructor.
	 */
	public LoginWindow() {
		super(500,190);
		getContentPane().setLayout(null);
		usernameLabel = new JLabel();
		usernameField = new JTextField(25);
		passwordLabel = new JLabel();
		passwordField = new JPasswordField(25);
		loginButton = new JButton(NavalBattle.getLocalizationManager().getString("login_login"));
		JButton registerButton = new JButton(NavalBattle.getLocalizationManager().getString("login_register"));
		JButton offlineButton = new JButton(NavalBattle.getLocalizationManager().getString("login_offline"));
		JLabel lblVersion = new JLabel(Constants.NAVALBATTLE_VERSION_TITLE);
		
		usernameLabel.setText(NavalBattle.getLocalizationManager().getString("login_username"));
		passwordLabel.setText(NavalBattle.getLocalizationManager().getString("login_password"));
		passwordLabel.setToolTipText(NavalBattle.getLocalizationManager().getString("login_roketgamer_tooltip"));
		passwordField.setToolTipText(NavalBattle.getLocalizationManager().getString("login_roketgamer_tooltip"));
		
		usernameLabel.setBounds(295,8,78,30);
		usernameField.setBounds(365,8,113,30);
		passwordLabel.setBounds(295,39,78,30);
		passwordField.setBounds(365,39,113,30);
		loginButton.setBounds(400,81,78,30);
		registerButton.setBounds(400, 113, 78, 30);
		offlineButton.setBounds(315, 81, 78, 30);
		lblVersion.setBounds(7, 139, 193, 14);
		getContentPane().add(usernameLabel);
		getContentPane().add(usernameField);
		getContentPane().add(passwordLabel);
		getContentPane().add(passwordField);
		getContentPane().add(loginButton);
		getContentPane().add(lblVersion);
		getContentPane().add(registerButton);
		getContentPane().add(offlineButton);
		
		loginButton.setFocusable(false);
		registerButton.setFocusable(false);
		offlineButton.setFocusable(false);
		
		passwordField.addKeyListener(Constants.keys);
		usernameField.addKeyListener(Constants.keys);
		usernameField.setText(NavalBattleIO.getAttribute("lastGoodUserName"));
		
		announcementButton = new JButton(NavalBattle.getLocalizationManager().getString("login_announcement"));
		announcementButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		announcementButton.setFocusable(false);
		announcementButton.setBounds(34, 68, 117, 30);
		getContentPane().add(announcementButton);
		
		updateButton = new JButton(NavalBattle.getLocalizationManager().getString("login_update"));
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		updateButton.setFocusable(false);
		updateButton.setBounds(34, 27, 117, 30);
		getContentPane().add(updateButton);
		
		lblCheckingForUpdate = new JLabel(NavalBattle.getLocalizationManager().getString("login_checking"));
		lblCheckingForUpdate.setBounds(34, 55, 153, 14);
		getContentPane().add(lblCheckingForUpdate);
		passwordField.addFocusListener(new Focus(this));
		usernameField.addFocusListener(new Focus(this));
		
		updateButton.setVisible(false);
		announcementButton.setVisible(false);
		
		Thread t = new Thread(new Runnable() {
			public void run() {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						lblCheckingForUpdate.setVisible(true);
					}
				});
				
				try {
					Thread.sleep(3500);
				} catch(Exception e) { }
				
				try {
					while(!NavalBattle.getBroadcastService().hasChecked()) { }
				} catch(Exception e) { }
				
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						lblCheckingForUpdate.setVisible(false);
					}
				});
				
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						try {
							updateButton.setVisible(NavalBattle.getBroadcastService().needsUpdate());
							announcementButton.setVisible(NavalBattle.getBroadcastService().hasAnnouncement());
						} catch(Exception e) {
							NavalBattle.getDebugWindow().printWarning("Error while setting BroadcastService button(s) visible.");
						}
					}
				});
				
				repaint();
			}
		});
		t.start();
		
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if(updateButton.isVisible()) {
					int dialogResult = JOptionPane.showConfirmDialog (null, NavalBattle.getLocalizationManager().getString("login_update_alert"), "NavalBattle",JOptionPane.YES_NO_OPTION);
					
					if (dialogResult != JOptionPane.YES_OPTION) {
						return;
					}
				}
				
				login();
			}
		});
		
		offlineButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				NavalBattle.getWindowHandler().getToasterManager().showToaster(new ImageIcon(getClass().getResource("/com/roketgamer/res/logo_100px.png")), NavalBattle.getLocalizationManager().getString("toast_offline_mode"));
				NavalBattle.getDebugWindow().printInfo("Opening in offline mode");
				NavalBattle.getDebugWindow().printWarning("NavalGame disabled");
				NavalBattle.getGameState().setOffline(true);
				nextWindow("LoggingInWindow");
				donewithMe();
			}
		});
		
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {			
				NavalBattle.getDebugWindow().printInfo("Opening register page");
				URLUtils.openURL(NavalBattle.getNavalGame().getServerLocation() + "/register.php?game=1&name=NavalBattle");
			}
		});
		
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				nextWindow("UpdateWindow");
			}
		});
		
		announcementButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				nextWindow("BroadcastWindow");
			}
		});
	}
	
	private String createString(char[] c){
		String temp = "";
		for(int index=0; index<c.length; index++)
			temp+=c[index];
		return temp;
	}
	
	/**
	 *  Method for handling login with NavalGame.
	 */
	public void login() {
		NavalBattleIO.saveAttribute(new SettingsAttribute("lastGoodUserName",usernameField.getText()));
		AuthStatus status = NavalBattle.getNavalGame().init(new APIKey(Constants.API_KEY), 
				new Player(usernameField.getText(), 
				new Password(createString(passwordField.getPassword()))), Constants.ROKETGAMER_LOG_HOOK);
		
		if (status == AuthStatus.GOOD) {
			NavalBattle.getDebugWindow().printInfo("User authenticated");
			NavalBattle.getDebugWindow().printInfo("Logged in as: " + NavalBattle.getNavalGame().getPlayer().getName());
			NavalBattleIO.saveAttribute(new SettingsAttribute("lastGoodUserName",NavalBattle.getNavalGame().getPlayer().getName()));
			NavalBattle.getGameState().setOffline(false);
			nextWindow("LoggingInWindow");
			donewithMe();
		} else {
			if(status == AuthStatus.BAD) {
				NavalBattle.getDebugWindow().printWarning("Authentication failed: AuthStatus.BAD");	
				JOptionPane.showMessageDialog(this, "Incorrect username or password. \nUse your application password to login.");
			} else if (status == AuthStatus.OFFLINE) {
				NavalBattle.getDebugWindow().printWarning("Authentication failed: AuthStatus.OFFLINE");	
				JOptionPane.showMessageDialog(this, "Unable to login. NavalGame API is offline. Check website.");
			} else if (status == AuthStatus.INVALID_API_KEY) {
				NavalBattle.getDebugWindow().printWarning("Authentication failed: AuthStatus.INVALID_API_KEY");	
				JOptionPane.showMessageDialog(this, "Unable to login. API key is invalid.");
			} else if (status == AuthStatus.UNKNOWN) {
				NavalBattle.getDebugWindow().printWarning("Authentication failed: AuthStatus.UNKNOWN");	
				JOptionPane.showMessageDialog(this, "Unable to login. Retry later or check the NavalGame website.");
			} else {
				NavalBattle.getDebugWindow().printWarning("Authentication failed: AuthStatus is not recognized.");
				NavalBattle.getDebugWindow().printWarning("Internet may be disconnected.");
				JOptionPane.showMessageDialog(this, "Unable to login. Check your internet connection.");
			}
		}
	}
}