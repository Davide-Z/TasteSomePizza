package gui.Buttons;

import org.newdawn.slick.Color;
import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.RoundedRectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.StateBasedGame;
import states.GameConfig;

import java.awt.Font;

/**
 * Created by tic-tac on 14/02/17.
 */
public class StateButton extends MouseOverArea{
    private int width;
    private int height;
    private int x;
    private int y;
    private boolean over;
    private String text;
    private Shape hitbox;
    private StateBasedGame game;
    private GameContainer container;
    private String action;
    private GameConfig config;
    private Font font;
    private TrueTypeFont ttf;

    public StateButton(StateBasedGame sbg, Image image, int x, int y, String text, String action) throws SlickException {
        super(sbg.getContainer(), image, x, y);
        this.x=x;
        this.y=y;
        this.game=sbg;
        this.width=image.getWidth();
        this.height=image.getHeight();
        this.action=action;
        this.text=text;
        this.hitbox = new RoundedRectangle(x+3,y+7,image.getWidth()-14,image.getHeight()-20,13);
        this.container=game.getContainer();
        this.config = GameConfig.getInstance(sbg);
    }

    public void render(Graphics g) {
        this.render(container, g);
        g.setColor(Color.white);
        g.drawString(text, (int)(x + hitbox.getWidth() / 2 -60), (int)(y + hitbox.getHeight() / 2-20)); //Affiche le texte du bouton, centré
        g.setLineWidth(1);
        g.draw(hitbox);
    }
    @Override
    public void mousePressed(int button, int mx, int my){
        over=hitbox.contains(mx,my);
        super.mousePressed(button, mx, my);
        if (over) {
            if((action.matches("quit"))&&(game.getCurrentStateID()==0)){       //quitter le jeu
                System.exit(0);
            }
            else if((action.matches("start"))&&(game.getCurrentStateID()==0)){//aller à l'écran de jeu
                game.enterState(1);
            }
            else if((action.matches("menu"))&&(game.getCurrentStateID()==1 | game.getCurrentStateID()==0)){    //aller au menu
                game.enterState(0);
            }
            else if((action.matches("wave !"))&&(game.getCurrentStateID()==1)){ //Début de vague
                game.enterState(2);
            }
            else if((action.matches("turret"))&&(game.getCurrentStateID()==1)){
                config.getTurretMenu().turretMode=!config.getTurretMenu().turretMode;
            }
        }
    }
}
