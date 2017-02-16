package Maps;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by tic-tac on 15/02/17.
 */
public class Mat {
    private int[][] cases;
    private int taille;
    public Mat(int taille){//créée une matrice dont les termes sont nuls
        cases =new int[taille][taille];
        this.taille=taille;
        for(int i=0;i<taille;i++) {
            Arrays.fill(cases[i], 0);
        }
    }

    public int[][] getCases(){
        return cases;
    }

    public int getSum(){
        int S=0;
        for(int i=0;i<taille;i++){
            for(int j=0;j<taille;j++){
                S+= cases[i][j];
            }
        }
        return S;
    }


    public void setRand(int p){
        Random rand=new Random();
        int x;
        for(int i=0;i<taille;i++){
            for(int j=0;j<taille;j++){
                x=rand.nextInt(100);
                if(x<p){
                    cases[i][j]=1;
                }
            }
        }
    }
    @Override
    public String toString(){
        String string="";
        for(int i=0;i<taille;i++){
            string+="|";
            for(int j=0;j<taille;j++){
                string+=" "+ cases[i][j];
            }
            string+="|\n";
        }
        return string;
    }
}
