package states;

import gui.TurretMenu;
import maps.Map;
import obj.Turret;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.ArrayList;

/**
 * Singleton qui contiendra toutes les données relatives à la partie: pv, argent, bonus, tourelles selectionnées, posées...
 * Created by tic-tac on 26/03/17.
 */
public class GameConfig {

    //       ATTRIBUTS       //
    private static GameConfig instance;
    private StateBasedGame stateBasedGame;
    private GameContainer gc;
    private int money; //TODO: combien d'argent au début?
    private String playerName;
    private Turret selectedTurret;
    private Map map;
    private ArrayList<Turret> usableTurrets;
    private TurretMenu turretMenu;

    private GameConfig(StateBasedGame sbg) throws SlickException, FileNotFoundException, URISyntaxException {
        money=100;
        stateBasedGame=sbg;
        map=new Map(stateBasedGame, 15);
        usableTurrets=new ArrayList<>();
    }

    /**
     * Methode qui vérifie si l'objet a déjà été instancié, si non l'instancie, puis renvoie l'objet déjà instancié
     * @return  config du jeu
     * @throws SlickException
     */
    public static GameConfig getInstance(StateBasedGame sbg) throws SlickException{
        if (instance==null){
            try {
                instance=new GameConfig(sbg);
            } catch (FileNotFoundException | URISyntaxException e) {
                e.printStackTrace();
            }
        }
        return GameConfig.instance;
    }


    //       LES GETTERS       //
    public int getMoney() {return money;}
    public String getPlayerName(){return playerName;}
    public Turret getTurret(){return selectedTurret;}
    public Map getMap(){return map;}
    public ArrayList<Turret> getUsableTurrets(){return usableTurrets;}
    public TurretMenu getTurretMenu() {return turretMenu;}

    //       LES SETTERS       //
    public void setMoney(int money) {this.money = money;}
    public void setPlayerName(String name){this.playerName=name;}
    public void setSelectedTurret(Turret turret){this.selectedTurret=turret;}
    public void setMap(Map map){this.map=map;}
    public void setTurretMenu(GameContainer gameContainer) throws SlickException, FileNotFoundException, URISyntaxException {
        this.turretMenu=new TurretMenu(stateBasedGame, gameContainer);
    }
    /**
     * Ajoute une tourelle à la liste des tourelles utilisables
     * @param turret
     */
    public void addUsableTurret(Turret turret) throws FileNotFoundException, SlickException, URISyntaxException {for(int i = 0; i<8; i++){this.usableTurrets.add(new Turret(turret.getType(), turret.getPos(), stateBasedGame));}}

}
