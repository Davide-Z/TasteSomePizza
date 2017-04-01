package obj;
//Author : Flo

import gui.FileLoader;
import maps.Vec;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;

public class Turret extends Displayable{
	int type;
	int projectileType;
	int damage;
	float fireRate;
	int range;
	int buyPrice;
	int sellPrice;
	int upgradePrice;
	int level;
	boolean upgrade;
	long lastFire=System.currentTimeMillis();
	Enemy lastEnemy=null;
	
	public Turret(int t, Vec p, StateBasedGame sbg, Wave w) throws FileNotFoundException, SlickException, URISyntaxException {
		super(t, p, sbg, w);
		actualWave.getTurretsAlive().add(this);
		assignType(t);
		super.sprite=FileLoader.getSpriteImage("cook.png");
	}
	public Turret(StateBasedGame sbg){
		super(sbg);
		super.type=0;
	}

	/**
	 * Constructeur utilisé pour copier une tourelle
	 * @param turret
	 */
	public Turret(Turret turret) throws FileNotFoundException, SlickException, URISyntaxException {
		super(turret.sbg);
		super.type=turret.type;
		super.pos=turret.pos;
		this.level=1;
		super.sprite=FileLoader.getSpriteImage("cook.png");
	}
	
	public void assignType(int t){
		if (t==1){
			// HighFireRate
			assignValues(1, 130, 2f, 10000, 200, 160, 200);
		}
		else if(t==2){
			// HighDamage
			assignValues(2, 300, 1.3f, 10000, 220, 190, 220);
		}
		else{
			// Default
			assignValues(0, 200, 1f, 10000, 150, 90, 150);
		}
		this.upgrade=false;
	}
	
	public void assignValues(int type,int damage, float fireRate, int range, int buyPrice, int sellPrice, int upgradePrice){
		this.type=type;
		this.damage=damage;
		this.fireRate=fireRate;
		this.range=range;
		this.buyPrice=buyPrice;
		this.sellPrice=sellPrice;
		this.upgradePrice=upgradePrice;
	}
	
	public void update(){
		Enemy e=null; // will be the target, if it exists
		// Si il y a un ennemi a portee et si on n'as pas tirer depuis lastFire millisecondes
		if( canFire() && (e=searchEnemy())!=null){
			Projectile p=new Projectile(e, this, actualWave); // On cree un nouveau projectile
			lastFire=System.currentTimeMillis();		// On met a jour l'heure du dernier tir
			lastEnemy=e;
			this.aimedDirection=aimingAt(e.getPos());
		}
		// In order to let the tower aim at the direction of the lastEnemy
		else if(lastEnemy!=null){
			if(lastEnemy.isAlive()==true){
				this.aimedDirection=aimingAt(lastEnemy.getPos());
			}
			else{
				this.lastEnemy=null;
			}
		}
	}
	
	public boolean canFire(){
		return System.currentTimeMillis() - lastFire > fireRate;
	}
	
	public void sell(){
		config.setMoney(config.getMoney()+sellPrice);
		this.disappear();
	}

	public void upgrade(){
		// It is just a first idea, i'll modify it later
		config.setMoney(config.getMoney()+upgradePrice);
		level++;
		sellPrice+=0.8*upgradePrice;
		upgradePrice*=1.3;
		fireRate*=1.1;
		}
	
	public Enemy searchEnemy(){
		// we travel the list of enemies until finding the first one who is at correct distance
		for(Enemy e : actualWave.getEnemiesAlive()){
			if( this.getPos().distance(e.getPos())	<	range*range){	// if the enemy is close enough
				return e;
			}
		}
		// If we didn't find an ennemy close enough
		return null;
	}
	
	public float aimingAt(Vec pos) { //direction of the turret 
		return (float) (Math.PI/2 - pos.getAngle() - this.pos.getAngle());
	}
	
	/*public float amingAt() { //direction of the turret 
		Enemy aimedEnemy = searchEnemy();
		if (aimedEnemy != null) {
			return (float) (Math.PI/2 - aimedEnemy.pos.getAngle() - this.pos.getAngle());
		}
		else return (float) -Math.PI/2;
	}*/
	
	// getters and setters :
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getProjectileType() {
		return projectileType;
	}
	@Override
	public void appear() {
		// TODO Auto-generated method stub
		
	}
}
