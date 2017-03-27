package gui.Buttons;

import gui.FileLoader;
import obj.Turret;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.StateBasedGame;
import states.GameConfig;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;

/**
 * Created by tic-tac on 08/03/17.
 */
public class TurretButton extends MouseOverArea {
    //      Attributs du moteur     //
    private GameContainer container;
    private StateBasedGame game;
    private Graphics graphics;
    private GameConfig config;

    //      Attributs propres       //
    private int x;
    private int y;
    private boolean over;
    private String text;
    private Shape hitbox;
    private Turret turret;


    public TurretButton(StateBasedGame sbg, GameContainer container, Image image, int x, int y, Turret turret) throws SlickException, FileNotFoundException, URISyntaxException {
        super(container, FileLoader.getImage("vide.png"), x, y);
        this.x=x;
        this.y=y;
        this.game=sbg;
        this.container=sbg.getContainer();
        this.graphics=container.getGraphics();
        this.turret=turret;
        config=GameConfig.getInstance(sbg);
        this.hitbox=new Rectangle(x,y,152,160);
        System.out.println(x+ " "+y);
    }

    /**
     * Si on clique dessus, on sélectionne la tourelle de ce bouton
     * si on reclique, on déselectionne la tourelle déjà choisie
     * @param button
     * @param mx
     * @param my
     */
    public void mousePressed(int button, int mx, int my){
        over=hitbox.contains(mx,my);
        super.mousePressed(button, mx, my);
        if(over){
            if(config.getTurret()!=this.turret){
                config.setSelectedTurret(this.turret);
            }
            else if(config.getTurret()==this.turret){
                config.setSelectedTurret(null);
            }
        }
    }

    public void setTurret(Turret turret){
        this.turret=turret;
    }

    public void render(){
        graphics.setLineWidth(1);
        graphics.setColor(Color.lightGray);
        graphics.fill(hitbox);
        graphics.setColor(Color.black);
        graphics.draw(hitbox);

        this.turret.render(x, y);
    }

    public void update(){

    }
}
