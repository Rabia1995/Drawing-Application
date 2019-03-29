

import java.awt.Color;
import java.awt.Graphics;

public class MyOval extends MyBoundedShape {


	//Constructor
	public MyOval() {
		super();
		setUnFilled(false);
	} 

	public MyOval(int x1, int y1, int x2, int y2, Color color, boolean notFill) {
		super(x1, y1, x2, y2, color, notFill);
	}


	public void draw(Graphics g) {
		g.setColor(getColor());

		//Check if its filled or unfilled
		if (filled()) {
			g.fillOval(getUpperLeftX(), getUpperLeftY(), getWidth(), getHeight());
		} 
		else 
		{

			g.drawOval(getUpperLeftX(), getUpperLeftY(), getWidth(), getHeight());
		}


	} 
} 