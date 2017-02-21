package maps;

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
            for(int j=0;j<taille;j++){
                resetCase(i,j);
            }
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

    public void setCase(int i, int j){
        cases[i][j]=1;
    }
    public void resetCase(int i, int j){
        cases[i][j]=0;
    }

    public void setRand(int p){ //Remplit au hasard avec des 1 (p% de 1 en moyenne)
        Random rand=new Random();
        int x;
        for(int i=0;i<taille;i++){
            for(int j=0;j<taille;j++){
                x=rand.nextInt(100);
                if(x<p){
                    setCase(i,j);
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
