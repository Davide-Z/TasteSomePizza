package gui;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.GUIContext;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Created by tic-tac on 14/02/17.
 */
public class StateButton extends MouseOverArea{
    protected int width;
    protected int height;
    protected int x;
    protected int y;
    protected int mouseX;
    protected int mouseY;
    protected boolean pressed;
    protected boolean over;
    protected String text;
    protected Shape hitbox;
    protected Image image;
    protected StateBasedGame game;
    protected String action;
    protected String vide="resources/vide.png";

    public StateButton(GUIContext container, StateBasedGame sbg, Image image, int x, int y,String text, String action, Shape hitbox) throws SlickException {
        super(container, image, x, y);
        this.image=image;
        this.x=x;
        this.y=y;
        this.game=sbg;
        this.width=image.getWidth();
        this.height=image.getHeight();
        this.action=action;
        this.text=text;
        this.hitbox = hitbox;
    }

    public StateButton(GUIContext container, StateBasedGame sbg, int x, int y, String text, String action, Shape hitbox) throws SlickException {
        super(container, new Image("resources/vide.png"), x, y);
        this.x=x;
        this.y=y;
        this.game=sbg;
        image = new Image(vide);
        this.width=(int)hitbox.getWidth();
        this.height=(int)hitbox.getHeight();
        this.action=action;
        this.text=text;
        this.hitbox = hitbox;
    }

    public void render(Graphics g){
        if(image.getResourceReference().matches(vide)){
            this.render(container,g);
            g.setColor(Color.white);
            g.fill(hitbox);
            g.setColor(Color.black);
            g.drawString(action, x+hitbox.getWidth()/2-20, y+hitbox.getHeight()/2-10);
        }
        else {
            this.render(container, g);
            g.setColor(Color.white);
            g.drawString(action, x+hitbox.getWidth()/2-20, y+hitbox.getHeight()/2- 10);
        }
    }
    @Override
    public void mousePressed(int button, int mx, int my){
        over=hitbox.contains(mx,my);
        super.mousePressed(button, mx, my);
        if (over) {
            if(action.matches("quit")){       //quitter le jeu
                System.exit(0);
            }
            else if(action.matches("start")){//aller à l'écran de jeu
                game.enterState(1);
            }
            else if(action.matches("menu")){    //aller au menu
                game.enterState(0);
            }
            else if(action.matches("wave !")); //Début de vague
        }
    }
}
