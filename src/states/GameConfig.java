package states;

/**
 * Singleton qui contiendra toutes les données relatives à la partie: pv, argent, bonus, tourelles selectionnées, posées...
 * Created by tic-tac on 26/03/17.
 */
public class GameConfig {

    private int money; //TODO: combien d'argent au début?

    private GameConfig(){

    }

    private static GameConfig instance = new GameConfig();

    public static GameConfig getInstance(){return GameConfig.instance;}


    //    LES GETTERS     //
    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
