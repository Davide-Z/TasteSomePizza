package maps;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.GUIContext;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by tic-tac on 15/02/17.
 */
public class Map {
    private int taille;
    private Case[][] cases;
    private ArrayList<Case> clickedCases;
    private Mouse mouse;
    private GUIContext gc;
    private StateBasedGame sbg;
    private Graphics g;
    public Vec posBase;
    public Vec spawn;
    public int baseHP;

    public Map(GUIContext gc, StateBasedGame sbg, int taille) throws SlickException{   //Initialise une map vide de taille taillextaille
        this.taille=taille;
        this.gc=gc;
        this.sbg=sbg;
        this.g=sbg.getContainer().getGraphics();
        cases = new Case[taille][taille];
        clickedCases=new ArrayList<Case>();
        for(int i=0;i<taille;i++){
            for(int j=0;j<taille;j++){
                cases[i][j]=new Case(gc, sbg, 1+(int)(i*720/taille), 1+(int)(j*720/taille),this);
            }
        }
        
        spawn = new Vec(0, 300);
        posBase = new Vec( 600, 300);
        baseHP = 100;
        
    }

    public void render() {
        for(int i=0;i<taille;i++){
            for(int j=0;j<taille;j++){
                cases[i][j].render(gc,g);
            }
        }
    }

    public void addClicked(Case c){
        this.clickedCases.add(c);
    }

    public Case[][] getCases(){
        return this.cases;
    }
    public int getTaille(){
        return this.taille;
    }
    public void resetClicked(){
        for(int i=clickedCases.size()-1;i>0;i--){
            clickedCases.get(i).reset();
            clickedCases.remove(i);
        }
    }
    
    public LinkedList<Vec> computePath(){
		//TODO
		LinkedList<Vec> path = new LinkedList<>();
		int x = this.spawn.getX();
		int y = this.spawn.getY();
		while (x<this.posBase.getX()) {
			x++;
			path.add(new Vec(x, y));
		}
		return path;
	}

}
