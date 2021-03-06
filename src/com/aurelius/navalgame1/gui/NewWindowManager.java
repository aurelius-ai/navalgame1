package com.aurelius.navalgame1.pavo.gui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import com.aurelius.navalgame1.pavo.Game;
import com.aurelius.navalgame1.pavo.PavoHelper;
import com.aurelius.navalgame1.pavo.Renderable;
import com.aurelius.navalgame1.pavo.gui.controls.Control;
import com.aurelius.navalgame1.pavo.gui.controls.PToolTip;
import com.aurelius.navalgame1.pavo.gui.controls.PWindow;
import com.aurelius.navalgame1.pavo.io.PavoImage;

public class NewWindowManager extends Renderable {
	private static final long serialVersionUID = 1L;
	ArrayList<PWindow> wins;
	MessageBox context = null;
	Game g;
	PavoImage grided = null;
	int lwsw = 0,lwsh = 0;
	Control tooltipschedule = null;
	public static NewWindowManager Inst;
	java.util.Timer tooltipticker;
	boolean showtip = false;
	PToolTip tip;
	int lmx = 0, lmy = 0;
	/**
	 * 
	 */
	public NewWindowManager(Game g) {
		super();
		this.g = g;
		tooltipticker = new Timer();
		tooltipticker.schedule(new CANDYLANDMOUNTAIN(this),0,250);
		tip = PToolTip.NOTOUCHING_GeneratorRex("Unknown perk.", 2000);
		wins = new ArrayList<PWindow>();
		Inst = this;
	}
	class CANDYLANDMOUNTAIN extends TimerTask {
		NewWindowManager nwm;
		long startTicks = 0;
		Control tipsch = null;
		public CANDYLANDMOUNTAIN(NewWindowManager nwm) {
			this.nwm = nwm;
		}
		public void run() {
			if (tipsch != nwm.tooltipschedule) {
				startTicks = System.currentTimeMillis();
				System.out.println("Tool tip scheduled.");
				tipsch = nwm.tooltipschedule;
			}
			
			if (System.currentTimeMillis() - startTicks >= 2000 && tipsch != null && !nwm.showtip) {
				nwm.showtip = true;
				tip.setToolTip(tipsch.getToolTip());
			}
		}
	}
	public Game getGame() {
		return g;
	}
	public void add(PWindow wnd) {
		wins.add(wnd);
	}
	public void remove(PWindow wnd) {
		wins.remove(wnd);
	}
	public PWindow get(int index) {
		return wins.get(index);
	}
	public int size() {
		return wins.size();
	}
	public boolean mouseMove(MouseEvent me) {
		int mx = me.getX();
		int my = me.getY();
		lmx = me.getX();
		lmy = me.getY();
		for (int c = 0; c < wins.size(); c++) {
			PWindow gw = wins.get(c);
			if (gw != null && gw.isVisible()) {
				if (mx >= gw.getLocX() && mx <= gw.getLocX() + gw.getWidth()
						&& my >= gw.getLocY() && my <= gw.getLocY() + gw.getHeight()) {
					gw.onMouseHover(mx-gw.getLocX(), my-gw.getLocY());
					if (gw.mouseMovedUpon() != tooltipschedule) {
						//System.out.println("tip cah");
						if (gw.mouseMovedUpon() != null && gw.mouseMovedUpon().getToolTip() != null
								&& gw.mouseMovedUpon().getToolTip() != "") {
							tooltipschedule = gw.mouseMovedUpon();
							render();
						}
						if (tooltipschedule != null && gw.mouseMovedUpon() == null) {
							tooltipschedule = null;
							render();
						}
					}
					return true;
				}
			}
		}
		return false;
	}
	public void ianOwjej10nJAnin345soaKOEe9201LIQUICK(MessageBox Ijsn9j20OKan01nJFNAnia) {
		context = Ijsn9j20OKan01nJFNAnia;
	}
	/**
	 * Y'all should translate some of these methods and see what u get ;)
	 */
	public void sa_ki_mal_dam_fin_vye_granmoun_kwit_soup_ansanm() {
		for (int c = 0; c < wins.size(); c++) {
			PWindow gw = wins.get(c);
			if (gw!=null) {
				gw.onMasterWindowResize();
			}
		}
	}
	int startx = 0,starty =0;
	PWindow dragWnd;
	public boolean mouseDown(MouseEvent me) {
		int mx = me.getX();
		int my = me.getY();
		boolean flag = false;
		startx = mx;
		starty = my;
		dragWnd = null;
		PWindow gw = context;
		if (gw!=null && gw.isVisible() && !gw.isDisposed()) {
			if (mx >= gw.getLocX() && mx <= gw.getLocX() + gw.getWidth()
					&& my >= gw.getLocY() && my <= gw.getLocY() + gw.getHeight()) {
				try {
					gw.onMouseDown(mx-gw.getLocX(), my-gw.getLocY(), me.getButton());
				}
				catch (Throwable t) {
					
				}
				if (gw.isDisposed()) {
					context = null;
					render();
					return true;
				}
				dragWnd = gw;
				return true;
			}
		}
		for (int c = 0; c < wins.size(); c++) {
			gw = wins.get(c);
			if (gw!=null && gw.isVisible() && !gw.isDisposed()) {
				if (mx >= gw.getLocX() && mx <= gw.getLocX() + gw.getWidth()
						&& my >= gw.getLocY() && my <= gw.getLocY() + gw.getHeight()) {
					gw.onMouseDown(mx-gw.getLocX(), my-gw.getLocY(), me.getButton());
					if (gw.isDisposed()) {
						render();
					}
					dragWnd = gw;
					return true;
				}
			}
		}
		return flag;
	}
	public boolean mouseDragged(MouseEvent me) {
		int mx = me.getX();
		int my = me.getY();
		boolean flag = false;
		
		if (dragWnd != null && dragWnd.isVisible() && !dragWnd.isDisposed()) {
			dragWnd.onMouseDrag(mx,my);
			flag = true;
		}
		return flag;
	}
	
	public boolean mouseUp(MouseEvent me) {
		int mx = me.getX();
		int my = me.getY();
		boolean flag = false;
		startx = mx;
		starty = my;
		dragWnd = null;
		PWindow gw = context;
		if (gw!=null && gw.isVisible() && !gw.isDisposed()) {
			if (mx >= gw.getLocX() && mx <= gw.getLocX() + gw.getWidth()
					&& my >= gw.getLocY() && my <= gw.getLocY() + gw.getHeight()) {
				gw.onMouseUp(mx-gw.getLocX(), my-gw.getLocY(), me.getButton());
				return true;
			}
		}
		boolean rerenderflag = false;
		for (int c = 0; c < wins.size(); c++) {
			gw = wins.get(c);
			if (gw!=null && gw.isVisible() && !gw.isDisposed()) {
				if (mx >= gw.getLocX() && mx <= gw.getLocX() + gw.getWidth()
						&& my >= gw.getLocY() && my <= gw.getLocY() + gw.getHeight()) {
					gw.onMouseUp(mx-gw.getLocX(), my-gw.getLocY(), me.getButton());
					if (gw.isDisposed()) {
						render();
						rerenderflag= true;
					}
					dragWnd = gw;
					return true;
				}
			}
		}
		if (rerenderflag) {
			this.render();
		}
		return flag;
	}
	
	public void $akafre() {
		for (int c = 0; c < wins.size(); c++) {
			PWindow gw = wins.get(c);
			if (gw!=null && !gw.isDisposed()) {
				gw.onMasterWindowResize();
			}
		}
	}

	public void render() {
		buffer = new PavoImage(Game.Settings.currentWidth,Game.Settings.currentHeight,BufferedImage.TYPE_INT_ARGB);
		if (grided == null || lwsw != Game.Settings.currentWidth || lwsh != Game.Settings.currentHeight) {
			grided = new PavoImage(Game.Settings.currentWidth,Game.Settings.currentHeight,BufferedImage.TYPE_INT_ARGB);
			Graphics2D g4 = PavoHelper.createGraphics(grided);
			g4.setColor(new Color(200,200,180,90));
			g4.fillRect(0,0,Game.Settings.currentWidth,Game.Settings.currentHeight);
			g4.setColor(new Color(20,20,45));
			for (int x = 0; x < Game.Settings.currentWidth*2; x += 8) {
				g4.drawLine(x,0,0,x);
			}
			lwsw = Game.Settings.currentWidth;
			lwsh = Game.Settings.currentHeight;
			g4.dispose();
		}
		Graphics2D g2 = PavoHelper.createGraphics(getBuffer());
		
		for (int c = 0; c < wins.size(); c++) {
			PWindow gw = wins.get(c);
			if (gw!=null && gw.isForcingIndividualChanges()) {
				if (gw.isVisible() && !gw.isDisposed()) {
					int gwx = gw.getLocX();
					int gwy = gw.getLocY();
					BufferedImage gwb = gw.getBuffer();
					g2.drawImage(gwb, gwx,gwy, null);
				}
			}
		}
		
		if (context != null) {
			g2.drawImage(grided, 0, 0, null);
			PWindow gw = context;
			int gwx = gw.getLocX();
			int gwy = gw.getLocY();
			BufferedImage gwb = gw.getBuffer();
			g2.drawImage(gwb, gwx,gwy, null);
		}
		
		if (tip != null && showtip) {
			g2.drawImage(tip.getBuffer(), lmx,lmy,null);
		}
		g2.dispose();
	}
}
