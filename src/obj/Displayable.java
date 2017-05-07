package obj;
//Author : Flo

import maps.Map;
import maps.Vec;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import states.GameConfig;
import gui.FileLoader;


public abstract class Displayable {
	protected StateBasedGame sbg;
	protected GameContainer gc;
	protected Graphics g;
	protected GameConfig config;
	protected Vec pos;
	protected int typeId;
	protected String name;
	protected Map actualMap;
	protected float aimedDirection;
	protected Wave actualWave;
	protected static int lastId=0;
	protected int id;	// Usefull for tests
	protected Image sprite;

	public Displayable(int t, Vec p, StateBasedGame sbg, Wave w) throws SlickException {
		this.typeId=t;
		if(p!=null){
			this.pos=p.copy();
		}
		else{
			this.pos=new Vec(0,0);
		}
		this.sbg=sbg;
		this.gc=sbg.getContainer();
		this.g=gc.getGraphics();
		this.actualWave=w;
		this.id=lastId;
		lastId++;
		this.config=GameConfig.getInstance(sbg);
	}

	/**
	 * initialise un Displayable avec juste l'essentiel
	 * @param sbg
	 */
	public Displayable(StateBasedGame sbg){
		this.sbg=sbg;
		this.gc=sbg.getContainer();
		this.g=gc.getGraphics();
		this.pos=new Vec(0,0);
		this.id=lastId;
		lastId++;
	}
	
	public void disappear(){
		// remove the object of the corresponding linkedList
		if(this instanceof Turret){
			Turret.aliveTurrets.remove(this);
		}
		else if(this instanceof Enemy){
			actualWave.aliveEnemies.remove(this);
		}
		else if(this instanceof Projectile){
			actualWave.aliveProjectiles.remove(this);
		}
	}
	
	public float aimingAt(Vec pos) { //direction of the turret 
		return (float) (Math.PI/2 - pos.getAngle() - this.pos.getAngle());
	}
	

	public void render(){
		this.sprite.draw(pos.getX(), pos.getY());
	}
	
	
	@Override
	public String toString(){
		if(this instanceof Turret){
			return "Turret "+id+" "+this.pos.toString()+" ";
		}
		else if(this instanceof Enemy){
			return "Enemy "+id+" "+this.pos.toString()+" ";
		}
		else if(this instanceof Projectile){
			return "Projectile "+id+" "+this.pos.toString()+" ";
		}
		else{
			return "Displayable"+id;
		}
	}
	
	 // Getters and Setters __________________________________________________
	public Vec getPos() {	return pos;	}
	public void setPos(Vec pos) {	this.pos = pos;	}
	public int getTypeId() {	return typeId;	}
	public void setTypeId(int typeId) {	this.typeId = typeId;	}
	public float getAimedDirection() {	return aimedDirection;	}
	public void setAimedDirection(float aimedDirection) {	this.aimedDirection = aimedDirection;	}
	public Wave getActualWave() {	return actualWave;	}
	public void setActualWave(Wave actualWave) {	this.actualWave = actualWave;	}
	public String getName(){return this.name;}
	public void setName(String name){this.name=name;}
	public StateBasedGame getSbg() {	return sbg;	}
	public void setSbg(StateBasedGame sbg) {	this.sbg = sbg;	}
}
