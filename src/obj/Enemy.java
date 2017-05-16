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

public class Enemy extends Displayable{
	//Attributs;
	private double speed;
	private int damage;
	private int hp;
	private LinkedList<Vec> path;
	private double posInPath;
	private int points;
	private String spriteName;
	private Image sprite;
	private int reward; // received for killing the enemy
	private Vec lastPos;
	private Direction dir;

	public Enemy(EnemyType t, LinkedList<Vec> path, StateBasedGame sbg, Map map, Wave wave) throws SlickException{
		super(t.typeId, map.spawn, sbg, wave);
		this.actualMap = map;
		this.posInPath = 0;
		this.path = path;
		this.assignValues(t);
	}

	public Enemy(EnemyType t,Vec pos, StateBasedGame sbg) throws SlickException {
		super(sbg);
		this.pos=pos;
		this.sprite= FileLoader.getSpriteImage("client");
		this.assignValues(t);
	}
	
	public Enemy(EnemyType t, int level, LinkedList<Vec> path, StateBasedGame sbg, Wave wave) throws SlickException{
		super(t.typeId, null, sbg, wave);
		this.actualMap = config.getMap();
		this.posInPath = 0;
		this.path = path;
		this.assignValues(t, level);
	}

	public Enemy(Enemy enemy, Vec pos) throws SlickException {
		super(enemy.sbg);
		super.typeId=enemy.typeId;
		super.pos=pos;
		super.sprite=FileLoader.getSpriteImage("client");
		super.name="Enemy";
	}

	public void assignValues(EnemyType t) throws SlickException{
		// assigns the values for the type t
		this.typeId=t.typeId;
		this.speed=t.speed;
		this.damage=t.damage;
		this.hp=t.health;
		super.sprite= t.sprite();
		super.name=t.type;
		this.reward=t.reward;
	}
	
	public void assignValues(EnemyType t, int level) throws SlickException{
		// assigns the values for the type t
		this.typeId=t.typeId;
		this.speed=t.speed;
		this.damage=t.damage+t.levelBonus*level;
		this.hp=t.health;
		super.sprite= t.sprite();
		super.name=t.type;
		this.reward=t.reward;
		this.sprite=t.sprite();
	}
	
	public void attack(){
		if (pos.equals(actualMap.posBase)){
			if (actualMap.baseHP-damage>0){
				actualMap.baseHP-=damage;	//damage
			}
			else {
				actualMap.baseHP=0;	//killed
			}
			this.disappear();
		}
	}
	
	public boolean isAlive(){
		return (this.hp>0);
	}

	public void move(int i){
		lastPos=this.pos;
		if (this.posInPath+this.speed*i<this.path.size()){	//la position ne depasse pas la taille de la liste des positions
			this.posInPath+=this.speed*i;
		}
		else {	//si on arrive a la fin
			this.posInPath=this.path.size()-1;
		}
		this.pos=this.path.get((int)this.posInPath);

		//Orientation:
		if(this.pos.getX()-this.lastPos.getX()>0){
			this.dir=Direction.RIGHT;
		}
		if(this.pos.getX()-this.lastPos.getX()<0){

			this.dir=Direction.LEFT;
		}
		if(this.pos.getY()-this.lastPos.getY()>0){

			this.dir=Direction.DOWN;
		}
		if(this.pos.getY()-this.lastPos.getY()<0){

			this.dir=Direction.UP;
		}
		super.sprite.setRotation(dir.angle);
	}
	
	@Override
	public void disappear(){
		this.hp=0;
		super.disappear();
	}

	// GETTERS AND SETTERS
	public int getHp() {	return hp;	}
	public void setHp(int hp) {	this.hp = hp;	}
	public int getPoints() {	return points;	}
	public void setPoints(int points) {	this.points = points;	}
	public int getDamage() {	return damage;	}
	public int getReward() {	return reward;	}
	public void setDamage(int damage) {	this.damage = damage;	}
	public void setReward(int reward) {	this.reward = reward;	}

}
