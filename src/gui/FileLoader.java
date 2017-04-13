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
        return new Image(System.getProperty("user.dir")+slash+"src"+slash+"resources"+ slash +resource+".png", false);
    }
    public  static Image getSpriteImage(String resource) throws URISyntaxException, FileNotFoundException, SlickException {
        return new Image(System.getProperty("user.dir")+slash+"src"+slash+"resources"+ slash + "sprites" + slash + resource, false);
    }
    public static Image getInterfaceImage(String resource) throws URISyntaxException, FileNotFoundException, SlickException {
        return new Image(System.getProperty("user.dir")+slash+"src"+slash+"resources"+slash+"interface"+slash+resource+".png", false);
    }
    public static InputStream getInputStream(String file){
        return Thread.currentThread().getContextClassLoader().getResourceAsStream("resources"+slash+file);
    }

    /**récupère un lien vers un fichier du dossier resources/sound
     *
     * @param resource  chemin du fichier dans le dossier sound
     * @return  chemin vers le fichier
     */
    public static String getSoundPath(String resource){
        return System.getProperty("user.dir")+slash+"src"+slash+"resources"+slash+"sound"+slash+resource;
    }
}