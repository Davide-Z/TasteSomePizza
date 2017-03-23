package obj;
//Author : Flo

import maps.Vec;
import maps.Map;
import maps.Case;
import maps.Mat;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import java.util.List;
import java.util.LinkedList;


public abstract class Displayable {
	StateBasedGame sbg;
	GameContainer gc;
	Graphics g;
	abstract void appear();
	Vec pos;
	int id; 	//TODO est ce qu'on a besoin d'un id si on a déj la position?
	String type;
	int typeId;
	int lastId=0; // sert pour creer les identifiants uniques
	Map actualMap;
	float aimedDirection;
	List<Turret> turretsAlive=new LinkedList<Turret>(); //Dav, je pense que ces listes ne devraient pas etre dans cette interface parce que on est entrain d'encapsuler les classes
	List<Enemy> enemiesAlive=new LinkedList<Enemy>(); //Dav, genre à la fin chaque ennemi aura comme attribut la liste d'ennemis
	List<Projectile> projectilesAlive=new LinkedList<Projectile>();
	
	public Displayable(){
		id=createNewId();
	}
	

	public Displayable(String t, StateBasedGame sbg){
		id=createNewId();
		type=t;
		this.sbg=sbg;
		this.gc=sbg.getContainer();
		this.g=gc.getGraphics();
	}
	
	public Displayable(String t, Vec p, StateBasedGame sbg){
		id=createNewId();
		type=t;
		pos=p;
		this.sbg=sbg;
		this.gc=sbg.getContainer();
		this.g=gc.getGraphics();
	}
	
	
	
	public int createNewId(){
		return ++lastId;
	}
	 
	public Vec getPos() {
		return pos;
	}
	public void setPos(Vec pos) {
		this.pos = pos;
	}
	public int getId() {
		return id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public float getAimedDirection() {
		return aimedDirection;
	}
	public void setAimedDirection(float aimedDirection) {
		this.aimedDirection = aimedDirection;
	}
	public int getTypeId() {
		return typeId;
	}
	
	public void disappear(){
		// remove the object of the corresponding linkedList
		if(this instanceof Turret){
			turretsAlive.remove(this);
		}
		else if(this instanceof Enemy){
			enemiesAlive.remove(this);
		}
		else if(this instanceof Projectile){
			projectilesAlive.remove(this);
		}
	}
}
