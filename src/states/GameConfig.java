package states;

/**
 * Singleton qui contiendra toutes les données relatives à la partie: pv, argent, bonus, tourelles selectionnées, posées...
 * Created by tic-tac on 26/03/17.
 */
public class GameConfig {
    private GameConfig(){}

    private static GameConfig instance = new GameConfig();

    public static GameConfig getInstance(){return GameConfig.instance;}
}
