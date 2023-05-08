/**
* WriteToFile.java
* @author Kevin
* @since May 17, 2022
* This class offers a function to write all the data of a user into a file titled the same as the username
*/

import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors

public class WriteToFile {

/**
  * Method that writes all the user's data into a file titled the same as the username
  * Preconditions: username and password are String objects, mean and timeMean are doubles greater than or equal to 0, num is an int greater than or equal to 0, data and timeData are non-empty int and long arrays respectively
  * Postconditions: the data of the current game is written onto the user's file
  * @ param username: String corresponding to the file name
  * @ param password: String that only allows the file to be opened if the password is matched
  * @ param mean: holds the average tries by the user
  * @ param timeMean: holds the average time by the user
  * @ param num: holds the number of games by the user
  * @ param data: holds the number of tries it took for each game
  * @ param timeData: holds the time it took for each game
  */
  public static boolean editFile (String username, String password, double mean, double timeMean, int num, int[] data, long[] timeData) {
    boolean ret = false;
    try {
      FileWriter myWriter = new FileWriter(username + ".txt");
      myWriter.write(password);
      myWriter.write("\n");
      myWriter.write("" + mean);
      myWriter.write("\n");
      myWriter.write("" + timeMean);
      myWriter.write("\n");
      myWriter.write("" + num);
      myWriter.write("\n");
      for (int i = 0; i < data.length; i++){
        if (i == data.length-1){
          myWriter.write(data[i] + "");
        }
        else{
          myWriter.write(data[i] + ",");
        }
      }
      myWriter.write("\n");
      if(timeData[0] != 0){
        for (int i = 0; i < timeData.length; i++){
          if (i == timeData.length-1){
            myWriter.write(timeData[i]+"");
          }
          else{
            myWriter.write(timeData[i] + ",");
          }
        }
      }
      else{
        myWriter.write("0");
      }
      myWriter.close();
      ret = true;
    } 
    catch (IOException e) {
      return ret;
    }
    return ret;
  }
}