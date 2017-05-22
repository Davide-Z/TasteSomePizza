package states;

import obj.Wave;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Classe de gestion des vues: pour les initialiser, passer de l'une à l'autre
 * Created by tic-tac on 25/01/17.
 */

public class GameStates extends StateBasedGame{

    GameConfig config;
    int currentState;
    int lastState;
    double[] tabVit={50f, 20f, 5f, 2f, 1f, 0.5f, 0.2f};
    int i=4;
    public GameStates(String name) {
        super(name);
    }

    @Override
    public void initStatesList(GameContainer gameContainer) throws SlickException { //Création et initialisation des vues

        GameConfig.getInstance().setTestMode(true);

        System.out.println(GameConfig.getInstance().testMode);


        addState(new StartState());
        addState(new MainGameState());
        addState(new WaveState());
        addState(new SettingsState());
        addState(new EndState());
        getContainer().getGraphics().setBackground(Color.decode("0xdba24f"));
        enterState(0);
    }

    @Override
    /**
     * Méthode qui est utilisée quand une touche est appuyée, pour les raccourcis claviers disponibles partout
     * (echappe = quitter, espace = aller au menu/en revenir)
     */
    public void keyPressed(int key, char c) {
        if (key == Input.KEY_ESCAPE) {
            getContainer().exit();
        }
        if (key == Input.KEY_SPACE) {
            currentState=getCurrentStateID();
            if (currentState != 0) {
                lastState=currentState;
                enterState(0);
            } else {
                enterState(lastState);
            }
        }
        if(key==Input.KEY_P	||	key==Input.KEY_UP	||	key==Input.KEY_DOWN	){
        	// First, we get the actualWave
        	if(	this.getCurrentState() != null 	&& 	this.getCurrentState() instanceof WaveState	){        			
       				WaveState ws=(WaveState)this.getCurrentState();
       				System.out.println(	"wave.vit=" + ws.getWave().getVit()	);
      				Wave w=(Wave)ws.getWave();
      				
      				
      				if(key==Input.KEY_P){	// Pause
      	  				if(	w.getVit() == 100000f	){	// vit=100 000 means it's so slowed 
      	  												// So it seems motionless
      	     				w.setVit(1.0f);
      	  				}
      	  				else{
      	  					w.setVit(100000f);
      	  				}
      	        	}
      				else if(key==Input.KEY_UP){	// speed up
      		     		if(i<tabVit.length-1){
      		     			i++;
      		     			w.setVit(	tabVit[i]	);
      		     		}
      		     		else{
      						w.setVit(	tabVit[tabVit.length-1]	);
      					}
      		        }
      				else if(key==Input.KEY_DOWN){	// slow down
      					if(i>0){
      						i--;
      						w.setVit(	tabVit[i]	);
      					}
      					else{
      						w.setVit(	tabVit[0]	);
      					}
      		        }

       				System.out.println(	"wave.vit=" + ws.getWave().getVit()+"\n"	);
        	}
        }
    }
}