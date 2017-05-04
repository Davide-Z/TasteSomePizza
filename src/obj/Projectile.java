package obj;
import gui.FileLoader;
import maps.Vec;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import java.util.LinkedList;
import java.math.*;

public class Projectile extends Displayable{
	double speed;
	int damage;
	Turret motherTurret;
	Enemy target;
	
	public Projectile(Enemy e, Turret mt, StateBasedGame sbg,  Wave w) throws SlickException{
		super(mt.projectileType, mt.getPos(), mt.sbg, w);
		//System.out.println("projectile créé par id="+mt.id+" mt.pos:"+mt.getPos().toString());
		super.sprite=FileLoader.getSpriteImage("pizza.png");
		this.target=e;
		this.typeId=mt.projectileType;
		this.motherTurret=mt;
		this.pos=mt.getPos().copy();
		this.actualWave.aliveProjectiles.add(this);
		assignType(typeId);
	}

	public void assignType(int t){
		//TODO
		this.speed=0.8;
		this.damage=10000000; // it's a test
		this.setTypeId(1);
	}	
	public boolean move(Vec p){
		// return true if projectile has arrived at the position pos
		
		// The projectile will move forward of a distance "speed"
		// The trajectory will be a line between target position pos
		// and projectile current position
		double distance=this.pos.distanceDouble(p);
		
		//If the target is closer than speed, then projectile is immediately 
		// put on the location of the target and return true
		if(distance<speed){
			this.pos=new Vec(p.getX(), p.getY());
			return true;
		}
		else{
			int x=this.pos.getX(); // initial position of the projectile
			int y=this.pos.getY();

			int moveX=(int)Math.round(speed/distance*(p.getX()-this.pos.getX()));
			int moveY=(int)Math.round(speed/distance*(p.getY()-this.pos.getY()));
			
			this.pos=new Vec(x+moveX, y+moveY);
			
			return false;
			}
	}
	
	public void searchAnotherEnemy(){
		boolean foundNewEnemy=false;
		Object aliveEnemiesCopie = actualWave.aliveEnemies.clone(); //Solution to concurrency problem
		for (Enemy e : (LinkedList<Enemy>) aliveEnemiesCopie) {
			if(e.isAlive()){
				target=e;
				foundNewEnemy=true;
				System.out.println(this.toString()+" "+"found the "+e.toString()+" and it has "+e.getHp()+" hp");
				if(move(target.getPos())){	// return true if the projectile hits the enemy
					hit(target);
				}
				break;
			}
		}
		//debug
		if(foundNewEnemy==false){
			System.out.println("projectile "+this.id+" \t\tDIDNT FIND ANY ENEMY_____________");
			this.disappear();
		}
	}
	
	public void update(){
		if(target==null || target.isAlive()==false){
			searchAnotherEnemy();	// will search for an other enemy alive, and if there isn't any
									// it calls disappear(), move() and hit(newTarget)
		}
		else{
			if(move(target.getPos())){	// return true if the projectile hits the enemy
				hit(target);
			}
		}
	}
	
	public void hit(Enemy tgt){
		tgt.setHp(tgt.getHp()-damage); // damage are made, if the enemy isn't alive after, it doesn't matter
		this.disappear(); // true because hit the enemy
	}
	
	// GETTERS AND SETTERS
	public int getTypeId() {	return typeId;	}
	public void setTypeId(int typeId) {	this.typeId = typeId;	}
	public double getSpeed() {	return speed;	}
	public void setSpeed(double speed) {	this.speed = speed;	}
	public int getDamage() {	return damage;	}
	public void setDamage(int damage) {	this.damage = damage;	}
	public Turret getMotherTurret() {	return motherTurret;	}
	public void setMotherTurret(Turret motherTurret) {	this.motherTurret = motherTurret;	}
	public Enemy getTarget() {	return target;	}
	public void setCible(Enemy tgt) {	this.target = tgt;	}
}
