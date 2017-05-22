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
	//NomDuTypeDeTourelle(name,	typeId, damage	fireRate range	buyCost	upgradeCost	sellCost    SpritePath      ProjectileSpriteName, projectileSoundName)
	DAVIDE("Davide", 			1,      20,	 	1000f,	120,	    100,	120,			80,		"Davide",  	"FriteC",				"Attaque-FriteC.wav"),
	FLORENT("Florent", 			2,      50, 	2000f, 	90,	  		150,	200,			120,	"Florent",	"DefaultProjectile",	"Attaque-FriteC.wav"),
	TABLE("Table", 		   		3,      0, 	    0f, 	0,	    	10,	    0,			    10,		"table",	"DefaultProjectile",	"Attaque-FriteC.wav"),
	GAEL("Gael", 				4,		150,	1500f, 	150, 		400, 	10, 			10, 	"gaelC", 	"Popcorn",				"Attaque-Popcorn.wav"),
	ILYES("Ilyes",				5,		30,	    500f,	100,		200,	10,				10,		"ilyesC",	"DefaultProjectile",	"Attaque-FriteC.wav"),
	UG("Ug",				    6,		100,	1000f,	80,			300,	10,				10,		"Ug",	"DefaultProjectile",	"Attaque-Popcorn.wav");


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
	private final String projectileSoundName;
	
	TurretType(String type, int typeId , int damage, float fireRate , int range, int buyPrice, int upgradePrice, int sellPrice, String spritePath, String projectilePath, String soundName) {
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
		this.projectileSoundName=soundName;
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

	public int getTypeId() {
		return typeId;
	}

	public int getSellPrice() {
		return sellPrice;
	}

	public String getProjectileSoundName() {
		return projectileSoundName;
	}
}
