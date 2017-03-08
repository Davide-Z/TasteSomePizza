package obj;
import maps.Vec;
import org.newdawn.slick.state.StateBasedGame;
import javax.swing.plaf.nimbus.State;

public class Enemy extends Displayable{
	
	public Enemy(String t, Vec p, int speed, int damage, int hp, Vec[] path, int posInPath, int points, StateBasedGame sbg) {
		super(t, p, sbg);
		this.speed = speed;
		this.damage = damage;
		this.hp = hp;
		this.path = path;
		this.posInPath = posInPath;
		this.points = points;
		enemiesAlive.add(this);
	}

	//Attributs;
	private int speed;
	private int damage;
	private int hp;
	private Vec[] path;
	private int posInPath;
	private int points;
	
	void attack(){
		if (pos.equals(actualMap.posBase)){
			if (actualMap.baseHP-damage>0){
				actualMap.baseHP-=damage;	//damage
			}
			else {
				actualMap.baseHP=0;	//killed
			}
			this.disappear();
		}
	}
	
	public boolean isAlive(){
		return (this.hp>0);
	}
	
	void move(){
		if (this.posInPath+this.speed<=this.path.length){	//la position ne depasse pas la taille de la liste des positions
			this.posInPath+=this.speed;
		}
		else {	//si on arrive a la fin
			this.posInPath=this.path.length;
		}
		this.pos=this.path[this.posInPath];

	}
	
	@Override
	void appear(){
		//TODO
	}
	
	@Override
	void disappear(){
		this.hp=0;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}
}
