//Rabia Tariq

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class DrawPanel extends JPanel {
	private MyShape shapes[];
	private int shapeCount;
	private int shapeType;
	private MyShape currentShape;
	private Color currentColor;
	private boolean filledShape;
	private JLabel statusLabel;


	public DrawPanel(JLabel status) {
		shapes = new MyShape[100];
		shapeCount = 0;
		shapeType = 0;
		currentShape = null;
		currentColor = Color.BLACK;

		setBackground(Color.WHITE);
		statusLabel = status;

		MouseHandler handler = new MouseHandler();
		addMouseListener(handler);
		addMouseMotionListener(handler);

	}

	class MouseHandler extends MouseAdapter implements MouseListener,MouseMotionListener 
	{
		@Override
		public void mousePressed(MouseEvent e) {
			if (shapeType == 0) 
			{
				currentShape = new MyLine(e.getX(), e.getY(), e.getX(), e.getY(),
						currentColor);
			} 
			
			else if (shapeType == 1) 
			{
				currentShape = new MyOval(e.getX(), e.getY(), e.getX(), e.getY(),
						currentColor, filledShape);
			} 
			
			else if (shapeType == 2) 
			{
				currentShape = new MyRectangle(e.getX(), e.getY(), e.getX(),
						e.getY(), currentColor, filledShape);
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			currentShape.setX2(e.getX());
			currentShape.setY2(e.getY());
			shapes[shapeCount] = currentShape;
			shapeCount++;
			currentShape = null;
			repaint();
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			currentShape.setX2(e.getX());
			currentShape.setY2(e.getY());
			statusLabel.setText(String.format("(%d,%d)", e.getX(), e.getY()));
			repaint();
			
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			statusLabel.setText(String.format("(%d, %d)", e.getX(), e.getY()));
		}
	}

		public void setCurrentColor(Color color) {
			currentColor = color;
		}

		public void setFilledShape(boolean filled) {
			filledShape = filled;
		}

		public void setCurrentShapeType(int shape) {
			shapeType = shape;
		}
		
		public void clearLastShape() {
			if (shapeCount <= 0) {
				return;
			} 
			else 
			{
				currentShape = null;
				shapeCount--;
				repaint();
			}
		}

		public void clearDrawing() {
			shapeCount = 0;
			repaint();
		}

		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			repaint();
			for (int i = 0; i < shapeCount; i++) {
				shapes[i].draw(g);
			}

			if (currentShape != null) {
				currentShape.draw(g);
			}
			
		}

		public void loadDrawing() {
	
			ObjectInputStream input = null;
	
			try {
		
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setFileSelectionMode(
						JFileChooser.FILES_ONLY);
				int result = fileChooser.showOpenDialog(DrawPanel.this);
		
				if (result == JFileChooser.CANCEL_OPTION)
					return;
				File fileName = fileChooser.getSelectedFile();
		
				if((fileName == null) ||(fileName.getName().equals(""))) {
			
					JOptionPane.showMessageDialog(DrawPanel.this,
							"Invalid File Name", "Invalid File Name", JOptionPane.ERROR_MESSAGE);
					return;
				}
		
					input = new ObjectInputStream(new FileInputStream(fileName));
		
					shapeCount = (Integer)input.readObject();
					shapes = (MyShape[]) input.readObject();
					repaint();
			
			}
			
			catch(EOFException eofexception) 
			{
				JOptionPane.showMessageDialog(DrawPanel.this, "No more records in file.",
				"End of file", JOptionPane.ERROR_MESSAGE);
			}
	
			catch (ClassNotFoundException classNotFoundException) 
			{
				JOptionPane.showMessageDialog(DrawPanel.this, "Unable to create object",
						"Class Not Found", JOptionPane.ERROR_MESSAGE);
			}
	
			catch (IOException ioException) 
			{
				JOptionPane.showMessageDialog(DrawPanel.this, "Error opening file.",
						"Error", JOptionPane.ERROR_MESSAGE);
			}
	
			finally 
			{
		
				try {
			
					if(input != null)
						input.close();
				}
		
				catch(IOException ioException) {
			
					JOptionPane.showMessageDialog(DrawPanel.this, "Error closing file.", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		
		public void saveDrawing() {
			
			ObjectOutputStream output = null;
			
			try {
				
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				
				int result = fileChooser.showSaveDialog(DrawPanel.this);
				
				if (result == JFileChooser.CANCEL_OPTION)
					return;
				
				File fileName = fileChooser.getSelectedFile();
				
				if ((fileName == null)||(fileName.getName().equals("")))
				{
					JOptionPane.showMessageDialog(DrawPanel.this, "Invalid File Name", "Invalid File Name",
							JOptionPane.ERROR_MESSAGE);
					return;
					
				}
				
				output = new ObjectOutputStream(new FileOutputStream(fileName));
				
				output.writeObject(shapeCount);
			}
			
			catch (IOException ioException) 
			{
				JOptionPane.showConfirmDialog(DrawPanel.this, "Error Opening File", "Error.",
						JOptionPane.ERROR_MESSAGE);
			}
			
			finally {
				try 
				{
					
					if(output != null)
						output.close();
				}
				
				catch(IOException ioException) {
					JOptionPane.showMessageDialog(DrawPanel.this, "Error closing file.","Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		}


}