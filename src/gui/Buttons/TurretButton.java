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

    public TurretButton(int x, int y, Turret turret) throws SlickException{
        this.x=x;
        this.y=y;
        this.turret=turret;
        this.turret.setPos(new Vec(x, y));
        //this.turret=new Turret(turret.getSbg(), new Vec(x,y));
        this.hitbox=new Rectangle(this.x,this.y,152,160);
    }

    public void setTurret(Turret turret){
        this.turret=turret;
    }

    public Turret getTurret(){
        return this.turret;
    }

    public void render(Graphics graphics, GameConfig config) {
        if (turret != null) {
            graphics.setLineWidth(1);
            graphics.setColor(Color.lightGray);
            graphics.fill(hitbox);
            graphics.setColor(Color.black);
            graphics.draw(hitbox);
            this.turret.render();
            graphics.drawString("Price : " + this.turret.getBuyPrice(), x + 5, y + 50);
            graphics.drawString("Damage : " + this.turret.getDamage(), x + 5, y + 70);
            graphics.drawString("Range : " + this.turret.getRange(), x + 5, y + 90);
            graphics.drawString("Fire Rate : " + this.turret.getFireRate(), x + 5, y + 110);
            graphics.drawString("Turret Type : " + this.turret.getTypeId(), x + 5, y + 130);
            
        }
    }

    public void update(StateBasedGame game, GameConfig config) {
        over = this.hitbox.contains(config.getMx(), config.getMy());
        if(over && game.getCurrentState().getID()==1){
            if (config.isMouseClicked() && config.wasMouseReleased) {
                if(config.getTurret()==null){
                    config.setSelectedTurret(this.turret);
                }
                else if(config.getTurret().getTypeId()!=this.turret.getTypeId()){
                    config.setSelectedTurret(this.turret);
                }
                else if(config.getTurret().getTypeId()==this.turret.getTypeId()){
                    config.setSelectedTurret(null);
                }
                config.wasMouseReleased =false;
            }
        }
    }
}
