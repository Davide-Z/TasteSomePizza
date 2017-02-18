package states;
import gui.StateButton;
import maps.Case;
import maps.Map;
import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Ellipse;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.RoundedRectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.awt.*;
import java.util.ArrayList;

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
        hitbox=new RoundedRectangle(winWidth-200,winHeight-50,200,50, 10);
        stateButton = new StateButton(container, sbg, winWidth-200,winHeight-50, "Menu principal", "menu", hitbox);
        map=new Map(gc, sbg, 15);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        //g.setBackground(Color.decode("0xdba24f"));
        turret.draw(winWidth/2-32,winHeight/2-32);
        turret.setRotation(alpha);
        g.setColor(Color.white);
        g.drawString(""+(alpha), 500,0);
        g.drawString("X:"+(int)mouseX+"\nY:"+(int)mouseY,0,winHeight-35);
        g.drawString(winWidth+"x"+winHeight, winWidth-73,0);
        stateButton.render(g);

        //Segmentation temporaire de l'écran
        g.setColor(Color.white);
        g.setLineWidth(4);
        g.drawLine(winWidth*0.703125f,0,winWidth*0.703125f, winHeight);
        g.drawString("Tourelles/menu",winWidth*0.72f, winHeight*0.01f);
        g.drawString("Carte",winWidth*0.1f, winHeight*0.01f);

        map.render();
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        alpha=(float)((Math.atan2((mouseX-winWidth/2),-(mouseY-winHeight/2))*360/(2*Math.PI)));
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