package obj;
//Author : Flo

import gui.FileLoader;
import maps.Vec;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import java.util.LinkedList;

public class Turret extends Displayable{
	public static LinkedList<Turret> aliveTurrets=new LinkedList<Turret>();
	int projectileType;
	int damage;
	float fireRate;
	int range;
	int buyPrice;
	int sellPrice;
	int upgradePrice;
	int level;
	boolean upgrade;
	long lastFire=System.currentTimeMillis()-100000;//-1000000 for the first shot
	Enemy lastEnemy=null;
	
	
	//Main constructor, for the "physical" turrets who will attack and  be rendered
	public Turret(int t, Vec p, StateBasedGame sbg, Wave w) throws SlickException {
		super(t, p, sbg, w);
		if(aliveTurrets==null){
			aliveTurrets=new LinkedList<Turret>();
		}
		assignType(t);
		super.sprite=FileLoader.getSpriteImage("cook.png");
		aliveTurrets.add(this);
	}
	
	public Turret(StateBasedGame sbg, Vec p) throws SlickException {
		super(sbg);
		super.typeId=0;
		super.sprite=FileLoader.getSpriteImage("cook.png");
		super.name="Turret";
		if(pos!=null){
			this.pos=p;
		}
		else{
			this.pos=new Vec(0,0);
		}
	}

	/**
	 * Constructeur utilisé pour copier une tourelle à une position donnée
	 * @param turret
	 */
	public Turret(Turret turret, Vec pos, StateBasedGame sbg, Wave w) throws SlickException {
		super(turret.getTypeId(), pos, turret.sbg, w);
		System.out.println("lastId="+lastId);
		id=lastId;
		lastId++;
		if(w!=null){
			this.actualWave=w;
		}
		assignType(this.typeId);
		super.sprite=FileLoader.getSpriteImage("cook.png");
		super.name="Turret";
		aliveTurrets.add(this);
		System.out.println("tourette ajout�e "+aliveTurrets.size());
	}
	public Turret(StateBasedGame sbg){
		super(sbg);
		super.typeId=0;
		super.name="Turret";
}
	
	public void assignType(int t){
		//type, damage, fireRate, range, buyPrice, sellPrice, upgradePrice
		if (t==1){
			// HighFireRate
			assignValues(1, 130, 500f, 10000, 200, 160, 200);
		}
		else if(t==2){
			// HighDamage
			assignValues(2, 300, 1.100f, 10000, 220, 190, 220);
		}
		else{
			// Default
			assignValues(0, 200, 800f, 10000, 150, 90, 150);
		}
		this.upgrade=false;
		this.level=1;
	}
	
	public void assignValues(int type,int damage, float fireRate, int range, int buyPrice, int sellPrice, int upgradePrice){
		this.typeId=type;
		this.damage=damage;
		this.fireRate=fireRate;
		this.range=range;
		this.buyPrice=buyPrice;
		this.sellPrice=sellPrice;
		this.upgradePrice=upgradePrice;
	}
	
	public void update(Wave wave) throws SlickException{
		this.actualWave=wave;
		Enemy e=null; // will be the target, if it exists
		// Si il y a un ennemi a portee et si on n'as pas tirer depuis lastFire millisecondes
		//System.out.println("CanFire: "+canFire()+"\tsearcheEnemy==null : "+(searchEnemy()==null));
		if( canFire() && (e=searchEnemy())!=null){
			System.out.println("Turret "+id+" created a new projectile");
			for(Projectile p : wave.aliveProjectiles){
				System.out.println(p.toString()+"\n");
			}
			wave.aliveProjectiles.add(new Projectile(e, this, sbg, actualWave)); // On cree un nouveau projectile
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
		return System.currentTimeMillis() - lastFire >= fireRate;
	}
	
	public void sell(){
		//TODO
		config.setMoney(config.getMoney()+sellPrice);
		this.disappear(); // disappear will aliveTurrets.remove()
	}

	public void upgrade(){
		//TODO
		config.setMoney(config.getMoney()+upgradePrice);
		level++;
		sellPrice+=0.8*upgradePrice;
		upgradePrice*=1.3;
		fireRate*=1.1;
		}
	
	public Enemy searchEnemy(){
		// we travel the list of enemies until finding the first one who is at correct distance
		if(actualWave.getAliveEnemies().isEmpty()==false){
			for(Enemy e : actualWave.getAliveEnemies()){
				if( (int)this.getPos().distance(e.getPos())	<=	range*range){	// if the enemy is close enough
					return e;
				}
			}
		}
		// If we didn't find an ennemy close enough
		return null;
	}
	
	public float aimingAt(Vec pos) { //direction of the turret 
		return (float) (Math.PI/2 - pos.getAngle() - this.pos.getAngle());
	}
	
	// getters and setters :
	public int getTypeId() {	return typeId;	}
	public void setTypeId(int typeId) {		this.typeId = typeId;	}
	public int getProjectileType() {	return projectileType;	}
	public static LinkedList<Turret> getAliveTurrets() {	return aliveTurrets;}
	public static void setAliveTurrets(LinkedList<Turret> aliveTurrets) {	Turret.aliveTurrets = aliveTurrets;	}
	
	@Override
	public String toString(){
		return "Tourelle "+id+" "+this.pos.toString()+" ";
	}
}
