package gui;

import gui.Buttons.StateButton;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import states.GameConfig;

import javax.swing.plaf.nimbus.State;
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
		menuButtons.add(new StateButton(FileLoader.getImage("interface/boutonDemarrer.png"), winWidth/2-152,winHeight/2-55,null, "start"));
		menuButtons.add(new StateButton(FileLoader.getImage("interface/boutonQuitter.png"), winWidth/2-152,winHeight/2+50,null, "quit"));
		mainButtons.add(new StateButton(FileLoader.getImage("interface/boutonOrange.png"),winWidth-275,winHeight-78, "Menu principal", "menu"));
		waveButtons.add(new StateButton(FileLoader.getImage("interface/boutonOrange.png"),winWidth-275,winHeight-78, "Menu principal", "menu"));
		mainButtons.add(new StateButton(FileLoader.getImage("interface/boutonOrange.png"), winWidth-275, winHeight-156, "Lancer la vague", "wave"));
		mainButtons.add(new StateButton(FileLoader.getImage("interface/boutonOrange.png"), winWidth-275, winHeight-234, "Tourelles/Ennemis", "turret"));
	}
	public void render(Graphics g){
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
