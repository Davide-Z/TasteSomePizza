package obj;
//Author : Flo

import maps.Vec;
import obj.Wave;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;
import states.GameStates;

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
	
	public Turret(String t, Vec p, StateBasedGame sbg, Wave w){
		super(t, p, sbg, w);
		type=t;
		actualWave.getTurretsAlive().add(this);
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
				lastEnemy=null;
			}
		}
	}
	
	public boolean canFire(){
		if(System.currentTimeMillis() - lastFire>fireRate) 
			return true;
		else
			return false;
	}
	
	public void sell(){
		GameStates.setMoney(GameStates.getMoney()+sellPrice);
		disappear();
	}

	public void upgrade(){
		// It is just a first idea, i'll modify it later
		GameStates.setMoney(GameStates.getMoney()-upgradePrice);
		level++;
		sellPrice+=0.8*upgradePrice;
		upgradePrice*=1.3;
		fireRate*=1.1;
		}
	
	public Enemy searchEnemy(){
		// En supposant que le type Vec soit un tableau de int de la forme pos.x et pos.y
		int x; 	// Coordonees des ennemies
		int y;
		
		// On parcourt la liste des ennemies sur le terrain, s'ils sont a distance on renvoit leur id
		for(Enemy e : actualWave.getEnemiesAlive()){
			x=e.pos.getX();
			y=e.pos.getY();
			if( this.getPos().distance(e.getPos())	<	range*range){	// Si l'ennemi est a bonne distance
				return e;
			}
		}
		
		// Si on n'a pas trouver d'ennemi
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
	public int getBuyPrice() {
		return buyPrice;
	}
	public void setBuyPrice(int buyPrice) {
		this.buyPrice = buyPrice;
	}
	public int getSellPrice() {
		return sellPrice;
	}
	public void setSellPrice(int sellPrice) {
		this.sellPrice = sellPrice;
	}
	public int getRange() {
		return range;
	}
	public void setRange(int range) {
		this.range = range;
	}
	public long getFireRate() {
		return fireRate;
	}
	public void setFireRate(int fireRate) {
		this.fireRate = fireRate;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public boolean isUpgrade() {
		return upgrade;
	}
	public void setUpgrade(boolean upgrade) {
	}
	public long getLastFire() {
		return lastFire;
	}
	public void setLastFire(long lastFire) {
		this.lastFire = lastFire;
	}
	public int getIdEnemy() {
		return idEnemy;
	}
	public void setIdEnemy(int idEnemy) {
		this.idEnemy = idEnemy;
	}
	public int getUpgradePrice() {
		return upgradePrice;
	}
	public void setUpgradePrice(int upgradePrice) {
		this.upgradePrice = upgradePrice;
	}
	public void setFireRate(long fireRate) {
		this.fireRate = fireRate;
	}
	@Override
	public void appear() {
		// TODO Auto-generated method stub
		
	}

}
