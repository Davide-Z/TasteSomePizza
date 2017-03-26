package gui;

import gui.Buttons.TurretButton;
import obj.Turret;
import org.newdawn.slick.GameContainer;
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

    private ArrayList<TurretButton> buttons;
    private ArrayList<Turret> usableTurrets;

    public TurretMenu(StateBasedGame sbg) throws SlickException{
        System.out.println("1");
        buttons=new ArrayList<>();
        System.out.println("2");
        config = GameConfig.getInstance(sbg);
        System.out.println("3");
        usableTurrets=config.getUsableTurrets();
        System.out.println("4");
        int i=0;
        int j=0;
        for(Turret t : usableTurrets){
            buttons.add(new TurretButton(sbg, null, (int)(container.getWidth()*0.703125f)+j*152, i*90, t)); //On crée un bouton
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
