/* 
 * File: FacePamphlet.java
 * -----------------------
 * When it is finished, this program will implement a basic social network
 * management system.
 */

import acm.program.*;
import acm.graphics.*;
import acm.util.*;

import java.awt.event.*;

import javax.swing.*;

// CURRENT STATUS:
// Something is wrong with the Canvas
// Program works fine in console mode
// When you implement the canvas, first off some of the images are placed incorrectly.
// Second, getting error messages throughout as you hit the buttons.
// Also, showMessage does not display when you add a profile.


public class FacePamphlet extends /*Console*/Program // console program only for testing - remove once canvas completed 
					implements FacePamphletConstants {

	/**
	 * This method has the responsibility for initializing the 
	 * interactors in the application, and taking care of any other 
	 * initialization that needs to be performed.
	 */
	
	private static final String nameLabelText = "Name";
	private static final String addButtonText = "Add";
	private static final String delButtonText = "Delete";
	private static final String lookupButtonText = "Lookup";
	private static final String statusButtonText = "Change Status";
	private static final String pictureButtonText = "Change Picture";
	private static final String friendButtonText = "Add Friend";
	
	public void init() {
		// You fill this in
		
		db = new FacePamphletDatabase();
		canvas = new FacePamphletCanvas();
		add(canvas);
		
		// North
		nameLabel = new JLabel(nameLabelText);
		nameField = new JTextField(TEXT_FIELD_SIZE);
		addButton = new JButton(addButtonText);
		delButton = new JButton(delButtonText);
		lookupButton = new JButton(lookupButtonText);
		
		add(nameLabel,NORTH);
		add(nameField,NORTH);
		add(addButton,NORTH);
		add(delButton,NORTH);
		add(lookupButton,NORTH);
		
		// West
		statusField = new JTextField(TEXT_FIELD_SIZE);
		statusButton = new JButton(statusButtonText);
		statusField.setActionCommand(statusButtonText);
		statusField.addActionListener(this);
		
		pictureField = new JTextField(TEXT_FIELD_SIZE);
		pictureButton = new JButton(pictureButtonText);
		pictureField.setActionCommand(pictureButtonText);
		pictureField.addActionListener(this);
		
		friendField = new JTextField(TEXT_FIELD_SIZE);
		friendButton = new JButton(friendButtonText);
		friendField.setActionCommand(friendButtonText);
		friendField.addActionListener(this);
		
		add(statusField,WEST);
		add(statusButton,WEST);
		add(new JLabel(EMPTY_LABEL_TEXT), WEST);
		add(pictureField,WEST);
		add(pictureButton,WEST);
		add(new JLabel(EMPTY_LABEL_TEXT), WEST);
		add(friendField,WEST);
		add(friendButton,WEST);
		
		addActionListeners();
    }
    
  
    /**
     * This class is responsible for detecting when the buttons are
     * clicked or interactors are used, so you will have to add code
     * to respond to these actions.
     */
    public void actionPerformed(ActionEvent e) {
		// You fill this in as well as add any additional methods
    	String cmd = e.getActionCommand();
		if (cmd == addButtonText && !nameField.getText().equals("")){
			canvas.showMessage(addProfile(nameField.getText()));
		} else if (cmd == delButtonText && !nameField.getText().equals("")){
			canvas.showMessage(delProfile(nameField.getText()));
			//println(delProfile(nameField.getText()));
		} else if (cmd == lookupButtonText && !nameField.getText().equals("")){
			canvas.showMessage(lookupProfile(nameField.getText()));
			//println(lookupProfile(nameField.getText()));
		} else if (cmd == statusButtonText && !statusField.getText().equals("")){
			canvas.showMessage(changeStatus(statusField.getText()));
			//println(changeStatus(statusField.getText()));
		} else if (cmd == pictureButtonText && !pictureField.getText().equals("")){
			canvas.showMessage(changePicture(pictureField.getText()));
			//println(changePicture(pictureField.getText()));
		} else if (cmd == friendButtonText && !friendField.getText().equals("")){
			canvas.showMessage(addFriend(friendField.getText()));
			//println(addFriend(friendField.getText()));
		}
	}
    
    private String addProfile(String profileName){
    	if(db.containsProfile(profileName)){
    		currentProfile = profileName;
    		canvas.displayProfile(db.getProfile(currentProfile));
			return "Profile already exists: "+db.getProfile(profileName).toString();
		} else {
			db.addProfile(new FacePamphletProfile(profileName));
			currentProfile = profileName;
    		canvas.displayProfile(db.getProfile(currentProfile));
			return "Added new profile: "+profileName;
		}
    	
    }
    
    private String delProfile(String profileName){
    	if(db.containsProfile(profileName)){
    		db.deleteProfile(profileName);
    		currentProfile = null;
    		canvas.clearDisplay();
    		return profileName+"'s profile has been deleted.";
    	} else {
    		currentProfile = null;
    		canvas.clearDisplay();
    		return "Profile with name "+profileName+" does not exist.";
    	}
    }
    
    private String lookupProfile(String profileName){
    	if(db.containsProfile(profileName)){
    		currentProfile = profileName;
    		canvas.displayProfile(db.getProfile(currentProfile));
    		return "Lookup: "+db.getProfile(profileName).toString();
    	} else {
    		currentProfile = null;
    		canvas.clearDisplay();
    		return "Profile with name "+profileName+" does not exist.";
    	}
    }
    
    private String addFriend(String addedFriend){
    	if (currentProfile==null){
    		return "Please select a profile";
    	}
    	if(!db.containsProfile(addedFriend)){
    		return "Sorry, that friend does not have a profile.";
    	} else {
    		if(db.getProfile(currentProfile).addFriend(addedFriend)){
    			db.getProfile(currentProfile).addFriend(addedFriend);
    			db.getProfile(addedFriend).addFriend(currentProfile);
    		} else {
    			return addedFriend+" is already a friend of "+currentProfile;
    		}
    		db.getProfile(addedFriend).addFriend(currentProfile);
    		canvas.displayProfile(db.getProfile(currentProfile));
    		return "Added friend "+addedFriend+" to "+currentProfile+"'s profile.";
    	}
    }
    
    private String changeStatus(String statusUpdate){
    	if (currentProfile==null){
    		return "Please select a profile before updating status.";
    	}
    	db.getProfile(currentProfile).setStatus(statusUpdate);
    	canvas.displayProfile(db.getProfile(currentProfile));
    	return "Updated "+currentProfile+"'s status: "+statusUpdate;
    }
    
    private String changePicture(String filename){
    	if (currentProfile==null){
    		return "Please select a profile";
    	}
    	GImage image = null;
    	try{
    		image = new GImage(filename);
    	} catch (ErrorException ex){
    		return "Cannot find that picture file...";
    	}
    	db.getProfile(currentProfile).setImage(image);
    	canvas.displayProfile(db.getProfile(currentProfile));
    	return "Updated "+currentProfile+"'s picture";
    }
    
    /* ivars */
    private String currentProfile;
    private JLabel nameLabel;
    private JTextField nameField;
    private JButton addButton;
    private JButton delButton;
    private JButton lookupButton;
    private JTextField statusField;
    private JButton statusButton;
    private JTextField pictureField;
    private JButton pictureButton;
    private JTextField friendField;
    private JButton friendButton;
    
    private FacePamphletDatabase db;
    private FacePamphletCanvas canvas;
}
