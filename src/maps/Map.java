package maps;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.gui.GUIContext;
import org.newdawn.slick.state.StateBasedGame;

import java.awt.*;
import java.util.ArrayList;

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

    public Map(GUIContext gc, StateBasedGame sbg, int taille){   //Initialise une map vide de taille taillextaille
        this.taille=taille;
        this.gc=gc;
        this.sbg=sbg;
        this.g=sbg.getContainer().getGraphics();
        cases = new Case[taille][taille];
        clickedCases=new ArrayList<Case>();
        for(int i=0;i<taille;i++){
            for(int j=0;j<taille;j++){
                cases[i][j]=new Case(gc, sbg, (int)(i*720/taille), (int)(j*720/taille),this);
            }
        }
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

}
