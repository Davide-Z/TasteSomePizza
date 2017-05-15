import gui.FileLoader;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import states.GameStates;

/**
 * Créée juste une fenêtre d'une certaine taille en se basant sur un gestionnaire de vues(GameStates)
 */
public class Main{
	public static void main(String[] args) throws SlickException {
		//System.setProperty("org.lwjgl.librarypath", System.getProperty("user.dir")+"/src/libs");
		Music music=new Music(FileLoader.getSoundPath("island_music.wav"));
		music.loop();
		StateBasedGame game=new GameStates("Taste Some Pizza !"); //Gestionnaire de vues
		Input.disableControllers();
		AppGameContainer app = new AppGameContainer(game, 1024,720,false); //Fenêtre
		app.setShowFPS(false);
		app.setVSync(false);
		app.start();
	}

}
