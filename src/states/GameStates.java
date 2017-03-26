package states;

import gui.FileLoader;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Classe de gestion des vues: pour les initialiser, passer de l'une à l'autre
 * Created by tic-tac on 25/01/17.
 */

public class GameStates extends StateBasedGame{

    public GameStates(String name) {
        super(name);
    }

    @Override
    public void initStatesList(GameContainer gameContainer) throws SlickException { //Création et initialisation des vues
        this.addState(new MenuState());
        this.getState(0).init(gameContainer,this);
        this.addState(new MainGameState());
        this.getState(1).init(gameContainer,this);
        this.addState(new WaveState());
        this.getState(2).init(gameContainer, this);
        this.enterState(0);
    }

}