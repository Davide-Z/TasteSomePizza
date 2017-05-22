package states;

import gui.ButtonsGroup;
import gui.FileLoader;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Created by Tic-Tac on 12/04/2017.
 */
public class SettingsState extends BasicGameState {

	//Paramètres du moteur
	private GameConfig config;
	//Paramètres d'interface
	private Image backgroundImage;
	private SpriteSheet piz;
	private Animation animPiz;
	private int winHeight;
	private int winWidth;
	private ButtonsGroup buttonsGroup;

	@Override
	public int getID() {
		return 3;
	}

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		this.config=GameConfig.getInstance();
		backgroundImage= FileLoader.getInterfaceImage("bgi");
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics graphics) throws SlickException {
		backgroundImage.draw();
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int i) throws SlickException {

	}
}
