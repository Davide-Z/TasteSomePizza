import org.newdawn.slick.*;
import org.newdawn.slick.tiled.TiledMap;

/**
 * Created by tic-tac on 01/02/17.
 */
public class ContainerTest extends BasicGame {
    GameContainer container;
    TiledMap map;

    public ContainerTest(String title) {
        super(title);
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        this.container=gameContainer;
    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {

    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {

    }

    public void keyPressed(int key, char c){
        if (key== Input.KEY_ESCAPE){
            container.exit();
        }
    }
}
