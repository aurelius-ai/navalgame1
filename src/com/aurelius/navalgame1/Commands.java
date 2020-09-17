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
	 * Commands loaded on game start
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
	    
	    add(new Command("version", "", "View version info", new CommandAction() { 
	    	public void onRun(Command c, String[] args) {
	    		NavalBattle.getDebugWindow().println(Constants.NAVALBATTLE_VERSION_TITLE + " (" + Constants.VERSION_CODE + ")");
	    	}}
	    ));
	    
	    add(new Command("credits", "", "NavalBattle credits", new CommandAction() { 
	    	public void onRun(Command c, String[] args) {
	    		NavalBattle.getDebugWindow().println("----------------- NavalBattle Credits -----------------");
	    		NavalBattle.getDebugWindow().println("Anthony \"abauer\" Bauer - game design lead");
	    		NavalBattle.getDebugWindow().println("Thomas \"TexasGamer\" Gaubert - SCM manager; NavalGame lead");
	    		NavalBattle.getDebugWindow().println("Max \"maximusvladimir\" K. - rendering lead, grid lead, Pavo lead");
	    		NavalBattle.getDebugWindow().println("JR \"DarkWarHero\" Vetus - TBD");
	    		NavalBattle.getDebugWindow().println("Matt \"Matthis5point0\" Waller - TBD");
	    		NavalBattle.getDebugWindow().println("Zach \"smeagle42\" Mathewson - Ship Designer");
	    		NavalBattle.getDebugWindow().println("");
	    		NavalBattle.getDebugWindow().println("GitHub - source code hosting");
	    		NavalBattle.getDebugWindow().println("NavalGame - online social gaming");
	    	}}
	    ));
	    
	    add(new Command("setscore", "<score>", "Set game score", new CommandAction() { 
	    	public void onRun(Command c, String[] args) {
	    		try {
	    			MainMenuWindow.spg.getGame().getTurnManager().getTurn().getPlayer().setscore(Integer.parseInt(args[0]));
		    		NavalBattle.getDebugWindow().printInfo("Game score set to " + MainMenuWindow.spg.getGame().getTurnManager().getTurn().getPlayer().getScore());
	    		} catch (Exception ex) {
	    			NavalBattle.getDebugWindow().printError("Missing or invalid arg: score");
	    		}
	    	}}
	    ));
	    
	    add(new Command("addscore", "<score>", "Add to game score", new CommandAction() { 
	    	public void onRun(Command c, String[] args) {
	    		try {
	    			MainMenuWindow.spg.getGame().getTurnManager().getTurn().getPlayer().addScore(Integer.parseInt(args[0]));
		    		NavalBattle.getDebugWindow().printInfo("Game score set to " + MainMenuWindow.spg.getGame().getTurnManager().getTurn().getPlayer().getScore());
	    		} catch (Exception ex) {
	    			NavalBattle.getDebugWindow().printError("Missing or invalid arg: score");
	    		}
	    	}}
	    ));
	    
	    add(new Command("removescore", "<score>", "Subtract from game score", new CommandAction() { 
	    	public void onRun(Command c, String[] args) {
	    		try {
	    			MainMenuWindow.spg.getGame().getTurnManager().getTurn().getPlayer().subtractscore(Integer.parseInt(args[0]));
		    		NavalBattle.getDebugWindow().printInfo("Game score set to " + MainMenuWindow.spg.getGame().getTurnManager().getTurn().getPlayer().getScore());
	    		} catch (Exception ex) {
	    			NavalBattle.getDebugWindow().printError("Missing or invalid arg: score");
	    		}
	    	}}
	    ));
	    
	    add(new Command("save", "<gamename>", "Saves the current game.", new CommandAction() {
	    	public void onRun(Command c, String[] args) {
	    		args[0] = args[0].toLowerCase();
	    		NavalBattleIO.saveGame(Game.Instance,args[0]);
	    	}
	    }));
	    
	    add(new Command("getscore", "", "Get game score", new CommandAction() { 
	    	public void onRun(Command c, String[] args) {
		    	NavalBattle.getDebugWindow().printInfo("Game score: " + MainMenuWindow.spg.getGame().getTurnManager().getTurn().getPlayer().getScore());
	    	}}
	    ));
	    
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