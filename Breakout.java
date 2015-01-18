/*
 * File: Breakout.java
 * -------------------
 * Name:
 * Section Leader:
 * 
 * This file will eventually implement the game of Breakout.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Breakout extends GraphicsProgram {

/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;

/** Dimensions of game board (usually the same) */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;

/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 10;

/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 30;

/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;

/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 10;

/** Separation between bricks */
	private static final int BRICK_SEP = 4;

/** Width of a brick */
	private static final int BRICK_WIDTH =
	  (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;

/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

/** Number of turns */
	private static final int NTURNS = 3;

/** DELAY */
	private static final int DELAY = 30;
	
/* Method: run() */
/** Runs the Breakout program. */
	public void run() {
		addMouseListeners();
		setup();
		while (gameover==false) {
			play();
		}
		if (lives>0) {
			removeAll();
			String labelText = "You win!";
			GLabel winLabel = new GLabel(labelText,HEIGHT/2,WIDTH/2);
			add(winLabel);
		} else {
			removeAll();
			String labelText = "You lose :(";
			GLabel loseLabel = new GLabel(labelText,HEIGHT/2,WIDTH/2);
			add(loseLabel);
			}
		}
		/* You fill this in, along with any subsidiary methods */
	
	// the two main components of this code are 'setup' and 'play', which are defined below:
	
	private void setup() {
		addRows();
		addPaddle();
		}
	private void play() {
		dropBall();
		alive = true;
		while (alive) {
			moveBall();
			pause(DELAY);
			ballNorth = Ball.getY();
			ballEast = Ball.getX()+BALL_RADIUS*2;
			ballSouth = Ball.getY()+BALL_RADIUS*2;
			ballWest = Ball.getX();
			centerOfBallX = Ball.getX()+BALL_RADIUS;
			centerOfPaddleX = Paddle.getX()+PADDLE_WIDTH/2;
			
			checkForWalls();
			checkForCollisions();
			if (numBricks == 0) {
				gameover = true;
				break;
			}
		}
	}
	
	// This section includes the code for the actual playing of the game:
	
	private void dropBall(){
		Ball = new GOval(WIDTH/2,HEIGHT/2,BALL_RADIUS*2,BALL_RADIUS*2);
		Ball.setFilled(true);
		vy = 3*8;/* +3 means move down */
		vx = rgen.nextDouble(1.0,3.0);
		if (rgen.nextBoolean(0.5)) {
			vx = -vx;
		}
		add(Ball);
	}
	private void moveBall(){
		tempVx = vx;
		tempVy = vy;
		adjustVelocity();
		Ball.setLocation(Ball.getX()+tempVx,Ball.getY()+tempVy);
	}
	private void adjustVelocity() {
		if (vy>0) {
			Counter = 0;
			double triesRequired = vy; // we want to test to see if an object will show up at 1 pixel away, 2 pixels away, 3 pixels away ... n pixels away, where n is the number of pixels you're slated to move next.
			while (Counter<triesRequired) {
				Counter++;
				if(checkPointForObject(centerOfBallX,ballSouth+Counter)) {
					tempVy = Counter;
					tempVx = vx*(tempVy/vy); // scales the change in velocity for x as well, to ensure that the line will remain straight
					break;
				} else {
					continue;
				}
			}
			Counter = 0;
			if (vx>0) {
				triesRequired = vx;
				while(Counter<triesRequired) {
					Counter++;
					if (checkPointForObject(ballEast+Counter,centerOfBallY) || ballEast + Counter == WIDTH) {
						double distanceToSideWall = Math.sqrt((Counter*Counter+vy*vy));
						double distanceToBottomWall = Math.sqrt((tempVy*tempVy+vx*vx));
						if (distanceToSideWall<distanceToBottomWall) { // basically we first looked at objects approaching below us, but now we look at obj we're approaching on our side. This checks to see which object is closer and sets the velocity such that it will directly hit that object.
							tempVx = Counter;
							tempVy = vy*(tempVx/vx);
						}
						break;
						} else {
							continue;
						}
					}
				} else if (vx<0) {
					triesRequired = -vx;
					while(Counter<triesRequired) {
						Counter++;
						if (checkPointForObject(ballWest-Counter,centerOfBallY) || ballWest - Counter == 0) {
							double distanceToSideWall = Math.sqrt((Counter*Counter+vy*vy));
							double distanceToBottomWall = Math.sqrt((tempVy*tempVy+vx*vx));
							if (distanceToSideWall<distanceToBottomWall) { // basically we first looked at objects approaching below us, but now we look at obj we're approaching on our side. This checks to see which object is closer and sets the velocity such that it will directly hit that object.
								tempVx = -Counter;
								tempVy = vy*(tempVx/vx);
							}
							break;
							} else {
								continue;
						}
					}
				}
			} else if (vy<0) {
				Counter = 0;
				double triesRequired = -vy; // we want to test to see if an object will show up at 1 pixel away, 2 pixels away, 3 pixels away ... n pixels away, where n is the number of pixels you're slated to move next.
				while (Counter<triesRequired) {
					Counter++;
					if(checkPointForObject(centerOfBallX,ballNorth-Counter) || ballNorth - Counter == 0) {
						tempVy = -Counter;
						tempVx = vx*(tempVy/vy); // scales the change in velocity for x as well, to ensure that the line will remain straight
						break;
					} else {
						continue;
					}
				}
				Counter = 0;
				if (vx>0) {
					triesRequired = -vx;
					while(Counter<triesRequired) {
						Counter++;
						if (checkPointForObject(ballEast+Counter,centerOfBallY) || ballEast + Counter == WIDTH) {
							double distanceToSideWall = Math.sqrt((Counter*Counter+vy*vy));
							double distanceToTopWall = Math.sqrt((tempVy*tempVy+vx*vx));
							if (distanceToSideWall<distanceToTopWall) { // basically we first looked at objects approaching below us, but now we look at obj we're approaching on our side. This checks to see which object is closer and sets the velocity such that it will directly hit that object.
								tempVx = Counter;
								tempVy = vy*(tempVx/vx);
							}
							break;
							} else {
								continue;
							}
						}
					} else if (vx<0) {
						while(Counter<triesRequired) {
							Counter++;
							if (checkPointForObject(ballWest-Counter,centerOfBallY) || ballWest - Counter == 0) {
								double distanceToSideWall = Math.sqrt((Counter*Counter+vy*vy));
								double distanceToTopWall = Math.sqrt((tempVy*tempVy+vx*vx));
								if (distanceToSideWall<distanceToTopWall) { // basically we first looked at objects approaching below us, but now we look at obj we're approaching on our side. This checks to see which object is closer and sets the velocity such that it will directly hit that object.
									tempVx = -Counter;
									tempVy = vy*(tempVx/vx);
								}
								break;
							} else {
								continue;
							}
						}
					}				
			}
		}
	
	private void checkForCollisions() {
		AudioClip bounceClip = MediaTools.loadAudioClip("bounce.au");
		GObject collidingObject = getCollidingObject();
		if (collidingObject == Paddle) {
			if (centerOfBallX<centerOfPaddleX) { // this just makes it so that if you position the paddle so the ball will land on the left side of the paddle, it will shoot off to the left. adds a bit of skill.
				vx = -(Math.abs(vx));
			} else {
				vx = Math.abs(vx);
			}
			vy = -vy;
			bounceClip.play();
		} else if (collidingObject != null) {
			remove(collidingObject);
			numBricks--;
			String labelText = "Bricks Hit: "+Integer.toString(numBricks);
			GLabel numBricksHit = new GLabel(labelText,20,20);
			add(numBricksHit);
			remove(numBricksHit);
			vy = -vy;
		}
	}
	private void checkForWalls() {
		checkRightWall();
		checkTopWall();
		checkLeftWall();
		checkBottomWall();
	}
	private GObject getCollidingObject() {
		GObject object = null;
		if (checkPointForObject(ballWest,ballNorth)) {
			object = getElementAt(ballWest,ballNorth);
		} else if (checkPointForObject(ballEast,ballNorth)) {
			object = getElementAt(ballEast,ballNorth);
		} else if (checkPointForObject(ballEast,ballSouth)) {
			object = getElementAt(ballEast,ballSouth);
		} else if (checkPointForObject(ballWest,ballSouth)) {
			object = getElementAt(ballWest,ballSouth);
		}
		return object;
	}
	private boolean checkPointForObject(double x, double y) {
		GObject object = getElementAt(x,y);
		if (object != null) {
			return true;
		} else {
			return false;
		}
	}
	// these methods check all of the walls and reverse course when the ball hits that wall by changing vx/vy.
	
	private void checkRightWall() {
		if (ballEast>=WIDTH) {
			vx = -vx;
		}
	}
	private void checkLeftWall() {
		if (ballWest<=0) {
			vx = -vx;
		}
	}
	private void checkTopWall() {
		if (ballNorth<=0) {
			vy = -vy;
		}
	}
	private void checkBottomWall() {
		if (ballSouth>=HEIGHT) {
			alive = false;
			lives--;
			if (lives==0) {
				gameover=true;
			}
			String labelText = "Lives remaining: "+Integer.toString(lives);
			//String labelText2 = "Bricks remaining: "+Integer.toString()
			GLabel lives = new GLabel(labelText,20,20);
			add(lives);
			pause(1.5*1000);
			remove(lives);
		}
	}
	
public void mouseMoved(MouseEvent e) {
		Last = (e.getX()); // this just gets the location of the pointer
		if (Last+PADDLE_WIDTH>WIDTH) { //this ensures that you cannot move the paddle off the side of the screen
			Last = (WIDTH-PADDLE_WIDTH);
		}
		Paddle.setLocation(Last, (HEIGHT-PADDLE_Y_OFFSET-PADDLE_HEIGHT));
	}
// This section includes the setup for the game:
	
	private void addRows(){
		int rowXStart = (WIDTH - (BRICK_WIDTH+BRICK_SEP)*NBRICKS_PER_ROW)/2;
		int rowYStart = BRICK_Y_OFFSET;
		int rowSetsRemaining = NBRICK_ROWS/2;
		while (rowSetsRemaining>0) {
			addSetOfRows(rowXStart, rowYStart);
			rowYStart+=(BRICK_HEIGHT+BRICK_SEP)*2;
			color = nextColor(color);
			rowSetsRemaining-=1;
		}
	}
	private void addPaddle() {
		Paddle = new GRect(-(WIDTH-PADDLE_WIDTH)/2,(HEIGHT-PADDLE_Y_OFFSET-PADDLE_HEIGHT),PADDLE_WIDTH,PADDLE_HEIGHT);
		Paddle.setFilled(true);
		add(Paddle);
	}
	/* Set the next color for a set of rows*/
	private Color nextColor(Color lastColor) {
		if (lastColor==Color.RED){
			return Color.ORANGE;
		} else if (lastColor==Color.ORANGE){
			return Color.YELLOW;
		} else if (lastColor==Color.YELLOW){
			return Color.GREEN;
		} else {
			return Color.CYAN;
		}
	}
	private void addSetOfRows(int rowXStart, int rowYStart) {
		int xCoord = rowXStart;
		int yCoord = rowYStart;
		int numBricks = 0;
		while (numBricks<NBRICKS_PER_ROW*2){
			brick = new GRect(xCoord,yCoord,BRICK_WIDTH,BRICK_HEIGHT);
			add(brick);
			brick.setFilled(true);
			brick.setColor(color);
			if (numBricks==NBRICKS_PER_ROW-1) {
				xCoord = rowXStart;
				yCoord = rowYStart+BRICK_HEIGHT+BRICK_SEP;
			} else {
				xCoord += BRICK_WIDTH+BRICK_SEP;
			}
			numBricks+=1;
		}
		
	}
	
	private Color color = Color.RED;
	private int lives = NTURNS;
	private boolean alive;
	private boolean gameover = false;
	private double vx,vy,tempVx,tempVy;
	private GRect brick;
	private int Last;
	private double Counter;
	private GRect Paddle;
	private GOval Ball;
	private int numBricks = NBRICK_ROWS*NBRICKS_PER_ROW;
	private double ballNorth, ballEast, ballSouth, ballWest, centerOfBallX, centerOfPaddleX, centerOfBallY;
	private RandomGenerator rgen = RandomGenerator.getInstance();
	

}
