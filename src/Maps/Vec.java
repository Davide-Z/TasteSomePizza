package Maps;

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
    protected float r;
    protected float angle;

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

    public float getR() {
        return r;
    }

    public void setR(float r) {
        this.r = r;
        this.setCart();
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
        this.setCart();
    }

    public Vec(int x, int y){ //Créée un vecteur de l'origine à (x,y)
        this.x=x;
        this.y=y;
    }

    public Vec(float longueur, float angle){ //Créée un vecteur avec sa longueur et son orientation
        this.angle=angle;
        this.r=longueur;
    }

    public void setCart(){//calcule x et y à partir de angle et r
        y=(int)(r*Math.sin((double)angle));
        x=(int)(r*Math.cos((double)angle));
    }

    public void setPolar(){//calcule r et angle à partir de x et y
        this.r=(float)Math.sqrt(x*x+y*y);
        this.angle=(float)Math.atan2(x,y);
    }
}
