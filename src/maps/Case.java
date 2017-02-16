package maps;

import com.sun.xml.internal.bind.v2.TODO;
import org.newdawn.slick.Image;
import org.newdawn.slick.gui.GUIContext;
import org.newdawn.slick.gui.MouseOverArea;

/**
 * Classe représentant une case de la carte, avec les éléments qu'elle contient, son image, sa position sur la carte, son accessibilité
 * Created by tic-tac on 16/02/17.
 */
public class Case extends MouseOverArea {
    private boolean access=true;
    private Image image;
    //TODO: listes d'éléments qu'elle contient, position, image
    public Case(GUIContext container, Image image, int x, int y) {
        super(container, image, x, y);
    }
    public Case(GUIContext container, Image image, int x, int y, boolean accessible) {
        super(container, image, x, y);

        this.access=accessible;
    }

}
