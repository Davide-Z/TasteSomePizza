package obj;

import maps.Map;
import maps.Vec;
import obj.Turret;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import java.util.LinkedList;
import java.util.List;

public class Wave {
	public LinkedList<Enemy> unspawnedEnemies;
	public LinkedList<Integer> delays;
	public LinkedList<Enemy> aliveEnemies;
	long lastSpawn;
	Map actualMap;

	public LinkedList<Projectile> aliveProjectiles=new LinkedList<Projectile>();

	public Wave(LinkedList<Enemy> unspawnedEnemies, LinkedList<Integer> delays, Map actualMap, StateBasedGame sbg, GameContainer gc) { //generate a wave with the list of enemies and their delays
		this.unspawnedEnemies = unspawnedEnemies;
		this.delays = delays;
		this.aliveEnemies = new LinkedList<Enemy>();
		this.lastSpawn = System.currentTimeMillis();
		this.actualMap = actualMap;
	}
	
	public void spawn(){ //to spawn the next enemy respecting the delay
		if (delays.size() != 0) {
			if( System.currentTimeMillis() - lastSpawn > delays.getFirst().longValue()){ //check delay
				aliveEnemies.add(unspawnedEnemies.removeFirst()); //transfer the next unspawned enemy to those alive/displayable
				delays.removeFirst(); //remove the delay associated to the enemy
				lastSpawn = System.currentTimeMillis();
			}
		}
	}
	
	public void aliveEnemiesUpdate(int i){ //remove, move, attack
		if (aliveEnemies.size() != 0) {
			Object aliveEnemiesCopie = aliveEnemies.clone(); //Solution to concurrency problem
			for (Enemy e : (LinkedList<Enemy>) aliveEnemiesCopie) {
				if (! e.isAlive()){ //remove dead enemies
					e.disappear();
					//aliveEnemies.remove(e);
				}
				else {
					e.move(i); //move alive enemies
					e.attack(); //attack if possible
				}
			}
		}
	}
	
	public Wave(int n, Map currentMap, StateBasedGame sbg, GameContainer gc) throws SlickException { //automacally creating wave of n enemies
		LinkedList<Enemy> enemies = new LinkedList<>();
        LinkedList<Integer> d = new LinkedList<>();
        LinkedList<Vec> currentPath = currentMap.computePath();
		this.aliveEnemies = new LinkedList<Enemy>();
		
		// updates the actualWave for each Turret
        if(Turret.aliveTurrets.isEmpty()==false){
        	for(Turret t : Turret.aliveTurrets){
            	t.setActualWave(this);
            }
        }
        for (int i=0; i<n; i++) {
        	enemies.add(new Enemy(1, currentPath, sbg, currentMap, this));
        	d.add(500);
        }
		this.unspawnedEnemies = enemies;
		this.delays = d;
		this.lastSpawn = System.currentTimeMillis();
		this.actualMap = currentMap;		
	}
	
	
	public LinkedList<Enemy> getAliveEnemies() {
		return aliveEnemies;
	}
	public LinkedList<Integer> getDelays() {
		return delays;
	}
	public LinkedList<Projectile> getAliveProjectiles() {
		return aliveProjectiles;
	}
	public void setAliveProjectiles(LinkedList<Projectile> aliveProjectiles) {
		this.aliveProjectiles = aliveProjectiles;
	}
	
}
