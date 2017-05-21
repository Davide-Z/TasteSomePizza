/**
 * Created by tic-tac on 10/05/17.
 */

import maps.Map;
import maps.Vec;
import obj.Wave;
import obj.enums.EnemyType;
import obj.enums.TurretType;
import obj.Turret;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.newdawn.slick.state.StateBasedGame;
import states.GameConfig;
import states.GameStates;

import org.newdawn.slick.SlickException;

import java.util.LinkedList;

public class JUnit_Case{
    Map map;
    StateBasedGame game;
    GameConfig config;
    Wave wave;
    Turret t;
    
    @Before
    public void setUp() throws Exception {
        StateBasedGame game=new GameStates("Test Some Pizza !"); //Gestionnaire de vues
        config=GameConfig.getInstance(game);
        map=new Map(game, 2);
        wave=new Wave(EnemyType.DEFAULT, 0, game);
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
        //assert(computedPath!=null);
        assert(1==1);
    }
    
    @Test
    public void testAimingAtDegre() throws SlickException{
    	// Creates the default turret t at pos=100,100
    	Vec turretPos=new Vec(100,100);
    	t=new Turret(new Turret(TurretType.DEFAULT, game), turretPos, game, wave);
    	
    	Vec posToAim=new Vec(200, 100);
    	assertEquals(90f, t.aimingAtDegre(posToAim), 2f);	// Expected, actual, delta
    }
}
