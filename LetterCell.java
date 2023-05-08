/**
* LetterCell.java
* @author Kevin, Derek, Dan
* @since May 17, 2022
* This class initializes each letter to a letter cell with colorcode attached
*/

public class LetterCell {
  //instance variables
  private char letter;
  private String color;

  //color placeholders
  public static final String ANSI_RESET = "\u001B[0m"; 
  public static final String ANSI_YELLOW = "\u001B[33m";
  public static final String ANSI_GREEN = "\u001B[32m";

/**
  * Constructor that takes no arguments
  * Postconditions: a letterCell object is created with default (blank) values
  */
  public LetterCell() {
    letter = '\0';
    color = "";
  }

/**
  * Constructor that takes the letter and its color
  * Preconditions: initLetter is a single character, initColor is either "green", "yellow", "reset", or "grey"
  * Postconditions: a letterCell object is created
  * @param initLetter: the letter that the letterCell object contains
  * @param initColor: the color associated with the letter (indicates correctness)
  */
  public LetterCell(char initLetter, String initColor) {
    letter = initLetter;
    if (initColor == "green") {
      color = ANSI_GREEN;
    }
    else if (initColor == "yellow") {
      color = ANSI_YELLOW;
    }
    else if (initColor == "reset" || initColor == "grey"){
      color = ANSI_RESET;
    }
  }

  /**
  * Method that returns the letter of the LetterCell object
  * Postconditions: the char letter is returned
  * @return: letter
  */
  public char getLetter() {
    return letter;
  }
  
/**
  * Method that returns the corresponding ANSI escape code or the color
  * of the LetterCell object
  * Postconditions: the ANSI escape code for the color is returned
  * @return: color
  */
  public String getColor() {
    return color;
  }

/**
  * Method that sets a new letter to the letterCell object
  * Preconditions: newLetter is a single character from the alphabet
  * Postconditions: the letter of the object is changed into newLetter
  * @param newLetter: the letter to be set as the current letter
  */
  public void setLetter(char newLetter) {
    letter = newLetter;
  }

/**
  * Method that sets a new color to the letterCell object
  * Preconditions: newColor is either "green", "yellow", "reset", or "grey"
  * Postconditions: the letter of the object is changed into newLetter
  * @param newLetter: the letter to be set as the current letter
  */
  public void setColor(String newColor) {
    if (newColor == "green") {
      color = ANSI_GREEN;
    }
    else if (newColor == "yellow") {
      color = ANSI_YELLOW;
    }
    else if (newColor == "reset" || newColor == "grey"){
      color = ANSI_RESET;
    }
  }

/**
  * Method that checks if the letters of the LetterCell objects are the same
  * Preconditions: other is a LetterCell object
  * Postconditions: returns true if the letter attributes are the same, otherwise false
  * @param other: the other LetterCell object to be compared with
  */
  public boolean equals(LetterCell other) {
    return (this.getLetter() == other.getLetter());
  }
  
/**
  * Method that returns the information that is printed onto the console
  * Postconditions: returns the letter with its corresponding color
  * @return: String of a colored letter
  */
  public String toString() {
    return color + String.valueOf(letter) + ANSI_RESET;
  }

}