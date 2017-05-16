package obj;

import maps.Map;
import maps.Vec;
import obj.Turret;

import obj.enums.EnemyType;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import states.GameConfig;

import java.util.LinkedList;
import java.util.List;

public class Wave {
	public LinkedList<Enemy> aliveEnemies;
	public LinkedList<Projectile> aliveProjectiles = new LinkedList<Projectile>();
	public LinkedList<Integer> delays;
	public LinkedList<Enemy> unspawnedEnemies;
	private GameConfig config;
	private Map actualMap;
	long lastSpawn;

	public Wave(EnemyType type, int level, StateBasedGame sbg) throws SlickException {
		this.config = GameConfig.getInstance(sbg);
		LinkedList<Enemy> enemies = new LinkedList<>();
		LinkedList<Integer> d = new LinkedList<>();
		// LinkedList<Vec> currentPath = this.config.getMap().computePath();
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
			for (int i = 0; i < 2 * level; i++) {
				LinkedList<Vec> currentPath = this.config.getMap().computePath();
				enemies.add(new Enemy(type, level, currentPath, sbg, this));
				d.add(500);
			}
		} else if (type == EnemyType.SLOW) {
			for (int i = 0; i < 3 + level; i++) {
				LinkedList<Vec> currentPath = this.config.getMap().computePath();
				enemies.add(new Enemy(type, level, currentPath, sbg, this));
				d.add(1000);
			}
		} else {
			for (int i = 0; i < 5 + 2 * level; i++) {
				LinkedList<Vec> currentPath = this.config.getMap().computePath();
				enemies.add(new Enemy(type, level, currentPath, sbg, this));
				d.add(200);
			}
		}

	}

	public Wave(int n, Map currentMap, StateBasedGame sbg) throws SlickException {
		this.config = GameConfig.getInstance(sbg);
		LinkedList<Enemy> enemies = new LinkedList<>();
		LinkedList<Integer> d = new LinkedList<>();
		LinkedList<Vec> currentPath = currentMap.computePath();
		this.aliveEnemies = new LinkedList<Enemy>();

		// updates the actualWave for each Turret
		if (!config.aliveTurrets.isEmpty()) {
			for (Turret t : config.aliveTurrets) {
				t.setActualWave(this);
			}
		}
		for (int i = 0; i < n; i++) {
			enemies.add(new Enemy(EnemyType.FAST, currentPath, sbg, currentMap, this));
			d.add(500);
		}
		this.unspawnedEnemies = enemies;
		this.delays = d;
		this.lastSpawn = System.currentTimeMillis();
		this.actualMap = currentMap;
	}

	public Wave(LinkedList<Enemy> unspawnedEnemies, LinkedList<Integer> delays, Map actualMap, StateBasedGame sbg)
			throws SlickException { // generate a wave with the list of enemies
		// and their delays
		this.config = GameConfig.getInstance(sbg);
		this.unspawnedEnemies = unspawnedEnemies;
		this.delays = delays;
		this.aliveEnemies = new LinkedList<Enemy>();
		this.lastSpawn = System.currentTimeMillis();
		this.actualMap = actualMap;
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

	public LinkedList<Projectile> getAliveProjectiles() {
		return aliveProjectiles;
	}

	public LinkedList<Integer> getDelays() {
		return delays;
	}

	public void setAliveProjectiles(LinkedList<Projectile> aliveProjectiles) {
		this.aliveProjectiles = aliveProjectiles;
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

}
