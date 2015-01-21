/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.awt.*;

public class Hangman extends ConsoleProgram {
	
	public void init() {
		canvas = new HangmanCanvas();
		add(canvas);
	}

    public void run() {
		while(true){
	    	setup();
			while(playing){
				playTurn();
			}
			String newGame = readLine("Play again? (y/n): ");
			if (newGame.equals("y")) {
				reset();
				continue;
			} else {
				break;
			}
		}
    	
	}
    private void setup() {
    	canvas.reset();
    	HangmanLexicon newLexicon = new HangmanLexicon();
		secretWord = newLexicon.getWord(rgen.nextInt(newLexicon.getWordCount()));
		println("Welcome to Hangman!");
		setGuessedWord(secretWord);
		canvas.displayWord(guessedWord);
    }
    private void reset() {
    	canvas.reset();
    	playing = true;
    	guessedWord = "";
    	guessesRemaining = 8;
    	lastCorrectIndex = 0;
    }
    private void playTurn() {
    	println("The word now looks like this: "+guessedWord);
    	println("You have "+guessesRemaining+" guesses left.");
		String enteredString = readLine("Your guess: ");
		if (enteredString.length()>1 || enteredString.isEmpty()) {
			println("Your guess is illegal, please try again.");
		} else {
			char guessChar = enteredString.charAt(0);
			guessChar = Character.toUpperCase(guessChar);
			checkGuess(guessChar);
			if (guessedWord.indexOf('-') == -1){
				println("You guessed the word: "+guessedWord);
				println("Congratulations - you win!");
				playing = false;
			} else if (guessesRemaining==0){
				println("The word was: "+secretWord);
				println("Sorry, you lose. Try again");
				playing = false;
			}
		}
    }
    private void setGuessedWord(String secretWord) {
    	for (int i = 0;i<secretWord.length();i++) {
    		guessedWord += '-';
    	}
    }
    private void checkGuess(char guess){
    	lastCorrectIndex=0;
    	lastCorrectIndex = secretWord.indexOf(guess,lastCorrectIndex);
    	correctGuess = true;
    	if (lastCorrectIndex==-1) {
    		correctGuess = false;
    		println("There are no "+guess+"'s in the word.");
    		if (incorrectGuesses.indexOf(guess)==-1) {
    			incorrectGuesses+=guess;
    		}
    		guessesRemaining--;
    		canvas.noteIncorrectGuess(guess);
		}
    	lastCorrectIndex = 0;
    	while(correctGuess){
    		lastCorrectIndex = secretWord.indexOf(guess,lastCorrectIndex);
    		if (lastCorrectIndex==-1) {
    			correctGuess = false;
    			break;
    		}
    		char[] charArray = guessedWord.toCharArray();
            charArray[lastCorrectIndex] = guess;
            guessedWord = new String(charArray);
            canvas.displayWord(guessedWord);
            lastCorrectIndex++;
    	}
    }
    
    private RandomGenerator rgen = RandomGenerator.getInstance();
    private int width;
    private int height;
    private String secretWord;
    private String guessedWord = "";
    private String incorrectGuesses = "";
    private boolean correctGuess;
    private int guessesRemaining = 8;
    private int lastCorrectIndex = 0;
    private boolean playing = true;
    private HangmanCanvas canvas;

}