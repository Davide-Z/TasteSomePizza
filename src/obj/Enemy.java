package obj;

import gui.FileLoader;
import maps.Map;
import maps.Vec;
import obj.enums.Direction;
import obj.enums.EnemyType;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import java.util.LinkedList;

public class Enemy extends Displayable {
	private int damage;
	private Direction dir;
	private int hp;
	private Vec lastPos;
	private LinkedList<Vec> path;
	private int points;
	private double posInPath;
	private int reward; // received for killing the enemy
	// Attributs;
	private double speed;
	private Image sprite;
	private String spriteName;

	public Enemy(Enemy enemy, Vec pos) throws SlickException {
		super(enemy.sbg);
		super.typeId = enemy.typeId;
		super.pos = pos;
		super.sprite = FileLoader.getSpriteImage("client");
		super.name = "Enemy";
	}

	public Enemy(EnemyType t, int level, LinkedList<Vec> path, StateBasedGame sbg, Wave wave) throws SlickException {
		super(t.typeId, null, sbg, wave);
		this.actualMap = config.getMap();
		this.posInPath = 0;
		this.path = path;
		this.assignValues(t, level);
	}

	public Enemy(EnemyType t, LinkedList<Vec> path, StateBasedGame sbg, Map map, Wave wave) throws SlickException {
		super(t.typeId, map.spawn, sbg, wave);
		this.actualMap = map;
		this.posInPath = 0;
		this.path = path;
		this.assignValues(t);
	}

	public Enemy(EnemyType t, Vec pos, StateBasedGame sbg) throws SlickException {
		super(sbg);
		this.pos = pos;
		this.sprite = FileLoader.getSpriteImage("client");
		this.assignValues(t);
	}

	public void assignValues(EnemyType t) throws SlickException {
		// assigns the values for the type t
		this.typeId = t.typeId;
		this.speed = t.speed;
		this.damage = t.damage;
		this.hp = t.health;
		super.sprite = t.sprite();
		super.name = t.type;
		this.reward = t.reward;
	}

	public void assignValues(EnemyType t, int level) throws SlickException {
		// assigns the values for the type t
		this.typeId = t.typeId;
		this.speed = t.speed;
		this.damage = t.damage + t.levelBonus * level;
		this.hp = t.health;
		super.sprite = t.sprite();
		super.name = t.type;
		this.reward = t.reward;
		this.sprite = t.sprite();
	}

	public void attack() {
		if (pos.equals(actualMap.posBase)) {
			if (actualMap.baseHP - damage > 0) {
				actualMap.baseHP -= damage; // damage
			} else {
				actualMap.baseHP = 0; // killed
			}
			this.disappear();
		}
	}

	@Override
	public void disappear() {
		this.hp = 0;
		super.disappear();
	}

	public int getDamage() {
		return damage;
	}

	// GETTERS AND SETTERS
	public int getHp() {
		return hp;
	}

	public int getPoints() {
		return points;
	}

	public int getReward() {
		return reward;
	}

	public boolean isAlive() {
		return (this.hp > 0);
	}

	public void move(int i) {
		lastPos = this.pos;
		if (this.posInPath + this.speed * i < this.path.size()) { // la position
			// ne
			// depasse
			// pas la
			// taille de
			// la liste
			// des
			// positions
			this.posInPath += this.speed * i;
		} else { // si on arrive a la fin
			this.posInPath = this.path.size() - 1;
		}
		this.pos = this.path.get((int) this.posInPath);

		// Orientation:
		if (this.pos.getX() - this.lastPos.getX() > 0) {
			System.out.println("going right");
			this.dir = Direction.RIGHT;
		}
		if (this.pos.getX() - this.lastPos.getX() < 0) {
			System.out.println("going left");

			this.dir = Direction.LEFT;
		}
		if (this.pos.getY() - this.lastPos.getY() > 0) {
			System.out.println("going down");

			this.dir = Direction.DOWN;
		}
		if (this.pos.getY() - this.lastPos.getY() < 0) {
			System.out.println("going up");

			this.dir = Direction.UP;
		}
		super.sprite.setRotation((float) dir.angle);
		System.out.println(this.dir.angle);
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public void setReward(int reward) {
		this.reward = reward;
	}

}
