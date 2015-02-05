
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import acm.graphics.*;
import acm.program.*;

import java.util.HashMap;
import java.util.Map.Entry;

public class SectionAssignment7 extends GraphicsProgram  {
	
	private static final String labelObjectName = "Name";
	private static final String addButtonName = "Add";
	private static final String removeButtonName = "Remove";
	private static final String clearButtonName = "Clear";
	private static final double BOX_WIDTH = 120;
	private static final double BOX_HEIGHT = 50;
	private static final int WINDOW_WIDTH = 500;
	private static final int WINDOW_HEIGHT = 300;

	public void init(){

		setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
		
		centerX = (double)WINDOW_WIDTH/2 - BOX_WIDTH/2;
		centerY = (double)WINDOW_HEIGHT/2 - BOX_HEIGHT/2;
		
		buttonAdd = new JButton(addButtonName);
		buttonRemove = new JButton(removeButtonName);
		buttonRemove.setActionCommand(removeButtonName);
		buttonsClear = new JButton(clearButtonName);
		buttonsClear.setActionCommand(clearButtonName);
		
		label = new JLabel(labelObjectName);
		field = new JTextField(20);
		field.setActionCommand(addButtonName); // when field generates an action, the name associated with it is the same name as adding a button
		field.addActionListener(this);
		
		add(label,NORTH);
		add(field,NORTH);
		add(buttonAdd,NORTH);
		add(buttonRemove,NORTH);
		add(buttonsClear,NORTH);
		
		addActionListeners();
		addMouseListeners();
	}
	
	
	public void actionPerformed(ActionEvent e){
		String cmd = e.getActionCommand(); // returns name of label clicked
		if (cmd.equals(addButtonName)){
			String buttonName = field.getText();
			GCompound button = createLabelBox(buttonName);
			objectsOnScreen.put(buttonName,button);
			add(button);
		} else if (cmd.equals(removeButtonName)){		
			remove(objectsOnScreen.get(field.getText()));
		} else if (cmd.equals(clearButtonName)){ // this works if you have it add a label - something wrong w/ GCanvas and/or recalling the objects on it.
			for(Entry<String,GCompound> entry : objectsOnScreen.entrySet()){
				GObject button = entry.getValue();
				remove(button);
			}
		}
	}
	
	private GCompound createLabelBox(String boxText){
		GRect box = new GRect(BOX_WIDTH,BOX_HEIGHT);
		GLabel boxLabel = new GLabel(boxText);
		GCompound finalBox = new GCompound();
		finalBox.add(box, centerX, centerY);
		finalBox.add(boxLabel, centerX+(BOX_WIDTH-boxLabel.getWidth())/2, centerY+BOX_HEIGHT/2+boxLabel.getAscent()/2);
		return finalBox;
	}
	
	public void mousePressed(MouseEvent e ) {
		x = e.getX();
		y = e.getY();
	}
	
	public void mouseDragged(MouseEvent e) {
		double dx = e.getX()-x;
		double dy = e.getY()-y;
		GObject object = getElementAt(x,y);
		object.setLocation(object.getX()+dx,object.getY()+dy);
		x += dx;
		y += dy;
	}
	
	private HashMap<String,GCompound> objectsOnScreen = new HashMap<String,GCompound>();
	private JLabel label;
	private JTextField field;
	private JButton buttonAdd;
	private JButton buttonRemove;
	private JButton buttonsClear;
	private double centerX;
	private double centerY;
	private double x;
	private double y;

}
