/*
 * Copyright (C) 2012 Oliver Aurelius Ellison

 */

package com.aurelius.navalgame1.data;

import java.util.ArrayList;

import com.aurelius.navalgame1.NavalBattle;
import com.aurelius.navalgame1.game.SinglePlayerGame;
import com.aurelius.navalgame1.gui.*;
import com.aurelius.navalgame1.gui.listeners.WindowCloser;
import com.aurelius.navalgame1.io.NavalBattleIO;
import com.aurelius.navalgame1.pavo.Game;
import com.aurelius.navalgame1.pavo.boost.BoostBuilder;
import com.jpii.gamekit.GameKit;
import com.jpii.gamekit.debug.*;

public class Commands {
	
	/**
	 * Loaded on start of game
	 */
	@SuppressWarnings("serial")
	public static final ArrayList<Command> COMMANDS = new ArrayList<Command>() {{
	    
	    add(new Command("quit", "", "Quit game", new CommandAction() { 
	    	public void onRun(Command c, String[] args) {
	    		WindowCloser.close();
	    	}}
	    ));
	    
	    add(new Command("boost", "", "Launches PavoBoost visual editor", new CommandAction()
	    {
	    	public void onRun(Command c, String[] args) {
	    		BoostBuilder builder = new BoostBuilder();
	    		builder.setVisible(true);
	    	}
	    }));
	    
	   
	    
	    add(new Command("openwindow", "<windowid>", "Force a window to appear", new CommandAction() { 
	    	public void onRun(Command c, String[] args) {
	    		args[0] = args[0].toLowerCase();
	    		if(args[0].equals("login") || args[0].equals("0") || args[0].equals("loginwindow")) {
	    			new LoginWindow();
	    		}
	    		
	    		if(args[0].equals("main") || args[0].equals("1") || args[0].equals("mainmenu") || args[0].equals("mainmenuwindow")) {
	    			new MainMenuWindow();
	    		}
	    		
	    		if(args[0].equals("credits") || args[0].equals("2") || args[0].equals("creditswindow")) {
	    			new CreditsWindow();
	    		}
	    		
	    		if(args[0].equals("help") || args[0].equals("3") || args[0].equals("helpwindow")) {
	    			new HelpWindow();
	    		}
	    		
	    		if(args[0].equals("game") || args[0].equals("6") || args[0].equals("gamewindow") || args[0].equals("newgame")) {
	    			new SinglePlayerGame();
	    		}
	    	}}
	    ));
	    
	    add(new Command("rginfo", "", "Get NavalGame info", new CommandAction() { 
	    	public void onRun(Command c, String[] args) {
	    		NavalBattle.getDebugWindow().println("NavalGame " + NavalBattle.getNavalGame().getVersion());
	    		NavalBattle.getDebugWindow().println("Server: " + NavalBattle.getNavalGame().getServerLocation());
	    		NavalBattle.getDebugWindow().println("Auth status: " + NavalBattle.getNavalGame().getStatus());
	    	}}
	    ));
	    
	    add(new Command("gamekitinfo", "", "Get GameKit info", new CommandAction() { 
	    	public void onRun(Command c, String[] args) {
	    		NavalBattle.getDebugWindow().println("GameKit " + GameKit.getVersion());
	    		NavalBattle.getDebugWindow().println("API level: " + GameKit.getApiLevel());
	    	}}
	    ));
	}};
}