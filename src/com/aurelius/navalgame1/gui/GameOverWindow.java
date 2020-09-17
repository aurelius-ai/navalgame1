/*
 * Copyright (C) 2012 Oliver Aurelius Ellison

 */
package com.aurelius.navalgame1.gui;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GameOverWindow extends BaseWindow {
	private static final long serialVersionUID = 1L;

	/**
	 * <code>GameOverWindow</code> constructor.
	 */
	public GameOverWindow() {
		super(500,250);
		getContentPane().setLayout(null);
		
		JLabel lblGameOver = new JLabel("Game Over");
		lblGameOver.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblGameOver.setBounds(169, 11, 121, 38);
		getContentPane().add(lblGameOver);
		
		JLabel scoreValue = new JLabel("200,000");
		scoreValue.setBounds(244, 60, 46, 14);
		getContentPane().add(scoreValue);
		
		JLabel lblScore = new JLabel("Score");
		lblScore.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblScore.setBounds(169, 60, 46, 14);
		getContentPane().add(lblScore);
		
		JLabel lblRounds = new JLabel("Rounds");
		lblRounds.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblRounds.setBounds(169, 85, 46, 14);
		getContentPane().add(lblRounds);
		
		JLabel label = new JLabel("4");
		label.setBounds(244, 85, 46, 14);
		getContentPane().add(label);
		
		JButton btnMainMenu = new JButton("Main Menu");
		btnMainMenu.setBounds(169, 133, 105, 28);
		getContentPane().add(btnMainMenu);
		
		JButton btnRoketGamer = new JButton("NavalGame");
		btnRoketGamer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnRoketGamer.setBounds(169, 172, 105, 28);
		getContentPane().add(btnRoketGamer);
		
		JLabel lblScoreSubmitted = new JLabel("Score Submitted");
		lblScoreSubmitted.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblScoreSubmitted.setBounds(121, 108, 94, 14);
		getContentPane().add(lblScoreSubmitted);
		
		JLabel lblYes = new JLabel("Yes");
		lblYes.setBounds(244, 108, 46, 14);
		getContentPane().add(lblYes);
	}
}