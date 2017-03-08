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
	abstract void disappear();
	Vec pos;
	int id; 	//TODO est ce qu'on a besoin d'un id si on a déj la position?
	String type;
	int lastId=0; // sert pour creer les identifiants uniques
	Map actualMap;
	List<Turret> turretsAlive=new LinkedList<Turret>();
	List<Enemy> enemiesAlive=new LinkedList<Enemy>();
	List<Projectile> projectilesAlive=new LinkedList<Projectile>();
	
	Displayable(){
		id=createNewId();
	}
	
	Displayable(String t, Vec p, StateBasedGame sbg){
		id=createNewId();
		type=t;
		pos=p;
		this.sbg=sbg;
		this.gc=sbg.getContainer();
		this.g=gc.getGraphics();
	}
	
	int createNewId(){
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
}
