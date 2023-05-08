/**
* Main.java
* @author Kevin, Derek, Dan
* @since May 17, 2022
* This class runs the full Garble game
*/

import java.util.Scanner; //import libraries
import javax.xml.transform.Templates;
import java.io.*;
import java.util.*;
import static java.lang.Math.toIntExact;

public class Main {
  /**
  * Main method that runs a full game
  * Preconditions: guest.txt exist and has at least default data
  */
  public static void main (String[] args) {
    Scanner s = new Scanner(System.in);
    String guestName = "guest";  //if choose not to login
    boolean played = false;    //if a game has been played
    String playerName = "";

    //default values for result if the player did not win
    int newTrial = -1;
    long newTime = 0;
    long[] result = {newTrial, newTime};

    System.out.println("Welcome to the Garble Game");

    //when a game has not been played
    while (!played){
      playerName = Login.checkLogin();
      //if not logged in -- game started under guest
      if (playerName.equals("guest")){
        result = Main.startGame(s,"guest");
        played = true;
      }
      //repeats the loop
      else if (playerName.equals("create")){
        System.out.println("");        
      }
      //if logged in -- game started under playername
      else {
        result = Main.startGame(s,playerName);
        played = true;
      }
    }

    //separate trial and time
    newTrial = toIntExact(result[0]);  //converts long back to int
    newTime = result[1];

    //check if the user won the game by checking if the results are still default or not
    if (newTrial != -1 && newTime != 0){
      //create two User objects holding all the data read from txt files
      User guest = createUserObject(guestName);
      User user = createUserObject(playerName);
      //if the user logged in -- store data under user
      if (!playerName.equals(guestName) && user != null){
        updateUserData(user, newTrial, newTime);;
        System.out.println("It takes you " + user.getPastMean() + " tries and " + user.getPastTimeMean() + " seconds on average to find the answer!");
      }
      //always store data under guests no matter logged in or not
      updateUserData(guest, newTrial, newTime);
      System.out.println("It takes all players " + guest.getPastMean() + " tries and " + guest.getPastTimeMean() + " seconds on average to find the answer!");
    }
  }
  
/**
  * Method that initializes and starts a full game
  * Preconditions: parameters are not null and user is not all spaces
  * Postconditions: An array consisting the tries and time it took to win the game is returned
  * @param user: the username of a user
  * @return: a long array of {tries, time} took to win the game
  *    returns {-1,0} if the play did not win the game
  */
  public static long[] startGame(Scanner s, String user){
    boolean chooseGame = false;
    int wordLength = 0;
    int tryLimit = 0;
    int timeLimit = 0;
    
    int newTrial = -1;
    long newTime = 0;
    long [] ret = {newTrial, newTime};
    
    System.out.println("Type \"classic\" to play with 6 tries and no time restriction");
    System.out.println("Type \"custom\" to choose your own word length, tries, and time limit");

    while (!chooseGame){
      String choice = s.nextLine().toLowerCase();
  
      if (choice.equals("classic")){
        System.out.println("Game has began");
        System.out.println();
        chooseGame = true;
        GameMode newGame = new GameMode();
        ret = newGame.runGame();
      }
  
      else if (choice.equals("custom")){
        chooseGame = false;
        while (wordLength < 4 || wordLength > 6) {
          System.out.print("Word length (4, 5, or 6): ");
          wordLength = s.nextInt();
        }
        while (tryLimit <= 0 || tryLimit > 20) {
          System.out.print("Number of tries (1 to 20): ");
          tryLimit = s.nextInt();
        }
        while (timeLimit <= 0) {
          System.out.print("Number of seconds: ");
          timeLimit = s.nextInt();
        }
        chooseGame = true;
        GameMode newGame = new GameMode(wordLength, tryLimit, timeLimit);
        ret = newGame.runGame();
      }
      else{
        System.out.println("invalid input");
      }
    }
    return ret;
  }

/**
  * Method that creates a User object for the player name
  * Preconditions: parameters are not null and not all spaces
  *    playerName.txt exist and has at least default data
  * Postconditions: an User object is returned
  * @param playerName: the username of a user
  * @return: a user Object
  */ 
  public static User createUserObject (String playerName){
    User user = new User (playerName, ReadFile.readPassword(playerName), ReadFile.readMean(playerName), ReadFile.readTimeMean(playerName), ReadFile.readNumOfGames(playerName), ReadFile.readData(playerName),ReadFile.readTimeData(playerName));
    return user;
  }

/**
  * Method that rewrites username.txt when trial and time are added
  * Preconditions: name is not null; trial > 0; time > 0;
  *    username.txt exist
  * Postconditions: username.txt is rewritten with the trial and time added
  * @param name: the username of a user
  * @param trial: tries it took the player to win the newest game
  * @param time: time it took the player to win the newest game
  */ 
  public static void updateUserData (User name, int trial, long time){
    boolean deleted = DeleteFile.deleteFile(name.getUsername());
    name.updatePastMean(trial);
    name.updatePastTimeMean(time);
    name.updateNumOfGames();
    name.updateTrialDistribution(trial);
    name.updateTimeDistribution(time);
    if(deleted){
      boolean writeValid = WriteToFile.editFile(name.getUsername(), name.getPassword(), name.getPastMean(), name.getPastTimeMean(), name.getNumOfGames(), name.getTrialDistribution(), name.getTimeDistribution());   
    }
  }
  
} //end class