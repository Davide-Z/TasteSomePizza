package obj.enums;

import gui.FileLoader;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Types d'enemis
 * Created by Tic-Tac on 08/04/2017.
 */
public enum EnemyType {

	//MODELE: (à mettre avant le ;)
	//NomDuTypeDeTourelle(name,	typeId, damage	   speed  health reward levelBonus    SpriteName)
	DEFAULT(         "Default",		0,      5,	    0.2f,	100,   10,      2,   "DefaultTurret"),
	FAST(               "Fast",    	1,      2,      0.6f,	40,	    2,      1,          "client"),
	SLOW(               "Slow",		2,     10,  	0.1f, 	250,   22,      3,          "client");
	// health a modifier après les tests

	public final String type;
	public final int typeId;
	public final int damage;
	public final float speed;
	public final int health;
	public final int reward;
	public final int levelBonus;
	public final String spritePath;

	EnemyType(String type, int typeId , int damage, float speed , int health, int reward, int levelBonus, String spritePath) {
		this.type = type;
		this.typeId = typeId;
		this.damage = damage;
		this.speed = speed;
		this.health = health;
		this.reward = reward;
		this.levelBonus=levelBonus;
		this.spritePath = spritePath;
	}


	//GETTERS:
	@Override
	public String toString(){
		return type;
	}

	public Image sprite() throws SlickException {return FileLoader.getSpriteImage(spritePath);}
}
