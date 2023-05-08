/**
* DeleteFile.java
* @author Kevin
* @since May 17, 2022
* This class offers a function to delete an existing file
*/

import java.io.File;  // Import the File class

public class DeleteFile {

/**
  * Function that detele the file named username.txt
  * Preconditions: username contains at least one letters other than space
  * Postconditions: return true if the empty file username.txt is deleted, false if otherwise
  * @param username: a String of username
  */
  public static boolean deleteFile(String username) { 
    File myObj = new File(username + ".txt"); 
    if (myObj.delete()) { 
      return true;
    } 
    else {
    } 
    return false;
  } 
}