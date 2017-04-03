package gui.Buttons;

import gui.FileLoader;
import maps.Vec;
import obj.Enemy;
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
    private Enemy enemy;
    private boolean clicked=false;
    int pass=0;
    public TurretButton(StateBasedGame sbg, GameContainer container, Image image, int x, int y, Turret turret) throws SlickException, FileNotFoundException, URISyntaxException {
        super(container, FileLoader.getImage("vide.png"), x, y);
        this.x=x;
        this.y=y;
        this.game=sbg;
        this.container=sbg.getContainer();
        this.graphics=container.getGraphics();
        this.turret=new Turret(turret, new Vec(x,y));
        config=GameConfig.getInstance(sbg);
        this.hitbox=new Rectangle(this.x,this.y,152,160);
        System.out.println(x+ " "+y);
    }
    public TurretButton(StateBasedGame sbg, GameContainer container, Image image, int x, int y, Enemy enemy) throws SlickException, FileNotFoundException, URISyntaxException {
        super(container, FileLoader.getImage("vide.png"), x, y);
        this.x=x;
        this.y=y;
        this.game=sbg;
        this.container=sbg.getContainer();
        this.graphics=container.getGraphics();
        this.enemy=new Enemy(enemy, new Vec(x,y));
        config=GameConfig.getInstance(sbg);
        this.hitbox=new Rectangle(this.x,this.y,152,160);
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
        /*
        over=this.hitbox.contains(mx, my);
        if(over && pass<1){
            if(config.getTurretMenu().turretMode){
                config.setSelectedTurret(this.turret);
                config.setSelectedEnemy(null);
                System.out.println("turret null? "+(this.turret==null));
            }
            else{
                config.setSelectedEnemy(this.enemy);
                config.setSelectedTurret(null);
                System.out.println("enemy null? "+(this.enemy==null));
            }
            //config.getMap().addClickedButtons(this);
            //clicked=true;
            pass++;
        }
        else if(over && pass>=2){
            pass=0;
        }
        System.out.println(pass);
        */
    }

    public void setTurret(Turret turret){
        this.turret=turret;
    }

    public Turret getTurret(){
        return this.turret;
    }
    public void render(){
        if(turret != null && config.getTurretMenu().turretMode) {
            graphics.setLineWidth(1);
            graphics.setColor(Color.lightGray);
            graphics.fill(hitbox);
            graphics.setColor(Color.black);
            graphics.draw(hitbox);
            this.turret.render();
        }
        else if(enemy != null && !config.getTurretMenu().turretMode){
            graphics.setLineWidth(1);
            graphics.setColor(Color.lightGray);
            graphics.fill(hitbox);
            graphics.setColor(Color.black);
            graphics.draw(hitbox);
            this.enemy.render();
        }
    }

    public void update() {
        over = this.hitbox.contains(config.getMx(), config.getMy());
        if(over && game.getCurrentState().getID()==1){
            if (config.isMouseClicked() && config.isMouseReleased) {
                if (config.getTurretMenu().turretMode) {
                    if(config.getTurret()==null){
                        config.setSelectedTurret(this.turret);
                    }
                    else if(config.getTurret().getType()!=this.turret.getType()){
                        config.setSelectedTurret(this.turret);
                    }
                    else if(config.getTurret().getType()==this.turret.getType()){
                        config.setSelectedTurret(null);
                    }
                    config.setSelectedEnemy(null);
                } else {
                    if(config.getEnemy()==null){
                        config.setSelectedEnemy(this.enemy);
                        config.setSelectedTurret(null);
                    }
                    else if(config.getEnemy().getType()!=this.enemy.getType()){
                        config.setSelectedEnemy(this.enemy);
                        config.setSelectedTurret(null);
                    }
                    else if(config.getEnemy().getType()==this.enemy.getType()){
                        config.setSelectedEnemy(null);
                        config.setSelectedTurret(null);
                    }
                }
                config.isMouseReleased=false;
                //config.getMap().addClickedButtons(this);
                //clicked=true;
            }
        }
    }

    public void reset(){clicked=false;}
}
