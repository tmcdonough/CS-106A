/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import acm.program.*;

import java.awt.event.*;

import javax.swing.*;

public class NameSurfer extends Program implements NameSurferConstants {
	
	private static final String labelName = "Name";
	private static final String graphButtonName = "Add";
	private static final String deleteButtonName = "Delete";
	private static final String clearButtonName = "Clear";

/* Method: init() */
/**
 * This method has the responsibility for reading in the data base
 * and initializing the interactors at the bottom of the window.
 */
	public void init() {
		
		db = new NameSurferDataBase("names-data.txt");
		
		graph = new NameSurferGraph();
		add(graph);
		
		
	    label = new JLabel(labelName);
		graphButton = new JButton(graphButtonName);
		deleteButton = new JButton(deleteButtonName);
		clearButton = new JButton(clearButtonName);
		
		field = new JTextField(20);
		field.setActionCommand(graphButtonName); // by setting action command for the field to the same name as that of the graph button, we get the same result by hitting the graph button as we do by pressing 'enter'
		field.addActionListener(this);
		
		add(label,NORTH);
		add(field,NORTH);
		add(graphButton,NORTH);
		//add(deleteButton,NORTH);
		add(clearButton,NORTH);
		
		addActionListeners();
	    
	}

/* Method: actionPerformed(e) */
/**
 * This class is responsible for detecting when the buttons are
 * clicked, so you will have to define a method to respond to
 * button actions.
 */
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if (cmd == graphButtonName){
			//println("Graph: "+db.findEntry(field.getText()));
			graph.addEntry(db.findEntry(field.getText()));
			graph.update();
		} else if (cmd == clearButtonName){
			graph.clear();
			graph.update();
		//} else if (cmd == deleteButtonName){
			//graph.deleteEntry(db.findEntry(field.getText()));
			//graph.update();
		}
	}
	
	private JLabel label;
	private NameSurferDataBase db;
	private JButton graphButton;
	private JButton deleteButton;
	private JButton clearButton;
	private JTextField field;
	private NameSurferGraph graph;
}
