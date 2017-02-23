package obj;
//Author : Flo
//Work In Progress, don't judge
  

public class Projectile extends Displayable{
	int speed;
	int damage;
	int motherTurret;
	Enemy cible;
	
	Projectile(Enemy enemy, int mt, String projectileType){
		id=createId();
		cible=enemy;
		type=projectileType;
		motherTurret=mt;
		ListEnemiesAlive[nbEnemiesAlive]=this;
		nbEnemiesAlive++;
		appear();
	}
	
	@Override
	void appear(){
	}
	
	@Override
	void disappear(){
		
	}
	
	void move(){
		if(cible.)
	}
	
	void hit(Enemy cible){
		if(e.getHp()-damage>0){
			e.setHp(e.getHp()-damage);
			this.disappear();
		}
		else{
			cible.disappear();
		}
		this.disappear();
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
	
}
