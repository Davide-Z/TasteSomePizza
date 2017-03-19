package gui;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;

/**
 * CLasse permettant de récupérer une image avec son lien au format :
 * "resources/cheminDansLeDossierResourcesVersL'image"
 * Created by tic-tac on 19/03/17.
 */
public class ImageLoader {
    public Image getImage(String resource) throws URISyntaxException, FileNotFoundException, SlickException {
        return new Image(Thread.currentThread().getContextClassLoader().getResourceAsStream(resource), resource, false);
    }
}
