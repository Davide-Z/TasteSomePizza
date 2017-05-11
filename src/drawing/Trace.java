package drawing;

import maps.Vec;
import obj.Enemy;
import obj.Projectile;
import obj.Turret;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

import java.util.LinkedList;


public class Trace {
	
	private StateBasedGame sbg;
	private GameContainer gc;
	private Graphics g;
	
	//image data
	private LinkedList<Animation> enemyAnim;
	private LinkedList<Image> turretImage;
	private LinkedList<Image> projectileImage;

	Trace(StateBasedGame sbg, GameContainer gc, Graphics g) throws SlickException{
		this.sbg = sbg;
		this.gc = gc;
		this.g= g;
		
		this.enemyAnim = new LinkedList<>();
		this.turretImage = new LinkedList<>();
		this.projectileImage = new LinkedList<>();
		
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
