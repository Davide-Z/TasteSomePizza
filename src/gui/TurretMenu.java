package gui;

import gui.Buttons.TurretButton;
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
    //Attributs du moteur
    private GameContainer container;
    private StateBasedGame game;
    private GameConfig config;

    private ArrayList<TurretButton> buttons;
    private ArrayList<Turret> usableTurrets;

    public TurretMenu(StateBasedGame sbg, GameContainer gameContainer) throws SlickException, FileNotFoundException, URISyntaxException {
        game=sbg;
        container=gameContainer;
        buttons=new ArrayList<>();
        config = GameConfig.getInstance(sbg);
        usableTurrets=config.getUsableTurrets();
        int i=0;
        int j=0;
        for(Turret t : usableTurrets){
            buttons.add(new TurretButton(game ,container, null, 724+j*148, 1+i*160, t)); //On crée un bouton
            j++;
            if(j>=2){
                i++;
                j=0;
            }
        }
    }

    public void render(){
        for(TurretButton b : buttons){
            b.render();
        }
    }

    public void update(){
        for(TurretButton b : buttons){
            b.update();
        }
    }

}
