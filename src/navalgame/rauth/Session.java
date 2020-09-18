/*
 * Copyright (C) 2012 Oliver Aurelius Ellison

 */
package com.navalgame.rauth;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import com.navalgame.NavalGame;

public class Session {
	
	private String sessionKey;
	
	/**
	 * Create a new <code>Session</code>.
	 * @param sessionID
	 */
	public Session(String sessionKey) {
		this.sessionKey = sessionKey;
	}
	
	/**
	 * Get session ID.
	 * @return
	 */
	public String getSessionKey() {
		return sessionKey;
	}
	
	/**
	 * Logout of the current <code>Session</code>. Note that future API calls will fail.
	 */
	public void logout() {
		try {
			URL url = new URL(NavalGame.SERVER_LOCATION + "/api/" + NavalGame.VERSION + "/auth/logout.php?key=" + NavalGame.getInstance().getKey());
			
			URLConnection connection = url.openConnection();
		    connection.addRequestProperty("Protocol", "Http/1.1");
		    connection.addRequestProperty("Connection", "keep-alive");
		    connection.addRequestProperty("Keep-Alive", "1000");
		    connection.addRequestProperty("User-Agent", "Web-Agent");

			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			in.close();
		} catch (Exception e) { }
	}
}
