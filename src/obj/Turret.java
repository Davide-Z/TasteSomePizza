package obj;
//Author : Flo

public class Turret extends Displayable{
	int buyPrice;
	int sellPrice;
	int range;
	long fireRate;
	int level;
	boolean upgrade;
	
	// Ce qui n'était pas dans l'UML :
	String projectileType;
	long lastFire=System.currentTimeMillis();
	int idEnemy; // Ennemi à cibler
	int upgradePrice;
	
	Turret(String t){
		type=t;
		id=createNewId();
		
	}
	void fire(){
		// Si il y a un ennemi à portée et si on n'as pas tiré depuis lastFire millisecondes
		if( searchEnemy()!=null	&&	System.currentTimeMillis()-lastFire>fireRate ){
			Projectile p=new Projectile(searchEnemy(), this); // On crée un nouveau projectile
			lastFire=System.currentTimeMillis();		// On met à jour l'heure du dernier tir
		}
	}
	void sell(){
		Game.setMoney(Game.getMoney()+sellPrice);
	}
	void upgrade(){
		Game.setMoney(Game.getMoney()-upgradePrice);
	}
	
	Enemy searchEnemy(){
		// En supposant que le type Vec soit un tableau de int de la forme pos.x et pos.y
		int x; 	// Coordonées des ennemies
		int y;
		
		// On parcourt la liste des ennemies sur le terrain, s'ils sont à distance on renvoit leur id
		for(int i=0 ; i<nbEnemiesAlive ; i++){
			x=ListEnnemiesAlive[i].x;
			y=ListEnnemiesAlive[i].y;
			if( (x-pos.x)*(x-pos.x) + (y-pos.y)*(y-pos.y) 	<	range*range){	// Si l'ennemi est à bonne distance
				return ListEnnemiesAlive[i];
			}
		}
		
		// Si on n'a pas trouvé d'ennemi
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
