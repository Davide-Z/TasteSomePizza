package gui;

import java.io.*;
/**
 * Classe permettant de récpérer un lien vers un fichier au format "InputStream"
 * Created by tic-tac on 18/03/17.
 */
public class FileLoader {
    public InputStream getRes(String file){
        return Thread.currentThread().getContextClassLoader().getResourceAsStream(file);
    }
}