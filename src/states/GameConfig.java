package states;

import gui.TurretMenu;
import maps.Map;
import obj.Enemy;
import obj.Turret;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
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
    private boolean turretIsSelected=false;
    private Turret selectedTurret;
    private Enemy selectedEnemy;
    private Map map;
    private ArrayList<Turret> usableTurrets;
    private TurretMenu turretMenu;
    private ArrayList<Enemy> usableEnemies;
    //Position de la souris
    private int mx=0;
    private int my=0;
    private boolean mouseClicked=false;
    public long clickPing=0;

    private GameConfig(StateBasedGame sbg) throws SlickException{
        money=100;
        stateBasedGame=sbg;
        gc=sbg.getContainer();
        map=new Map(stateBasedGame, 15, this);
        usableTurrets=new ArrayList<>();
        usableEnemies=new ArrayList<>();
    }

    /**
     * Methode qui vérifie si l'objet a déjà été instancié, si non l'instancie, puis renvoie l'objet déjà instancié
     * @return  config du jeu
     * @throws SlickException
     */
    public static GameConfig getInstance(StateBasedGame sbg) throws SlickException{
        if (instance==null){
            instance=new GameConfig(sbg);
        }
        return GameConfig.instance;
    }


    //       LES GETTERS       //
    public int getMoney() {return money;}
    public String getPlayerName(){return playerName;}
    public Turret getTurret(){return selectedTurret;}
    public Enemy getEnemy(){return selectedEnemy;}
    public Map getMap(){return map;}
    public ArrayList<Turret> getUsableTurrets(){return usableTurrets;}
    public TurretMenu getTurretMenu() {return turretMenu;}
    public boolean isTurretSelected(){return this.turretIsSelected;}
    //       LES SETTERS       //
    public void setMoney(int money) {this.money = money;}
    public void setPlayerName(String name){this.playerName=name;}
    public void setSelectedTurret(Turret turret){this.selectedTurret=turret;}
    public void setSelectedEnemy(Enemy enemy){this.selectedEnemy=enemy;}
    public void setMap(Map map){this.map=map;}
    public void setTurretMenu(GameContainer gameContainer) throws SlickException, FileNotFoundException, URISyntaxException {
        this.turretMenu=new TurretMenu(stateBasedGame, gameContainer);
    }
    /**
     * Ajoute une tourelle à la liste des tourelles utilisables
     * @param turret
     */
    public void addUsableTurret(Turret turret) throws FileNotFoundException, SlickException, URISyntaxException {
        for(int i = 0; i<1; i++){
            this.usableTurrets.add(new Turret(turret, null));
        }
        this.usableEnemies.add(new Enemy(0, null, 1,10,10,stateBasedGame));
    }
    public void setTurretIsSelected(boolean setting){this.turretIsSelected=setting;}

    public ArrayList<Enemy> getUsableEnemies() {
        return usableEnemies;
    }
/*
    public void updateMouse() {
        this.mx= Mouse.getX();
        this.my= gc.getHeight()-Mouse.getY();
        this.mouseClicked= Mouse.isButtonDown(0);
    }
*/
    public int getMx() {
        mx=Mouse.getX();
        return mx;
    }

    public int getMy() {
        my=gc.getHeight()-Mouse.getY();
        return my;
    }

    public boolean isMouseClicked() {
        mouseClicked=Mouse.isButtonDown(Input.MOUSE_LEFT_BUTTON);
        if(!mouseClicked){
            this.clickPing=System.currentTimeMillis();
        }
        return mouseClicked;
    }
}
