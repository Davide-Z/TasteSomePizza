package obj;
import gui.FileLoader;
import maps.Map;
import maps.Vec;
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
	
	public Enemy(int t, LinkedList<Vec> path, StateBasedGame sbg, Map map, Wave wave) throws SlickException{
		super(t, map.spawn, sbg, wave);
		this.actualMap = map;
		this.posInPath = 0;
		this.path = path;
		this.assignValues(t);
	}

	public Enemy(int t,Vec pos, StateBasedGame sbg) throws SlickException {
		super(sbg);
		super.gc=sbg.getContainer();
		super.g=gc.getGraphics();
		this.pos=pos;
		super.sprite= FileLoader.getSpriteImage("client.png");
		this.sprite=super.sprite;
		this.assignValues(t);
	}

	public Enemy(Enemy enemy, Vec pos) throws SlickException {
		super(enemy.sbg);
		super.typeId=enemy.typeId;
		super.pos=pos;
		super.sprite=FileLoader.getSpriteImage("client.png");
		super.name="Enemy";
	}

	public void assignValues(int t) throws SlickException{
		// assigns the values for the type t
		this.typeId=t;
		if(t==1){
			this.speed=0.6;
			this.damage=2;
			this.hp=40;
			super.sprite= FileLoader.getSpriteImage("client.png");
			this.sprite=super.sprite;
			super.name="Enemy 1";
			this.reward=2;
		}
		if(t==2){
			this.speed=0.2;
			this.damage=10;
			this.hp=250;
			super.sprite= FileLoader.getSpriteImage("client.png");
			this.sprite=super.sprite;
			super.name="Enemy 2";
			this.reward=22;
		}
		else{	// default
			this.speed=0.3;
			this.damage=5;
			this.hp=100;
			super.sprite= FileLoader.getSpriteImage("client.png");
			this.sprite=super.sprite;
			super.name="Enemy 0";
			this.reward=10;
		}
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
		if (this.posInPath+this.speed*i<this.path.size()){	//la position ne depasse pas la taille de la liste des positions
			this.posInPath+=this.speed*i;
		}
		else {	//si on arrive a la fin
			this.posInPath=this.path.size()-1;
		}
		this.pos=this.path.get((int)this.posInPath);
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
