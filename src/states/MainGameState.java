package states;


import gui.Buttons.StateButton;
import gui.FileLoader;
import obj.Turret;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;

/**
 * Vue du jeu principal, où on va placer les tourelles et lancer la vague
 * Created by tic-tac on 11/02/17.
 */
public class MainGameState extends BasicGameState {
    //Attributs du moteur
    private GameContainer container;
    private StateBasedGame game;
    private GameConfig config;

    //Attributs d'interface
    int winHeight;
    int winWidth;
    StateButton menuButton;
    StateButton waveButton;
    StateButton turretMenuButton;
    float mouseX;
    float mouseY;
    double updateTime=System.currentTimeMillis();

    //Dav test
    //public Wave wave;

    public MainGameState() throws SlickException {
    }

    /**
     * Renvoie l'ID de cette vue
     * @return ID
     */
    @Override
    public int getID() {
        return 1;
    }

    /**
     * Méthode qui se fait une fois au début, pour initialiser les différents paramètres
     * @param gameContainer Container du jeu
     * @param stateBasedGame Le moteur du jeu
     * @throws SlickException
     */
    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        game=stateBasedGame;
        container=gameContainer;
        config=GameConfig.getInstance(game);
        winHeight=container.getHeight();
        winWidth=container.getWidth();

        try {
            menuButton = new StateButton(stateBasedGame, FileLoader.getImage("interface/boutonOrange.png"),winWidth-275,winHeight-78, "Menu principal", "menu");
            waveButton = new StateButton(stateBasedGame, FileLoader.getImage("interface/boutonOrange.png"), winWidth-275, winHeight-156, "Lancer la vague", "wave");
            turretMenuButton = new StateButton(stateBasedGame, FileLoader.getImage("interface/boutonOrange.png"), winWidth-275, winHeight-234, "Tourelles/Ennemis", "turret");
            config.addUsableTurret(new Turret(game));
            config.setTurretMenu(gameContainer);
        } catch (URISyntaxException | FileNotFoundException e) {
            e.printStackTrace();
        }
        //Dav test
     //   wave = new Wave(56, map, sbg, gc);

    }

    /**
     * Méthode qui centralise tout ce que l'on affichera sur cette vue
     * @param gc Container du jeu
     * @param sbg Moteur du jeu
     * @param g gestionnaire graphique
     * @throws SlickException
     */
    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.setBackground(Color.decode("0xdba24f"));
        g.setColor(Color.white);
        g.drawString("X:"+(int)mouseX+"\nY:"+(int)mouseY,0,winHeight-35);
        g.drawString(winWidth+"x"+winHeight, winWidth-73,0);
        menuButton.render(g);
        waveButton.render(g);
        turretMenuButton.render(g);

        //Segmentation temporaire de l'écran
        g.setColor(Color.black);
        g.setLineWidth(4);
        g.drawLine(2+winWidth*0.703125f,0,2+winWidth*0.703125f, winHeight);
        g.drawString("Tourelles/menu",winWidth*0.703125f+6, 0);
        if (config.getTurret()!=null) {
            g.drawString("tourelle: " + config.getTurret().toString(), winWidth * 0.703125f, winHeight - 300);
        }
        else {
            g.drawString("tourelle: Aucune", winWidth * 0.703125f, winHeight - 300);
        }
        if (config.getEnemy()!=null) {
            g.drawString("ennemi: " + config.getEnemy().toString(), winWidth * 0.703125f, winHeight - 400);
        }
        else {
            g.drawString("ennemi: Aucun", winWidth * 0.703125f, winHeight - 400);
        }
       config.getMap().render();

        g.setColor(Color.black);

        g.drawString("Carte",3, 3);

        config.getTurretMenu().render();
        
      /*  //Dav test
        g.drawString("Number of enemies alive : "+wave.aliveEnemies.size(), 3, 20);
        g.drawString("HP : "+map.baseHP, 3, 40);
        g.drawString("Number of unspawned enemies : "+wave.unspawnedEnemies.size(), 3, 60);
        for (Enemy e : wave.aliveEnemies) {
        	//g.drawString("o", e.getPos().getX(), e.getPos().getY());
        	turret.draw(e.getPos().getX(), e.getPos().getY());
        }*/
    }

    /**
     * Méthode pour mettre à jour les différents paramètres entre deux images
     * @param gc Container du jeu
     * @param sbg Moteur du jeu
     * @param i delta de temps entre deux images
     * @throws SlickException
     */
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        //config.updateMouse();
        try {
            config.getMap().update();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        config.getTurretMenu().update();
        //Dav test
      /*  wave.spawn();
        wave.aliveEnemiesUpdate(i); */
    }

    /**
     * Méthode pour pouvoir changer d'état plus rapidement ou quitter, avec le clavier
     * @param key Touche appuyée
     * @param c charactère reçu
     */
    public void keyPressed(int key, char c){
        if(key== Input.KEY_ESCAPE){
            container.exit();
        }
        if(key==Input.KEY_SPACE){
            game.enterState(0);
        }
    }

    /**
     * Méthode donnant les mouvements de la sourie
     * @param oldX
     * @param oldY
     * @param newX
     * @param newY
     */
    @Override
    public void mouseMoved(int oldX, int oldY, int newX, int newY){
        mouseX=(float)(newX);
        mouseY=(float)(newY);
    }
}