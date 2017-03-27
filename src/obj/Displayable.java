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
	protected String type;
	private int typeId;
	// private int lastId=0; //  A remettre si finalement on se sert de id. Sert pour creer les identifiants uniques
	protected Map actualMap;
	protected float aimedDirection;
	protected Wave actualWave;

	protected Image sprite;

	public Displayable(String t, StateBasedGame sbg, Wave w) throws SlickException {
		type=t;
		this.sbg=sbg;
		this.gc=sbg.getContainer();
		this.g=gc.getGraphics();
		this.actualWave=w;
		this.config=GameConfig.getInstance(sbg);
	}
	
	public Displayable(String t, Vec p, StateBasedGame sbg, Wave w){
		type=t;
		pos=p;
		this.sbg=sbg;
		this.gc=sbg.getContainer();
		this.g=gc.getGraphics();
		this.actualWave=w;
	}

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
	public Wave getActualWave() {
		return actualWave;
	}
	public void setActualWave(Wave actualWave) {
		this.actualWave = actualWave;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	public void render(int x, int y){
		this.sprite.draw(x, y);
	}
	public abstract void appear();
}
