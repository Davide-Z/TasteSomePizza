package obj;
//Author : Flo

import gui.FileLoader;
import maps.Vec;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import java.util.LinkedList;

public class Turret extends Displayable{
	float fireRate;
	int range;
	int buyPrice;
	int sellPrice;
	int upgradePrice;
	int level;
	boolean upgrade;
	long lastFire=System.currentTimeMillis()-100000;//-1000000 for the first shot
	Enemy lastEnemy=null;
	protected String projectileSpriteName;
	
	// Data of the projectile
	int projectileType;
	int damage;
	
	
	//Main constructor, for the "physical" turrets who will attack and  be rendered
	public Turret(int t, Vec p, StateBasedGame sbg, Wave w) throws SlickException {
		super(t, p, sbg, w);
		if(config.aliveTurrets==null){
			config.aliveTurrets=new LinkedList<Turret>();
		}
		assignType(t);
		super.sprite=FileLoader.getSpriteImage("cook.png");
		config.aliveTurrets.add(this);
	}
	
	public Turret(int t, StateBasedGame sbg) throws SlickException {
		super(t, sbg);
		assignType(t);
		super.sprite=FileLoader.getSpriteImage("cook.png");
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
		this.aimedDirection=0;
		config.aliveTurrets.add(this);
		System.out.println("tourette ajout�e "+config.aliveTurrets.size());
	}
	public Turret(StateBasedGame sbg) throws SlickException{
		super(sbg);
		super.typeId=0;
		super.name="Turret";
}
	
	public void assignType(int t) throws SlickException{
		//type, damage, fireRate, range, buyPrice, sellPrice, upgradePrice, spriteName, projectileSpriteName
		if (t==1){
			// HighFireRate
			assignValues(1, 50, 500f, 10, 200, 160, 200, "HighFireRateTurret.png", "HighFireRateProjectile.png");
		}
		else if(t==2){
			// HighDamage
			assignValues(2, 150, 1500f, 50, 220, 190, 220, "HighDamageTurret.png", "HighDamageProjectile.png");
		}
		else{
			// Default
			assignValues(0, 80, 800f, 15, 150, 90, 150, "DefaultTurret.png", "DefaultProjectile.png");
		}
		this.upgrade=false;
		this.level=1;
	}
	
	public void assignValues(int type,int damage, float fireRate, int range, int buyPrice, int sellPrice, int upgradePrice, String spriteName, String projectileSpriteName) throws SlickException{
		this.typeId=type;
		this.damage=damage;
		this.fireRate=fireRate;
		this.range=range;
		this.buyPrice=buyPrice;
		this.sellPrice=sellPrice;
		this.upgradePrice=upgradePrice;
		this.sprite=FileLoader.getSpriteImage(spriteName);
		this.projectileSpriteName=projectileSpriteName;
	}

	//TODO:relancer une vague avec des tourelles retirées remet ces tourelles en place...
	public void update(Wave wave) throws SlickException{
		this.actualWave=wave;
		Enemy e=null; // will be the target, if it exists
		// Si il y a un ennemi a portee et si on n'as pas tirer depuis lastFire millisecondes
		if( canFire() && (e=searchEnemy())!=null){
			/*System.out.println("Turret "+id+" created a new projectile");
			for(Projectile p : wave.aliveProjectiles){
				System.out.println(p.toString()+"\n");
			}*/
			wave.aliveProjectiles.add(new Projectile(e, this, sbg, actualWave)); // On cree un nouveau projectile
			lastFire=System.currentTimeMillis();		// On met a jour l'heure du dernier tir
			lastEnemy=e;
			//this.aimedDirection=aimingAtDegre(e.getPos());
		}
		// In order to let the tower aim at the direction of the lastEnemy
		else if(lastEnemy!=null){
			if(lastEnemy.isAlive()==true){
				this.aimedDirection=aimingAtDegre(lastEnemy.getPos());
			}
			else{
				this.lastEnemy=null;
			}
		}
		this.sprite.setCenterOfRotation(24,24);
		if(e!=null)
			System.out.println("aimedDirection="+aimedDirection+"\tthis.pos"+pos.toString()+"\te.pos"+lastEnemy.getPos().toString()+"\taimedD="+aimedDirection);
		this.sprite.setRotation(aimedDirection);
		this.sprite.setCenterOfRotation(24,24);
		if(e!=null)
			System.out.println("aimedDirection="+aimedDirection+"\tthis.pos"+pos.toString()+"\te.pos"+lastEnemy.getPos().toString()+"\taimedD="+aimedDirection);
		this.sprite.setRotation(aimedDirection);
	}
	
	public float aimingAtDegre(Vec p){
		float x1=pos.getX()+24;	// +24 because the pos of the turret is pos but the 
		float y1=pos.getY()+24; // center of the turret is pos.x+24;pos.y+24
		float x2=p.getX();
		float y2=p.getY();
		
		if(x1-x2>0){
			if(y1-y2>0){
				//1
				System.out.println("1	(y2-y1)/(x1-x2)="+(y2-y1)/(x2-x1));
				return (float)(270f+180f/Math.PI*Math.atan(	(y2-y1)/(x2-x1)	));
			}
			else if(y1-y2<0){
				//4
				System.out.println("4 (x1-x2)/(y2-y1)="+((x1-x2)/(y2-y1)));
				return (float)(180f	+	180f/Math.PI*Math.atan(	(x1-x2)/(y2-y1)	));
			}
			else{	//y1=y2
				System.out.println("x1-x2<0 & y1=y2");
				return -90f;
			}
		}
		else if(x1-x2<0){
			if(y1-y2>0){
				//2
				System.out.println("2	360/Math.PI*Math.atan(	(x2-x1)/(y1-y2)	)="+(180/Math.PI*Math.atan(	(x2-x1)/(y1-y2)	)));
				return (float)(180f/Math.PI*Math.atan(	(x2-x1)/(y1-y2)	));
			}
			else if(y1-y2<0){
				//3
				System.out.println("3	(y2-y1)/(x2-x1)="+(y2-y1)/(x2-x1));
				return (float)(90f	+	180f/Math.PI*Math.atan(	(y2-y1)/(x2-x1)	));
			}
			else{	//y1=y2
				System.out.println("x1-x2<0 & y1=y2");
				return 90f;
			}
		}
		else{	//x1=x2
			if(y1<y2){
				System.out.println("x1=x2 & y1<y2");
				return 180f;
			}
			else{
				System.out.println("x1=x2 & y1>=y2");
				return 0f;
			}
		}
	}
	
	public boolean canFire(){
		return System.currentTimeMillis() - lastFire >= fireRate;
	}
	
	public void sell(){
		config.setMoney(config.getMoney()+sellPrice);
		this.disappear(); // disappear will aliveTurrets.remove()
	}

	public void upgrade(){
		config.setMoney(config.getMoney()-upgradePrice);
		level++;
		sellPrice+=0.8*upgradePrice;
		upgradePrice*=1.2;
		damage*=1.1;
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
	
	@Override
	public String toString(){
		return "Tourelle "+id+" "+this.pos.toString()+" ";
	}
	
	// getters and setters :
	public int getTypeId() {	return typeId;	}
	public void setTypeId(int typeId) {		this.typeId = typeId;	}
	public int getProjectileType() {	return projectileType;	}
	public LinkedList<Turret> getAliveTurrets() {	return config.aliveTurrets;}
	public void setAliveTurrets(LinkedList<Turret> aliveTurrets) {	config.aliveTurrets = aliveTurrets;	}
	public int getBuyPrice() {	return buyPrice;	}
	public int getSellPrice() {	return sellPrice;	}
	public int getUpgradePrice() {	return upgradePrice;	}
	public int getLevel() {	return level;	}
	public int getDamage() {	return damage;	}
	public void setBuyPrice(int buyPrice) {	this.buyPrice = buyPrice;	}
	public void setSellPrice(int sellPrice) {	this.sellPrice = sellPrice;	}
	public void setUpgradePrice(int upgradePrice) {	this.upgradePrice = upgradePrice;	}
	public void setLevel(int level) {	this.level = level;	}
	public void setProjectileType(int projectileType) {	this.projectileType = projectileType;	}
	public void setDamage(int damage) {	this.damage = damage;	}
	public String getProjectileSpriteName() {	return projectileSpriteName;	}
	public void setProjectileSpriteName(Image projectileSpriteName) {	projectileSpriteName = projectileSpriteName;	}
}
