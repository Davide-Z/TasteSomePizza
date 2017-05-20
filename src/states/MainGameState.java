package states;


import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Vue du jeu principal, où on va placer les tourelles et lancer la vague
 * Created by tic-tac on 11/02/17.
 */
public class MainGameState extends BasicGameState {
    //Attributs du moteur
    private GameConfig config;

    //Attributs d'interface
    private int winHeight;
    private int winWidth;



    //Dav test
    //public Wave wave;

    MainGameState() throws SlickException {
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
        config=GameConfig.getInstance(stateBasedGame);
        config.setTurretMenu();
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
        g.setColor(Color.white);
        g.drawString("X:"+config.getMx()+"\nY:"+config.getMy(),0,winHeight-35);
        g.drawString(winWidth+"x"+winHeight, winWidth-73,0);
        //Segmentation temporaire de l'écran
        g.setColor(Color.black);
        g.setLineWidth(4);
        g.drawLine(2+winWidth*0.703125f,0,2+winWidth*0.703125f, winHeight);
        g.drawString("Tourelles/menu",winWidth*0.713125f+6, 0);
        if (config.getTurret()!=null) {
            g.drawString(config.getTurret().toString(), winWidth * 0.713125f, winHeight - 300);
        }
        else {
            g.drawString("Tourelle: Aucune", winWidth * 0.713125f, winHeight - 300);
        }
        g.setColor(Color.black);

        g.drawString("Carte",3, 3);

        config.getMap().render(g);
        config.getTurretMenu().render(g);
        config.getButtonsGroup().render(g);
        g.drawString("Money:"+config.getMoney(), winWidth*0.9f, winHeight*0.5f);

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
        config.update();
        /* //Dav test
        wave.spawn();
        wave.aliveEnemiesUpdate(i); */
    }
}