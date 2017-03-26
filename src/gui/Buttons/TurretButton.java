package gui.Buttons;

import obj.Turret;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.StateBasedGame;
import states.GameConfig;

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


    public TurretButton(StateBasedGame sbg, Image image, int x, int y, Turret turret) throws SlickException{
        super(sbg.getContainer(), image, x, y);
        this.container=sbg.getContainer();
        this.graphics=container.getGraphics();
        this.turret=turret;
        config=GameConfig.getInstance(sbg);
        this.hitbox=new Rectangle(x,y,152,90);
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
            if(config.getTurret()!=null){
                config.setSelectedTurret(this.turret);
            }
            else if(config.getTurret()==this.turret){
                config.setSelectedTurret(null);
            }
        }
        System.out.println(config.getTurret().toString());
    }

    public void setTurret(Turret turret){
        this.turret=turret;
    }

    public void render(){
        graphics.setColor(Color.lightGray);
        graphics.fill(hitbox);
        graphics.setColor(Color.black);
        graphics.draw(hitbox);
    }

    public void update(){

    }
}
