/*
 * File: Yahtzee.java
 * ------------------
 * This program will eventually play the Yahtzee game.
 */

import acm.io.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import acm.program.*;
import acm.util.*;

public class Yahtzee extends GraphicsProgram implements YahtzeeConstants {
	
	public static void main(String[] args) {
		new Yahtzee().start(args);
	}
	
	public void run() {
		IODialog dialog = getDialog();
		nPlayers = dialog.readInt("Enter number of players");
		playerNames = new String[nPlayers];
		for (int i = 1; i <= nPlayers; i++) {
			playerNames[i - 1] = dialog.readLine("Enter name for player " + i);
		}
		display = new YahtzeeDisplay(getGCanvas(), playerNames);
		playGame();
	}
	

	private void playGame() {
				
		allPlayerScores = new HashMap<Integer,HashMap>();
		
		// this will create a hashmap that includes each player's number as a key, with value being a hashMap that stores their category scores.
		// the hashmap that stores their category scores will initialize each category as -1 if it is a dynamic category (i.e., can be selected by the player in a scoring round) and 0 if it is a total category (i.e., total score, upper score, lower score, etc.)
		
		for (int j = 1;j<nPlayers+1;j++){
			HashMap<Integer,Integer> playerScores = createPlayerScores(j);
			allPlayerScores.put(j,playerScores);
		}
		
		for(int rounds=1;rounds<N_SCORING_CATEGORIES+1;rounds++){ // this reflects the number of rounds in a game. loop will repeat for that many rounds.
			for(int p=1;p<nPlayers+1;p++){ // this reflects the number of players in a game. this for loop will cause the same sequence to be repeated for each player, in each round
				
				int category;
				
				// beginning of player's turn
				String name = playerNames[p-1];
				display.printMessage(name + "'s turn");
				int[] finalDice = new int[N_DICE]; // this initializes the final dice set. We alter this as new dice are rolled.
				
				display.printMessage("When ready, roll away!");
				display.waitForPlayerToClickRoll(p);
				initialRoll(finalDice); // the first roll generates 5 random numbers and assigns them to the final dice array.
				display.displayDice(finalDice);
				
				// We now allow the player two more re-rolls
				for(int turns=1;turns<=2;turns++){ 
					display.printMessage("Select die you would like to re-roll now, and then hit roll. You have "+(2-turns+1)+" re-rolls remaining");
					display.waitForPlayerToSelectDice();
					
					// Next we check to see which dice the player has selected for re-roll
					for(int diceOnBoard=0;diceOnBoard<N_DICE;diceOnBoard++){
						
						// for each that is selected, add a new randomly generated integer to the board
						if (display.isDieSelected(diceOnBoard)){ 
							finalDice[diceOnBoard] = rgen.nextInt(1,6);
						}
					}
					display.displayDice(finalDice);
				}
				
				// Now that we've got the final dice configuration for this turn, the player needs to select what category they want scored
				display.printMessage("Select a category for this roll:");
				
				while(true){
					
					category = display.waitForPlayerToSelectCategory();
					
					// this will pull the player's current scores from the master HashMap, and will then pull the current score for the category the player has selected
					HashMap<Integer,Integer> currentPlayerScores = allPlayerScores.get(p);
					int currentCategoryScore = currentPlayerScores.get(category);
					
					// checks the current dice array vs. the selected category to see if it is valid.
					boolean categoryIsValid = checkCategoryValidity(finalDice,category);
					
					// checks to see if there are other valid categories on the board (if there are not, we make it OK to select an invalid category and give the player a 0).
					boolean validAlternatives = checkForValidAlternatives(currentPlayerScores, finalDice);
					
					if (currentCategoryScore!=-1){
						display.printMessage("You have alreaday used this category - please select another.");
					} else {
						if(categoryIsValid){
							turnScore = scoreCategory(finalDice,category);
							currentPlayerScores.put(category, turnScore);
							allPlayerScores.put(p, currentPlayerScores);
							break;
						} else {
							if(validAlternatives){
								display.printMessage("This category is not valid for your current dice roll - please select another.");
							} else {
								turnScore = 0;
								currentPlayerScores.put(category, turnScore);
								allPlayerScores.put(p, currentPlayerScores);
								break;
							}
						}
						
					}
					
				}
				
				display.printMessage(name+" scored a "+turnScore);
				display.updateScorecard(category, p, turnScore);
			}
		}
		winner = tallyFinalScores();
		String winnerString = playerNames[winner-1];
		display.printMessage(winnerString+" wins with "+highest+" points!");
		}
	
	private boolean checkXOfAKind(int[] diceArray, int category, int kind){
		int[] numberCounts = new int[] {0,0,0,0,0,0};
		for (int d = 0;d<N_DICE;d++){ // this checks each die on the board
			int dieValue = diceArray[d];
			numberCounts[dieValue-1]++;
		}
		for(int e = 0;e<6;e++){
			if(numberCounts[e]>=kind){return true;}
		}
		return false;
	}
	
	private boolean checkFullHouse(int[] diceArray, int category){
		int[] numberCounts = new int[] {0,0,0,0,0,0};
		for (int d = 0;d<N_DICE;d++){ // this checks each die on the board
			int dieValue = diceArray[d];
			numberCounts[dieValue-1]++;
		}
		boolean three = false;
		boolean two = true;
		for(int e = 0;e<6;e++){
			if(numberCounts[e]>=2&&numberCounts[e]>=3){
				three = true;
			} else if (numberCounts[e]>=2){
				two = true;
			}
		}
		
		if(three&&two){
			return true;
		} else {
			return false;
		}
	}
	
	private boolean checkXStraight(int[] diceArray, int category, int kind){

		// first step is to put the dice in order from smallest to largest
		
		int[] sortedDiceArray = new int[diceArray.length];
		
		for (int i = 0;i<diceArray.length;i++){
			sortedDiceArray[i] = diceArray[i];
		}
		
		Arrays.sort(sortedDiceArray);
		
		// next we need to see if there is a straight
		
		int straight;
		int lastInt;
		
		for (int j = 0;j<diceArray.length;j++){
			straight = 1;
			lastInt = sortedDiceArray[j];
			println("Checking dice #"+(j+1)+": "+sortedDiceArray[j]);
			for (int k = j+1;k<diceArray.length;k++){
				println("Against dice #"+(k+1)+": "+sortedDiceArray[k]);
				if (sortedDiceArray[k]-lastInt==1){
					straight++;
					lastInt=sortedDiceArray[k];
					println("Straight Count: "+straight);
					if (straight==kind){
						return true;
					}
					continue;
				} else if(sortedDiceArray[k]==lastInt){
					continue;
				} else {
					break;
				}
			}
		}
		return false;
	}
	
	
	private boolean checkCategoryValidity(int[] diceArray, int category){
		if(category<=6){
			return true;
		} else if(category==9){ // three of a kind
			return checkXOfAKind(diceArray,category,3);
		} else if(category==10){// four of a kind
			return checkXOfAKind(diceArray,category,4);
		} else if(category==11){ // full house
			return checkFullHouse(diceArray,category);
		} else if(category==12){ // small straight
			return checkXStraight(diceArray,category,4);
		} else if(category==13){ //large straight
			return checkXStraight(diceArray,category,5);
		} else if(category==14){ // yahtzee
			return checkXOfAKind(diceArray,category,5);
		} else {return true;}
	}
	
	private int tallyFinalScores(){
		
		highest = 0;
		int winnerInt = 0;
		
		for (int i = 1;i<=nPlayers;i++){

			int totalScore = 0;
			int upperScore = 0;
			int upperBonus = 0;
			int lowerScore = 0;
			
			HashMap<Integer,Integer> playerScores = allPlayerScores.get(i);
			
			for (int cat = 1; cat <= N_CATEGORIES;cat++){ // this corresponds with the 6 categories that sum up to the "upper" category
				if(cat<=6){
					upperScore+=playerScores.get(cat);
				} else if (cat>8&&cat<=15){
					lowerScore+=playerScores.get(cat);
				}
			}
			
			if(upperScore>=63)upperBonus=35;
			totalScore=upperScore+upperBonus+lowerScore;
			
			display.updateScorecard(7,i,upperScore);
			display.updateScorecard(8, i, upperBonus);
			display.updateScorecard(16, i, lowerScore);
			display.updateScorecard(17, i, totalScore);

			if (totalScore>highest){
				highest=totalScore;
				winnerInt=i;
			}
		}
		return winnerInt;
	}
	
	private void initialRoll(int[] dice){
		for(int die=0;die<N_DICE;die++){
			dice[die] = rgen.nextInt(1,6);
		}
	}
	
	private int scoreCategory(int[] diceConfiguration,int category){
		int score = 0;
		if (category==1||category==2||category==3||category==4||category==5||category==6){
			for(int i=0;i<5;i++){
				if(diceConfiguration[i]==category){
					score+=category;
				}
			}
		} else if (category==9||category==10){
			int[] multiples = new int[6]; // corresponds to the possible values for a dice roll
			for (int i = 0;i<5;i++){ // corresponds to the number of dice rolls
				multiples[diceConfiguration[i]-1]++;
			}
			for (int i = 0;i<6;i++){ // again corresponds to number of possible values for a dice roll - we're looking for that multiple
				if (multiples[i]>=category-6){ // if the number of instances of a roll 1-6 is equal to 3 or 4 (case 7 or 8 less 4)...
					score+=(i+1)*(multiples[i]); // then add to the score the product of (i+1), which is equal to the particular die that was rolled multiple times, and the ith integer in the multiples array, which stores how many occurances of that particular die occured.
				}
			}
		} else if (category==11){
			score+=25;
		} else if (category==12){
			score+=30;
		} else if (category==13){
			score+=40;
		} else if (category==14){
			score+=50;
		} else if (category==15){
			for (int i=0;i<5;i++){
				score+=diceConfiguration[i];
			}
		}
		return score;
	}
	
	private HashMap<Integer,Integer> createPlayerScores(int playerNumber){
		HashMap<Integer,Integer> playerScores = new HashMap<Integer,Integer>();
		for (int c = 1;c<N_CATEGORIES+1;c++){
			if (c==7||c==8||c==16||c==17){
				playerScores.put(c, 0);
			} else {
				playerScores.put(c, -1);
			}
		}
		return playerScores;
	}
	
	private boolean checkForValidAlternatives(HashMap<Integer,Integer> currentPlayerScores, int[] finalDice){
		boolean result = false;
		for (int c=1;c<=N_CATEGORIES;c++){
			if (currentPlayerScores.get(c)==-1 && checkCategoryValidity(finalDice,c)){
				result = true;
			}
		}
		return result;
	}
		
/* Private instance variables */
	private int nPlayers;
	private int turnScore;
	private int highest;
	private String[] playerNames;
	private YahtzeeDisplay display;
	private RandomGenerator rgen = new RandomGenerator();
	private int winner;
	private HashMap<Integer,HashMap> allPlayerScores;
	private boolean[][] playerScoringCategories;
	private int[][] playerCategoryScores;
	private int[][] playerFinalCatScores;
	
	

}
