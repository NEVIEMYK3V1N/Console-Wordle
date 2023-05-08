/**
* ReadFile.java
* @author Kevin
* @since May 17, 2022
* This class provides functions to extract user data from local files
*/

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class ReadFile {
/**
  * Function that read each line in username.txt and stores them in an array
  * Preconditions: username.txt is a valid file
  * Postconditions: return an array of length 6 
  * @param username: a string of username
  * @return: an array of each line accordingly
  *    {-1,-1,-1,-1,-1,-1} if the file doesn't exist
  */
  private static String[] readUserInfo(String username){
    String[] info = new String[6];
    try {
      File myObj = new File(username + ".txt");
      Scanner myReader = new Scanner(myObj);
      int i = 0;
      while (myReader.hasNextLine() && i < info.length) {
        info[i] = myReader.nextLine();
        i++;
      }
      myReader.close();
    } 
    catch (FileNotFoundException e) {
      String[] ret = {"-1","-1","-1","-1","-1","-1"};
      return ret;
    }
    return info;
  }

/**
  * Function that read the password
  * Preconditions: username.txt is a valid file
  * Postconditions: the password string is returned
  * @param username: a string of username
  * @return: password string (first line in username.txt)
  */
  public static String readPassword(String username) {
    String password = "";
    password = readUserInfo(username)[0];
    return password;
  }

/**
  * Function that read the average tries
  * Preconditions: username.txt is a valid file
  * Postconditions: the average tries is returned
  * @param username: a string of username
  * @return: average tries (second line in username.txt)
  */
  public static double readMean(String username) {
    double mean = 0.0;
    mean = Double.parseDouble(readUserInfo(username)[1]);
    return mean;
  }

/**
  * Function that read the average time
  * Preconditions: username.txt is a valid file
  * Postconditions: the average time is returned
  * @param username: a string of username
  * @return: average time (third line in username.txt)
  */  
  public static double readTimeMean(String username) {
    double timeMean = 0.0;
    timeMean = Double.parseDouble(readUserInfo(username)[2]);
    return timeMean;
  }

/**
  * Function that read the total number of games
  * Preconditions: username.txt is a valid file
  * Postconditions: the total number of games is returned
  * @param username: a string of username
  * @return: total number of games (fourth line in username.txt)
  */
  public static int readNumOfGames (String username){
    int num = 0;
    num = Integer.parseInt(readUserInfo(username)[3]);
    return num;
  }

/**
  * Function that read the past game trial data
  * Preconditions: username.txt is a valid file
  * Postconditions: the past game trials are returned
  * @param username: a string of username
  * @return: past game trials (fifth line in username.txt)
  */ 
  public static int[] readData(String username) {
    String dataString = readUserInfo(username)[4];
    String[] stringArray = dataString.split(",");
    int[] dataArray = new int[stringArray.length];
    for (int i = 0; i < dataArray.length; i++){
      dataArray[i] = Integer.parseInt(stringArray[i]);
    }
    return dataArray;
  }
  
/**
  * Function that read the past game times data
  * Preconditions: username.txt is a valid file
  * Postconditions: the past game times are returned
  * @param username: a string of username
  * @return: past game times (sixth line in username.txt)
  */ 
  public static long[] readTimeData(String username) {
    String timeDataString = readUserInfo(username)[5];
    String[] timeStringArray = timeDataString.split(",");
    long[] timeDataArray = new long[timeStringArray.length];
    if (!timeStringArray[0].equals("0")){
      for (int i = 0; i < timeDataArray.length; i++){
        timeDataArray[i] = Integer.parseInt(timeStringArray[i]);
      }
      return timeDataArray;
    }
    else{
      long[] specialRet = {0};
      return specialRet;
    }

  }

}

/**
  * User file (username.txt) format: 
  *   String password 
  *   double mean 
  *   double timeMean
  *   int num
  *   int[] data
  *   long[] timeData) 
*/