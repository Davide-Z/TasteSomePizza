package obj;
//Author : Flo

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
	StateBasedGame sbg;
	GameContainer gc;
	Graphics g;
	// Ce qui n'�tait pas dans l'UML :
	String projectileType;
	long lastFire=System.currentTimeMillis();
	int idEnemy; // Ennemi � cibler
	int upgradePrice;
	
	Turret(String t, StateBasedGame sbg){
		type=t;
		id=createNewId();
		this.sbg=sbg;
		this.gc=sbg.getContainer();
		this.g=gc.getGraphics();
		TurretsAlive.add(this);
	}
	
	void fire(){
		// Si il y a un ennemi a portee et si on n'as pas tirer depuis lastFire millisecondes
		if( searchEnemy()!=null	&&	canFire() ){
			Projectile p=new Projectile(searchEnemy(), this); // On cr�e un nouveau projectile
			lastFire=System.currentTimeMillis();		// On met � jour l'heure du dernier tir
		}
	}
	
	boolean canFire(){
		if(System.currentTimeMillis() - lastFire>fireRate) 
			return true;
		else
			return false;
	}
	
	void sell(){
		GameStates.setMoney(GameStates.getMoney()+sellPrice);
		disappear();
	}

	void upgrade(){
		GameStates.setMoney(GameStates.getMoney()-upgradePrice);
		this.
	}
	
	Enemy searchEnemy(){
		// En supposant que le type Vec soit un tableau de int de la forme pos.x et pos.y
		int x; 	// Coordonees des ennemies
		int y;
		
		// On parcourt la liste des ennemies sur le terrain, s'ils sont a distance on renvoit leur id
		for(Enemy e : EnemiesAlive){
			x=e.pos.getX();
			y=e.pos.getY();
			if( (x-pos.getX())*(x-pos.getX()) + (y-pos.getY())*(y-pos.getY()) 	<	range*range){	// Si l'ennemi est a bonne distance
				return e;
			}
		}
		
		// Si on n'a pas trouver d'ennemi
		return null;
	}
	
	
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
	void appear() {
		// TODO Auto-generated method stub
		
	}
	@Override
	void disappear() {
		// TODO Auto-generated method stub
		
	}

}
