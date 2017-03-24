package obj;
//Author : Flo
import maps.Vec;
import obj.Wave;

public class Projectile extends Displayable{
	int speed;
	int damage;
	Turret motherTurret;
	Enemy target;
	//Listes des ennemis en vie + leur nombre
	
	public Projectile(Enemy enemy, Turret mt, Wave w){
		super(mt.projectileType, mt.getPos(), mt.sbg, w);
		target=enemy;
		type=mt.projectileType;
		motherTurret=mt;
		this.pos=motherTurret.getPos();
		actualWave.getProjectilesAlive().add(this);
		appear();
	}
	
	@Override
	public void appear(){
	}
	
	public boolean move(Vec pos){
		// return true if projectile arrived at the position pos
		
		// The projectile will move forward of a distance "speed"
		// The trajectory will be a line between target position pos
		// and projectile current position
		int distance=this.getPos().distance(pos);
		
		//If the target is closer than speed, then projectile is immediately 
		// put on the location of the target and return true
		if(distance<speed){
			getPos().setX(pos.getX());
			getPos().setY(pos.getY());
			return true;
		}
		else{
			int x=getPos().getX(); // initial position of the projectile
			int y=getPos().getY();

			int moveX=speed*(pos.getX()-getPos().getX())/distance;
			int moveY=speed*(pos.getY()-getPos().getY())/distance;
			
			getPos().setX(x+moveX);
			getPos().setY(y+moveY);
			
			return false;
		}
	}
	
	public void update(){
		if(target.isAlive()==false){
			disappear();
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
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public int getDamage() {
		return damage;
	}
	public void setDamage(int damage) {
		this.damage = damage;
	}

	public Turret getMotherTurret() {
		return motherTurret;
	}

	public void setMotherTurret(Turret motherTurret) {
		this.motherTurret = motherTurret;
	}

	public Enemy getTarget() {
		return target;
	}

	public void setCible(Enemy tgt) {
		this.target = tgt;
	}
	
}
