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
		this.config = GameConfig.getInstance(sbg);
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
		this.config = GameConfig.getInstance(sbg);
		this.id = lastId;
		lastId++;
	}

	Displayable(TurretType t, StateBasedGame sbg) throws SlickException {
		this.sbg = sbg;
		this.pos = new Vec(0, 0);
		this.config = GameConfig.getInstance(sbg);
		this.typeId = t.getTypeId();
		this.id = lastId;
		lastId++;
	}

	public float aimingAt(Vec pos) { // direction of the turret
		return (float) (Math.PI / 2 - pos.getAngle() - this.pos.getAngle());
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

	public Wave getActualWave() {
		return actualWave;
	}

	public float getAimedDirection() {
		return aimedDirection;
	}

	public String getName() {
		return this.name;
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

	public void setActualWave(Wave actualWave) {
		this.actualWave = actualWave;
	}

	public void setAimedDirection(float aimedDirection) {
		this.aimedDirection = aimedDirection;
	}

	public void setName(String name) {
		this.name = name;
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
