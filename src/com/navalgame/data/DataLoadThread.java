/*
 * Copyright (C) 2012 Oliver Aurelius Ellison

 */
package com.navalgame.data;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import com.navalgame.Player;
import com.navalgame.NavalGame;
import com.navalgame.gravatar.Gravatar;
import com.navalgame.gravatar.GravatarDefaultImage;
import com.navalgame.gravatar.GravatarRating;

public class DataLoadThread extends Thread {
	
	private Player player;
	
	public DataLoadThread(Player player) {
		this.player = player;
		NavalGame.getInstance().getLoggerHook().printInfo("DataLoadThread created");
	}

	@Override
	public void run() {
		loadUserData();
		loadFriends();
		loadGravatars();
		
		player.setDataLoaded(true);
		NavalGame.getInstance().getLoggerHook().printInfo("DataLoadThread completed");
	}
	
	private void loadUserData() {
		try {
			URL url = new URL(NavalGame.SERVER_LOCATION + "/api/" + NavalGame.VERSION + "/user/getuserinfo.php?key=" + 
								NavalGame.getInstance().getSession().getSessionKey());
			
			URLConnection connection = url.openConnection();
		    connection.addRequestProperty("Protocol", "Http/1.1");
		    connection.addRequestProperty("Connection", "keep-alive");
		    connection.addRequestProperty("Keep-Alive", "1000");
		    connection.addRequestProperty("User-Agent", "Web-Agent");

			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			String result = in.readLine();
			
			if (result.contains("true")) {
				player.setEmail(in.readLine());
				NavalGame.getInstance().getLoggerHook().printInfo("Loaded user email: " + player.getEmail());
			} else if (result.contains("false")) {
				if(result.contains("Invalid session key")) {
					NavalGame.getInstance().getLoggerHook().printError("Invalid session key when loading user email");
				} else {
					NavalGame.getInstance().getLoggerHook().printError("Unknown error when loading user email (returned false)");
				}
			} else {
				NavalGame.getInstance().getLoggerHook().printError("Unknown error when loading user email (other)");
			}

			in.close();
		} catch (Exception e) { }
	}
	
	private void loadFriends() {
		// TODO: Implement
	}
	
	private void loadGravatars() {
		Gravatar gravatar = new Gravatar();
		gravatar.setRating(GravatarRating.GENERAL_AUDIENCES);
		gravatar.setDefaultImage(GravatarDefaultImage.WAVATAR);
		
		gravatar.setSize(16);
		player.setAvatar(gravatar.download(player.getEmail()), 16);
		
		gravatar.setSize(32);
		player.setAvatar(gravatar.download(player.getEmail()), 32);
		
		gravatar.setSize(64);
		player.setAvatar(gravatar.download(player.getEmail()), 64);
		
		gravatar.setSize(128);
		player.setAvatar(gravatar.download(player.getEmail()), 128);
		
		gravatar.setSize(256);
		player.setAvatar(gravatar.download(player.getEmail()), 256);
		
		NavalGame.getInstance().getLoggerHook().printInfo("Loaded Gravatars");
	}
}
