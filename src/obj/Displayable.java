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


public abstract class Displayable {
	protected StateBasedGame sbg;
	protected GameContainer gc;
	protected Graphics g;
	protected GameConfig config;
	protected Vec pos;
	protected int typeId;
	protected String name;
	// private int lastId=0; //  A remettre si finalement on se sert de id. Sert pour creer les identifiants uniques
	protected Map actualMap;
	protected float aimedDirection;
	protected Wave actualWave;

	protected Image sprite;

	public Displayable(int t, StateBasedGame sbg, Wave w) throws SlickException {
		this.typeId=t;
		this.sbg=sbg;
		this.gc=sbg.getContainer();
		this.g=gc.getGraphics();
		this.actualWave=w;
		this.config=GameConfig.getInstance(sbg);
	}
	
	public Displayable(int t, Vec p, StateBasedGame sbg, Wave w){
		this.typeId=t;
		this.pos=p;
		this.sbg=sbg;
		this.gc=sbg.getContainer();
		this.g=gc.getGraphics();
		this.actualWave=w;
	}

	/**
	 * initialise un Displayable avec juste l'essentiel
	 * @param sbg
	 */
	public Displayable(StateBasedGame sbg){
		this.sbg=sbg;
		this.gc=sbg.getContainer();
		this.g=gc.getGraphics();
	}
	
	// A remettre si finalement on se sert de id
	/*public int createNewId(){
		return ++lastId;
	}*/
	
	
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
	
	public float aimingAt(Vec pos) { //direction of the turret 
		return (float) (Math.PI/2 - pos.getAngle() - this.pos.getAngle());
	}
	
	 // Getters and Setters __________________________________________________
	public Vec getPos() {
		return pos;
	}
	public void setPos(Vec pos) {
		this.pos = pos;
	}
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	public float getAimedDirection() {
		return aimedDirection;
	}
	public void setAimedDirection(float aimedDirection) {
		this.aimedDirection = aimedDirection;
	}
	public Wave getActualWave() {
		return actualWave;
	}
	public void setActualWave(Wave actualWave) {
		this.actualWave = actualWave;
	}
	public void render(){
		this.sprite.draw(pos.getX(), pos.getY());
	}
	public abstract void appear();
	public String getName(){return this.name;}
	public void setName(String name){this.name=name;}

	@Override
	public String toString(){
		return this.name+"-"+this.typeId;
	}
}
