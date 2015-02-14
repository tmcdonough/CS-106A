/*
 * File: FacePamphletCanvas.java
 * -----------------------------
 * This class represents the canvas on which the profiles in the social
 * network are displayed.  NOTE: This class does NOT need to update the
 * display when the window is resized.
 */


import acm.graphics.*;

import java.awt.*;
import java.util.*;

public class FacePamphletCanvas extends GCanvas 
					implements FacePamphletConstants {
	
	/** 
	 * Constructor
	 * This method takes care of any initialization needed for 
	 * the display
	 */
	public FacePamphletCanvas() {
		// You fill this in
	}

	
	/** 
	 * This method displays a message string near the bottom of the 
	 * canvas.  Every time this method is called, the previously 
	 * displayed message (if any) is replaced by the new message text 
	 * passed in.
	 */
	public void showMessage(String msg) {
		if(status!=null){remove(status);}
		status = new GLabel(msg);
		status.setFont(MESSAGE_FONT);
		status.setLocation(getWidth()/2,getHeight()-BOTTOM_MESSAGE_MARGIN);
		add(status);
	}
	
	
	/** 
	 * This method displays the given profile on the canvas.  The 
	 * canvas is first cleared of all existing items (including 
	 * messages displayed near the bottom of the screen) and then the 
	 * given profile is displayed.  The profile display includes the 
	 * name of the user from the profile, the corresponding image 
	 * (or an indication that an image does not exist), the status of
	 * the user, and a list of the user's friends in the social network.
	 */
	public void displayProfile(FacePamphletProfile profile) {
		clearDisplay();
		displayName(profile.getName());
		displayPicture(profile.getImage());
		displayStatus(profile.getName(), profile.getStatus());
		displayFriends(profile.getFriendsIt());
	}
	
	public void clearDisplay(){
		removeAll();
	}
	
	private void displayName(String name){
		GLabel nameDisplay = new GLabel(name);
		nameDisplay.setColor(Color.BLUE);
		nameDisplay.setFont(PROFILE_NAME_FONT);
		nameY = TOP_MARGIN+nameDisplay.getHeight();
		nameDisplay.setLocation(LEFT_MARGIN,nameY);
		add(nameDisplay);
	}
	
	private void displayPicture(GImage profilePicture){
		if (profilePicture==null){
			displayEmptyProfilePicture();
		} else {
			profilePicture.scale(IMAGE_WIDTH/profilePicture.getWidth(),IMAGE_HEIGHT/profilePicture.getHeight());
			profilePicture.setLocation(LEFT_MARGIN,nameY+IMAGE_MARGIN);
			add(profilePicture);
		}
	}
	
	private void displayStatus(String name, String status){
		GLabel statusDisplay = new GLabel(name+" is "+status);
		statusDisplay.setFont(PROFILE_STATUS_FONT);
		statusDisplay.setLocation(LEFT_MARGIN,getHeight()-STATUS_MARGIN/*+statusDisplay.getHeight()*/);
		add(statusDisplay);
	}
	
	private void displayFriends(Iterator<String> friendList){
		GLabel friendsTitle = new GLabel("Friends: ");
		friendsTitle.setFont(PROFILE_FRIEND_LABEL_FONT);
		double friendY = nameY+IMAGE_MARGIN;
		friendsTitle.setLocation(getWidth()/2,friendY);
		add(friendsTitle);
		while (friendList.hasNext()){
			GLabel friend = new GLabel(friendList.next());
			friend.setFont(PROFILE_FRIEND_FONT);
			friendY+=friend.getHeight();
			friend.setLocation(getWidth()/2,friendY);
			add(friend);
		}
	}
	
	private void displayEmptyProfilePicture(){
		GRect empty = new GRect(IMAGE_WIDTH,IMAGE_HEIGHT);
		GLabel emptyText = new GLabel("No Image");
		emptyText.setFont(PROFILE_IMAGE_FONT);
		empty.setLocation(LEFT_MARGIN,nameY+IMAGE_MARGIN);
		emptyText.setLocation(LEFT_MARGIN+IMAGE_WIDTH/2-emptyText.getWidth()/2,nameY+IMAGE_MARGIN+IMAGE_HEIGHT/2+emptyText.getHeight()/2);
		add(empty);
		add(emptyText);
	}
	
	/* ivars */
	double nameY;
	GLabel status;

	
}
