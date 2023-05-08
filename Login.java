/**
* Login.java
* @author Kevin
* @since May 17, 2022
* This class provides functions for user to login or create accounts
*/

import java.util.Scanner;

public class Login{

/**
  * Method that returns the username being logged in which the game data will be stored
  * Postconditions: a valid username is returned if successfully logged in
  * @return: valid username if successfully logged in,
  *    guest if not logged in
  */ 
  public static String checkLogin (){
    Scanner s = new Scanner(System.in);
    System.out.println("Please enter \"login\" to login into existing account");
    System.out.println("\"guest\" to play without logging in;");
    System.out.println("or \"create\" to create a new account.");

    String ret = "guest";
    boolean chooseLogin = false;
    String loginInput;
    while (!chooseLogin){
      loginInput = s.nextLine();
      loginInput = loginInput.toLowerCase();
      
      if (loginInput.equals("guest")){
        chooseLogin = true;
        System.out.println("You are now playing as a guest! Have Fun!");
        return ret;
      }
        
      else if (loginInput.equals("create")){
        boolean chooseUsername = false;
        System.out.print("What username would you like: ");
        while (!chooseUsername){
          String usernameInput = s.nextLine();
          chooseUsername = createNewAccount(usernameInput);
          if(!chooseUsername){
            System.out.print("Invalid Input, try again: ");
          }
        }
        System.out.println("Account created, you can now login");
        chooseLogin = true;
        ret = "create";
      }

      else if (loginInput.equals("login")){
        String playerName = loginAccount();
        if (!(playerName.equals("create"))){
          chooseLogin = true;
          ret = playerName;
        }
        else{
          System.out.println("Please enter \"login\" to login into existing account");
          System.out.println("\"guest\" to play without logging in;");
          System.out.println("or \"create\" to create a new account.");
        }
      }
        
      else{
        System.out.print("Invalid input, try again: ");
      }
    }
    return ret;
  } // end main method

/**
  * Method that creates and writes the default for a new username.txt file and returns a boolean describing if this process is successful
  * Preconditions: username is not null nor only spaces
  * Postconditions: username.txt is created and default values are wrote to the file
  * @param username: the username of the new account
  * @return: returns true if the new file is successfully created, false otherwise
  */
  public static boolean createNewAccount(String username){
    Scanner s = new Scanner(System.in);
    boolean valid = false;
    String lowerUsername = username.toLowerCase();
    if (lowerUsername.equals("allplayerdata") || lowerUsername.equals("guest") || lowerUsername.equals("create")){
      return valid;
    }
    valid = CreateFile.newFile(username);
    if (valid){
      System.out.print("What password would you like: ");
      String password = s.nextLine();
      User player = new User(username, password);
      //( String password, double mean, double timeMean, int num, int[] data, long[] timeData) 
      boolean writeValid = WriteToFile.editFile(player.getUsername(), player.getPassword(), player.getPastMean(), player.getPastTimeMean(), player.getNumOfGames(), player.getTrialDistribution(), player.getTimeDistribution());  
    }
    return valid;
  }

/**
  * Method that returns the username being logged in
  * Preconditions:
  * Postconditions: a valid username is returned if successfully logged in
  * @return: valid username if successfully logged in,
  *    create if wishing to create a new account instead
  *    "" if failed to login
  */ 
  public static String loginAccount(){
    Scanner s = new Scanner (System.in);
    String ret = "";
    boolean usernameValid = false;
    boolean passwordValid = false;
    String validName = "";
    
    System.out.print("What is your username: ");
    String usernameInput = s.nextLine();

    //not continuing until a valid username is entered that a file username.txt exist and can be read
    while (!usernameValid){
      double userMean = ReadFile.readMean(usernameInput);
      if (usernameInput.toLowerCase().equals("create")){
        ret = "create";
        return ret; 
      }
      else if (userMean < 0){
        System.out.println("Username does not exist, try again;");
        System.out.print("or \"create\" to create a new account: ");
        usernameInput = s.nextLine();
      }
      else{
        usernameValid = true;
        validName = usernameInput;
      }
    }
    
    System.out.print("What is your password: ");
    //not continuing until a valid password is entered that matches the password in file username.txt
    while (!passwordValid && usernameValid){
      String passwordInput = s.nextLine();
      if (passwordInput.toLowerCase().equals("create")){
        ret = "create";
        return ret;
      }
      String userPassword = ReadFile.readPassword(validName);
      if (!(passwordInput.equals(userPassword))){
        System.out.println("Password does not match, try again;");
        System.out.print("or \"create\" to create a new account: ");
      }
      else{
        passwordValid = true;
        ret = validName;
      }
    }
    return ret;
  }

} // end class