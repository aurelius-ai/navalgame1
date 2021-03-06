/*
 * Copyright (C) 2012 Oliver Aurelius Ellison

 */
package com.navalgame;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import com.navalgame.log.LoggerHook;
import com.navalgame.rauth.*;

public class NavalGame {
	
	/** NavalGame components */
	private APIKey key;
	private Player player;
	private AuthStatus status;
	private Session session;
	private LoggerHook loggerHook;
	
	private static NavalGame instance;
	
	/** Constants */
	public static final String VERSION = "1.0";
	public static final String SERVER_LOCATION = "http://www.roketgamer.com";
	
	/**
	 * Initialize NavalGame. Returns <code>AuthStatus</code>.
	 * @param key
	 * @param player
	 * @param forceAuth
	 * @return
	 */
	public AuthStatus init(APIKey key, Player player, LoggerHook loggerHook) {
		instance = this;
		
		this.key = key;
		this.player = player;
		this.loggerHook = loggerHook;
		
		try {
			URL url = new URL(SERVER_LOCATION + "/api/" + VERSION + "/auth/login.php?key=" + key.getKey() + "&username=" + player.getName()
					+ "&password=" + player.getPassword().getPassword());
			
			URLConnection connection = url.openConnection();
		    connection.addRequestProperty("Protocol", "Http/1.1");
		    connection.addRequestProperty("Connection", "keep-alive");
		    connection.addRequestProperty("Keep-Alive", "1000");
		    connection.addRequestProperty("User-Agent", "Web-Agent");

			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			String result = in.readLine();
			
			if (result.contains("true")) {
				session = new Session(in.readLine());
				status = AuthStatus.GOOD;
				loggerHook.printInfo("Logged in successfully");
				player.loadData();
			} else if (result.contains("false")) {
				if(result.contains("Invalid API key")) {
					status = AuthStatus.INVALID_API_KEY;
					loggerHook.printError("Invalid API key");
				} else if (result.contains("Invalid user")) {
					status = AuthStatus.BAD;
					loggerHook.printError("Invalid user");
				} else if(result.contains("offine")) {
					status = AuthStatus.OFFLINE;
					loggerHook.printError("Server is offline");
				}
			} else {
				status = AuthStatus.UNKNOWN;
				loggerHook.printError("Unknown AuthStatus");
			}

			in.close();
		} catch (Exception e) { }
		
		return status;
	}
	
	/**
	 * Returns current <code>APIKey</code>.
	 * @return
	 */
	public APIKey getKey() {
		return key;
	}
	
	/**
	 * Return current <code>Player</code>.
	 * @return
	 */
	public Player getPlayer() {
		return player;
	}
	
	/**
	 * Return current <code>Session</code>.
	 * @return
	 */
	public Session getSession() {
		return session;
	}
	
	/**
	 * Return current <code>AuthStatus</code>.
	 * @return
	 */
	public AuthStatus getStatus() {
		return status;
	}
	
	/**
	 * Return current <code>LoggerHook</code>.
	 * @return
	 */
	public LoggerHook getLoggerHook() {
		return loggerHook;
	}
	
	/**
	 * Return NavalGame version.
	 * @return
	 */
	public String getVersion() {
		return VERSION;
	}
	
	/**
	 * Return NavalGame server location.
	 * @return
	 */
	public String getServerLocation() {
		return SERVER_LOCATION;
	}
	
	/**
	 * Get current <code>NavalGame</code> instance.
	 */
	public static NavalGame getInstance() {
		return instance;
	}
}
