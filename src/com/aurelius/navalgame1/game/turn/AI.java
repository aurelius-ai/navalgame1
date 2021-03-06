package com.aurelius.navalgame1.game.turn;

import java.util.ArrayList;

import com.aurelius.navalgame1.NavalBattle;
import com.aurelius.navalgame1.game.NavalGame;
import com.aurelius.navalgame1.game.NavalManager;
import com.aurelius.navalgame1.game.entity.MoveableEntity;
import com.aurelius.navalgame1.game.entity.PortEntity;
import com.aurelius.navalgame1.game.entity.Boat;
import com.aurelius.navalgame1.game.turn.DamageCalculator;
import com.aurelius.navalgame1.pavo.Game;
import com.aurelius.navalgame1.pavo.grid.Asset;
import com.aurelius.navalgame1.pavo.grid.GridHelper;
import com.aurelius.navalgame1.pavo.grid.Location;
import com.aurelius.navalgame1.util.GrammarManager;

public class AI extends Player{
	
	ArrayList<Asset> primaryEnemies;
	ArrayList<Asset> secondaryEnemies;
	int numBS, numPS, numAC, numSM;
	
	
	public AI() {
		super(getNewName());
		primaryEnemies = new ArrayList<Asset>();
		secondaryEnemies = new ArrayList<Asset>();
	}
	
	private static String getNewName() {
		return GrammarManager.generateFullName(Game.Settings.rand.nextInt());
	}
	
	public void addEnemyEntityP(Asset e){
		primaryEnemies.add(e);
	}

	public void addEnemyEntityS(Asset e){
		secondaryEnemies.add(e);
	}
	
	public static void delay(int n)
	{
		long startDelay = System.currentTimeMillis();
		long endDelay = 0;
		while (endDelay - startDelay < n)
			endDelay = System.currentTimeMillis();
	}
	
	public void takeTurn(){
		for(int k = 0; k < getTotalEntities(); k++)
		{
			Asset ent = getEntity(k);
			
		//	this.nextEntity(ent);
			//delay(10000);
			/*while (ent.getManager().getWorld().isBeingAnimated()) {
				try {
					Thread.sleep(0);
				}
				catch (Throwable t) {
					
				}
			}*/
			
			if(ent.getHandle()%10 == 1){
				MoveableEntity currentEntity;
				currentEntity = (MoveableEntity)ent;
				if(currentEntity.getHandle()==11){
					//Sub
					moveAIShip(currentEntity);
					if(!diplomacy){
					determineCurrentEnemiesP(currentEntity);
					if(currentEntity.getMissileCount()>0)
					determineCurrentEnemiesS(currentEntity);
					organizeMoveableEnemiesHP(primaryEnemies);
					organizeMoveableEnemiesHP(secondaryEnemies);
					shipShopping(currentEntity);
					attackEnemies(1, currentEntity);
					}
			
				}
				if(currentEntity.getHandle()==21){
					//AC
				moveAIShip(currentEntity);
				if(!diplomacy){
				determineCurrentEnemiesP(currentEntity);
				determineCurrentEnemiesS(currentEntity);
				organizeMoveableEnemiesHP(primaryEnemies);
				organizeMoveableEnemiesHP(secondaryEnemies);
				shipShopping(currentEntity);
				attackEnemies(2, currentEntity);
				}
				}
				if(currentEntity.getHandle()==31){
					//BS
					moveAIShip(currentEntity);
					if(!diplomacy){
					determineCurrentEnemiesP(currentEntity);
					if(currentEntity.getMissileCount()>0)
					determineCurrentEnemiesS(currentEntity);
					organizeMoveableEnemiesHP(primaryEnemies);
					organizeMoveableEnemiesHP(secondaryEnemies);
					shipShopping(currentEntity);
					attackEnemies(3, currentEntity);
					}
				}
			}
			else{
				PortEntity currentEntityP;
				currentEntityP = (PortEntity)ent;
				portShopping(currentEntityP);				
			}
		}
		diplomacyCounter--;
		if(diplomacyCounter == 0)
			diplomacy = false;
		turnOver=true;

	}
	private void shipShopping(MoveableEntity e){
		//hull upgrade: 200
		if(this.getScore()>250 && e.getPercentHealth()<50){
		this.subtractscore(200);
		e.hardenHull();
		}
		
		//Missile X5 : 250
		if(this.getScore()>300 && primaryEnemies.size()>3){
		this.subtractscore(250);
		e.increaseMissile();
		}
		
		//increase range 700
		if(this.getScore()>750 && e.getPercentHealth()>50 && primaryEnemies.size()<1){
		this.subtractscore(700);
		e.increaseRange();
		}
		
		//anti missile 350
		if(this.getScore()>400 && primaryEnemies.size()>4){
		this.subtractscore(350);
		e.deflectMissile();
		}
		
		//repair ship 300		
		if(this.getScore()> 350 && e.getPercentHealth()<25){
			this.subtractscore(300);
			e.repair();
		}
	}
	
	private void getShipNumbers(){
	numPS = numBS = numSM = numAC = 0;
		for(int k = 0; k < getTotalEntities(); k++){
			Asset ent = getEntity(k);
			if(ent.getHandle()%10 == 1){
				MoveableEntity currentEntity;
				currentEntity = (MoveableEntity)ent;
				if(currentEntity.getHandle()==11){
				numSM++;
				}
				if(currentEntity.getHandle()==21){
				numAC++;
				}
				if(currentEntity.getHandle()==11){
				numBS++;
				}
			}
	
		else{
			numPS++;
			}
		}
	}
	
	
	private void portShopping(PortEntity p){
		getShipNumbers();
		//repair port 500
		if(this.getScore()>550 && p.getPercentHealth()<25){
		this.subtractscore(500);
		p.repair();
		}
		
		//purchase battleship 1000
		if(this.getScore()>1050 && numBS<2){
		this.subtractscore(1000);
		p.spawnBattleship();
		}
		
		//purchase sub 1250
		if(this.getScore()>1300 && numSM<2){
		this.subtractscore(1250);
		p.spawnSubmarine();
		}
		
		//purchase ac 1250
		if(this.getScore()>1300 ){
		this.subtractscore(1250);
		p.spawnAC();
		}

		
	}

	public void attackEnemies(int n, MoveableEntity currentEntity)
	{
		
		if(pickEnemyP(n)!=-1)
		primaryAttack(n, currentEntity);
		if(pickEnemyS(n)!=-1)
		secondaryAttack(n, currentEntity);
		
		secondaryEnemies.clear();
		primaryEnemies.clear();
	}
	
	public void primaryAttack(int n, MoveableEntity currentEntity )
	{
		
		Asset ene = primaryEnemies.get(pickEnemyP(n));
		if(ene.getHandle()==2){
			PortEntity enemyEntity;
			enemyEntity = (PortEntity)ene;
			DamageCalculator.doPrimaryDamage(currentEntity, enemyEntity);
		}
		else{
		MoveableEntity enemyEntity;
		enemyEntity = (MoveableEntity)ene;
		DamageCalculator.doPrimaryDamage(currentEntity, enemyEntity);
		}
	}
	
	public void secondaryAttack(int n, MoveableEntity currentEntity)
	{
		Asset ene = secondaryEnemies.get(pickEnemyS(n));
		if(ene.getHandle()==2){
			PortEntity enemyEntity;
			enemyEntity = (PortEntity)ene;
			DamageCalculator.doSecondaryDamage(currentEntity, enemyEntity);
		}
		else{
		MoveableEntity enemyEntity;
		enemyEntity = (MoveableEntity)ene;
		DamageCalculator.doSecondaryDamage(currentEntity, enemyEntity);
		}
	}
	
	private void organizeMoveableEnemiesHP(ArrayList<Asset> Enemy){		
		Asset temp;
		for (int p = 1; p < Enemy.size(); p++){			
			for (int q = 0; q < Enemy.size()-1; q++){
				if(getHealth(Enemy.get(q))>getHealth(Enemy.get(q+1))){
					temp = Enemy.get(q);
					Enemy.set(q,Enemy.get(q+1));
					Enemy.set((q+1), temp);
				}
			}
		}
	}
	
	private int getHealth(Asset e){
		if(e.getHandle()==2)
			return ((PortEntity)e).getPercentHealth();
		if(e.getHandle()%10==1)
			return ((MoveableEntity)e).getPercentHealth();
		else{
			return -1;
		}
	}
	
	public int pickEnemyS(int currentShip)
	{

		if(!secondaryEnemies.isEmpty()){
			
			for(int k = 0; k < secondaryEnemies.size(); k++){
				if(secondaryEnemies.get(k).getHandle()==2)
					return k;
			}
		switch (currentShip) {
	      case 1:	for(int k = 0; k < secondaryEnemies.size(); k++){
						if(secondaryEnemies.get(k).getHandle()==21)
							return k;
	      				}
	      			for(int k = 0; k < secondaryEnemies.size(); k++){
	      				if(secondaryEnemies.get(k).getHandle()==31)
	      					return k;
	      				}
	      			for(int k = 0; k < secondaryEnemies.size(); k++){
	      				if(secondaryEnemies.get(k).getHandle()==11)
	      					return k;
	      				}
	      			
	      case 2:	for(int k = 0; k < secondaryEnemies.size(); k++){
						if(secondaryEnemies.get(k).getHandle()==31)
							return k;
						}
					for(int k = 0; k < secondaryEnemies.size(); k++){
						if(secondaryEnemies.get(k).getHandle()==21)
							return k;
						}
					for(int k = 0; k < secondaryEnemies.size(); k++){
						if(secondaryEnemies.get(k).getHandle()==11)
							return k;
						}
						
	      case 3:	for(int k = 0; k < secondaryEnemies.size(); k++){
						if(secondaryEnemies.get(k).getHandle()==11)
							return k;
						}
					for(int k = 0; k < secondaryEnemies.size(); k++){
						if(secondaryEnemies.get(k).getHandle()==31)
							return k;
						}
					for(int k = 0; k < secondaryEnemies.size(); k++){
						if(secondaryEnemies.get(k).getHandle()==21)
							return k;
						}
     			
		}
	}
		return -1;
	}
	
	public int pickEnemyP(int currentShip)
	{

		if(!primaryEnemies.isEmpty()){
			for(int k = 0; k < primaryEnemies.size(); k++){
				if(primaryEnemies.get(k).getHandle()==2)
					return k;
			}
		switch (currentShip) {
	      case 1:	for(int k = 0; k < primaryEnemies.size(); k++){
						if(primaryEnemies.get(k).getHandle()==21)
							return k;
	      				}
	      			for(int k = 0; k < primaryEnemies.size(); k++){
	      				if(primaryEnemies.get(k).getHandle()==31)
	      					return k;
	      				}
	      			for(int k = 0; k < primaryEnemies.size(); k++){
	      				if(primaryEnemies.get(k).getHandle()==11)
	      					return k;
	      				}
	      			
	      case 2:	for(int k = 0; k < primaryEnemies.size(); k++){
						if(primaryEnemies.get(k).getHandle()==31)
							return k;
						}
					for(int k = 0; k < primaryEnemies.size(); k++){
						if(primaryEnemies.get(k).getHandle()==21)
							return k;
						}
					for(int k = 0; k < primaryEnemies.size(); k++){
						if(primaryEnemies.get(k).getHandle()==11)
							return k;
						}
						
	      case 3:	for(int k = 0; k < primaryEnemies.size(); k++){
						if(primaryEnemies.get(k).getHandle()==11)
							return k;
						}
					for(int k = 0; k < primaryEnemies.size(); k++){
						if(primaryEnemies.get(k).getHandle()==31)
							return k;
						}
					for(int k = 0; k < primaryEnemies.size(); k++){
						if(primaryEnemies.get(k).getHandle()==21)
							return k;
						}
     			
		}
	}
		return -1;
	}
	
	public void moveAIShip(MoveableEntity e){
		int topX = e.getLocation().getCol()-e.getMovementLeft()+1;	   
		int topY = e.getLocation().getRow()-e.getMovementLeft()+1;
		int currentX=topX;
		int currentY=topY;
		e.toggleMoveable();
		//delay!
	do
		{	
			currentX=topX;
			currentY=topY;
			currentX += (int) (Math.random()*((e.getMovementLeft() * 2) + 1));
			currentY += (int) (Math.random()*((e.getMovementLeft() * 2) + 1));
		}
		while(!GridHelper.canMoveTo(e.getManager(), e, e.getCurrentOrientation(), currentY, currentX,e.getWidth()));
		e.toggleMoveable();
	//	if (!e.getManager().isEntityAnimated())
		//	e.animatedMoveTo(new Location(currentY,currentX), 30);
		//else
			e.moveTo(new Location(currentY,currentX));
		//delay
	}
	public void determineCurrentEnemiesS(MoveableEntity e){
		int topX = (e.getLocation().getCol()-e.getSecondaryRange())+1;	   
		int topY = (e.getLocation().getRow()-e.getSecondaryRange())+1;	 		
		for (int x = topX; x < (e.getLocation().getCol()+e.getSecondaryRange())+1; x++) {
			for (int y = topY; y < (e.getLocation().getRow()+e.getSecondaryRange())+1; y++) {
				Asset location = e.getManager().findEntity(y,x);
				if(location!=null){
					Player temp = ((NavalManager)location.getManager()).getGame().getTurnManager().findPlayer(location); 
					if (temp!=null){
					if(!(temp.equals(this))&&!secondaryEnemies.contains(location)){
						//entity at spot is not owned by this AI
						if(location.getHandle()==11) {
							if(!((Boat)location).isSumberged()){
								addEnemyEntityS(location);
							}
						}
						else {
							addEnemyEntityS(location);
						}

					}
					}
				}
			}
		}
	}
	
	public void determineCurrentEnemiesP(MoveableEntity e){
		int topX = (e.getLocation().getCol()-e.getPrimaryRange())+1;	   
		int topY = (e.getLocation().getRow()-e.getPrimaryRange())+1;	 		
		for (int x = topX; x < (e.getLocation().getCol()+e.getPrimaryRange())+1; x++) {
			for (int y = topY; y < (e.getLocation().getRow()+e.getPrimaryRange())+1; y++) {
				Asset location = e.getManager().findEntity(y,x);
				if(location!=null){
					Player temp = ((NavalManager)location.getManager()).getGame().getTurnManager().findPlayer(location); 
					if (temp!=null){
					if(!(temp.equals(this))&&!primaryEnemies.contains(location)){
						//entity at spot is not owned by this AI
						if(location.getHandle()==11) {
							if(!((Boat)location).isSumberged()){
								addEnemyEntityS(location);
							}
						}
						else{
							addEnemyEntityS(location);
						}
					}
					}
				}
			}
		}
	}
	
	public void reset(){
		if(assets.size()==0)
			return;
		NavalManager nm = (NavalManager)assets.get(0).getManager();
		
		for(int index = 0; index<primaryEnemies.size(); index+=0){
			primaryEnemies.get(index).dispose();
			nm.getGame().getTurnManager().removeEntity(primaryEnemies.remove(index));
		}
		for(int index = 0; index<secondaryEnemies.size(); index+=0){
			secondaryEnemies.get(index).dispose();
			nm.getGame().getTurnManager().removeEntity(secondaryEnemies.remove(index));
		}
		for(int index = 0; index<assets.size(); index+=0){
			assets.get(index).dispose();
			nm.getGame().getTurnManager().removeEntity(assets.remove(index));
		}
	}
	
	public void endTurn(){
		super.endTurn();
	}
}
