package states;

import gui.ButtonsGroup;
import gui.FileLoader;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Vue du menu principal, avec un bouton pour entrer en jeu, un pour quitter, et un pour les réglages
 * Created by tic-tac on 01/02/17.
 */
public class StartState extends BasicGameState {

    //Paramètres du moteur
    private GameConfig config;
    //Paramètres d'interface
    private Image backgroundImage;
    private SpriteSheet piz;
    private Animation animPiz;
    private int x=0;
    private int y=0;
    private int winHeight;
    private int winWidth;
    private ButtonsGroup buttonsGroup;

    public StartState() throws SlickException {
    }

    /**
     * Renvoie l'ID de cette vue
     * @return ID
     */
    @Override
    public int getID() {
        return 0;
    }

    /**
     * Méthode qui se fait une fois au début, pour initialiser les différents paramètres
     * @param gameContainer Container du jeu
     * @param stateBasedGame Le moteur du jeu
     * @throws SlickException exception Interne à Slck
     */
    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        winHeight=gameContainer.getHeight();
        winWidth=gameContainer.getWidth();
        config=GameConfig.getInstance(stateBasedGame);
        buttonsGroup=config.getButtonsGroup();
        try {
            backgroundImage=FileLoader.getImage("interface"+File.separator+"bgi");
            piz=new SpriteSheet(FileLoader.getSpriteImage("Piz.png"), 256,256);
            buttonsGroup.init(stateBasedGame, gameContainer);
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
        animPiz= new Animation(piz,75);
    }

    /**
     * Méthode qui centralise tout ce que l'on affichera sur cette vue
     * @param gc Container du jeu
     * @param sbg Moteur du jeu
     * @param g gestionnaire graphique
     * @throws SlickException exception Interne à Slick
     */
    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        backgroundImage.draw();
        try {
            buttonsGroup.render(g);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        g.setLineWidth(5);
        g.setColor(Color.white);
        g.drawString("X:"+ x +"\nY:"+ y,0,winHeight-35);
        g.drawString(winWidth+"x"+winHeight, winWidth-73,0);
        animPiz.draw(winWidth-250,winHeight-505);
    }

    /**
     * Méthode pour mettre à jour les différents paramètres entre deux images
     * @param gc Container du jeu
     * @param sbg Moteur du jeu
     * @param i delta de temps entre deux images
     * @throws SlickException Exception interne à Slick
     */
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        config.getButtonsGroup().update(sbg);
        x=gc.getInput().getMouseX();
        y=gc.getInput().getMouseY();
    }
}