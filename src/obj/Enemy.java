package obj;
import gui.FileLoader;
import maps.Map;
import maps.Vec;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import java.util.LinkedList;

public class Enemy extends Displayable{
	
	public Enemy(int t, double speed, int damage, int hp, LinkedList<Vec> path, int points, StateBasedGame sbg, Map map, Wave wave) throws SlickException{
		super(t, map.spawn, sbg, wave);
		this.actualMap = map;
		this.posInPath = 0;
		this.speed = speed;
		this.damage = damage;
		this.hp = hp;
		this.path = path;
		this.points = points;
		wave.aliveEnemies.add(this);
		this.spriteName="client.png";
		super.sprite= FileLoader.getSpriteImage("client.png");
		this.sprite=super.sprite;
		super.name="Enemy";
	}

	public Enemy(int t,Vec pos, double speed, int damage, int hp, StateBasedGame sbg) throws SlickException {
		super(sbg);
		this.typeId=t;
		this.speed=speed;
		this.damage=damage;
		this.hp=hp;
		super.gc=sbg.getContainer();
		super.g=gc.getGraphics();
		this.pos=pos;
		super.sprite= FileLoader.getSpriteImage("client.png");
		this.sprite=super.sprite;
		super.name="Enemy";
	}

	public Enemy(Enemy enemy, Vec pos) throws SlickException {
		super(enemy.sbg);
		super.typeId=enemy.typeId;
		super.pos=pos;
		super.sprite=FileLoader.getSpriteImage("client.png");
		super.name="Enemy";
	}

	//Attributs;
	private double speed;
	private int damage;
	private int hp;
	private LinkedList<Vec> path;
	private double posInPath;
	private int points;
	private String spriteName;
	private Image sprite;
	
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

}
