package obj;
//Author : Flo

import maps.Vec;
import obj.enums.TurretType;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import static obj.enums.TurretType.BLOCK;

public class Turret extends Displayable {
	private float fireRate;
	private int range;
	private int buyPrice;
	private int sellPrice;
	private long lastFire = System.currentTimeMillis() - 100000;// -1000000 for
	// the first
	// shot
	private Enemy lastEnemy = null;
	Image projectileSprite;
	private TurretType type;

	// Data of the projectile
	int projectileType;
	private int damage;

	public Turret(TurretType t, StateBasedGame sbg) throws SlickException {
		super(t, sbg);
		assignType(t);
	}

	/**
	 * Constructeur utilisé pour copier une tourelle à une position donnée
	 * 
	 * @param turret
	 */
	public Turret(Turret turret, Vec pos, StateBasedGame sbg, Wave w) throws SlickException {
		super(turret.getTypeId(), pos, turret.sbg, w);
		id = lastId;
		lastId++;
		if (w != null) {
			this.actualWave = w;
		}
		assignType(turret.type);
		super.name = turret.type.getType();
		this.aimedDirection = 0;
		config.aliveTurrets.add(this);
	}

	private void assignType(TurretType t) throws SlickException {
		this.type = t;
		this.name = t.toString();
		this.typeId = t.getTypeId();
		this.damage = t.getDamage();
		this.fireRate = t.getFirerate();
		this.range = t.getRange();
		this.buyPrice = t.getBuyCost();
		this.sellPrice = t.getSellPrice();
		this.sprite = t.getSprite();
		this.projectileSprite = t.getProjectileSprite();
	}

	// TODO:relancer une vague avec des tourelles retirées remet ces tourelles
	// en place...
	public void update(Wave wave) throws SlickException {
		this.actualWave = wave;
		Enemy e;
		// Si il y a un ennemi a portee et si on n'as pas tirer depuis lastFire
		// millisecondes
		if (canFire() && (e = searchEnemy()) != null) {
			wave.aliveProjectiles.add(new Projectile(e, this, sbg, actualWave)); // On
			// cree
			// un
			// nouveau
			// projectile
			lastFire = System.currentTimeMillis(); // On met a jour l'heure du
			// dernier tir
			lastEnemy = e;
			// this.aimedDirection=aimingAtDegre(e.getPos());
		}
		// In order to let the tower aim at the direction of the lastEnemy
		else if (lastEnemy != null) {
			if (lastEnemy.isAlive()) {
				this.aimedDirection = aimingAtDegre(lastEnemy.getPos());
			} else {
				this.lastEnemy = null;
			}
		}
		this.sprite.setCenterOfRotation(24, 24);
		this.sprite.setRotation(aimedDirection);
	}

	private float aimingAtDegre(Vec p) {
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

	private boolean canFire() {
		return (type != BLOCK && System.currentTimeMillis() - lastFire >= fireRate);
	}

	private Enemy searchEnemy() {
		// we travel the list of enemies until finding the first one who is at
		// correct distance
		if (!actualWave.getAliveEnemies().isEmpty()) {
			for (Enemy e : actualWave.getAliveEnemies()) {
				if ((int) this.getPos().distance(e.getPos()) <= range * range) { // if
					// the
					// enemy
					// is
					// close
					// enough
					return e;
				}
			}
		}
		// If we didn't find an ennemy close enough
		return null;
	}

	public float aimingAt(Vec pos) { // direction of the turret
		return (float) (Math.PI / 2 - pos.getAngle() - this.pos.getAngle());
	}

	// getters and setters :
	public int getTypeId() {	return typeId;	}
	public void setTypeId(int typeId) {	this.typeId = typeId;	}
	public int getBuyPrice() {	return buyPrice;	}
	public int getSellPrice() {	return sellPrice;	}
	public int getDamage() {	return damage;	}
	public float getFireRate() {	return fireRate;	}
	public int getRange() {	return range;	}
}