package states;

import java.util.LinkedList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import maps.Map;
import maps.Vec;
import obj.Enemy;

public class Wave {
	
	StateBasedGame sbg;
	GameContainer gc;
	Graphics g;

	LinkedList<Enemy> unspawnedEnemies;
	LinkedList<Integer> delays;
	LinkedList<Enemy> aliveEnemies;
	long lastSpawn;
	Map actualMap;

	public Wave(LinkedList<Enemy> unspawnedEnemies, LinkedList<Integer> delays, Map actualMap, StateBasedGame sbg, GameContainer gc) { //generate a wave with the list of enemies and their delays
		super();
        this.gc=gc;
        this.sbg=sbg;
        this.g=sbg.getContainer().getGraphics();
		this.unspawnedEnemies = unspawnedEnemies;
		this.delays = delays;
		this.aliveEnemies = new LinkedList<Enemy>();
		this.lastSpawn = System.currentTimeMillis();
		this.actualMap = actualMap;
	}
	
	void spawn(){ //to spawn the next enemy respecting the delay
		if (delays.size() != 0) {
			if( System.currentTimeMillis() - lastSpawn > delays.getFirst().longValue()){ //check delay
				aliveEnemies.add(unspawnedEnemies.removeFirst()); //transfer the next unspawned enemy to those alive/displayable
				delays.removeFirst(); //remove the delay associated to the enemy
				lastSpawn = System.currentTimeMillis();
			}
		}
	}
	
	void aliveEnemiesUpdate(int i){ //remove, move, attack
		if (aliveEnemies.size() != 0) {
			Object aliveEnemiesCopie = aliveEnemies.clone(); //Solution to concurrency problem
			for (Enemy e : (LinkedList<Enemy>) aliveEnemiesCopie) {
				if (! e.isAlive()){ //remove dead enemies
					aliveEnemies.remove(e);
				}
				else {
					e.move(i); //move alive enemies
					e.attack(); //attack if possible
				}
			}
		}
	}
	
	Wave(int n, Map actualMap, StateBasedGame sbg, GameContainer gc) { //automacally creating wave of n enemies
        super();
		LinkedList<Enemy> enemies = new LinkedList<Enemy>();
        LinkedList<Integer> d = new LinkedList<Integer>();
        for (int i=0; i<n; i++) {
        	enemies.add(new Enemy("a", 1, 5, 10, actualMap.computePath(), 1, sbg, actualMap));
        	d.add(300);
        }
        this.gc=gc;
        this.sbg=sbg;
        this.g=sbg.getContainer().getGraphics();
		this.unspawnedEnemies = enemies;
		this.delays = d;
		this.aliveEnemies = new LinkedList<Enemy>();
		this.lastSpawn = System.currentTimeMillis();
		this.actualMap = actualMap;
        
	}
	
	
	public LinkedList<Enemy> getAliveEnemies() {
		return aliveEnemies;
	}
	public LinkedList<Integer> getDelays() {
		return delays;
	}
	
	
}
