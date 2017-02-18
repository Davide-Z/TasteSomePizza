import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Controlleur qui va gérer toutes les entrées du joueur(qui se font à la souris principalement)
 * Created by tic-tac on 17/02/17.
 */
public class MouseListener implements org.newdawn.slick.MouseListener {

    private StateBasedGame sbg;
    private GameContainer gc;
    private Graphics g;

    public MouseListener(StateBasedGame sbg, GameContainer gc, Graphics g){
        this.sbg=sbg;
        this.gc=gc;
        this.g=g;
    }

    /*
    TODO: Est ce que ce serait pas mieux d'envoyer dans MouseListener lors de son instanciation tous,
    TODO: les boutons qui existent(ArrayList)+ autres objets, et le laisser gérer(+add tourelles et ennemis)
    */
    @Override
    public void mouseWheelMoved(int i) {

    }

    @Override
    public void mouseClicked(int i, int i1, int i2, int i3) {

    }

    @Override
    public void mousePressed(int i, int i1, int i2) {

    }

    @Override
    public void mouseReleased(int i, int i1, int i2) {

    }

    @Override
    public void mouseMoved(int i, int i1, int i2, int i3) {

    }

    @Override
    public void mouseDragged(int i, int i1, int i2, int i3) {

    }

    @Override
    public void setInput(Input input) {

    }

    @Override
    public boolean isAcceptingInput() {
        return false;
    }

    @Override
    public void inputEnded() {

    }

    @Override
    public void inputStarted() {

    }
}
