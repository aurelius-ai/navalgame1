package com.aurelius.navalgame1.game.turn;

import java.util.ArrayList;

import com.aurelius.navalgame1.game.NavalGame;
import com.aurelius.navalgame1.game.NavalManager;
import com.aurelius.navalgame1.game.entity.MoveableEntity;
import com.aurelius.navalgame1.pavo.grid.Asset;

public class Player {
	
	ArrayList<Asset> assets;
	public String name;
	protected boolean turnOver;
	int score;
	byte color;
	int playernumber;
	int diplomacyCounter = 0;
	boolean diplomacy = false;
	
	public Player(String name){
		assets = new ArrayList<Asset>();
		this.name = name;
		turnOver = false;
		score = 0;
	}
	
	public void setPlayerNumber(int pnum){
		playernumber = pnum;
	}
	
	public void setTeamColor(byte b){
		color = b;
	}
	
	public void startTurn(){
		
	}
	
	public void takeTurn(){
		resetTurn();
	}
	
	public void endTurn(){
		
	}
	
	public void resetTurn(){
		resetMovement();
		resetAttack();
		if(assets.size()>0&&assets.get(0)!=null)
		((NavalManager)assets.get(0).getManager()).getGame().getHud().update();
	}
	
	public void resetMovement(){
		for (int index =0; index<assets.size(); index++){
			Asset e1 = assets.get(index);
			if(e1.getHandle()%10 == 1){
				MoveableEntity e = (MoveableEntity) e1;
				e.resetMovement();
			}
		}
	}
	
	public void resetAttack(){
		for (int index =0; index<assets.size(); index++){
			Asset e1 = assets.get(index);
			if(e1.getHandle()%10 == 1){
				MoveableEntity e = (MoveableEntity) e1;
				e.resetAttack();
			}
		}
	}
	
	public boolean myEntity(Asset e){
		return assets.contains(e);
	}
	
	public void addEntity(Asset e){
		assets.add(e);
		e.setTeamColor(color);
	}
	
	public Asset getEntity(int index) {
		return assets.get(index);
	}
	
	public boolean isTurnOver(){
		return turnOver;
	}
	
	public int getTotalEntities() {
		return assets.size();
	}
	
	public void addScore(int add){
		score +=add;
	}
	
	public void setscore(int set){
		score = set;
	}
	
	public void subtractscore(int sub){
		score -=sub;
	}
	
	public int getScore(){
		return score;
	}
	
	public void removeEntity(Asset e){
		while(assets.remove(e))
			;
	}
	
	public void nextEntity(Asset e){
		Asset temp = null;
		if(e==null){
			temp = assets.get(0);
		}
		else{
			if(assets.contains(e)){
				int index = assets.indexOf(e)+1;
				if(index>=assets.size())
					index -= assets.size();
				temp = assets.get(index);
			}
			else{
				temp = assets.get(0);
			}
		}
		if(temp == null)
			return;
		temp.getManager().getWorld().animatedSetLoc(temp.getLocation(),0.054392019f);
		((NavalGame)temp.getManager().getWorld().getGame()).getHud().setEntity(temp);
	}

	public boolean ownsEntity() {
		for(int index = 0; index<assets.size();index++){
			if(assets.get(index).getHandle()%10 == 1)
				return true;
		}
		return false;
	}

	public boolean ownsPort() {		
		for(int index = 0; index<assets.size();index++){
			if(assets.get(index).getHandle() == 2)
				return true;
		}		
		return false;
	}
	
	
}
