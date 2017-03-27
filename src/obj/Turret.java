package obj;
//Author : Flo

import gui.FileLoader;
import maps.Vec;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;

public class Turret extends Displayable{

	int buyPrice;
	int sellPrice;
	int range;
	long fireRate;
	int level;
	boolean upgrade;
	// Ce qui n'etait pas dans l'UML :
	String projectileType;
	long lastFire=System.currentTimeMillis();
	int idEnemy; // Ennemi a cibler
	int upgradePrice;
	Enemy lastEnemy=null;
	
	public Turret(String t, Vec p, StateBasedGame sbg, Wave w) throws FileNotFoundException, SlickException, URISyntaxException {
		super(t, p, sbg, w);
		actualWave.getTurretsAlive().add(this);
		assignType(t);
		super.sprite=FileLoader.getSpriteImage("cook.png");
	}
	public Turret(StateBasedGame sbg){
		super(sbg);
		super.type="tourelle de test";
	}

	/**
	 * Constructeur utilisé pour copier une tourelle
	 * @param original
	 */
	public Turret(String type, Vec pos, StateBasedGame sbg) throws FileNotFoundException, SlickException, URISyntaxException {
		super(sbg);
		super.type=type;
		super.pos=pos;
		super.sprite=FileLoader.getSpriteImage("cook.png");
	}
	
	public void assignType(String t){
		if(t.equals("default")){
			this.buyPrice=10;
			this.sellPrice=8;
			this.range=1000000; // it's a test
			this.level=1;
			this.upgrade=false;
			this.projectileType="default";
			this.upgradePrice=6;
			this.setTypeId(1);
		}
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getProjectileType() {
		return projectileType;
	}
	@Override
	public void appear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String toString(){
		return this.type;
	}
}
