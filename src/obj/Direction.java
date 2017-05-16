package obj;

public enum Direction{
	UP(0),
	DOWN(3.14),
	LEFT(1.6),
	RIGHT(4.7);

	private Direction(double angle){
		this.angle=angle;
	}
	public final double angle;
}
