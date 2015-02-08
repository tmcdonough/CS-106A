/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes or the window is resized.
 */

import acm.graphics.*;

import java.awt.event.*;
import java.util.*;
import java.util.Map.Entry;
import java.awt.*;

public class NameSurferGraph extends GCanvas
	implements NameSurferConstants, ComponentListener {
	
	private HashMap<Integer,NameSurferEntry> currentNames = new HashMap<Integer,NameSurferEntry>();
	private double height = getHeight();
	private double width = getWidth();
	private double horizontalLineY1 = height/15;
	private double horizontalLineY2 = height/15*14;

	/**
	* Creates a new NameSurferGraph object that displays the data.
	*/
	public NameSurferGraph() {
		addComponentListener(this);
		//	 You fill in the rest //
	}
	
	/**
	* Clears the list of name surfer entries stored inside this class.
	*/
	public void clear() {
		currentNames.clear();
		nameSurferIndex = 0;
	}
	
	/* Method: addEntry(entry) */
	/**
	* Adds a new NameSurferEntry to the list of entries on the display.
	* Note that this method does not actually draw the graph, but
	* simply stores the entry; the graph is drawn by calling update.
	*/
	public void addEntry(NameSurferEntry entry) {
		nameSurferIndex++;
		currentNames.put(nameSurferIndex,entry);
	}
	
	public void deleteEntry(NameSurferEntry entry){
		
		int keyToDelete = -1;
		
		for(Map.Entry<Integer,NameSurferEntry> entry2 : currentNames.entrySet()){
			Integer entryNo = entry2.getKey();
			NameSurferEntry entryValue = entry2.getValue();
			if (entryValue==entry){
				keyToDelete = entryNo;
			}
			
		}
		
		if(keyToDelete!=-1){
			currentNames.remove(keyToDelete);
		}
		
		nameSurferIndex = 0;
	}
	
	
	
	/**
	* Updates the display image by deleting all the graphical objects
	* from the canvas and then reassembling the display according to
	* the list of entries. Your application must call update after
	* calling either clear or addEntry; update is also called whenever
	* the size of the canvas changes.
	*/
	public void update() {
		removeAll();
		addBackground();
		addNames();
	}
	
	private void addNames(){
		
		if (currentNames.size()>0){
			for(Map.Entry<Integer,NameSurferEntry> entry : currentNames.entrySet()){
				Integer entryNo = entry.getKey();
				NameSurferEntry entryValue = entry.getValue();
				String currentName = entryValue.getName();
				Color currentColor = getColor(entryNo);
				HashMap<Integer,Integer> currentScores = entryValue.getScores();
				drawScores(currentName, currentColor, currentScores);
				drawScoreLines(currentColor, currentScores);
			}
		}
	}
	
	private Color getColor(Integer index){
		if (index % 4==1){
			return Color.BLACK;
		} else if (index % 4 == 2){
			return Color.RED;
		} else if (index % 4 == 3){
			return Color.BLUE;
		} else {
			return Color.MAGENTA;
		}
	}
	
	private void drawScores(String currentName, Color currentColor, HashMap<Integer,Integer> currentScores){
		double oneRankIncrement = (horizontalLineY2 - horizontalLineY1)/1000;
		double leftBound = 0;
		double rightBound = leftBound + width/11;
		double yPos;
		for (int i = 1900; i<=2000; i+=10){
			
			int score = currentScores.get(i);
			String nameDisplay = currentName;
			
			if(score==0){
				nameDisplay+=" *";
				yPos = horizontalLineY2 - oneRankIncrement;
			} else {
				nameDisplay+=" "+Integer.toString(score);
				yPos = horizontalLineY2 - oneRankIncrement*(1000-score);
			}
			GLabel nameDisplayLabel = new GLabel(nameDisplay);
			//double xPos = (rightBound - leftBound - nameDisplayLabel.getWidth()) / 2 + leftBound;
			double xPos = leftBound;
			nameDisplayLabel.setLocation(xPos,yPos);
			nameDisplayLabel.setColor(currentColor);
			add(nameDisplayLabel);
			rightBound+=(width/11);
			leftBound+=(width/11);
		}
	}
	
	private void drawScoreLines(Color currentColor, HashMap<Integer,Integer> currentScores){
		
		double oneRankIncrement = (horizontalLineY2 - horizontalLineY1)/1000;
		
		double xStart = 0;
		double yStart = 0;
		double xEnd = 0;
		double yEnd = 0;
		
		double xPos = 0;
		double yPos = 0;
		
		boolean drawLines = false;
		
		for (int i = 1900; i<= 2000; i+=10){
			
			int score = currentScores.get(i);
			if(score==0){
				yPos = horizontalLineY2;
			} else {
				yPos = horizontalLineY2 - oneRankIncrement*(1000-score);
			}
			
			if(drawLines){
				xEnd = xPos;
				yEnd = yPos;
				GLine lineSegment = new GLine(xStart,yStart,xEnd,yEnd);
				lineSegment.setColor(currentColor);
				add(lineSegment);
				xStart = xEnd;
				yStart = yEnd;
			} else {
				xStart = xPos;
				yStart = yPos;
				drawLines = true;
			}
			xPos+=width/11;
		}
	}
	
	private void addBackground(){
		
		height = getHeight();
		width = getWidth();
		int decade = 1900;
		
		horizontalLineY1 = height/15;
		horizontalLineY2 = height/15*14;
		GLine horizontalLine1 = new GLine(0,horizontalLineY1,width,horizontalLineY1);
		GLine horizontalLine2 = new GLine(0,horizontalLineY2,width,horizontalLineY2);
		add(horizontalLine1);
		add(horizontalLine2);
		
		for (int i = 1; i <= 11; i++){
			GLine verticalLine = new GLine(width/11*i,0,width/11*i,height);
			add(verticalLine);
			GLabel decadeLabel = new GLabel(Integer.toString(decade));
			double labelX = width/11*(i-1) + width/11*.05;
			double labelY = height - ((height-horizontalLineY2)-decadeLabel.getHeight())/2;
			decadeLabel.setLocation(labelX,labelY);
			add(decadeLabel);
			decade+=10;
		}
		
		
	}
	
	
	
	
	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) { }
	public void componentMoved(ComponentEvent e) { }
	public void componentResized(ComponentEvent e) { update(); }
	public void componentShown(ComponentEvent e) { }
	
	/* Instance variables */
	private int nameSurferIndex = 0;

}
