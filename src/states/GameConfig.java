package states;

import gui.ButtonsGroup;
import gui.Timer;
import gui.TurretMenu;
import maps.Map;
import obj.Turret;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import java.util.LinkedList;

/**
 * Singleton qui contiendra toutes les données relatives à la partie: pv, argent, bonus, tourelles selectionnées, posées...
 * Created by tic-tac on 26/03/17.
 */
public class GameConfig {

    //       ATTRIBUTS       //
    private static GameConfig instance=null;
    private int money;
    private String playerName;
    private Turret selectedTurret;
    private Map map;
    private TurretMenu turretMenu;
    public LinkedList<Turret> aliveTurrets;
    //Position de la souris
    private int mx=0;
    private int my=0;
    private boolean mouseClicked=false;
    public boolean wasMouseReleased =true;
    private ButtonsGroup buttonsGroup;
    private Timer timer;


    public int level=1;

    public boolean testMode;


    private GameConfig() throws SlickException{
        money=2000;
        map=new Map(15);
        buttonsGroup=new ButtonsGroup();
        aliveTurrets=new LinkedList<>();
        timer=new Timer(System.currentTimeMillis());

        //TODO:instancier les musiques quand elles seront ajoutées
    }

    /**
     * Methode qui vérifie si l'objet a déjà été instancié, si non l'instancie, puis renvoie l'objet déjà instancié
     * @return  config du jeu
     * @throws SlickException
     */
    public static GameConfig getInstance() throws SlickException{
        if (instance==null){
            instance=new GameConfig();
        }
        return GameConfig.instance;
    }

    //       LES GETTERS       //
    public int getMoney() {return money;}
    public Turret getTurret(){return selectedTurret;}
    public Map getMap(){return map;}
    public TurretMenu getTurretMenu() {return turretMenu;}
    //       LES SETTERS       //
    public void setSelectedTurret(Turret turret){this.selectedTurret=turret;}
    public void setTurretMenu(StateBasedGame game) throws SlickException{this.turretMenu=new TurretMenu(game);}

    public int getMx() {
        mx=Mouse.getX();
        return mx;
    }

    public int getMy() {
        my=720-Mouse.getY();
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

    public void update(StateBasedGame game) throws SlickException {
        turretMenu.update(game);
        buttonsGroup.update(game);
        map.update(game,this);
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

	public Timer getTimer(){return timer;}

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public void setTestMode(boolean testMode) {
        this.testMode = testMode;
    }
}
