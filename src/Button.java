import org.lwjgl.input.Mouse;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.GUIContext;

/**
 * Created by tic-tac on 14/02/17.
 */
public class Button extends AbstractComponent {
    public Button(GUIContext container) {
        super(container);
    }


    public void mouseMoved(int oldx, int oldy, int newx, int newy){
    }

    public void mousePressed(int button, int x, int y) {

    }

    @Override
    public void render(GUIContext guiContext, Graphics graphics) throws SlickException {

    }

    @Override
    public void setLocation(int i, int i1) {

    }

    @Override
    public int getX() {
        return 0;
    }

    @Override
    public int getY() {
        return 0;
    }

    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }
}
