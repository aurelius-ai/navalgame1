/*
 * Copyright (C) 2012 Oliver Aurelius Ellison

 */

package com.navalgame.achievement;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import com.navalgame.NavalGame;

public class Achievement {
	
	private int id;
	private String name;
	private boolean isSecret;
	
	/**
	 * Create a new <code>Achievement</code>.
	 * @param id
	 * @param name
	 * @param isSecret
	 */
	public Achievement(int id, String name, boolean isSecret) {
		this.id = id;
		this.name = name;
		this.isSecret = isSecret;
	}
	
	/**
	 * Get <code>Achievement</code> ID.
	 * @return id
	 */
	public int getID() {
		return id;
	}
	
	/**
	 * Get <code>Achievement</code> name.
	 * @return name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Returns if <code>Achievement</code> is secret.
	 * @return
	 */
	public boolean isSecret() {
		return isSecret;
	}
	
	/**
	 * Returns if current logged in user has achieved achievement.
	 * @return
	 */
	public boolean hasAchieved() {
		try {
			URL url = new URL(NavalGame.SERVER_LOCATION + "/api/" + NavalGame.VERSION + "/achievement/check.php?session=" + NavalGame.getInstance().getSession().getSessionKey().trim() + "id=" + id);

			URLConnection connection = url.openConnection();
		    connection.addRequestProperty("Protocol", "Http/1.1");
		    connection.addRequestProperty("Connection", "keep-alive");
		    connection.addRequestProperty("Keep-Alive", "1000");
		    connection.addRequestProperty("User-Agent", "Web-Agent");
		    
		    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			
			String result = in.readLine();
			if (result.contains("true")) {
				in.close();
				return true;
			} else {
				in.close();
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	/**
	 * Submit an achievement. Returns if operation is successful.
	 * The server will not record duplicates but it is good practice to
	 * not submit achievements that have already been awarded.
	 * @return
	 */
	public boolean submit() {
		try {
			URL url = new URL(NavalGame.SERVER_LOCATION + "/api/" + NavalGame.VERSION + "/achievement/submit.php?session=" + NavalGame.getInstance().getSession().getSessionKey().trim() + "id=" + id);

			URLConnection connection = url.openConnection();
		    connection.addRequestProperty("Protocol", "Http/1.1");
		    connection.addRequestProperty("Connection", "keep-alive");
		    connection.addRequestProperty("Keep-Alive", "1000");
		    connection.addRequestProperty("User-Agent", "Web-Agent");
			
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			String result = in.readLine();
			if (result.contains("true")) {
				in.close();
				return true;
				
			} else {
				in.close();
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
}
