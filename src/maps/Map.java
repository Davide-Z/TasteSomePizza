package maps;

import gui.Buttons.TurretButton;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.GUIContext;
import org.newdawn.slick.state.StateBasedGame;
import states.GameConfig;

import java.util.ArrayList;
import java.util.LinkedList;


//TODO: Méthode plus légit(moins dépendante de slick) pour écouter les inputs (Jpanels, mouselisteners, canvas...)
/**
 * Created by tic-tac on 15/02/17.
 */
public class Map {
    private int taille;
    private Case[][] cases;
    private ArrayList<Case> clickedCases;
    private GUIContext gc;
    private StateBasedGame sbg;
    private Graphics g;
    public Vec posBase;
    public Vec spawn;
    public int baseHP;
    private GameConfig config;
    private ArrayList<TurretButton> clickedButtons;
    private int time;

    public Map(StateBasedGame sbg, int taille, GameConfig conf) throws SlickException{   //Initialise une map vide de taille taillextaille
        this.taille=taille;
        this.gc=sbg.getContainer();
        this.sbg=sbg;
        this.g=sbg.getContainer().getGraphics();
        cases = new Case[taille][taille];
        clickedCases= new ArrayList<>();
        clickedButtons=new ArrayList<>();
        this.config=conf;
        for(int i=0;i<taille;i++){
            for(int j=0;j<taille;j++){
                cases[i][j]=new Case(sbg, 1+ i*720/taille, 1+ j*720/taille,this, this.config);
            }
        }
        
        spawn = new Vec(0, 336);
        posBase = new Vec(672, 336);
        baseHP = 100;
        
    }

    public void render() { //Affiche chaque case
        for(int i=0;i<taille;i++){
            for(int j=0;j<taille;j++){
                cases[i][j].render();
            }
        }
    }

    public void addClicked(Case c){
        this.clickedCases.add(c);
    }
    public void addClickedButtons(TurretButton turretButton) {this.clickedButtons.add(turretButton);}
    public ArrayList<Case> getClicked(){return this.clickedCases;}

    public Case[][] getCases(){
        return this.cases;
    }
    public int getTaille(){
        return this.taille;
    }

    public void resetClicked(int t){     //Evite le double clic sur une case
        time+=t;
        if(time>=10*t) {
            for (int i = 0; i < clickedCases.size(); i++) {
                clickedCases.get(i).reset();
                clickedCases.remove(i);
            }
            time=0;
        }
    }
    public void resetButtons(int t){
       // System.out.println("t="+t);
        time+=t;
      //  System.out.println("time="+time);
        if(time>=10*t) {
            for (int i = 0; i < clickedButtons.size(); i++) {
                System.out.println("resetButtons :");
                System.out.println(clickedButtons.get(i).getTurret() == null);
                clickedButtons.get(i).reset();
                clickedButtons.remove(i);
            }
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
