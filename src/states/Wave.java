package states;

import java.util.LinkedList;
import obj.Enemy;

public class Wave {
	

	LinkedList<Enemy> unspawnedEnemies;
	LinkedList<Integer> delays;
	LinkedList<Enemy> aliveEnemies;
	long lastSpawn;

	public Wave(LinkedList<Enemy> unspawnedEnemies, LinkedList<Integer> delays) { //generate a wave with the list of enemies and their delays
		super();
		this.unspawnedEnemies = unspawnedEnemies;
		this.delays = delays;
		this.aliveEnemies = new LinkedList<Enemy>();
		this.lastSpawn = System.currentTimeMillis();
	}
	
	void spawn(){ //to spawn the next enemy respecting the delay
		if(System.currentTimeMillis() - lastSpawn > delays.getFirst().longValue()){ //check delay
			aliveEnemies.add(unspawnedEnemies.removeFirst()); //transfer the next unspawned enemy to those alive/displayable
			delays.removeFirst(); //remove the delay associated to the enemy
		}
	}
	
	void aliveEnemiesUpdate(){ //remove, move, attack
		for (Enemy e : aliveEnemies) {
			if (! e.isAlive()){ //remove dead enemies
				aliveEnemies.remove(e);
			}
			else {
				e.move(); //move alive enemies
				e.attack(); //attack if possible
			}
		}
	}
	
	
	
	public LinkedList<Enemy> getAliveEnemies() {
		return aliveEnemies;
	}
	public LinkedList<Integer> getDelays() {
		return delays;
	}
	
	
}
