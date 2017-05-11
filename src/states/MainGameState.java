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
    private GameContainer container;
    private StateBasedGame game;
    private GameConfig config;

    //Attributs d'interface
    int winHeight;
    int winWidth;



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

        config.initializeUsableTurrets();
        config.setTurretMenu(gameContainer);
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
        g.setColor(Color.black);

        g.drawString("Carte",3, 3);

        config.getMap().render(g);
        config.getTurretMenu().render(g);
        config.getButtonsGroup().render(g);
        /* //Dav test
        g.drawString("Number of enemies alive : "+wave.aliveEnemies.size(), 3, 20);
        g.drawString("HP : "+config.getMap().baseHP, 3, 40);
        g.drawString("Number of unspawned enemies : "+wave.unspawnedEnemies.size(), 3, 60);
        for (Enemy e : wave.aliveEnemies) {
        	//g.drawString("o", e.getPos().getX(), e.getPos().getY());
        	e.render();
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
        config.update();
        /* //Dav test
        wave.spawn();
        wave.aliveEnemiesUpdate(i); */
    }
}