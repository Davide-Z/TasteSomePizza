package states;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Ellipse;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Created by tic-tac on 11/02/17.
 */
public class MainGameState extends BasicGameState {

    private GameContainer container;
    private StateBasedGame game;
    Image turret;
    float alpha;
    int winHeight;
    int winWidth;

    float mouseX;
    float mouseY;

    @Override
    public int getID() {
        return 1;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        this.game=sbg;
        this.container=gc;
        turret=new Image("resources/sprites/cook.png");
        winHeight=gc.getHeight();
        winWidth=gc.getWidth();
        alpha=turret.getRotation();
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        turret.draw(winWidth/2-64,winHeight/2-64);
        turret.setRotation(alpha);
        g.setColor(Color.white);
        g.drawString(""+alpha, 500,0);
        g.drawString("X:"+mouseX+"\nY:"+mouseY,0,winHeight-35);
        g.drawString(winWidth+"x"+winHeight, winWidth-73,0);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        if (alpha<=360f) {
            alpha += i * 0.1f;
        }
        else{
            alpha=0;
        }
        alpha= (float)((Math.atan2((mouseX-winWidth/2),-(mouseY-winHeight/2))*360/(2*Math.PI)));
    }

    public void keyPressed(int key, char c){
        if(key== Input.KEY_ESCAPE){
            container.exit();
        }
        if(key==Input.KEY_SPACE){
            game.enterState(0);
        }
    }

    @Override
    public void mouseMoved(int oldX, int oldY, int newX, int newY){
        mouseX=(float)(newX);
        mouseY=(float)(newY);
    }
}