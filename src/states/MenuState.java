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
    Rectangle startRect = new Rectangle(195,215,250,50);
    Rectangle exitRect = new Rectangle(195,300,250,50);

    int mouseX;
    int mouseY;

    @Override
    public int getID() {
        return 0;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        this.container=gameContainer;
        this.game=stateBasedGame;
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics gr) throws SlickException {
        gr.setBackground(Color.white);
        gr.setColor(Color.lightGray);
        gr.setLineWidth(5);
        gr.fill(startRect);
        gr.fill(exitRect);
        gr.setColor(Color.black);
        gr.draw(startRect);
        gr.draw(exitRect);
        gr.drawString("\"ESPACE\" pour commencer", 215,230);
        gr.drawString("\"ECHAPE\" pour quitter", 223,315);
        gr.drawString("X:"+mouseX+"\nY:"+mouseY,50,50);

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
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        Input input= gameContainer.getInput();
    }
}