import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

/**
 * Created by tic-tac on 25/01/17.
 */
/*

    public static void main(String[] args) throws SlickException {
        AppGameContainer app=new AppGameContainer(new ContainerTest("Titre"), 640,480,false);
        app.setShowFPS(false);
        app.start();
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
