package states;


import gui.ImageLoader;
import gui.Buttons.StateButton;
import maps.Map;
import obj.*;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;

import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Vue du jeu principal, où on va placer les tourelles et lancer la vague
 * Created by tic-tac on 11/02/17.
 */
public class MainGameState extends BasicGameState {
    //Attributs du moteur
    private GameContainer container;
    private StateBasedGame game;
    private ImageLoader imageLoader;

    //Attributs d'interface
    Image turret;
    float alpha;
    int winHeight;
    int winWidth;
    StateButton stateButton;
    float mouseX;
    float mouseY;
    Map map;
    
    //Dav test
    public Wave wave;

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
     * @param gc Container du jeu
     * @param sbg Le moteur du jeu
     * @throws SlickException
     */
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        this.game=sbg;
        this.container=gc;
        imageLoader=new ImageLoader();
        winHeight=gc.getHeight();
        winWidth=gc.getWidth();

        try {
            turret=imageLoader.getImage("resources/sprites/cook.png");
            alpha=turret.getRotation();
            stateButton = new StateButton(container, sbg, imageLoader.getImage("resources/interface/boutonOrange.png"),winWidth-275,winHeight-78, "Menu principal", "menu");

        } catch (URISyntaxException | FileNotFoundException e) {
            e.printStackTrace();
        }

        map=new Map(sbg, 15);

        //Dav test        
        wave = new Wave(56, map, sbg, gc);

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
        turret.draw(winWidth-184,winHeight/2-32);
        turret.setRotation(alpha);
        g.setColor(Color.white);
        g.drawString(""+(alpha), 500,0);
        g.drawString("X:"+(int)mouseX+"\nY:"+(int)mouseY,0,winHeight-35);
        g.drawString(winWidth+"x"+winHeight, winWidth-73,0);
        stateButton.render(g);

        //Segmentation temporaire de l'écran
        g.setColor(Color.black);
        g.setLineWidth(4);
        g.drawLine(2+winWidth*0.703125f,0,2+winWidth*0.703125f, winHeight);
        g.drawString("Tourelles/menu",winWidth*0.703125f+6, 0);


        map.render();

        g.setColor(Color.black);

        g.drawString("Carte",3, 3);
        
        //Dav test
        g.drawString("Number of enemies alive : "+wave.aliveEnemies.size(), 3, 20);
        g.drawString("HP : "+map.baseHP, 3, 40);
        g.drawString("Number of unspawned enemies : "+wave.unspawnedEnemies.size(), 3, 60);
        for (Enemy e : wave.aliveEnemies) {
        	//g.drawString("o", e.getPos().getX(), e.getPos().getY());
        	turret.draw(e.getPos().getX(), e.getPos().getY());
        }
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
        alpha=(float)((Math.atan2((mouseX+184-winWidth),-(mouseY+32-winHeight/2))*360/(2*Math.PI)));
        map.resetClicked();
        
        //Dav test
        wave.spawn();
        wave.aliveEnemiesUpdate(i);
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