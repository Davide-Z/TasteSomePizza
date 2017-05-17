package obj;

import maps.Vec;
import obj.enums.Direction;
import obj.enums.EnemyType;
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
	private int reward; // received for killing the enemy
	private Vec lastPos;
	private Direction dir;

	public Enemy(EnemyType t, int level, LinkedList<Vec> path, StateBasedGame sbg, Wave wave) throws SlickException{
		super(t.typeId, null, sbg, wave);
		this.actualMap = config.getMap();
		this.posInPath = 0;
		this.path = path;
		this.assignValues(t, level);
	}
	
	private void assignValues(EnemyType t, int level) throws SlickException{
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
	
	void attack(){
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
	
	boolean isAlive(){
		return (this.hp>0);
	}

	void move(int i){
		lastPos=this.pos;
		if (this.posInPath+this.speed*i/actualWave.vit<this.path.size()){	//la position ne depasse pas la taille de la liste des positions
			this.posInPath+=this.speed*i/actualWave.vit;
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
	int getHp() {	return hp;	}
	void setHp(int hp) {	this.hp = hp;	}
	int getReward() {	return reward;	}
}