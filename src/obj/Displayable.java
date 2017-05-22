package obj;
//Author : Flo

import maps.Map;
import maps.Vec;
import obj.enums.TurretType;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import states.GameConfig;

public abstract class Displayable {
	static int lastId = 0;
	protected GameConfig config;
	protected StateBasedGame sbg;
	Map actualMap;
	Wave actualWave;
	float aimedDirection;
	int id; // Useful for tests
	String name;
	Vec pos;
	Image sprite;
	int typeId;

	Displayable(int t, Vec p, StateBasedGame sbg, Wave w) throws SlickException {
		this.typeId = t;
		if (p != null) {
			this.pos = p.copy();
		} else {
			this.pos = new Vec(0, 0);
		}
		this.sbg = sbg;
		this.actualWave = w;
		this.id = lastId;
		lastId++;
		this.config = GameConfig.getInstance();
	}

	/**
	 * initialise un Displayable avec juste l'essentiel
	 * 
	 * @param sbg
	 * @throws SlickException
	 */
	Displayable(StateBasedGame sbg) throws SlickException {
		this.sbg = sbg;
		this.pos = new Vec(0, 0);
		this.config = GameConfig.getInstance();
		this.id = lastId;
		lastId++;
	}

	Displayable(TurretType t, StateBasedGame game) throws SlickException {
		this.pos = new Vec(0, 0);
		this.sbg=game;
		this.config = GameConfig.getInstance();
		this.typeId = t.getTypeId();
		this.id = lastId;
		lastId++;
	}

	public float aimingAt(Vec pos) { // direction of the turret
		return (float) (Math.PI / 2 - pos.getAngle() - this.pos.getAngle());
	}
	
	public float aimingAtDegre(Vec p) {
		float x1 = pos.getX() + 24; // +24 because the pos of the turret is pos
		// but the
		float y1 = pos.getY() + 24; // center of the turret is pos.x+24;pos.y+24
		float x2 = p.getX()+24;
		float y2 = p.getY()+24;

		if (x1 - x2 > 0) {
			if (y1 - y2 > 0) {
				// 1
				return (float) (270f + 180f / Math.PI * Math.atan((y2 - y1) / (x2 - x1)));
			} else if (y1 - y2 < 0) {
				// 4
				return (float) (180f + 180f / Math.PI * Math.atan((x1 - x2) / (y2 - y1)));
			} else { // y1=y2
				return -90f;
			}
		} else if (x1 - x2 < 0) {
			if (y1 - y2 > 0) {
				// 2
				return (float) (180f / Math.PI * Math.atan((x2 - x1) / (y1 - y2)));
			} else if (y1 - y2 < 0) {
				// 3
				return (float) (90f + 180f / Math.PI * Math.atan((y2 - y1) / (x2 - x1)));
			} else { // y1=y2
				return 90f;
			}
		} else { // x1=x2
			if (y1 < y2) {
				return 180f;
			} else {
				return 0f;
			}
		}
	}

	public void disappear() {
		// remove the object of the corresponding linkedList
		if (this instanceof Turret) {
			config.aliveTurrets.remove(this);
		} else if (this instanceof Enemy) {
			actualWave.aliveEnemies.remove(this);
		} else if (this instanceof Projectile) {
			actualWave.aliveProjectiles.remove(this);
		}
	}

	// Getters and Setters __________________________________________________
	public Vec getPos() {
		return pos;
	}

	public StateBasedGame getSbg() {
		return sbg;
	}

	public int getTypeId() {
		return typeId;
	}

	public void render() {
		this.sprite.draw(pos.getX(), pos.getY());
	}

	void setActualWave(Wave actualWave) {
		this.actualWave = actualWave;
	}

	public void setPos(Vec pos) {
		this.pos = pos;
	}

	public void setSbg(StateBasedGame sbg) {
		this.sbg = sbg;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	@Override
	public String toString() {
		if (this instanceof Turret) {
			return "Tourelle : " + this.name + "\n@" + this.pos.toString() + " ";
		} else if (this instanceof Enemy) {
			return "Ennemi : " + name + "\n@" + this.pos.toString() + " ";
		} else if (this instanceof Projectile) {
			return "Projectile : " + name + "\n@" + this.pos.toString() + " ";
		} else {
			return "Displayable" + name;
		}
	}
}
