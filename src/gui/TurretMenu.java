package gui;

import gui.Buttons.TurretButton;
import obj.Turret;
import obj.enums.TurretType;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import states.GameConfig;

import java.util.ArrayList;

/**
 *
 * Created by tic-tac on 26/03/17.
 */
public class TurretMenu {
    //Attributs du moteur
    private GameConfig config;

    //Si true, mode pose de tourelles, si false, pose d'ennemis
    private ArrayList<TurretButton> turretButtons;

    public TurretMenu(StateBasedGame game) throws SlickException{
        turretButtons = new ArrayList<>();
        config = GameConfig.getInstance();
        int i = 0;
        int j = 0;
        for (TurretType t : TurretType.values()) {
            turretButtons.add(new TurretButton(724 + j * 148, 1 + i * 160, new Turret(t, game))); //On crée un bouton
            j++;
            if (j >= 2) {
                i++;
                j = 0;
            }
        }
    }

    public void render(Graphics g){
        for (TurretButton b : turretButtons) {
            b.render(g, config);
        }
    }

    public void update(StateBasedGame game){
        for (TurretButton b : turretButtons) {
            b.update(game, config);
        }
    }
}
