/**
* CreateFile.java
* @author Kevin
* @since May 17, 2022
* This class offers a function to create a new file if there are no duplicates
*/

import java.io.File;  // Import the File class
import java.io.IOException;  // Import the IOException class to handle errors

public class CreateFile {
  
/**
  * Function that creates a new file named username.txt
  * Preconditions: username contains at least one letters other than space
  * Postconditions: return true if the empty file username.txt is created, false if otherwise.
  * @param username: a username as a String
  */
  
  public static boolean newFile(String username) {
    boolean ret = false;
    try {
      File myObj = new File(username+".txt");
      if (myObj.createNewFile()) {  
        ret = true;
      } 
    } 
    catch (IOException e) {
      return ret;
    }
    return ret;
  }
}
