package obj;

import maps.Map;
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

	List<Turret> turretsAlive=new LinkedList<Turret>(); //Dav, je pense que ces listes ne devraient pas etre dans cette interface parce que on est entrain d'encapsuler les classes
	List<Enemy> enemiesAlive=new LinkedList<Enemy>(); //Dav, genre à la fin chaque ennemi aura comme attribut la liste d'ennemis
	List<Projectile> projectilesAlive=new LinkedList<Projectile>();

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
					aliveEnemies.remove(e);
				}
				else {
					e.move(i); //move alive enemies
					e.attack(); //attack if possible
				}
			}
		}
	}
	
	public Wave(int n, Map actualMap, StateBasedGame sbg, GameContainer gc) throws SlickException { //automacally creating wave of n enemies
		LinkedList<Enemy> enemies = new LinkedList<>();
        LinkedList<Integer> d = new LinkedList<>();
        for (int i=0; i<n; i++) {
        	enemies.add(new Enemy(0, 0.8, 5, 10, actualMap.computePath(), 1, sbg, actualMap, this));
        	d.add(300);
        }
		this.unspawnedEnemies = enemies;
		this.delays = d;
		this.aliveEnemies = new LinkedList<>();
		this.lastSpawn = System.currentTimeMillis();
		this.actualMap = actualMap;
        
	}
	
	
	public LinkedList<Enemy> getAliveEnemies() {
		return aliveEnemies;
	}
	public LinkedList<Integer> getDelays() {
		return delays;
	}
	public List<Turret> getTurretsAlive() {
		return turretsAlive;
	}
	public void setTurretsAlive(List<Turret> turretsAlive) {
		this.turretsAlive = turretsAlive;
	}
	public List<Enemy> getEnemiesAlive() {
		return enemiesAlive;
	}
	public void setEnemiesAlive(List<Enemy> enemiesAlive) {
		this.enemiesAlive = enemiesAlive;
	}
	public List<Projectile> getProjectilesAlive() {
		return projectilesAlive;
	}
	public void setProjectilesAlive(List<Projectile> projectilesAlive) {
		this.projectilesAlive = projectilesAlive;
	}
	
}
