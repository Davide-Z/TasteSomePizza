package states;

import gui.FileLoader;
import gui.Buttons.StateButton;
import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.awt.*;
import java.awt.Font;
import java.io.*;
import java.net.URISyntaxException;

/**
 * Vue du menu principal, avec un bouton pour entrer en jeu, un pour quitter, et un pour les réglages
 * Created by tic-tac on 01/02/17.
 */
public class MenuState extends BasicGameState {

    //Paramètres du moteur
    private GameContainer container;
    private StateBasedGame game;
    private GameConfig config;

    //Paramètres d'interface
    private Image backgroundImage;
    private Image imgBouton;
    private StateButton startButton;
    private StateButton exitButton;
    private SpriteSheet piz;
    private Animation animPiz;
    private int x=0;
    private int y=0;
    private int winHeight;
    private int winWidth;
    private Font font;
    private TrueTypeFont ttf;

    public MenuState() throws SlickException {
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
        game=stateBasedGame;
        container=gameContainer;
        config=GameConfig.getInstance(game);
        InputStream inputStream= null;
        inputStream = FileLoader.getRes("fly_n_walk.ttf");
        winHeight=container.getHeight();
        winWidth=container.getWidth();
        try {
            font=Font.createFont(Font.TRUETYPE_FONT,inputStream);
            font=font.deriveFont(font.getSize()*45f);
            ttf=new TrueTypeFont(font, true);
            backgroundImage=FileLoader.getImage("interface/bgi.png");
            imgBouton=FileLoader.getImage("interface/boutonOrange.png");
            piz=new SpriteSheet(FileLoader.getSpriteImage("Piz.png"), 256,256);
        } catch (URISyntaxException | FontFormatException | IOException e) {
            e.printStackTrace();
        }
        startButton = new StateButton(game, imgBouton, winWidth/2-152,winHeight/2-55, "Demarrer", "start");
        exitButton=new StateButton(game, imgBouton, winWidth/2-152,winHeight/2+50, "Quitter", "quit");
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
        g.setLineWidth(5);
        g.setColor(Color.white);
        startButton.render(g);
        exitButton.render(g);
        g.drawString("X:"+ x +"\nY:"+ y,0,winHeight-35);
        g.drawString(winWidth+"x"+winHeight, winWidth-73,0);
        g.setColor(Color.black);
        g.setFont(ttf);
        g.drawString("Taste Some Pizza !", winWidth/2-250, 100);
        if(gc.getInput().isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)){
            g.setColor(Color.white);
            g.drawRect(x,y,32,32);
        }
        animPiz.draw(155,238);
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
        x=gc.getInput().getMouseX();
        y=gc.getInput().getMouseY();
    }

    /**
     * Méthode pour pouvoir changer d'état plus rapidement ou quitter, avec le clavier
     * @param key Touche appuyée
     * @param c charactère reçu
     */
    @Override
    public void keyPressed(int key, char c){
        if(key== Input.KEY_ESCAPE){
            container.exit();
        }
        if(key==Input.KEY_SPACE){
            game.enterState(1);
        }
    }
}