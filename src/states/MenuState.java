package states;

import gui.FileLoader;
import gui.ImageLoader;
import gui.StateButton;
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

    //Paramètres d'interface
    private Image imgBouton;
    private StateButton startButton;
    private StateButton exitButton;
    private SpriteSheet piz;
    private Animation animPiz;
    private int mouseX;
    private int mouseY;
    private int winHeight;
    private int winWidth;
    private Font font;
    private TrueTypeFont ttf;
    private FileLoader linkLoader;
    private ImageLoader imageLoader;

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
     * @param gc Container du jeu
     * @param sbg Le moteur du jeu
     * @throws SlickException exception Interne à Slck
     */
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        this.container=gc;
        this.game=sbg;
        linkLoader=new FileLoader();
        imageLoader=new ImageLoader();
        //InputStream inputStream=ResourceLoader.getResourceAsStream("./src/resources/fly_n_walk.ttf");
        InputStream inputStream= null;
        inputStream = linkLoader.getRes("resources/fly_n_walk.ttf");
        winHeight=gc.getHeight();
        winWidth=gc.getWidth();

        try {
            font=Font.createFont(Font.TRUETYPE_FONT,inputStream);
            font=font.deriveFont(font.getSize()*45f);
            ttf=new TrueTypeFont(font, true);
            imgBouton=imageLoader.getImage("resources/interface/boutonOrange.png");
            piz=new SpriteSheet(imageLoader.getImage("resources/sprites/Piz.png"), 256,256);
        } catch (URISyntaxException | FontFormatException | IOException e) {
            e.printStackTrace();
        }
        startButton = new StateButton(gc, game, imgBouton, winWidth/2-152,winHeight/2-55, "Demarrer", "start");
        exitButton=new StateButton(gc, game, imgBouton, winWidth/2-152,winHeight/2+50, "Quitter", "quit");
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
        g.setBackground(Color.lightGray);
        g.setLineWidth(5);
        g.setColor(Color.white);
        startButton.render(g);
        exitButton.render(g);
        g.drawString("X:"+(int)mouseX+"\nY:"+(int)mouseY,0,winHeight-35);
        g.drawString(winWidth+"x"+winHeight, winWidth-73,0);
        g.setFont(ttf);
        g.drawString("Taste Some Pizza !", winWidth/2-250, 100);
        if(gc.getInput().isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)){
            g.setColor(Color.white);
            int x=gc.getInput().getMouseX();
            int y=gc.getInput().getMouseY();

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

    /**
     * Méthode donnant les mouvements de la sourie
     * @param oldX ancienne X position de la souris
     * @param oldY ancienne Y position de la souris
     * @param newX nouvelle X position de la souris
     * @param newY nouvelle Y position de la souris
     */
    @Override
    public void mouseMoved(int oldX, int oldY, int newX, int newY){
        mouseX=newX;
        mouseY=newY;
    }
}