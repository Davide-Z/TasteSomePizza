import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Game;
import org.newdawn.slick.SlickException;

/**
 * Created by tic-tac on 11/02/17.
 */
public class Main {
    public static void main(String[] args) throws SlickException {
        AppGameContainer app = new AppGameContainer(new GameStates("Taste Some Pizza !"), 640,480,false);
        app.setShowFPS(false);
        app.start(); //Démarre le jeu
    }
}
