package obj;

import maps.Vec;
import obj.enums.EnemyType;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import states.GameConfig;
import java.util.LinkedList;

public class Wave {
	public LinkedList<Enemy> aliveEnemies;
	public LinkedList<Projectile> aliveProjectiles = new LinkedList<Projectile>();
	private LinkedList<Integer> delays;
	public LinkedList<Enemy> unspawnedEnemies;
	private GameConfig config;
	private long lastSpawn;
	private double vit=1f;

	public Wave(EnemyType type, int level, StateBasedGame sbg) throws SlickException {
		this.config = GameConfig.getInstance(sbg);
		LinkedList<Enemy> enemies = new LinkedList<>();
		LinkedList<Integer> d = new LinkedList<>();
		//LinkedList<Vec> currentPath = this.config.getMap().computePath();
		this.aliveEnemies = new LinkedList<Enemy>();
		this.unspawnedEnemies = enemies;
		this.delays = d;
		this.lastSpawn = System.currentTimeMillis();

		// updates the actualWave for each Turret
		if (!config.aliveTurrets.isEmpty()) {
			for (Turret t : config.aliveTurrets) {
				t.setActualWave(this);
			}
		}

		if (type == EnemyType.FAST) {
			for (int i = 0; i < 2*level; i++) {
				LinkedList<Vec> currentPath = this.config.getMap().computePath();
				enemies.add(new Enemy(type, level, currentPath, sbg, this));
				d.add((int) (500 * vit));
			}
		}

		else if (type == EnemyType.SLOW){
			for (int i = 0; i < 3+level; i++) {
				LinkedList<Vec> currentPath = this.config.getMap().computePath();
				enemies.add(new Enemy(type, level, currentPath, sbg, this));
				d.add((int) (1000 * vit));
			}
		}
		else {
			for (int i = 0; i < 5+2*level; i++) {
				LinkedList<Vec> currentPath = this.config.getMap().computePath();
				enemies.add(new Enemy(type, level, currentPath, sbg, this));
				d.add((int) (200 * vit));
			}
		}

	}

	public void aliveEnemiesUpdate(int i) { // remove, move, attack
		if (aliveEnemies.size() != 0) {
			Object aliveEnemiesCopie = aliveEnemies.clone(); // Solution to
																// concurrency
																// problem
			for (Enemy e : (LinkedList<Enemy>) aliveEnemiesCopie) {
				if (!e.isAlive()) { // remove dead enemies
					e.disappear();
					// aliveEnemies.remove(e);
				} else {
					e.move(i); // move alive enemies
					e.attack(); // attack if possible
				}
			}
		}
	}

	public LinkedList<Enemy> getAliveEnemies() {
		return aliveEnemies;
	}

	public void spawn() { // to spawn the next enemy respecting the delay
		if (delays.size() != 0) {
			if (System.currentTimeMillis() - lastSpawn > delays.getFirst().longValue()) { // check
																							// delay
				aliveEnemies.add(unspawnedEnemies.removeFirst()); // transfer
																	// the next
																	// unspawned
																	// enemy to
																	// those
																	// alive/displayable
				delays.removeFirst(); // remove the delay associated to the
										// enemy
				lastSpawn = System.currentTimeMillis();
			}
		}
	}

	public double getVit() {
		return vit;
	}

	public void setVit(double newVit) {
		// need to update the spawns delays
		double coef=newVit / this.vit; // coefficient between the new and the old vit
		LinkedList<Integer> newDelays=new LinkedList<Integer>();
		for(int del : this.delays){
			newDelays.add((int) (del*coef));
		}
		delays=newDelays;
		this.vit = newVit;
	}

}