import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import states.GameStates;

/**
 * Créée juste une fenêtre d'une certaine taille en se basant sur un gestionnaire de vues(GameStates)
 */
public class Main{
	public static void main(String[] args) throws SlickException {
		StateBasedGame game=new GameStates("Taste Some Pizza !");
		AppGameContainer app = new AppGameContainer(game, 1024,720,false);
		app.setShowFPS(false);
		app.setVSync(false);
		app.start();
	}

}
