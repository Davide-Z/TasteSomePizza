package states;

import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Classe de gestion des vues: pour les initialiser, passer de l'une à l'autre
 * Created by tic-tac on 25/01/17.
 */

public class GameStates extends StateBasedGame{

    private static int money=100;
    public GameStates(String name) {
        super(name);
    }

    public static int getMoney() {
        return money;
    }

    public static void setMoney(int money) {
        GameStates.money = money;
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