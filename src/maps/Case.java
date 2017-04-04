package maps;

import obj.Displayable;
import obj.Enemy;
import obj.Turret;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.StateBasedGame;
import states.GameConfig;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;

/**
 * Classe représentant une case de la carte, avec les éléments qu'elle contient, son image, sa position sur la carte, son accessibilité
 * Created by tic-tac on 16/02/17.
 */
public class Case extends MouseOverArea {
	//position du coin haut gauche
	private int x;
	private int y;
	//Toute case est accessible par défaut
	private boolean access=true;
	private Rectangle interieur;
	private Rectangle cadre;
	private boolean over;
	private GameContainer container;
	private StateBasedGame sbg;
	private Graphics g;
	private GameConfig config;
	private Map map;
	private Image image;
	private Turret turret=null;
	private Enemy enemy=null;

	public Case(StateBasedGame sbg, int x, int y, Map map, GameConfig conf) throws SlickException{
	    super(sbg.getContainer(),null,x,y,48,48);
		this.x=x;
		this.y=y;
		interieur=new Rectangle(x+1,y-1,47,47);
		cadre=new Rectangle(x+1,y-1,48,48);
		this.sbg=sbg;
		this.container=sbg.getContainer();
		this.g=container.getGraphics();
		this.map=map;
		this.image=null;
		this.config=conf;
	}

	public void render() {
	    g.setColor(Color.white);
	    g.fill(interieur);
	    g.setColor(Color.lightGray);
	    g.setLineWidth(2);

		//image.draw(x+1,y-1);
		g.draw(cadre);

		if (turret != null){
			turret.render();
		}
		else if(enemy != null){
			enemy.render();
		}
	}

	public void update() throws InterruptedException {
		over=interieur.contains(config.getMx(),config.getMy());
		if (over && sbg.getCurrentStateID()==1) { //Si la souris est sur la case, on est sr l'écran de jeu
			if (config.isMouseClicked() && config.isMouseReleased) {   //Si la souris est cliquée et était relachée avant
				System.out.println("Case cliquée:" + (1 + (this.getX() / 48)) + "x" + (1 + (this.getY() / 48)));
				if (config.getTurretMenu().turretMode) {
					if (turret == null && enemy == null && config.getTurret() != null) {
						try {
							this.turret = new Turret(config.getTurret(), new Vec(this.x, this.y));
						} catch (FileNotFoundException | SlickException | URISyntaxException e) {
							e.printStackTrace();
						}
					} else {
						this.turret = null;
					}
				} else {
					if (enemy == null && turret == null && config.getEnemy() != null) {
						try {
							this.enemy = new Enemy(config.getEnemy(), new Vec(this.x, this.y));
							System.out.println(this.enemy.getTypeId());
						} catch (FileNotFoundException | SlickException | URISyntaxException e) {
							e.printStackTrace();
						}
					} else {
						this.enemy = null;
					}
				}
				config.isMouseReleased=false; //La souris est plus relachée (pour éviter d'appuyer plusieurs fois)
			}
		}
	}

	@Override
    public void mousePressed(int button, int mx, int my){
    }

	@Override
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	@Override
	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public Displayable getTurret() {
		return turret;
	}

	public void setTurret(Turret turret) {
		this.turret = turret;
	}
	
	public int getXInteger() { //integer coordinate 
		return (this.x-1)/48;
	}
	
	public int getYInteger() {
		return (this.y-1)/48;
	}
}
