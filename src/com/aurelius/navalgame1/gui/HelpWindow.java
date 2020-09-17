/*
 * Copyright (C) 2012 Oliver Aurelius Ellison

 */
package com.aurelius.navalgame1.gui;

import javax.swing.*;

import com.aurelius.navalgame1.NavalBattle;
import com.aurelius.navalgame1.util.URLUtils;

import java.awt.*;
import java.awt.event.*;

public class HelpWindow extends BaseWindow {
	private static final long serialVersionUID = 1L;
	private JLabel lblTitle;

	/**
	 * <code>HelpWindow</code> constructor.
	 */
	public HelpWindow() {
		getContentPane().setLayout(null);
		
		lblTitle = new JLabel("NavalBattle Help");
		JButton btnClose = new JButton("Close");
		JLabel lblYoutubeTutorials = new JLabel("YouTube Tutorials");
		JButton btnVideoOverview = new JButton("Overview");
		JButton btnVideoNavigation = new JButton("Navigation");
		JButton btnVideoCombat = new JButton("Combat");
		JButton btnVideoUpgradeShop = new JButton("Upgrades");
		JButton btnVideoMultiplayer = new JButton("Multiplayer");
		JButton btnVideoRoketgamer = new JButton("NavalGame");
		
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		lblTitle.setBounds(10, 11, 121, 23);
		btnClose.setBounds(10, 137, 90, 30);
		lblYoutubeTutorials.setBounds(20, 36, 111, 14);
		btnVideoOverview.setBounds(10, 61, 100, 30);
		btnVideoNavigation.setBounds(130, 61, 100, 30);
		btnVideoCombat.setBounds(250, 61, 100, 30);
		btnVideoUpgradeShop.setBounds(370, 61, 100, 30);
		btnVideoMultiplayer.setBounds(130, 102, 100, 30);
		btnVideoRoketgamer.setBounds(250, 102, 100, 30);
		
		getContentPane().add(lblTitle);
		getContentPane().add(btnClose);
		getContentPane().add(lblYoutubeTutorials);
		getContentPane().add(btnVideoOverview);
		getContentPane().add(btnVideoNavigation);
		getContentPane().add(btnVideoCombat);
		getContentPane().add(btnVideoUpgradeShop);
		getContentPane().add(btnVideoMultiplayer);
		getContentPane().add(btnVideoRoketgamer);
		
		btnVideoOverview.setFocusable(false);
		btnVideoNavigation.setFocusable(false);
		btnVideoCombat.setFocusable(false);
		btnVideoUpgradeShop.setFocusable(false);
		btnVideoMultiplayer.setFocusable(false);
		btnVideoRoketgamer.setFocusable(false);
		btnClose.setFocusable(false);
		
		btnVideoRoketgamer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				NavalBattle.getDebugWindow().printInfo("Opening NavalGame tutorial");
				URLUtils.openURL("http://www.google.com");
			}
		});
		btnVideoOverview.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				NavalBattle.getDebugWindow().printInfo("Opening overview tutorial");
				URLUtils.openURL("http://www.google.com");
			}
		});
		btnClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				nextWindow("MainMenuWindow");
			}
		});
		btnVideoOverview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnVideoNavigation.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				NavalBattle.getDebugWindow().printInfo("Opening navigation tutorial");
				URLUtils.openURL("http://www.google.com");
			}
		});
		btnVideoCombat.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				NavalBattle.getDebugWindow().printInfo("Opening combat tutorial");
				URLUtils.openURL("http://www.google.com");
			}
		});
		btnVideoUpgradeShop.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				NavalBattle.getDebugWindow().printInfo("Opening upgrade shop tutorial");
				URLUtils.openURL("http://www.google.com");
			}
		});
		btnVideoMultiplayer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				NavalBattle.getDebugWindow().printInfo("Opening Multiplayer tutorial");
				URLUtils.openURL("http://www.google.com");
			}
		});
	}
}