import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;
import states.GameStates;

public class Main{
	public static void main(String[] args) throws SlickException {
		AppGameContainer app = new AppGameContainer(new GameStates("bite"), 1024,720,false);
		app.setShowFPS(false);
		app.start();
	}

}
