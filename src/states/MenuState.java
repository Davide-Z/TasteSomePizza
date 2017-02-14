package states;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.awt.*;

/**
 * Created by tic-tac on 01/02/17.
 */
public class MenuState extends BasicGameState {

    private GameContainer container;
    private StateBasedGame game;
    Rectangle startRect;
    Rectangle exitRect;

    int mouseX;
    int mouseY;

    int winHeight;
    int winWidth;
    int space=25;

    @Override
    public int getID() {
        return 0;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        this.container=gc;
        this.game=sbg;
        winHeight=gc.getHeight();
        winWidth=gc.getWidth();
        startRect = new Rectangle(winWidth/2-125,winHeight/2-25,250,50);
        exitRect = new Rectangle(winWidth/2-125,winHeight/2-125+space,250,50);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.setBackground(Color.black);
        g.setColor(Color.white);
        g.setLineWidth(5);
        g.fill(startRect);
        g.fill(exitRect);
        g.setColor(Color.white);
        g.draw(startRect);
        g.draw(exitRect);
        g.drawString("X:"+mouseX+"\nY:"+mouseY,0,winHeight-35);
        g.drawString(winWidth+"x"+winHeight, winWidth-73,0);
        g.setColor(Color.black);
        g.drawString("\"ESPACE\" pour commencer", winWidth/2-108,winHeight/2-86);
        g.drawString("\"ECHAPE\" pour quitter", winWidth/2-95,winHeight/2-35+space);

    }

    @Override
    public void keyPressed(int key, char c){
        if(key== Input.KEY_ESCAPE){
            container.exit();
        }
        if(key==Input.KEY_SPACE){
            game.enterState(1);
        }
    }

    @Override
    public void mouseMoved(int oldX, int oldY, int newX, int newY){
        mouseX=newX;
        mouseY=newY;
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        Input input= gc.getInput();
    }
}