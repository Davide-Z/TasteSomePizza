import maps.Vec;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;
import states.GameStates;

public class Main{
	public static void main(String[] args) throws SlickException {
		AppGameContainer app = new AppGameContainer(new GameStates("Taste Some Pizza !"), 1024,720,false);
		app.setShowFPS(false);
		app.setVSync(false);
		app.start();
	}

}
