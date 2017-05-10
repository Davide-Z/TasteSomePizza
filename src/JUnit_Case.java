/**
 * Created by tic-tac on 10/05/17.
 */

import maps.Map;
import maps.Vec;
import org.junit.Before;
import org.junit.Test;
import org.newdawn.slick.state.StateBasedGame;
import states.GameConfig;
import states.GameStates;

import java.util.LinkedList;

public class JUnit_Case{
    Map map;
    StateBasedGame game;
    GameConfig config;
    @Before
    public void setUp() throws Exception {
        StateBasedGame game=new GameStates("Test Some Pizza !"); //Gestionnaire de vues
        config=GameConfig.getInstance(game);
        map=new Map(game, 2);
    }

    @Test
    public void testToMatrix(){
        int[][] matrixMap=map.toMatrix();
        int[][] matrix = new int[map.getTaille()][map.getTaille()];
        for (int i=0; i<map.getTaille(); i++) {
            for (int j=0; j<map.getTaille(); j++) {
                if (map.getCases()[i][j].getTurret() == null) {
                    matrix[i][j]=0;
                }
                else {
                    matrix[i][j]=1;
                }
            }
        }
        assert(map.toMatrix().length==matrix.length);
        System.out.println("Length is ok");
        for (int i=0; i<map.getTaille(); i++) {
            for (int j=0; j<map.getTaille(); j++) {
                assert(matrixMap[i][j]==matrix[i][j]);
            }
            System.out.println("Line "+(i+1)+" is ok");
        }
    }

    @Test
    public void testComputePath(){
        LinkedList<Vec> computedPath=map.computePath();
        assert(computedPath!=null);
    }
}
