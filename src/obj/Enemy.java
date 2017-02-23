package obj;
import maps.Vec;;
  
public class Enemy extends Displayable{
	
	public Enemy(String t, Vec p, int speed, int damage, int hp, Vec[] path, int posInPath, int points) {
		super(t, p);
		this.speed = speed;
		this.damage = damage;
		this.hp = hp;
		this.path = path;
		this.posInPath = posInPath;
		this.points = points;
	}

	//Attributs;
	int speed;
	int damage;
	int hp;
	Vec[] path;
	int posInPath;
	int points;
	
	void attack(){
		if (pos.equals(actualMap.posBase)){
			actualMap.baseHP-=damage;
		}
	}
	
	@Override
	void appear(){
		//TODO:
		System.out.println("");
	}
	
	@Override
	void disappear(){
		
	}
}
