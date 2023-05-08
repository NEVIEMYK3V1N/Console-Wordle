/**
* GameMode.java
* @author Kevin, Derek, Dan
* @since May 17, 2022
* This class initializes different Gamemodes with different word length, time limit, and try limit.
*/

import java.util.Scanner;

public class GameMode {

  //instance variables
  private int wordLength;
  private int tryLimit;
  private long timeLimit;
  private long startTime;
  private long timeUsed;
  private int tries;
  private boolean done;


/**
  * Constructor that takes no parameters
  * Postconditions: the gamemode object is initialized to default (classic mode)
  */
  public GameMode() {
    this.wordLength = 5;
    this.tryLimit = 6;
    this.timeLimit = Integer.MAX_VALUE;
  }
  
/**
  * Constructor that takes the length, tries and time
  * Preconditions: length, tries and time are positive
  * Postconditions: the gamemode object is created and attributes are set accordingly
  * @param length: length of desired words
  * @param tries: the maximum attempts to guess word
  * @param time: the maximum time to guess word
  */ 
  public GameMode(int length, int tries, long time){
    this.wordLength = length;
    this.tryLimit = tries;
    this.timeLimit = time;
  }

  /**
  * Method that runs a full game and returns the tries and time used
  * Postconditions: a long array of tries and time used is returned
  * @return: {tries used, time used}
  *    positive values if the player guessed the word
  *    {-1,0} if the player failed
  */
  public long[] runGame() {
    long[] ret = {-1,0};
    Scanner inputScanner = new Scanner(System.in);
    
    //set up the target word for players to guess
    WordRow targetWord = new WordRow();
    if (wordLength == 4) {
      targetWord = new WordRow(RandomWord.fourLetter(), "green");
    }
    else if (wordLength == 5) {
      targetWord = new WordRow(RandomWord.fiveLetter(), "green");
    }
    else if (wordLength == 6) {
      targetWord = new WordRow(RandomWord.sixLetter(), "green");
    }

    //initialize variables that keep track of time and tries
    this.startTime = System.currentTimeMillis();
    this.timeUsed = 0;
    this.tries = 0;

    this.done = false; //checks if the player guessed correctly

    //continues running until times or tries are all used or the player correctly guesses the target word
    while (!this.done && this.tries < this.tryLimit && this.timeUsed < this.timeLimit){
      this.tries++;
      System.out.println("Take a guess (try #" + this.tries + ")");
      String guess = inputScanner.nextLine().toLowerCase();
      //check for invalid input
      while (guess.length() != wordLength && !done && this.timeUsed < this.timeLimit){
        System.out.println("Invalid guess");
        this.updateTimeUsed();
        guess = inputScanner.nextLine().toLowerCase();
      }
      //once valid, create a corresponding WordRow object
      WordRow guessWord = new WordRow(guess);

      guessWord.updateColors(targetWord);
      System.out.println(guessWord);

      //checks if the guess is correct
      if (guessWord.isCorrect(targetWord)) {
        inputScanner.close();
        this.done = true;
        this.updateTimeUsed();
        System.out.println("You Found The Answer in " + this.timeUsed + " seconds and " + this.tries + " tries.");
        ret[0] = this.tries;
        ret[1] = this.timeUsed;
        return ret;
      }
      this.checkTryAndTime(targetWord);
    }
    return ret;
  }    

/**
  * Method that checks if try and time limit has been exceeded
  * Preconditions: targetWord is a WordRow
  * Postconditions: updates the time, sets the variable "done" as true 
  * and ends the game if either the try or time limit is exceeded
  * @param targetWord: the word the user is trying to guess
  */
  private void checkTryAndTime(WordRow targetWord){
    this.updateTimeUsed();
    if (this.tries >= this.tryLimit && !this.done) {
      System.out.println("GAME OVER\nYou used up your "+ this.tryLimit + " tries\nThe correct word was " + targetWord);
      this.done = true;
    }
    else if (this.timeUsed > this.timeLimit && !this.done){
      System.out.println("GAME OVER\nYour "+ this.timeLimit + " seconds are up\nThe correct word was " + targetWord); 
      this.done = true;
    }
}

  /**
  * Method that updates the time used
  * Postconditions: time used is updated to current
  */
  private void updateTimeUsed(){
    this.timeUsed = (System.currentTimeMillis() - this.startTime)/1000;
  }

  /**
  * Method that returns the word length
  * Postconditions: word length is returned
  * @return: word length
  */
  public int getWordLength() {
    return wordLength;
  }
}

