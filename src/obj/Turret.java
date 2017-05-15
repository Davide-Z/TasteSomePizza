package obj;
//Author : Flo

import gui.FileLoader;
import maps.Vec;
import obj.enums.TurretType;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import java.util.LinkedList;
import static obj.enums.TurretType.*;

public class Turret extends Displayable{
	private float fireRate;
	private int range;
	private int buyPrice;
	private int sellPrice;
	private int upgradePrice;
	private int level;
	private boolean upgrade;
	private long lastFire=System.currentTimeMillis()-100000;//-1000000 for the first shot
	private Enemy lastEnemy=null;
	Image projectileSprite;
	private TurretType type;
	
	// Data of the projectile
	int projectileType;
	private int damage;
	
	
	//Main constructor, for the "physical" turrets who will attack and  be rendered
	public Turret(TurretType t, Vec p, StateBasedGame sbg, Wave w) throws SlickException {
		super(t.getTypeId(), p, sbg, w);
		if(config.aliveTurrets==null){
			config.aliveTurrets=new LinkedList<Turret>();
		}
		assignType(t);
		config.aliveTurrets.add(this);
	}
	
	public Turret(TurretType t, StateBasedGame sbg) throws SlickException {
		super(t, sbg);
		assignType(t);
	}
	/**
	 * Constructeur utilisé pour copier une tourelle à une position donnée
	 * @param turret
	 */
	public Turret(Turret turret, Vec pos, StateBasedGame sbg, Wave w) throws SlickException {
		super(turret.getTypeId(), pos, turret.sbg, w);
		id=lastId;
		lastId++;
		if(w!=null){
			this.actualWave=w;
		}
		assignType(turret.type);
		super.name=turret.type.getType();
		this.aimedDirection=0;
		config.aliveTurrets.add(this);
	}
	public Turret(StateBasedGame sbg) throws SlickException{
		super(sbg);
		super.typeId=0;
		super.name="Turret";
}
	private void assignType(TurretType t) throws SlickException{
		this.type=t;
		this.typeId=t.getTypeId();
		this.damage=t.getDamage();
		this.fireRate=t.getFirerate();
		this.range=t.getRange();
		this.buyPrice=t.getBuyCost();
		this.sellPrice=t.getSellPrice();
		this.upgradePrice=t.getUpgradeCost();
		this.sprite=t.getSprite();
		this.projectileSprite=t.getProjectileSprite();
		this.upgrade=false;
		this.level=1;
	}

	//TODO:relancer une vague avec des tourelles retirées remet ces tourelles en place...
	public void update(Wave wave) throws SlickException{
		this.actualWave=wave;
		Enemy e;
		// Si il y a un ennemi a portee et si on n'as pas tirer depuis lastFire millisecondes
		if( canFire() && (e=searchEnemy())!=null){
			wave.aliveProjectiles.add(new Projectile(e, this, sbg, actualWave)); // On cree un nouveau projectile
			lastFire=System.currentTimeMillis();		// On met a jour l'heure du dernier tir
			lastEnemy=e;
			//this.aimedDirection=aimingAtDegre(e.getPos());
		}
		// In order to let the tower aim at the direction of the lastEnemy
		else if(lastEnemy!=null){
			if(lastEnemy.isAlive()){
				this.aimedDirection=aimingAtDegre(lastEnemy.getPos());
			}
			else{
				this.lastEnemy=null;
			}
		}
		this.sprite.setCenterOfRotation(24,24);
		this.sprite.setRotation(aimedDirection);
		this.sprite.setCenterOfRotation(24,24);
		this.sprite.setRotation(aimedDirection);
	}
	
	private float aimingAtDegre(Vec p){
		float x1=pos.getX()+24;	// +24 because the pos of the turret is pos but the 
		float y1=pos.getY()+24; // center of the turret is pos.x+24;pos.y+24
		float x2=p.getX();
		float y2=p.getY();
		
		if(x1-x2>0){
			if(y1-y2>0){
				//1
				return (float)(270f+180f/Math.PI*Math.atan(	(y2-y1)/(x2-x1)	));
			}
			else if(y1-y2<0){
				//4
				return (float)(180f	+	180f/Math.PI*Math.atan(	(x1-x2)/(y2-y1)	));
			}
			else{	//y1=y2
				return -90f;
			}
		}
		else if(x1-x2<0){
			if(y1-y2>0){
				//2
				return (float)(180f/Math.PI*Math.atan(	(x2-x1)/(y1-y2)	));
			}
			else if(y1-y2<0){
				//3
				return (float)(90f	+	180f/Math.PI*Math.atan(	(y2-y1)/(x2-x1)	));
			}
			else{	//y1=y2
				return 90f;
			}
		}
		else{	//x1=x2
			if(y1<y2){
				return 180f;
			}
			else{
				return 0f;
			}
		}
	}
	
	private boolean canFire(){
		return (type!=BLOCK && System.currentTimeMillis() - lastFire >= fireRate);
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
	
	private Enemy searchEnemy(){
		// we travel the list of enemies until finding the first one who is at correct distance
		if(!actualWave.getAliveEnemies().isEmpty()){
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
	public void setProjectileSpriteName(Image projectileSpriteName) {	projectileSpriteName = projectileSpriteName;	}
	public float getFireRate() {
		return fireRate;
	}

	public int getRange() {
		return range;
	}

	public TurretType getType() {
		return type;
	}
}
