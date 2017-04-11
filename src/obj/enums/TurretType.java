package obj.enums;

import gui.FileLoader;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;

/**
 * Created by Tic-Tac on 08/04/2017.
 */
public enum TurretType {

	//MODELE: (à mettre avant le ;)
	//NomDuTypeDeTourelle(name,		damage	fireRate range	buyCost	upgradeCost	sellCost	)
	Default("Default", 				100,	1.0f,	10000,	100,	30,			100,	"cook.png"),
	HighFireRate("HighFireRate", 	65, 	2.5f,	10000,	200,	50,			160,	"cook.png"),
	HighDamage("HighDamage", 		250, 	0.8f, 	10000,	220,	55,			180,	"cook.png");
													// range a modifier apr�s les tests
	
	;
	private String type="";
	private int damage;
	private float fireRate;
	private int range;
	private int buyPrice;
	private int sellPrice;
	private int upgradePrice;
	private String spritePath;
	
	TurretType(String type, int damage, float fireRate , int range, int buyPrice, int upgradePrice, int sellPrice, String spritePath) {
		this.type=type;
		this.damage=damage;
		this.fireRate=fireRate;
		this.range=range;
		this.buyPrice=buyPrice;
		this.upgradePrice=upgradePrice;
		this.sellPrice=buyPrice;
		this.spritePath=spritePath;
	} //TODO:Ajouter le constructeur qui prendrait en compte chaque paramètre des tourelles

	@Override
	public String toString(){
		return this.type;
	}

	public Image getSprite() throws FileNotFoundException, SlickException, URISyntaxException {
		return FileLoader.getSpriteImage(this.spritePath);
	}

	public String getType() {
		return type;
	}

	public int getDamage() {
		return damage;
	}

	public int getFirerate() {
		return firerate;
	}

	public int getRange() {
		return range;
	}

	public int getBuyCost() {
		return buyCost;
	}

	public int getUpgradeCost() {
		return upgradeCost;
	}
}
