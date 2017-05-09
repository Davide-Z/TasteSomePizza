package states;

import gui.FileLoader;
import gui.Buttons.StateButton;
import obj.Enemy;
import obj.Projectile;
import obj.Turret;
import obj.Wave;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import audio.Son;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by tic-tac on 24/03/17.
 */
public class WaveState extends BasicGameState {
    //Attributs du moteur
    private GameConfig config;

    //Attributs d'interface
    int winHeight;
    int winWidth;
    StateButton menuButton;
    StateButton waveButton;
    StateButton turretMenuButton;
    float mouseX;
    float mouseY;
    public Wave wave;
    private boolean hasBegun=false;
    
    public WaveState() throws SlickException {
    }

    /**
     * Renvoie l'ID de cette vue
     *
     * @return ID
     */
    @Override
    public int getID() {return 2;}

    /**
     * Méthode qui se fait une fois au début, pour initialiser les différents paramètres
     *
     * @param container  Container du jeu
     * @param game Le moteur du jeu
     * @throws SlickException
     */
    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        config = GameConfig.getInstance(game);
        winHeight = container.getHeight();
        winWidth = container.getWidth();
    }

    /**
     * Méthode qui centralise tout ce que l'on affichera sur cette vue
     *
     * @param gc  Container du jeu
     * @param sbg Moteur du jeu
     * @param g   gestionnaire graphique
     * @throws SlickException
     */
    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
    	g.setColor(Color.white);
        g.drawString("X:" + (int) mouseX + "\nY:" + (int) mouseY, 0, winHeight - 35);
        g.drawString(winWidth + "x" + winHeight, winWidth - 73, 0);
        //Segmentation temporaire de l'écran
        g.setColor(Color.black);
        g.setLineWidth(4);
        g.drawLine(2 + winWidth * 0.703125f, 0, 2 + winWidth * 0.703125f, winHeight);
        g.drawString("Tourelles/menu", winWidth * 0.703125f + 6, 0);
        g.setColor(Color.black);

        g.drawString("Carte", 3, 3);

        config.getMap().render(g);
        config.getTurretMenu().render(g);
        config.getButtonsGroup().render(g);

        //Dav test
    g.drawString("Number of enemies alive : "+wave.aliveEnemies.size(), 3, 20);
    g.drawString("HP : "+config.getMap().baseHP, 3, 40);
    g.drawString("Number of unspawned enemies : "+wave.unspawnedEnemies.size(), 3, 60);
    g.drawString("Money : "+config.getMoney(), 3, 80);
    for (Enemy e : wave.aliveEnemies) {
        //g.drawString("o", e.getPos().getX(), e.getPos().getY());
        e.render();
    }
    
    for(Turret t : Turret.aliveTurrets){
    	t.render();
    }
    
    for(Projectile p : wave.aliveProjectiles){
    	p.render();
    }
    }

    /**
     * Méthode pour mettre à jour les différents paramètres entre deux images
     *
     * @param gc  Container du jeu
     * @param sbg Moteur du jeu
     * @param i   delta de temps entre deux images
     * @throws SlickException
     */
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
    	if (hasBegun) {
            wave = new Wave(10, config.getMap(), sbg, gc);
            hasBegun=false;
    	}
        config.update();
        //Dav test
        if(!wave.unspawnedEnemies.isEmpty()) {
            wave.spawn();
        }
        wave.aliveEnemiesUpdate(i);

        if(wave.getAliveEnemies().isEmpty()==false && Turret.aliveTurrets.isEmpty()==false){
            for(Turret t : Turret.aliveTurrets){
               	t.update(wave);
            }
            
            // To avoid ConcurrentModificationException, does a copy
            Object copyOfAliveProjectiles=wave.aliveProjectiles.clone();
            for(Projectile p : (LinkedList<Projectile>)copyOfAliveProjectiles){
            	p.update();
            }
        }
        
        /*
        if (wave.getAliveEnemies().isEmpty() && wave.unspawnedEnemies.isEmpty()){
        String stream=FileLoader.getSoundPath("Intro.wav");
        Son son = new Son(stream);
        son.run();}
        */
    }

    /**
     * Méthode donnant les mouvements de la sourie
     *
     * @param oldX
     * @param oldY
     * @param newX
     * @param newY
     */
    @Override
    public void mouseMoved(int oldX, int oldY, int newX, int newY) {
        mouseX = (float) (newX);
        mouseY = (float) (newY);
    }

	public void setHasBegun(boolean hasBegun) {
		this.hasBegun = hasBegun;
	}
    
    
}