package states;

import gui.StateButton;
import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.RoundedRectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import java.awt.Font;

/**
 * Created by tic-tac on 01/02/17.
 */
public class MenuState extends BasicGameState {

    private GameContainer container;
    private StateBasedGame game;
    private Rectangle startRect;
    Rectangle exitRect;
    Image imgBouton;
    RoundedRectangle startHitbox;
    RoundedRectangle exitHitbox;
    StateButton startButton;
    StateButton exitButton;

    int mouseX;
    int mouseY;

    int winHeight;
    int winWidth;
    int space=25;

    Font font = new Font("Arial", Font.BOLD, 48);
    TrueTypeFont ttf=new TrueTypeFont(font, true);

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
        imgBouton = new Image("resources/interface/boutonOrange.png");
        startHitbox=new RoundedRectangle(winWidth/2-150,winHeight/2-50, imgBouton.getWidth()-14, imgBouton.getHeight()-20,13);
        startButton = new StateButton(gc, game, imgBouton, winWidth/2-152,winHeight/2-55, "Demarrer", "start", startHitbox);
        exitHitbox= new RoundedRectangle(winWidth/2-150,winHeight/2+55, imgBouton.getWidth()-14, imgBouton.getHeight()-20,13);
        exitButton=new StateButton(gc, game, imgBouton, winWidth/2-152,winHeight/2+50, "Quitter", "quit", exitHitbox);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.setBackground(Color.black);
        g.setLineWidth(5);
        g.setColor(Color.white);
        startButton.render(g);
        exitButton.render(g);
        g.draw(startHitbox);
        g.draw(exitHitbox);
        g.drawString("X:"+(int)mouseX+"\nY:"+(int)mouseY,0,winHeight-35);
        g.drawString(winWidth+"x"+winHeight, winWidth-73,0);
        g.setFont(ttf);
        g.drawString("Taste Some Pizza !", winWidth/2-250, 100);
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