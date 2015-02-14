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

public class FacePamphletCanvasBackup extends GCanvas 
					implements FacePamphletConstants {
	
	/** 
	 * Constructor
	 * This method takes care of any initialization needed for 
	 * the display
	 */
	public FacePamphletCanvasBackup() {
		// You fill this in
	}

	
	/** 
	 * This method displays a message string near the bottom of the 
	 * canvas.  Every time this method is called, the previously 
	 * displayed message (if any) is replaced by the new message text 
	 * passed in.
	 */
	public void showMessage(String msg) {
		
		// removes the last console message
		if(console!=null){remove(console);}
		
		// sets parameters for the console msg location
		double x = getWidth()/2;
		double y = getHeight()-BOTTOM_MESSAGE_MARGIN;
				
		
		// add the newest console msg
		console = new GLabel(msg);
		console.setFont(MESSAGE_FONT);
		console.setLocation(x,y);
		add(console);
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
		
		clearProfileView();
		
		addProfileName(profile);
		addProfileStatus(profile);
		addProfilePicture(profile);
		addProfileFriends(profile);

	}
	
	public void clearProfileView(){
		
		if(profileName!=null&&profileName.isVisible()){remove(profileName);}
		if(profileImage!=null&&profileImage.isVisible()){remove(profileName);}
		if(blankProfilePicture!=null&&blankProfilePicture.isVisible()){remove(blankProfilePicture);}
		if(blankProfilePictureLabel!=null&&blankProfilePictureLabel.isVisible()){remove(blankProfilePictureLabel);}
		if(profileStatus!=null&&profileStatus.isVisible()){remove(profileStatus);}
		if(profileFriends!=null&&profileFriends.isVisible()){remove(profileFriends);}
		
	}
	
	// add the profile name to the canvas
	private void addProfileName(FacePamphletProfile profile){
		profileName = new GLabel(profile.getName());
		profileName.setColor(Color.BLUE);
		profileName.setFont(PROFILE_NAME_FONT);
		double x = LEFT_MARGIN;
		double y = TOP_MARGIN+profileName.getHeight();
		profileName.setLocation(x,y);
		add(profileName);
	}
	
	// add the profile status to the canvas
	private void addProfileStatus(FacePamphletProfile profile){
		if(!profile.getStatus().equals("")){
			profileStatus = new GLabel(profile.getName()+" is "+profile.getStatus());
			profileStatus.setFont(PROFILE_STATUS_FONT);
			double x = LEFT_MARGIN;
			double y = getHeight()-STATUS_MARGIN+profileStatus.getHeight();
			profileStatus.setLocation(x,y);
			add(profileStatus);
		} else {
			profileStatus = new GLabel("No current status");
			profileStatus.setFont(PROFILE_STATUS_FONT);
			double x = LEFT_MARGIN;
			double y = getHeight()-STATUS_MARGIN+profileStatus.getHeight();
			profileStatus.setLocation(x,y);
			add(profileStatus);
		}
	}
	
	// add the profile picture to the canvas
	private void addProfilePicture(FacePamphletProfile profile){
		if(!profile.getImage().equals(null)){
			profileImage = profile.getImage();
			profileImage.scale(IMAGE_WIDTH,IMAGE_HEIGHT);
			blankProfilePicture.setLocation(LEFT_MARGIN,profileName.getY()+IMAGE_MARGIN);
			add(blankProfilePicture);
		} else {
			blankProfilePicture = new GRect(IMAGE_WIDTH,IMAGE_HEIGHT);
			blankProfilePicture.setLocation(LEFT_MARGIN,profileName.getY()+IMAGE_MARGIN);
			add(blankProfilePicture);
			blankProfilePictureLabel = new GLabel("No Image");
			blankProfilePictureLabel.setFont(PROFILE_IMAGE_FONT);
			double x = IMAGE_WIDTH/2-blankProfilePictureLabel.getWidth()/2+blankProfilePicture.getX();
			double y = blankProfilePicture.getY()+IMAGE_HEIGHT/2+blankProfilePictureLabel.getHeight()/2;
			blankProfilePictureLabel.setLocation(x,y);
			add(blankProfilePictureLabel);
		}
	}
	
	// add the friends list to the canvas
	private void addProfileFriends(FacePamphletProfile profile){
		profileFriends = new GCompound();
		
		GLabel friendsHeader = new GLabel("Friends:");
		friendsHeader.setLocation(getWidth()/2,IMAGE_MARGIN);
		friendsHeader.setFont(PROFILE_FRIEND_LABEL_FONT);
		double currentY = IMAGE_MARGIN;
		profileFriends.add(friendsHeader);
		
		ArrayList<FacePamphletProfile> friends = new ArrayList<FacePamphletProfile>();
		for (int i = 0; i < friends.size(); i++ ){
			GLabel friend = new GLabel(friends.get(i).getName());
			friend.setFont(PROFILE_FRIEND_FONT);
			currentY += friend.getHeight();
			friend.setLocation(getWidth()/2,currentY);
			profileFriends.add(friend);
		}
		
		add(profileFriends);
	}
	

	
	/* ivars */
	GLabel console;
	GLabel profileName;
	GImage profileImage;
	GLabel profileStatus;
	GRect blankProfilePicture;
	GLabel blankProfilePictureLabel;
	GCompound profileFriends;
	GObject remove;
	
}
