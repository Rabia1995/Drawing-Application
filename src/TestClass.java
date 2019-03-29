

import java.awt.EventQueue;

import javax.swing.JFrame;

public class TestClass {

	private JFrame frame;

	
	 //Launch the application.
	 
	public static void main(String[] args) {
		DrawFrame application = new DrawFrame();
		application.setVisible(true);
		application.setSize(600, 600);
		
	}

	// Create the application.
	public TestClass() {
		initialize();
	}

	
	 // Initialize the contents of the frame.
	 
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 746, 413);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
	}
}
