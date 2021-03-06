/*
 * Copyright (C) 2012 Oliver Aurelius Ellison
 */

package com.aurelius.navalgame1.game;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import com.aurelius.navalgame1.game.gui.HUD;
import com.aurelius.navalgame1.game.gui.PauseWindow;
import com.aurelius.navalgame1.game.gui.StatusBar;
import com.aurelius.navalgame1.game.turn.TurnManager;
import com.aurelius.navalgame1.pavo.*;
import com.aurelius.navalgame1.pavo.gui.OmniMap;

/**
 * The game file.
 */

public class NavalGame extends Game{
	private static final long serialVersionUID = 1L;
	NavalManager nm;
	TurnManager tm;
	HUD hud;
	StatusBar sb;
	OmniMap omnimap;
	PauseWindow pw;
	int lastMinute = 0;
	float airStrike = -1;
	int hw = 0, hh = 0;
	
	public NavalGame(WorldSize ws,TurnManager tm){
		super(ws);
		init(tm);
	}
	
	public NavalGame(WorldSize ws,TurnManager tm,PavoOpenState pos, String flags) {
		super(ws,pos,flags);
		init(tm);
	}
	
	private void init(TurnManager tm){
		pw = new PauseWindow(getWindows());
		nm = new NavalManager(getWorld());
		this.tm = tm;
		hud = new HUD(getWindows(),getTurnManager(),0,Settings.currentHeight-150,Settings.currentWidth, 150);
		sb = new StatusBar(getWindows(),this);
		getWorld().setEntityManager(nm);
		omnimap = new OmniMap(getWorld());
		getWindows().add(hud);
		getWindows().add(sb);
	}
	
	/**
	 * Mulithreaded updater.
	 */
	public void update() {
		if (getNumUpdates() % 750 != 0) {
			return;
		}
		if (omnimap == null)
			omnimap = new OmniMap(getWorld());
		omnimap.render();
		
		if (hw != Game.Settings.currentWidth || hh != Game.Settings.currentHeight) {
			hw = Game.Settings.currentWidth;
			hh = Game.Settings.currentHeight;
			hud.setLocY(hh-150);
			hud.repaint();
		}
	}
	/**
	 * Called right when sunset starts.
	 */
	public void becomingSunset() {
		
	}
	
	public void doSync() {
		
	}
	
	/**
	 * Called right when sunrise starts.
	 */
	public void becomingSunrise() {
		
	}
	
	/**
	 * Called right when nighttime starts.
	 */
	public void becomingNight() {
		
	}
	
	/**
	 * Called right when daytime starts.
	 */
	public void becomingDay() {
		
	}
	int pppx = 0;
	int pppy = 0;
	public void mouseDragged(MouseEvent me) {
		super.mouseDragged(me);
		
		if (guiUsedMouseDrag)
			return;
		if (omnimap.mouseDragged(me) && startInDown)
			return;
		int mx = me.getX();
		int my = me.getY();
		int mzx = 0;
		int mzy = 0;
		int ww = (Game.Settings.currentWidth/2);
		int wh = (Game.Settings.currentHeight/2);
		int ad = 24;
		if (mx < ww) {
			mzx = (ww - mx)/ad;
		}
		else
			mzx = -((mx-ww))/ad;
		if (my < wh) {
			mzy = (wh - my)/ad;
		}
		else
			mzy = -((my-wh))/ad;
		int fgax = getWorld().getScreenX()+mzx;
		int fgaz = getWorld().getScreenY()+mzy;
		if (fgax > 200)
			fgax = 200;
		if (fgaz > 200)
			fgaz = 200;
		if (fgax < -((PavoHelper.getGameWidth(getWorld().getWorldSize()) * 100)-100))
			fgax = -((PavoHelper.getGameWidth(getWorld().getWorldSize()) * 100)-100);
		if (fgaz < -((PavoHelper.getGameHeight(getWorld().getWorldSize()) * 100)-100))
			fgaz = -((PavoHelper.getGameHeight(getWorld().getWorldSize()) * 100)-100);
		if ((getWorld().getScreenX() != fgax || getWorld().getScreenY() != fgaz) && !isAClient()) {
			getSelfServer().send("bounds:"+fgax+","+fgaz);
		}
		if ((getWorld().getScreenX() != fgax || getWorld().getScreenY() != fgaz) && isAClient()) {
			getSelfClient().send("bounds:"+fgax+","+fgaz);
		}
		getWorld().setLoc(fgax, fgaz);
		//omnimap.writeBuffer();
		//omnimap.render();
		forceUpdate();
		getWorld().forceRender();
	}
	public void mouseWheelChange(MouseWheelEvent mwe) {
		super.mouseWheelChange(mwe);
		
		int fgax = getWorld().getScreenX();
		int fgaz = getWorld().getScreenY()+(-mwe.getWheelRotation() * 50);
		if (fgax > 200)
			fgax = 200;
		if (fgaz > 200)
			fgaz = 200;
		if (fgax < -((PavoHelper.getGameWidth(getWorld().getWorldSize()) * 100)-100))
			fgax = -((PavoHelper.getGameWidth(getWorld().getWorldSize()) * 100)-100);
		if (fgaz < -((PavoHelper.getGameHeight(getWorld().getWorldSize()) * 100)-100))
			fgaz = -((PavoHelper.getGameHeight(getWorld().getWorldSize()) * 100)-100);
		getWorld().setLoc(fgax,fgaz);
		int chx = (-getWorld().getScreenX()) + mwe.getX();
		int chy = (-getWorld().getScreenY()) + mwe.getY(); 
		chx /= 50;
		chy /= 50;
		sb.setMouseTileLocation(chy, chx);
	}
	public OmniMap getMap() {
		return omnimap;
	}
	/**
	 * Button1 == Left
	 * Button2 == Middle?
	 * Button3 == Right?
	 */
	public void mouseDown(MouseEvent me) {
		super.mouseDown(me);
		
		if (guiUsedMouseDown)
			return;
		
		int chx = (-getWorld().getScreenX()) + me.getX();
		int chy = (-getWorld().getScreenY()) + me.getY(); 
		chx /= 50;
		chy /= 50;
		
		startInDown = (omnimap.mouseDown(me));
		if (startInDown) {
			return;
		}
		
		
		
		else if(hud.hudClick(chx,chy,me.getButton()==MouseEvent.BUTTON1)){ }		
		
		else if(!isAClient()){
			getHud().setEntity(nm.findEntity(chy, chx));
		}
	}
	boolean startInDown = false;
	public void mouseUp(MouseEvent me) {
		super.mouseUp(me);
		
		if (guiUsedMouseUp)
			return;
	}
	public void mouseMove(MouseEvent me) {
		super.mouseMove(me);
		omnimap.mouseMoved(me);
		int chx = (-getWorld().getScreenX()) + me.getX();
		int chy = (-getWorld().getScreenY()) + me.getY(); 
		chx /= 50;
		chy /= 50;
		sb.setMouseTileLocation(chy, chx);
	}
	BoxBlurFilter bbf = new BoxBlurFilter();
	public void render() {
		if (lastMinute != getWorld().getTimeManager().getCurrentMinutes()) {
			lastMinute = getWorld().getTimeManager().getCurrentMinutes();
			sb.repaint();
		}
		super.render();
		if (airStrike >= 0 && airStrike < 40) {
			airStrike += 1.4f;
			int f = (int)airStrike;
			if  (f > 12)
				f = 12;
			bbf.setHRadius(f);
			bbf.setIterations(1);
			bbf.filter(getBuffer(), getBuffer());
			if (airStrike >= 40)
				airStrike = -1;
		}
		Graphics2D g = PavoHelper.createGraphics(getBuffer());
		g.drawImage(omnimap.getBuffer(), Game.Settings.currentWidth-158, 40, null);
		g.dispose();
	}
	
	public HUD getHud(){
		return hud;
	}
	
	public NavalManager getManager(){
		return nm;
	}
	
	
	public TurnManager getTurnManager(){
		return tm;
	}
}
