package drawing;

import obj.*;

import java.util.Currency;
import java.util.LinkedList;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.StateBasedGame;

import maps.*;


public class Trace {
	
	StateBasedGame sbg;
	GameContainer gc;
	Graphics g;
	
	//image data
	LinkedList<Animation> enemyAnim;
	LinkedList<Image> turretImage;
	LinkedList<Image> projectileImage;

	Trace(StateBasedGame sbg, GameContainer gc, Graphics g) throws SlickException{
		this.sbg = sbg;
		this.gc = gc;
		this.g= g;
		
		this.enemyAnim = new LinkedList<Animation>();
		this.turretImage =  new LinkedList<Image>();
		this.projectileImage =  new LinkedList<Image>();
		
		//filling enemyAnim
		for ( int i=1; i<=1; i++) {//i number of enemy type
			SpriteSheet enemySprite = new SpriteSheet("src/resources/sprites/enemy"+i+".png", 256,256); //set resolution
			enemyAnim.add(new Animation(enemySprite, 75)); //set duration
		}
		
		//filling turretImage
		for ( int i=1; i<=1; i++) {//i number of turret type
			turretImage.add(new Image("src/resources/sprites/turret"+i+".png"));
		}
		
		//filling projectileImage
		for (int i=1; i<=1; i++) {//i number of projectile type
			projectileImage.add(new Image("src/resources/sprites/projectile"+i+".png"));
		}
	}
	
	void enemy(Enemy e) { // direction of the enemy ???
		//TODO
		
	}
	
	void turret(Turret t) { //draw the turret t
		Image currentImage = turretImage.get(t.getTypeId()).copy();
		currentImage.setRotation(t.aimingAt(new Vec(0,0))); //set angle
		currentImage.draw(t.getPos().getX(), t.getPos().getY(), 48, 48); //size 48 same as cases
	}
	
	void projectile(Projectile p) {
		Image currentImage = projectileImage.get(p.getTypeId()).copy();
		currentImage.setRotation(p.getMotherTurret().aimingAt(new Vec(0,0))); //set angle same as the mother turret
		currentImage.draw(p.getPos().getX(), p.getPos().getY(), 32, 32); //size 32
	}

}
