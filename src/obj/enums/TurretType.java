package obj.enums;

import gui.FileLoader;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Types de tourelles
 * Created by Tic-Tac on 08/04/2017.
 */
public enum TurretType {

	//MODELE: (à mettre avant le ;)
	//NomDuTypeDeTourelle(name,	typeId, damage	fireRate range	buyCost	upgradeCost	sellCost        SpriteName      ProjectileSpriteName)
	DEFAULT("Default", 			0,      80,	    800f,	15,	    120,	150,			120,		"DefaultTurret",         "DefaultProjectile"),
	FIRERATE("HighFireRate", 	1,      50, 	1500f,	50,	    150,	220,			150,   "HighFireRateTurret",         "DefaultProjectile"),
	DAMAGE("HighDamage", 		2,      150, 	500f, 	10,	    250,	200,			250,	 "HighDamageTurret",         "DefaultProjectile"),
	BLOCK("Block", 		        3,      0, 	    0f, 	0,	    10,	    0,			    10,	                "table",         "DefaultProjectile"),
	GAEL("Gael", 10,10,500f, 10, 10, 10, 10, "gaelC", "DefaultProjectile"),
	ILYES("Ilyes", 10,10,500f, 10, 10, 10, 10, "ilyesC", "DefaultProjectile");
	//DAVIDE("Ilyes", 10,10,500f, 10, 10, 10, 10, "ilyesC", "DefaultProjectile"),
	//FLO("Ilyes", 10,10,500f, 10, 10, 10, 10, "ilyesC", "DefaultProjectile"),
	//UG("Ilyes", 10,10,500f, 10, 10, 10, 10, "ilyesC", "DefaultProjectile");
													// range a modifier apr�s les tests

	private final String projectilePath;
	private final String type;
	private final int typeId;
	private final int damage;
	private final float fireRate;
	private final int range;
	private final int buyPrice;
	private final int sellPrice;
	private final int upgradePrice;
	private final String spritePath;
	
	TurretType(String type, int typeId , int damage, float fireRate , int range, int buyPrice, int upgradePrice, int sellPrice, String spritePath, String projectilePath) {
		this.type=type;
		this.typeId=typeId;
		this.damage=damage;
		this.fireRate=fireRate;
		this.range=range;
		this.buyPrice=buyPrice;
		this.upgradePrice=upgradePrice;
		this.sellPrice=sellPrice;
		this.spritePath=spritePath;
		this.projectilePath=projectilePath;
	} //TODO:Ajouter le constructeur qui prendrait en compte chaque paramètre des tourelles

	@Override
	public String toString(){
		return this.type;
	}

	public Image getSprite() throws SlickException {
		return FileLoader.getSpriteImage(this.spritePath);
	}
	public Image getProjectileSprite() throws SlickException{
		return FileLoader.getSpriteImage(this.projectilePath);
	}

	public String getType() {
		return type;
	}

	public int getDamage() {
		return damage;
	}

	public float getFirerate() {
		return fireRate;
	}

	public int getRange() {
		return range;
	}

	public int getBuyCost() {
		return buyPrice;
	}

	public int getUpgradeCost() {
		return upgradePrice;
	}

	public int getTypeId() {
		return typeId;
	}

	public int getSellPrice() {
		return sellPrice;
	}

	public String getSpritePath() {
		return spritePath;
	}
}
