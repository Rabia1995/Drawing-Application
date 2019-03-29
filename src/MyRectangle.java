
import java.awt.Color;
import java.awt.Graphics;

public class MyRectangle extends MyBoundedShape {


	//Constructor
	public MyRectangle() {
		super();
		setUnFilled(false);
	}
	
	public MyRectangle(int x1, int y1, int x2, int y2, Color color,boolean notFill) 
	{
		super(x1, y1, x2, y2, color, notFill);
	} 


	public void draw(Graphics g) {g.setColor(getColor());

		//Check if it is filled
		if (filled()) {
			g.fillRect(getUpperLeftX(), getUpperLeftY(), getWidth(), getHeight());
		} 
		else 
		{

			g.drawRect(getUpperLeftX(), getUpperLeftY(), getWidth(), getHeight());
		}

	} 
} 