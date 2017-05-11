package gui;

import gui.Buttons.TurretButton;
import obj.Enemy;
import obj.Turret;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import states.GameConfig;

import java.util.ArrayList;

/**
 * Created by tic-tac on 26/03/17.
 */
public class TurretMenu {
    //Attributs du moteur
    private GameContainer container;
    private StateBasedGame game;
    private GameConfig config;

    //Si true, mode pose de tourelles, si false, pose d'ennemis
    public boolean turretMode=true;

    private ArrayList<TurretButton> turretButtons;
    private ArrayList<Turret> usableTurrets;

    public TurretMenu(StateBasedGame sbg, GameContainer gameContainer) throws SlickException{
        game = sbg;
        turretButtons = new ArrayList<>();
        config = GameConfig.getInstance(sbg);
        usableTurrets = config.getUsableTurrets();
        int i = 0;
        int j = 0;
        for (Turret t : usableTurrets) {
            turretButtons.add(new TurretButton(724 + j * 148, 1 + i * 160, t)); //On crée un bouton
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
