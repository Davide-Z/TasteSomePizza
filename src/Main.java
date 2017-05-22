import gui.FileLoader;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;
import states.GameStates;

/**
 * Créée juste une fenêtre d'une certaine taille en se basant sur un gestionnaire de vues(GameStates)
 */
public class Main{
	public static void main(String[] args) throws SlickException {
		Music music=new Music(FileLoader.getSoundPath("dansePizza.wav"));
		music.loop();
		StateBasedGame game=new GameStates("Taste Some Pizza !"); //Gestionnaire de vues
		((GameStates)game).setTestMode(false);
		Input.disableControllers();
		AppGameContainer app = new AppGameContainer(game, 1024,720,false); //Fenêtre
		app.setShowFPS(false);
		app.setVSync(false);
		app.start();
	}

}
