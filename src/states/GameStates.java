package states;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Classe de gestion des vues: pour les initialiser, passer de l'une à l'autre
 * Created by tic-tac on 25/01/17.
 */

public class GameStates extends StateBasedGame{

    int currentState;
    int lastState;
    public GameStates(String name) {
        super(name);
    }

    @Override
    public void initStatesList(GameContainer gameContainer) throws SlickException { //Création et initialisation des vues
        this.addState(new MenuState());
        this.addState(new MainGameState());
        this.addState(new WaveState());
        //this.enterState(0);
        this.getContainer().getGraphics().setBackground(Color.decode("0xdba24f"));
        this.enterState(0);
    }

    @Override
    /**
     * Méthode qui est utilisée quand une touche est appuyée, pour les raccourcis claviers disponibles partout
     * (echappe = quitter, espace = aller au menu/en revenir)
     */
    public void keyPressed(int key, char c) {
        if (key == Input.KEY_ESCAPE) {
            this.getContainer().exit();
        }
        if (key == Input.KEY_SPACE) {
            currentState=this.getCurrentStateID();
            if (currentState != 0) {
                lastState=currentState;
                this.enterState(0);
            } else {
                this.enterState(lastState);
            }
        }
    }
}