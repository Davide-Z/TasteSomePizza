package maps;

/**
 * Classe qui représente des vecteurs et positions
 *
 * /!\ Le coin haut gauche est en (0,0), y vers le bas et x vers la droite /!\
 *
 * Created by tic-tac on 15/02/17.
 */
public class Vec {
    protected int x;
    protected int y;
    protected double r;
    protected double angle;

    public Vec(int x, int y){ //Créée un vecteur de l'origine à (x,y)
        this.x=x;
        this.y=y;
        this.setPolar();
    }

    public Vec(float longueur, double angle){ //Créée un vecteur avec sa longueur et son orientation
        this.angle=angle;
        this.r=longueur;
        this.setCart();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
        this.setPolar();
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
        this.setPolar();
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
        this.setCart();
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
        this.setCart();
    }

    public void setCart(){//calcule x et y à partir de angle et r
        double xf=r*Math.sin((double)angle);
        if(xf-Math.floor(xf)>=0.5) {
            x = (int)xf+1;
        }
        else{
            x=(int)xf;
        }

        double yf=r*Math.cos((double)angle);
        if(yf-Math.floor(yf)>=0.5) {
            y = (int)yf+1;
        }
        else{
            y=(int)yf;
        }
    }

    public void setPolar(){//calcule r et angle à partir de x et y
        this.r=Math.sqrt(x*x+y*y);
        this.angle=Math.atan2(x,y);
    }
}
