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
	//NomDeTypeDeTourelle(paramètres)
	TestTurret("turretTest", 10,1,3,100,30,"cook.png")
	;
	private String type="";
	private int damage;
	private int firerate;
	private int range;
	private int buyCost;
	private int upgradeCost;
	private String spritePath;
	TurretType(String type, int damage, int firerate , int range, int buyCost, int upgradeCost, String spritePath) {
		this.type=type;
		this.damage=damage;
		this.firerate=firerate;
		this.range=range;
		this.buyCost=buyCost;
		this.upgradeCost=upgradeCost;
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
