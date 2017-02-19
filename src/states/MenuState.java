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
import org.newdawn.slick.util.ResourceLoader;

import java.awt.*;
import java.awt.Font;
import java.io.IOException;
import java.io.InputStream;

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


    private SpriteSheet piz;
    private Animation animPiz;

    int mouseX;
    int mouseY;

    int winHeight;
    int winWidth;
    int space=25;

    Font font;
    TrueTypeFont ttf;

    @Override
    public int getID() {
        return 0;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        this.container=gc;
        this.game=sbg;
        InputStream inputStream=ResourceLoader.getResourceAsStream("./resources/fly_n_walk.ttf");
        try {
            font=Font.createFont(Font.TRUETYPE_FONT,inputStream);
            font=font.deriveFont(font.getSize()*45f);
            ttf=new TrueTypeFont(font, true);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        winHeight=gc.getHeight();
        winWidth=gc.getWidth();
        startRect = new Rectangle(winWidth/2-125,winHeight/2-25,250,50);
        exitRect = new Rectangle(winWidth/2-125,winHeight/2-125+space,250,50);
        imgBouton = new Image("resources/interface/boutonOrange.png");
        startButton = new StateButton(gc, game, imgBouton, winWidth/2-152,winHeight/2-55, "Demarrer", "start");
        exitButton=new StateButton(gc, game, imgBouton, winWidth/2-152,winHeight/2+50, "Quitter", "quit");

        piz=new SpriteSheet("resources/sprites/Piz.png", 256,256);
        animPiz= new Animation(piz,75);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.setBackground(Color.lightGray);
        g.setLineWidth(5);
        g.setColor(Color.white);
        startButton.render(g);
        exitButton.render(g);
        g.drawString("X:"+(int)mouseX+"\nY:"+(int)mouseY,0,winHeight-35);
        g.drawString(winWidth+"x"+winHeight, winWidth-73,0);
        g.setFont(ttf);
        g.drawString("Taste Some Pizza !", winWidth/2-250, 100);
        if(gc.getInput().isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)){
            g.setColor(Color.white);
            int x=gc.getInput().getMouseX();
            int y=gc.getInput().getMouseY();

            g.drawRect(x,y,32,32);
        }
        animPiz.draw(155,238);
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