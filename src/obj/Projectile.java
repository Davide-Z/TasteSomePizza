package obj;
//Author : Flo


public class Projectile extends Displayable{
	int speed;
	int damage;
	Turret motherTurret;
	Enemy cible;
	//Listes des ennemis en vie + leur nombre
	
	Projectile(Enemy enemy, Turret mt){
		super(mt.projectileType, mt.getPos(), mt.sbg);
		cible=enemy;
		type=mt.projectileType;
		motherTurret=mt;
		this.pos=motherTurret.getPos();
		ProjectilesAlive.add(this);
		appear();
	}
	
	@Override
	void appear(){
	}
	
	@Override
	void disappear(){
		
	}
	
	void move(){
	}
	/*
	void hit(Enemy cible){
		if(cible.getHp()-damage>0){
			cible.setHp(cible.getHp()-damage);
		}
		else{
			cible.disappear();
		}
		this.disappear();
	}
	*/
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

	public Enemy getCible() {
		return cible;
	}

	public void setCible(Enemy cible) {
		this.cible = cible;
	}
	
}
