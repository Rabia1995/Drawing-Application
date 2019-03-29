
import java.awt.Color;

public abstract class MyBoundedShape extends MyShape {
	private boolean unFilled; 

	//Constructor
	public MyBoundedShape() {

		super();
		setUnFilled(false);
		}


	public MyBoundedShape(int x1, int y1, int x2, int y2, Color color,boolean filled) {
		super(x1, y1, x2, y2, color);
		unFilled = filled;
	}

	//Check if its filled or unfilled
	public boolean filled() {
		return unFilled;
	} 


	public void setUnFilled(boolean filled) {
		unFilled = filled;
	} 


	//Get Methods
	public int getUpperLeftX() {

		return Math.min(getX1(), getX2());
	}


	public int getUpperLeftY() {

		return Math.min(getY1(), getY2());
	}


	public int getWidth() {

		return Math.abs(getX2() - getX1());
	}


	public int getHeight() {

		return Math.abs(getY2() - getY1());
	}
} 