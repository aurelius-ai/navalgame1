package com.aurelius.navalgame1.game.turn;

import java.awt.Font;

import com.aurelius.navalgame1.NavalBattle;
import com.aurelius.navalgame1.data.NavalGameData;
import com.aurelius.navalgame1.game.NavalManager;
import com.aurelius.navalgame1.game.entity.PortEntity;
import com.aurelius.navalgame1.pavo.gui.NewWindowManager;
import com.aurelius.navalgame1.pavo.gui.controls.PButton;
import com.aurelius.navalgame1.pavo.gui.controls.PText;
import com.aurelius.navalgame1.pavo.gui.controls.PWindow;
import com.aurelius.navalgame1.pavo.gui.events.PMouseEvent;
import com.aurelius.navalgame1.util.NavalUtils;

	public class PortShop extends PWindow {
		
		PortEntity port;
		Player current;
		
		PText score;
		
		public PortShop(NewWindowManager parent,PortEntity pe) {
			super(parent);
			parent.add(this);
			setSize(500, 250);
			setLoc(390, 275);
			setText("Port Shop");
			port = pe;
			initItems();
			
			current = null;
			if(port == null){
				super.setVisible(false);
			}
			else{
				super.setVisible(true);
				current = ((NavalManager)port.getManager()).getGame().getTurnManager().findPlayer(pe);
			}
		}
		
		private void initItems(){
			PText shop = new PText(this);
			score = new PText(this);
			PButton bship = new PButton(this);
			PButton sub = new PButton(this);
			PButton ac = new PButton(this);
			PButton repair = new PButton(this);
			
			PText stock = new PText(this);
			PText price = new PText(this);
			PText bprice = new PText(this);
			PText sprice = new PText(this);
			PText acprice = new PText(this);
			PText rprice = new PText(this);
			
			shop.setFont(new Font("Verdana Bold", 1, 18));
			score.setFont(new Font("Verdana Bold", 1, 12));
			stock.setFont(new Font("Verdana Bold", 1, 12));
			price.setFont(new Font("Verdana Bold", 1, 12));
			
			shop.setText("Port Shop");
			score.setText("Your Current Score is "+((NavalManager)port.getManager()).getGame().getTurnManager().getTurn().getPlayer().getScore());
			bship.setText("Purchase Battleship");
			sub.setText("Purchase Boat");
			ac.setText("Purchase Aircraft Carrier");
			repair.setText("Repair Port");
			
			stock.setText("Stock");
			price.setText("Price");
			bprice.setText("1000");
			sprice.setText("1250");
			acprice.setText("1250");
			rprice.setText("500");
			
			shop.setLoc(200,35);
			score.setLoc(5, 230);
			bship.setLoc(20, 90);
			sub.setLoc(20, 120);
			ac.setLoc(20, 150);
			repair.setLoc(20, 180);
			
			stock.setLoc(60, 60);
			price.setLoc(390, 60);
			bprice.setLoc(390, 90);
			sprice.setLoc(390, 120);
			acprice.setLoc(390, 150);
			rprice.setLoc(390, 180);
			
			addControl(shop);
			addControl(score);		
			addControl(bship);
			addControl(sub);
			addControl(ac);
			addControl(stock);
			addControl(price);
			addControl(bprice);
			addControl(sprice);
			addControl(acprice);
			addControl(repair);
			addControl(rprice);
			
			bship.addMouseListener(new PMouseEvent(){
				public void mouseDown(int x, int y, int buttonid) {
					buyBattleShip();
					NavalUtils.submitAchievement(NavalGameData.ACHIEVEMENT_THRIFT_SHOP);
				}
			});
			
			sub.addMouseListener(new PMouseEvent(){
				public void mouseDown(int x, int y, int buttonid) {
					buySubmarine();
					NavalUtils.submitAchievement(NavalGameData.ACHIEVEMENT_THRIFT_SHOP);
				}
			});
			
			ac.addMouseListener(new PMouseEvent(){
				public void mouseDown(int x, int y, int buttonid) {
					buyCarrier();
					NavalUtils.submitAchievement(NavalGameData.ACHIEVEMENT_THRIFT_SHOP);
				}
			});
			
			repair.addMouseListener(new PMouseEvent(){
				public void mouseDown(int x, int y, int buttonid) {
					repairAction();
					NavalUtils.submitAchievement(NavalGameData.ACHIEVEMENT_THRIFT_SHOP);
				}
			});
		}
		
		public void update(){
			score.setText("Your Current Score is "+((NavalManager)port.getManager()).getGame().getTurnManager().getTurn().getPlayer().getScore());
		}
		
		private void buyBattleShip(){
			if(current!=null && port!=null){
				if(current.getScore()>=1000){
					current.subtractscore(1000);
					NavalBattle.getGameState().addPointsSpent(1000);
					port.spawnBattleship();
				}
				update();
			}
		}
		
		private void buySubmarine(){
			if(current!=null && port!=null){
				if(current.getScore()>=1250){
					current.subtractscore(1250);
					NavalBattle.getGameState().addPointsSpent(1250);
					port.spawnSubmarine();
				}
				update();
			}
		}
		
		private void buyCarrier(){
			if(current!=null && port!=null){
				if(current.getScore()>=1250){
					current.subtractscore(1250);
					NavalBattle.getGameState().addPointsSpent(1250);
					port.spawnAC();
				}
				update();
			}
		}
		
		private void repairAction(){
			if(current!=null && port!=null){
				if(current.getScore()>=500){
					current.subtractscore(500);
					NavalBattle.getGameState().addPointsSpent(500);
					port.repair();
				}
				update();
			}
		}
	}