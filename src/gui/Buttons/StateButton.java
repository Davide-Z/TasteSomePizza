package gui.Buttons;

import gui.FileLoader;
import gui.Timer;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.RoundedRectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.StateBasedGame;
import states.GameConfig;
import states.WaveState;

/**
 * Created by tic-tac on 14/02/17.
 */
public class StateButton{
    private String imagePath;
    private Image currentImage;
    private Image baseImage;
    private Image overImage;
    private Image mask;
    private int x;
    private int y;
    private boolean over;
    private String text;
    private Shape hitbox;
    private String action;

    public StateButton(String imagePath, int x, int y, String text, String action) throws SlickException {
        this.x=x;
        this.y=y;
        this.action=action;
        this.text=text;
        this.imagePath=imagePath;
        this.baseImage=FileLoader.getInterfaceImage(imagePath);
        this.hitbox = new RoundedRectangle(x,y,baseImage.getWidth()+1,baseImage.getHeight()+2,12);
        if(action.equals("start") | action.equals("quit") | action.equals("settings")) {
            overImage=FileLoader.getInterfaceImage(imagePath+"_over");
            mask = FileLoader.getInterfaceImage(imagePath + "_mask");
        }
        currentImage=baseImage;
    }

    public void render(Graphics g) throws SlickException{
        if(over && (action.equals("start") | action.equals("quit") | action.equals("settings"))){
            currentImage=overImage;
        }
        else{
           currentImage=baseImage;
        }
        currentImage.draw(x,y);
        g.setColor(Color.white);
        if(text!=null) {
            g.drawString(text, (int) (x + hitbox.getWidth() / 2 - 60), (int) (y + hitbox.getHeight() / 2 - 20)); //Affiche le texte du bouton, centré
        }
        //g.setLineWidth(1);
       // g.draw(hitbox);
    }
    
    public void update(StateBasedGame game, GameConfig config){
        if(action.equals("start") | action.equals("quit") | action.equals("settings")) { //Si c'est des boutons pour lesquels un masque est prévu, on s'en sert
            over = (this.hitbox.contains(config.getMx(), config.getMy())) && (this.mask.getColor(config.getMx()-this.x, config.getMy()-this.y).equals(Color.white));
        }
        else{
            over = this.hitbox.contains(config.getMx(), config.getMy());
        }
            if ( over && config.isMouseClicked() && config.wasMouseReleased) {//Si le bouton est appuyé & n'était pas appuyé juste avant
                if((action.matches("quit"))&&(game.getCurrentStateID()==0)){       //quitter le jeu
                    System.exit(0);
                }
                else if((action.matches("start"))&&(game.getCurrentStateID()==0)){//aller à l'écran de jeu
                    config.setTimer(new Timer(System.currentTimeMillis()));
                    game.enterState(1);
                }
                else if((action.matches("menu"))&&(game.getCurrentStateID()==1 | game.getCurrentStateID()==2)){    //aller au menu
                    game.enterState(0);
                }
                else if((action.matches("wave"))&&(game.getCurrentStateID()==1)){ //Début de vague
                    ((WaveState) game.getState(2)).setHasBegun(true);
                	game.enterState(2);
                }
                else if((action.matches("settings"))&&(game.getCurrentStateID()==0)){
                    game.enterState(3);
                }
                config.wasMouseReleased =false; //le bouton n'est plus appuyé
        }
    }
}
