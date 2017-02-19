package maps;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
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
	private GUIContext container;
	private StateBasedGame sbg;
	private boolean clicked=false;
	private Map map;

	public Case(GUIContext container, StateBasedGame sbg, int x, int y, Map map) {
	    super(container,null,x,y,48,48);
		this.x=x;
		this.y=y;
		interieur=new Rectangle(x+1,y+1,47,47);
		cadre=new Rectangle(x+1,y+1,48,48);
		this.container=container;
		this.sbg=sbg;
		this.map=map;
	}

	public void reset(){
		clicked=false;
	}
	@Override
	public void render(GUIContext guiContext, Graphics g) {
	    g.setColor(Color.white);
	    g.fill(interieur);
	    g.setColor(Color.darkGray);
	    g.setLineWidth(1);
	    g.draw(cadre);
	}

	@Override
    public void mousePressed(int button, int mx, int my){
	    over=interieur.contains(mx,my);
	    if (over&&sbg.getCurrentStateID()==1&&!clicked){
	        //TODO:si bouton pressé sur case, ajouter tourelle selectionnée(voir si il faudrait pas faire ça dans une classe à part)
            System.out.println("Case cliquée:"+(1+(this.getX()/48))+"x"+(1+(this.getY()/48)));
		}
		this.map.addClicked(this);
		clicked=true;
    }
}
