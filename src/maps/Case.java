package maps;

import obj.Turret;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.gui.GUIContext;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.StateBasedGame;

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
	private boolean clicked=false;
	private Map map;
	private Image image;
	private Turret turret=null;

	public Case(StateBasedGame sbg, int x, int y, Map map) throws SlickException{
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
	}

	public void reset(){
		clicked=false;
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

	}

	@Override
    public void mousePressed(int button, int mx, int my){
	    over=interieur.contains(mx,my);
	    if (over&&sbg.getCurrentStateID()==1&&!clicked){ //Si la souris est sur la case, on est sr l'écran de jeu & si on n'a pas déjà cliqué trop récemment
            System.out.println("Case cliquée:"+(1+(this.getX()/48))+"x"+(1+(this.getY()/48)));
            if(turret==null){

			}
		}
		this.map.addClicked(this);
		clicked=true;
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

	public Turret getTurret() {
		return turret;
	}

	public void setTurret(Turret turret) {
		this.turret = turret;
	}
}
