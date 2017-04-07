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
public class StateButton{
    private final Image image;
    private int x;
    private int y;
    private boolean over;
    private String text;
    private Shape hitbox;
    private String action;

    public StateButton(Image image, int x, int y, String text, String action) throws SlickException {
        this.x=x;
        this.y=y;
        this.action=action;
        this.text=text;
        this.image=image;
        this.hitbox = new RoundedRectangle(x,y,image.getWidth()+1,image.getHeight()+2,12);
    }

    public void render(Graphics g) {
        image.draw(x,y);
        g.setColor(Color.white);
        if(text!=null) {
            g.drawString(text, (int) (x + hitbox.getWidth() / 2 - 60), (int) (y + hitbox.getHeight() / 2 - 20)); //Affiche le texte du bouton, centré
        }
        g.setLineWidth(1);
        g.draw(hitbox);
    }
    
    public void update(StateBasedGame game, GameConfig config){
        over = this.hitbox.contains(config.getMx(), config.getMy());
            if ( over && config.isMouseClicked() && config.wasMouseReleased) {
                if((action.matches("quit"))&&(game.getCurrentStateID()==0)){       //quitter le jeu
                    System.exit(0);
                }
                else if((action.matches("start"))&&(game.getCurrentStateID()==0)){//aller à l'écran de jeu
                    game.enterState(1);
                }
                else if((action.matches("menu"))&&(game.getCurrentStateID()==1 | game.getCurrentStateID()==0 | game.getCurrentStateID()==2)){    //aller au menu
                    game.enterState(0);
                }
                else if((action.matches("wave"))&&(game.getCurrentStateID()==1)){ //Début de vague
                    game.enterState(2);
                }
                else if((action.matches("turret"))&&(game.getCurrentStateID()==1)){
                    config.getTurretMenu().turretMode=!config.getTurretMenu().turretMode;
                }
                config.wasMouseReleased =false;
        }
    }
}
