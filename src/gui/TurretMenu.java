package gui;

import gui.Buttons.TurretButton;
import obj.Enemy;
import obj.Turret;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import states.GameConfig;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.ArrayList;

/**
 * Created by tic-tac on 26/03/17.
 */
public class TurretMenu {
    private final ArrayList<Enemy> usableEnemies;
    //Attributs du moteur
    private GameContainer container;
    private StateBasedGame game;
    private GameConfig config;

    //Si true, mode pose de tourelles, si false, pose d'ennemis
    public boolean turretMode=true;

    private ArrayList<TurretButton> turretButtons;
    private ArrayList<TurretButton> enemyButtons;
    private ArrayList<Turret> usableTurrets;

    public TurretMenu(StateBasedGame sbg, GameContainer gameContainer) throws SlickException, FileNotFoundException, URISyntaxException {
        game = sbg;
        container = gameContainer;
        turretButtons = new ArrayList<>();
        enemyButtons = new ArrayList<>();
        config = GameConfig.getInstance(sbg);
        usableTurrets = config.getUsableTurrets();
        usableEnemies = config.getUsableEnemies();
        int i = 0;
        int j = 0;
        for (Turret t : usableTurrets) {
            turretButtons.add(new TurretButton(game, container, null, 724 + j * 148, 1 + i * 160, t)); //On crée un bouton
            j++;
            if (j >= 2) {
                i++;
                j = 0;
            }
        }
        i = 0;
        j = 0;
        for (Enemy e : usableEnemies) {
            enemyButtons.add(new TurretButton(game, container, null, 724 + j * 148, 1 + i * 160, e));
            j++;
            if (j >= 2) {
                i++;
                j = 0;
            }
        }
    }

    public void render(){
        if (turretMode) {
            for (TurretButton b : turretButtons) {
                b.render();
            }
        }
        else{
            for (TurretButton b : enemyButtons){
                b.render();
            }
        }
    }

    public void update(){
        if (turretMode) {
            for (TurretButton b : turretButtons) {
                b.update();
            }
        }
        else{
            for (TurretButton b : enemyButtons){
                b.update();
            }
        }
    }

}
