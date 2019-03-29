//Rabia Tariq

import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.Font;


public class DrawFrame extends JFrame {
	private JComboBox<?> shapeSelection;
	private JComboBox<Object> colorBox;
	private JCheckBox Filled;
	private JButton undo;
	private JButton clear;
	private JButton load;
	private JButton save;
	private JPanel Panel;
    private JPanel statusBar;
    private DrawPanel mouse;
    private JLabel statusLabel;


    private String colorNames[] = { "Black","White", "Pink", "Red","Blue","Orange","Magenta","Yellow", "Cyan",
    "Green","Gray","Dark Gray","Light Gray"};
    private Color colors[] = { Color.BLACK, Color.WHITE, Color.PINK,
    Color.RED, Color.BLUE, Color.ORANGE, Color.MAGENTA,
    Color.YELLOW, Color.CYAN, Color.GREEN, Color.GRAY, Color.DARK_GRAY,
    Color.LIGHT_GRAY };


    private String[] shapeChoice = {"Line","Oval","Rectangle" };
    private int shapeNum[] = { 0, 1, 2 };


    public DrawFrame() {
    super("Java Drawings");
    
    Panel = new JPanel(new FlowLayout());
	statusBar = new JPanel();
	statusLabel = new JLabel("");
	
	
	undo = new JButton("Undo");
    Panel.add(undo);
	undo.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			mouse.clearLastShape();
		}
	});
    undo.setFont(new Font("Arial", Font.PLAIN, 20));
    
    clear = new JButton("Clear");
    Panel.add(clear);
	clear.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			mouse.clearDrawing();
		}
	});

    clear.setFont(new Font("Arial", Font.PLAIN, 20));
    
    
        
    colorBox = new JComboBox<Object>(colorNames);
    Panel.add(colorBox, BorderLayout.NORTH);
	colorBox.addItemListener(new ItemListener() {
		public void itemStateChanged(ItemEvent e) {
			if (e.getStateChange() == ItemEvent.SELECTED)
				;
			mouse.setCurrentColor(colors[colorBox.getSelectedIndex()]);
		}
	});
    colorBox.setFont(new Font("Arial", Font.PLAIN, 20));

    shapeSelection = new JComboBox(shapeChoice);
    Panel.add(shapeSelection);
	shapeSelection.addItemListener(new ItemListener() {
		public void itemStateChanged(ItemEvent e) {
			if (e.getStateChange() == ItemEvent.SELECTED);
			mouse.setCurrentShapeType(shapeNum[shapeSelection.getSelectedIndex()]);
		}
	});
    shapeSelection.setFont(new Font("Arial", Font.PLAIN, 20));
    
    
    Filled = new JCheckBox("Filled");
	Filled.setFont(new Font("Arial", Font.PLAIN, 20));
	Panel.add(Filled);
	Filled.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			mouse.setFilledShape(Filled.isSelected());
		}	
	});


	getContentPane().add(Panel, BorderLayout.NORTH);
	getContentPane().add(statusBar, BorderLayout.SOUTH);
	
	load = new JButton("Load");
	statusBar.add(load);
	load.setFont(new Font("Arial", Font.PLAIN, 20));
	load.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			mouse.loadDrawing();
		}
	});   
	
	save = new JButton("Save");
	statusBar.add(save);
	save.setFont(new Font("Arial", Font.PLAIN, 20));
	save.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			mouse.saveDrawing();
		}
	});
	statusBar.add(statusLabel);

	mouse = new DrawPanel(statusLabel);
	getContentPane().add(mouse, BorderLayout.CENTER);

	
	}
}
