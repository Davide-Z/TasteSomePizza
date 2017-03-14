package obj;
import maps.Vec;
import maps.Map;
import org.newdawn.slick.state.StateBasedGame;

import java.util.LinkedList;

import javax.swing.plaf.nimbus.State;

public class Enemy extends Displayable{
	
	public Enemy(String t, double speed, int damage, int hp, LinkedList<Vec> path, int points, StateBasedGame sbg, Map map) {
		super(t, sbg);
		this.actualMap = map;
		this.posInPath = 0;
		this.pos = actualMap.spawn;
		this.speed = speed;
		this.damage = damage;
		this.hp = hp;
		this.path = path;
		this.points = points;
		enemiesAlive.add(this);
	}

	//Attributs;
	private double speed;
	private int damage;
	private int hp;
	private LinkedList<Vec> path;
	private int posInPath;
	private int points;
	
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
		this.pos=this.path.get(this.posInPath);

	}
	
	@Override
	void appear(){
		//TODO
	}
	
	@Override
	void disappear(){
		this.hp=0;
		super.disappear();
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

}
