package obj.enums;

/**
 * Created by tic-tac on 15/05/17.
 */
public enum Direction {
    UP(0),
    DOWN(180),
    LEFT(-90),
    RIGHT(90);

    Direction(float angle){
        this.angle=angle;
    }

    public float angle;
}
