package states;

import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

/**
 * Created by tic-tac on 25/01/17.
 */

public class GameStates extends StateBasedGame{

    public GameStates(String name) {
        super(name);
    }

    @Override
    public void initStatesList(GameContainer gameContainer) throws SlickException {
        this.addState(new MenuState());
        this.addState(new MainGameState());
    }

}