package states;

import gui.StateButton;
import maps.Map;
import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.RoundedRectangle;
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

    StateButton stateButton;
    RoundedRectangle hitbox;
    float mouseX;
    float mouseY;

    Map map;

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
        stateButton = new StateButton(container, sbg, winWidth-201,winHeight-51, "Menu principal", "menu");
        map=new Map(gc, sbg, 15);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.setBackground(Color.decode("0xdba24f"));
        turret.draw(winWidth-184,winHeight/2-32);
        turret.setRotation(alpha);
        g.setColor(Color.white);
        g.drawString(""+(alpha), 500,0);
        g.drawString("X:"+(int)mouseX+"\nY:"+(int)mouseY,0,winHeight-35);
        g.drawString(winWidth+"x"+winHeight, winWidth-73,0);
        stateButton.render(g);

        //Segmentation temporaire de l'écran
        g.setColor(Color.black);
        g.setLineWidth(4);
        g.drawLine(2+winWidth*0.703125f,0,2+winWidth*0.703125f, winHeight);
        g.drawString("Tourelles/menu",winWidth*0.703125f, 0);


        map.render();

        g.setColor(Color.black);

        g.drawString("Carte",0, 0);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        alpha=(float)((Math.atan2((mouseX+184-winWidth),-(mouseY+32-winHeight/2))*360/(2*Math.PI)));
        map.resetClicked();
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