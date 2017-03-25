package gui;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.io.*;
import java.net.URISyntaxException;

/**
 * Classe permettant de récpérer un lien vers un fichier au format "InputStream"
 * Created by tic-tac on 18/03/17.
 */
public abstract class FileLoader {
    private static String slash = File.separator;

    public static Image getImage(String resource) throws URISyntaxException, FileNotFoundException, SlickException {
        return new Image(Thread.currentThread().getContextClassLoader().getResourceAsStream("resources" + slash +resource), resource, false);
    }
    public  static Image getSpriteImage(String resource) throws URISyntaxException, FileNotFoundException, SlickException {
        return new Image(Thread.currentThread().getContextClassLoader().getResourceAsStream("resources" + slash + "sprites" + slash + resource), resource, false);
    }
    public static InputStream getRes(String file){
        return Thread.currentThread().getContextClassLoader().getResourceAsStream("resources"+slash+file);
    }
}