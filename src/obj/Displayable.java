package obj;
//Author : Flo

import maps.*;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;
import obj.Wave;
import java.util.List;
import java.util.LinkedList;


public abstract class Displayable {
	protected StateBasedGame sbg;
	protected GameContainer gc;
	protected Graphics g;
	public abstract void appear();
	protected Vec pos;
	protected int id; 	//TODO est ce qu'on a besoin d'un id si on a déj la position?
	protected String type;
	private int typeId;
	private int lastId=0; // sert pour creer les identifiants uniques
	protected Map actualMap;
	protected float aimedDirection;
	protected Wave actualWave;

	
	public Displayable(){
		id=createNewId();
	}
	

	public Displayable(String t, StateBasedGame sbg, Wave w){
		id=createNewId();
		type=t;
		this.sbg=sbg;
		this.gc=sbg.getContainer();
		this.g=gc.getGraphics();
		this.actualWave=w;
	}
	
	public Displayable(String t, Vec p, StateBasedGame sbg, Wave w){
		id=createNewId();
		type=t;
		pos=p;
		this.sbg=sbg;
		this.gc=sbg.getContainer();
		this.g=gc.getGraphics();
		this.actualWave=w;
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
			actualWave.getTurretsAlive().remove(this);
		}
		else if(this instanceof Enemy){
			actualWave.getEnemiesAlive().remove(this);
		}
		else if(this instanceof Projectile){
			actualWave.getProjectilesAlive().remove(this);
		}
	}
	public void render(){}

	public void setId(int id) {
		this.id = id;
	}
}
