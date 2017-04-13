package gui;

import gui.Buttons.StateButton;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import states.GameConfig;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.ArrayList;

/**
 * Created by Tic-Tac on 05/04/2017.
 */
public class ButtonsGroup {
	private ArrayList<StateButton> buttons;
	private ArrayList<StateButton> menuButtons;
	private ArrayList<StateButton> mainButtons;
	private ArrayList<StateButton> waveButtons;
	private ArrayList<StateButton> endButtons;
	private int stateID;
	private GameConfig config;

	public ButtonsGroup(){
		buttons = new ArrayList<>();
		menuButtons = new ArrayList<>();
		mainButtons = new ArrayList<>();
		waveButtons= new ArrayList<>();
		endButtons = new ArrayList<>();
		stateID = 0;
	}
	public void init(StateBasedGame game,GameContainer container) throws FileNotFoundException, SlickException, URISyntaxException {
		config=GameConfig.getInstance(game);
		int winWidth=container.getWidth();
		int winHeight=container.getHeight();
		menuButtons.add(new StateButton("start", (int)(winWidth*0.48),(int)(winHeight*0.4),null, "start"));
		menuButtons.add(new StateButton("quit", (int)(winWidth*0.65),(int)(winHeight*0.1),null, "quit"));
		menuButtons.add(new StateButton("settings", (int)(winWidth*0.87),(int)(winHeight*0.03),null, "settings"));
		mainButtons.add(new StateButton("boutonOrange",winWidth-275,winHeight-78, "Menu principal", "menu"));
		waveButtons.add(new StateButton("boutonOrange",winWidth-275,winHeight-78, "Menu principal", "menu"));
		mainButtons.add(new StateButton("boutonOrange", winWidth-275, winHeight-156, "Lancer la vague", "wave"));
		mainButtons.add(new StateButton("boutonOrange", winWidth-275, winHeight-234, "Tourelles/Ennemis", "turret"));
	}
	public void render(Graphics g) throws FileNotFoundException, SlickException, URISyntaxException {
		for(StateButton b : buttons){
			b.render(g);
		}
	}

	public void update(StateBasedGame game){
		this.stateID=game.getCurrentStateID();
		if(stateID==0){
			buttons=menuButtons;
		}
		else if(stateID==1){
			buttons=mainButtons;
		}
		else if(stateID==2){
			buttons=waveButtons;
		}
		else if(stateID==3){
			buttons=endButtons;
		}
		for(StateButton b : buttons){
			b.update(game, config);
		}
	}
}
