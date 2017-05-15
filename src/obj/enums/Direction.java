package obj.enums;

/**
 * Created by tic-tac on 15/05/17.
 */
public enum Direction {
    UP(0),
    DOWN(Math.PI),
    LEFT(-Math.PI/2),
    RIGHT(Math.PI/2);

    private Direction(double angle){
        this.angle=angle*180/(Math.PI);
    }

    public double angle;
}
