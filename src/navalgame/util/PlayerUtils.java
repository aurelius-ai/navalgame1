/*
 * Copyright (C) 2012 Oliver Aurelius Ellison

 */
package com.navalgame.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import com.navalgame.NavalGame;

public class PlayerUtils {
	
	/**
	 * Convert username to user ID. Returns -1 if not found or
	 * if operation fails.
	 * @param username
	 * @return
	 */
	public static int toId(String username) {
		try {
			URL url = new URL(NavalGame.SERVER_LOCATION + "/api/" + NavalGame.VERSION + "/public/getuserid.php?user=" + username);
			
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

			String result = in.readLine();
			if(!result.equals(null))
				return Integer.parseInt(result);
			else
				return -1;
		} catch (Exception e) { 
			return -1;
		}
	}
	
	/**
	 * Convert user ID to username. Returns an empty String
	 * if not found or if operation fails.
	 * @param id
	 * @return
	 */
	public static String toString(int id) {
		try {
			URL url = new URL(NavalGame.SERVER_LOCATION + "/api/" + NavalGame.VERSION + "/public/getusername.php?id=" + id);
			
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

			String result = in.readLine();
			
			if(!result.equals(null))
				return result;
			else
				return "";
		} catch (Exception e) { 
			return "";
		}
	}
}