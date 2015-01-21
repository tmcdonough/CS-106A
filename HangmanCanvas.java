/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import java.awt.Font;

import acm.graphics.*;

public class HangmanCanvas extends GCanvas {

/** Resets the display so that only the scaffold appears */
	public void reset() {
		removeAll();
		wrongLetters = "";
		addScaffold(getWidth(),getHeight());
	}

/**
 * Updates the word on the screen to correspond to the current
 * state of the game.  The argument string shows what letters have
 * been guessed so far; unguessed letters are indicated by hyphens.
 */
	public void displayWord(String word) {
		// first make sure there isn't an existing label - if there is, remove it
		GObject checkForOldLabel = getElementAt(guessPosX,guessPosY);
		if (checkForOldLabel != null) {
			remove(checkForOldLabel);
		}
		// then re-add the label for the updated "guessed" word
		guessSoFar = new GLabel(word);
		guessPosX = scaffoldStart.getX()/2;
		guessPosY = scaffoldStart.getY()+scaffoldStart.getY()/10;
		guessSoFar.setLocation(guessPosX,guessPosY);
		guessSoFar.setFont(new Font("Arial",Font.BOLD, 24));
		add(guessSoFar);
	}

/**
 * Updates the display to correspond to an incorrect guess by the
 * user.  Calling this method causes the next body part to appear
 * on the scaffold and adds the letter to the list of incorrect
 * guesses that appears at the bottom of the window.
 */
	public void noteIncorrectGuess(char letter) {
		addToWrongLetters(letter);
		displayWrongLetters();
		addNextBodyPart(wrongLetters.length());
		
	}
	private void addToWrongLetters(char letter) {
		wrongLetters+=letter;
	}
	private void displayWrongLetters() {
		// first make sure there isn't an existing label - if there is, remove it
		GObject checkForOldLabel = getElementAt(wrongLettersPosX,wrongLettersPosY);
		if (checkForOldLabel != null) {
			remove(checkForOldLabel);
		}
		wrongLettersPosX = guessPosX;
		wrongLettersPosY = guessPosY+guessSoFar.getHeight()*2;
		GLabel wrongLetterDisplay = new GLabel(wrongLetters);
		wrongLetterDisplay.setLocation(wrongLettersPosX,wrongLettersPosY);
		add(wrongLetterDisplay);
	}
	private void addScaffold(int canvasWidth, int canvasHeight){
		
		ropeStart = new GPoint(canvasWidth/2,canvasHeight/5);
		ropeEnd = new GPoint(ropeStart.getX(),ropeStart.getY()+ROPE_LENGTH);
		beamStart = new GPoint(ropeStart.getX()-BEAM_LENGTH,ropeStart.getY());
		beamEnd = ropeStart;
		scaffoldStart = new GPoint(beamStart.getX(),beamStart.getY()+SCAFFOLD_HEIGHT);
		scaffoldEnd = beamStart;
		
		rope = new GLine(ropeStart.getX(),ropeStart.getY(),ropeEnd.getX(),ropeEnd.getY());
		beam = new GLine(beamStart.getX(),beamStart.getY(),beamEnd.getX(),beamEnd.getY());
		scaffold = new GLine(scaffoldStart.getX(),scaffoldStart.getY(),scaffoldEnd.getX(),scaffoldEnd.getY());
		
		add(rope);
		add(beam);
		add(scaffold);
	}
	private void addNextBodyPart(int numberOfLetters){
		if (numberOfLetters==1) {
			addHead();
		} else if (numberOfLetters==2) {
			addBody();
		} else if (numberOfLetters==3) {
			addLeftArm();
		} else if (numberOfLetters==4) {
			addRightArm();
		} else if (numberOfLetters==5) {
			addLeftLeg();
		} else if (numberOfLetters==6) {
			addRightLeg();
		} else if (numberOfLetters==7) {
			addLeftFoot();
		} else {
			addRightFoot();
		}
	}
	
	private void addHead() {
		double x = ropeEnd.getX()-(double)HEAD_RADIUS;
		double y = ropeEnd.getY();
		head = new GOval(x,y,HEAD_RADIUS*2,HEAD_RADIUS*2);
		add(head);
	}
	private void addBody() {
		GPoint start = new GPoint(ropeEnd.getX(),ropeEnd.getY()+HEAD_RADIUS*2);
		GPoint end = new GPoint(start.getX(),start.getY()+BODY_LENGTH);
		body = new GLine(start.getX(),start.getY(),end.getX(),end.getY());
		add(body);
	}
	private void addLeftArm() {
		// arm
		GPoint start = new GPoint(ropeEnd.getX(),ropeEnd.getY()+HEAD_RADIUS*2+ARM_OFFSET_FROM_HEAD);
		GPoint end = new GPoint(start.getX()-UPPER_ARM_LENGTH,start.getY());
		arm = new GLine(start.getX(),start.getY(),end.getX(),end.getY());
		// "hand"
		GPoint start2 = end;
		GPoint end2 = new GPoint(start2.getX(),start.getY()+LOWER_ARM_LENGTH);
		hand = new GLine(start2.getX(),start2.getY(),end2.getX(),end2.getY());
		add(arm);
		add(hand);
	}
	private void addRightArm() {
		// arm
		GPoint start = new GPoint(ropeEnd.getX(),ropeEnd.getY()+HEAD_RADIUS*2+ARM_OFFSET_FROM_HEAD);
		GPoint end = new GPoint(start.getX()+UPPER_ARM_LENGTH,start.getY());
		arm = new GLine(start.getX(),start.getY(),end.getX(),end.getY());
		// "hand"
		GPoint start2 = end;
		GPoint end2 = new GPoint(start2.getX(),start.getY()+LOWER_ARM_LENGTH);
		hand = new GLine(start2.getX(),start2.getY(),end2.getX(),end2.getY());
		add(arm);
		add(hand);
	}
	private void addLeftLeg() {
		// hip
		GPoint start = new GPoint(ropeEnd.getX(),ropeEnd.getY()+HEAD_RADIUS*2+BODY_LENGTH);
		GPoint end = new GPoint(start.getX()-HIP_WIDTH,start.getY());
		hip = new GLine(start.getX(),start.getY(),end.getX(),end.getY());
		// leg
		GPoint start2 = end;
		GPoint end2 = new GPoint(start2.getX(),start2.getY()+LEG_LENGTH);
		leg = new GLine(start2.getX(),start2.getY(),end2.getX(),end2.getY());
		add(hip);
		add(leg);
	}
	private void addRightLeg() {
		// hip
		GPoint start = new GPoint(ropeEnd.getX(),ropeEnd.getY()+HEAD_RADIUS*2+BODY_LENGTH);
		GPoint end = new GPoint(start.getX()+HIP_WIDTH,start.getY());
		hip = new GLine(start.getX(),start.getY(),end.getX(),end.getY());
		// leg
		GPoint start2 = end;
		GPoint end2 = new GPoint(start2.getX(),start2.getY()+LEG_LENGTH);
		leg = new GLine(start2.getX(),start2.getY(),end2.getX(),end2.getY());
		add(hip);
		add(leg);
	}
	private void addLeftFoot() {
		GPoint start = new GPoint(ropeEnd.getX()-HIP_WIDTH,ropeEnd.getY()+HEAD_RADIUS*2+BODY_LENGTH+LEG_LENGTH);
		GPoint end = new GPoint(start.getX()-FOOT_LENGTH,start.getY());
		foot = new GLine(start.getX(),start.getY(),end.getX(),end.getY());
		add(foot);
	}
	private void addRightFoot() {
		GPoint start = new GPoint(ropeEnd.getX()+HIP_WIDTH,ropeEnd.getY()+HEAD_RADIUS*2+BODY_LENGTH+LEG_LENGTH);
		GPoint end = new GPoint(start.getX()+FOOT_LENGTH,start.getY());
		foot = new GLine(start.getX(),start.getY(),end.getX(),end.getY());
		add(foot);
	}
	
/* variables */
	private String wrongLetters = "";
	private GPoint ropeStart;
	private GPoint ropeEnd;
	private GPoint beamStart;
	private GPoint beamEnd;
	private GPoint scaffoldStart;
	private GPoint scaffoldEnd;
	private GPoint headPosition;
	private GOval head;
	private GLine body;
	private GLine arm;
	private GLine hand;
	private GLine hip;
	private GLine leg;
	private GLine foot;
	private GLine rope;
	private GLine beam;
	private GLine scaffold;
	private double guessPosX, guessPosY;
	private double wrongLettersPosX, wrongLettersPosY;
	private GLabel guessSoFar;
	
	

/* Constants for the simple version of the picture (in pixels) */
	private static final int SCAFFOLD_HEIGHT = 360;
	private static final int BEAM_LENGTH = 144;
	private static final int ROPE_LENGTH = 18;
	private static final int HEAD_RADIUS = 36;
	private static final int BODY_LENGTH = 144;
	private static final int ARM_OFFSET_FROM_HEAD = 28;
	private static final int UPPER_ARM_LENGTH = 72;
	private static final int LOWER_ARM_LENGTH = 44;
	private static final int HIP_WIDTH = 36;
	private static final int LEG_LENGTH = 108;
	private static final int FOOT_LENGTH = 28;

}
