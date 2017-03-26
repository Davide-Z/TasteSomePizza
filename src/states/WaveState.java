package states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Created by tic-tac on 24/03/17.
 */
public class WaveState extends BasicGameState {

    //Attributs du moteur
    private GameContainer container;
    private StateBasedGame game;
    private GameConfig config;

    public WaveState() throws SlickException {
    }

    //Attributs d'interface


    @Override
    public int getID() {
        return 2;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        game=stateBasedGame;
        container=gameContainer;
        config=GameConfig.getInstance(game);
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {

    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {

    }
}
