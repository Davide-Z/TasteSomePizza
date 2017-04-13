package gui.Buttons;

import maps.Vec;
import obj.Enemy;
import obj.Turret;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.StateBasedGame;
import states.GameConfig;

/**
 * Created by tic-tac on 08/03/17.
 */
public class TurretButton {
    //      Attributs propres       //
    private int x;
    private int y;
    private boolean over;
    private String text;
    private Shape hitbox;
    private Turret turret;
    private Enemy enemy;

    public TurretButton(int x, int y, Turret turret) throws SlickException{
        this.x=x;
        this.y=y;
        this.turret=new Turret(turret, new Vec(x,y));
        this.hitbox=new Rectangle(this.x,this.y,152,160);
    }
    public TurretButton(int x, int y, Enemy enemy) throws SlickException{
        this.x=x;
        this.y=y;
        this.enemy=new Enemy(enemy, new Vec(x,y));
        this.hitbox=new Rectangle(this.x,this.y,152,160);
    }

    public void setTurret(Turret turret){
        this.turret=turret;
    }

    public Turret getTurret(){
        return this.turret;
    }

    public void render(Graphics graphics, GameConfig config){
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

    public void update(StateBasedGame game, GameConfig config) {
        over = this.hitbox.contains(config.getMx(), config.getMy());
        if(over && game.getCurrentState().getID()==1){
            if (config.isMouseClicked() && config.wasMouseReleased) {
                if (config.getTurretMenu().turretMode) {
                    if(config.getTurret()==null){
                        config.setSelectedTurret(this.turret);
                    }
                    else if(config.getTurret().getTypeId()!=this.turret.getTypeId()){
                        config.setSelectedTurret(this.turret);
                    }
                    else if(config.getTurret().getTypeId()==this.turret.getTypeId()){
                        config.setSelectedTurret(null);
                    }
                    config.setSelectedEnemy(null);
                } else {
                    if(config.getEnemy()==null){
                        config.setSelectedEnemy(this.enemy);
                        config.setSelectedTurret(null);
                    }
                    else if(config.getEnemy().getTypeId()!=this.enemy.getTypeId()){
                        config.setSelectedEnemy(this.enemy);
                        config.setSelectedTurret(null);
                    }
                    else if(config.getEnemy().getTypeId()==this.enemy.getTypeId()){
                        config.setSelectedEnemy(null);
                        config.setSelectedTurret(null);
                    }
                }
                config.wasMouseReleased =false;
            }
        }
    }
}
