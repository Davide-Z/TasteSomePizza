package states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
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
        this.addState(new MainGameState());
        this.addState(new WaveState());
        this.enterState(0);
    }

}