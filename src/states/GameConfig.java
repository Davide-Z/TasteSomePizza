package states;

import gui.ButtonsGroup;
import gui.TurretMenu;
import maps.Map;
import obj.Enemy;
import obj.Turret;
import obj.Wave;
import obj.enums.TurretType;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Singleton qui contiendra toutes les données relatives à la partie: pv, argent, bonus, tourelles selectionnées, posées...
 * Created by tic-tac on 26/03/17.
 */
public class GameConfig {

    //       ATTRIBUTS       //
    private static GameConfig instance=null;
    private StateBasedGame stateBasedGame;
    private GameContainer gc;
    private int money; //TODO: combien d'argent au début?
    private String playerName;
    private Turret selectedTurret;
    private Enemy selectedEnemy;
    private Map map;
    private ArrayList<Turret> usableTurrets;
    private TurretMenu turretMenu;
    public LinkedList<Turret> aliveTurrets;
    //Position de la souris
    private int mx=0;
    private int my=0;
    private boolean mouseClicked=false;
    public boolean wasMouseReleased =true;
    private ButtonsGroup buttonsGroup;
    public Wave wave;
    public Wave nextWave;
    public int level=1;

    //Musiques
    Sound click;
    Music introMusic;
    Music levelMusic;


    private GameConfig(StateBasedGame sbg) throws SlickException{
        money=2000;
        stateBasedGame=sbg;
        gc=sbg.getContainer();
        map=new Map(stateBasedGame, 15);
        buttonsGroup=new ButtonsGroup();
        usableTurrets=new ArrayList<>();
        aliveTurrets=new LinkedList<>();
        //TODO:instancier les musiques quand elles seront ajoutées
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
    
    public boolean withdraw(int asked){
    	// Return false if not enough money. Else returns true and withdraws
    	if(money>asked){
    		this.money-=asked;
    		return true;
    	}
    	else{
    		return false;
    	}
    }

    //       LES GETTERS       //
    public int getMoney() {return money;}
    public String getPlayerName(){return playerName;}
    public Turret getTurret(){return selectedTurret;}
    public Enemy getEnemy(){return selectedEnemy;}
    public Map getMap(){return map;}
    public ArrayList<Turret> getUsableTurrets(){return usableTurrets;}
    public TurretMenu getTurretMenu() {return turretMenu;}
    //       LES SETTERS       //
    public void setMoney(int money) {this.money = money;}
    public void setPlayerName(String name){this.playerName=name;}
    public void setSelectedTurret(Turret turret){this.selectedTurret=turret;}
    public void setSelectedEnemy(Enemy enemy){this.selectedEnemy=enemy;}
    public void setMap(Map map){this.map=map;}
    public void setTurretMenu(GameContainer gameContainer) throws SlickException{
        this.turretMenu=new TurretMenu(stateBasedGame, gameContainer);
    }
    /**
     * Ajoute une tourelle à la liste des tourelles utilisables
     */
    public void initializeUsableTurrets() throws SlickException {
        for(TurretType t: TurretType.values()){
            this.usableTurrets.add(new Turret(t, this.stateBasedGame));
        }
    }
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
            this.wasMouseReleased =true;
        }
        return mouseClicked;
    }

    public ButtonsGroup getButtonsGroup(){return this.buttonsGroup;}

    public void addMoney(int m){
    	this.money+=m;
    }
    public void update() throws SlickException {
        turretMenu.update(stateBasedGame);
        buttonsGroup.update(stateBasedGame);
        map.update(stateBasedGame, this);
    }

    public Wave getWave() {
        return this.wave;
    }
    public Wave getNextWave() {
        return this.wave;
    }

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
	public void purchase(int amount) {
		this.money-=amount;
	}
	
	public void sell(int amount) {
		this.money+=amount;
	}
    
}
