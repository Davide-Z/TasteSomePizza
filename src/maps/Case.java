package maps;

import obj.Enemy;
import obj.Turret;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;
import states.GameConfig;

/**
 * Classe représentant une case de la carte, avec les éléments qu'elle contient, son image, sa position sur la carte, son accessibilité
 * Created by tic-tac on 16/02/17.
 */
public class Case{
	//position du coin haut gauche
	private int x;
	private int y;
	//Toute case est accessible par défaut
	private boolean accessible=true;
	private Rectangle interieur;
	private Rectangle cadre;
	private boolean over;
	private Image image;
	private Turret turret=null;

	public Case(int x, int y) throws SlickException{
		this.x=x;
		this.y=y;
		interieur=new Rectangle(x+1,y-1,47,47);
		cadre=new Rectangle(x+1,y-1,48,48);
		this.image=null;
	}

	public void render(Graphics g) {
	    g.setColor(Color.lightGray);
	    g.fill(interieur);
	    g.setColor(Color.black);
	    g.setLineWidth(2);
		g.draw(cadre);
		if (turret != null){
			turret.render();
		}
	}

	public void update(StateBasedGame sbg, GameConfig config) throws SlickException {
		over=interieur.contains(config.getMx(),config.getMy());
		if (over && sbg.getCurrentStateID()==1) { //Si la souris est sur la case, on est sr l'écran de jeu
			if (config.isMouseClicked() && config.wasMouseReleased) {   //Si la souris est cliquée et était relachée avant
				if (turret == null && config.getTurret() != null) {
					this.turret = new Turret(config.getTurret().getTypeId(), new Vec(this.x, this.y), sbg, null);
				} else {
					config.aliveTurrets.remove(this.turret);
					this.turret = null;
				}
				config.wasMouseReleased =false; //La souris est plus relachée (pour éviter d'appuyer plusieurs fois)
			}
		}
	}

	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}


	public Turret getTurret() {
		return turret;
	}

	public void setTurret(Turret turret) {
		this.turret = turret;
	}
}
