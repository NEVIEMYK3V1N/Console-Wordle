/**
* CreateFile.java
* @author Kevin, Derek, Dan
* @since May 17, 2022
* This class initializes a word string to a wordRow object with each letter being a letterCell object
*/

public class WordRow {
  //instance variables
  private LetterCell[] letters;
  private int wordLength;

/**
  * Constructor that takes no arguments
  * Postconditions: a WordRow object is created with default values (5 letter word)
  */
  public WordRow() {
    letters = new LetterCell[5];
    wordLength = 5;
  }

/**
  * Constructor that takes the word
  * Preconditions: word is a String
  * Postconditions: a WordRow object is created
  * @param word: the String that the WordRow object represents
  */
  public WordRow(String word) {
    wordLength = word.length();
    letters = new LetterCell[wordLength];
    for (int i = 0; i < wordLength; i++) {
      letters[i] = new LetterCell((word.substring(i, i+1)).charAt(0), "grey");
    }
  }

/**
  * Constructor that takes the word and colour
  * Preconditions: word is a String, initColor is either "green", "yellow", "reset", or "grey"
  * Postconditions: a WordRow object is created
  * @param word: the String that the WordRow object represents
  * @param colour: the colour associated with the word (indicates correctness, will change later)
  */
  public WordRow(String word, String colour) {
    wordLength = word.length();
    letters = new LetterCell[wordLength];
    for (int i = 0; i < wordLength; i++) {
      letters[i] = new LetterCell((word.substring(i, i+1)).charAt(0), colour);
    }
  }

/**
  * Method that returns the letter at the indicated index argument
  * Preconditions: i is an integer from 0 to (length of the word) - 1
  * Postconditions: the letter at index i is returned
  * @ param i: the index of the returned letter
  */
  public LetterCell getLetterCell(int i) {
    return letters[i];
  }

/**
  * Method that replaces the letter at the specificed index with another letter
  * Preconditions: index is an integer from 0 to (length of the word) - 1, letter is a String
  * Postconditions: the letter at index i is replaced with the new letter
  * @ param index: the index of the letter to be replaced
  * @ param letter: the letter to replace the old letter
  */  
  public void replaceLetter(int index, String letter) {
    letters[index].setLetter(letter.toLowerCase().charAt(0));
  }


/**
  * method that updates the color of a WordRow when compared to another WordRow
  * Preconditions: initLetter is a single character, initColor is either "green", "yellow", "reset", or "grey"
  * Postconditions: a letterCell object is created
  * @param initLetter: the letter that the letterCell object contains
  * @param initColor: the color associated with the letter (indicates correctness)
  */
  public void updateColors(WordRow correctWord) {
    //copy correctWord so it doesn't change the correct answer
    String emptyWord = "";
    for (int a = 0; a < wordLength; a++) {
      emptyWord = emptyWord + "-";
    }
    WordRow answerTemp = new WordRow(emptyWord);
    for (int i = 0; i < wordLength; i++) {
      answerTemp.replaceLetter(i, String.valueOf(correctWord.getLetterCell(i).getLetter()));
    }
    int[] colorForLetter = new int[wordLength]; 
    //0 is grey, yellow is 1, green is 2 (easier for further documentation)
    for (int i = 0; i < wordLength; i++) {
      //code to get green letters (if in correct spot)
      if (this.getLetterCell(i).equals(answerTemp.getLetterCell(i))) {
        answerTemp.getLetterCell(i).setLetter(("-").charAt(0));
        colorForLetter[i] = 2;
      } 
    }
    for (int j = 0; j < wordLength; j++) { 
      //check for any correct letters (yellow)
      for (int k = 0; k < wordLength; k++) {
        if ((this.getLetterCell(j).equals(answerTemp.getLetterCell(k))) && colorForLetter[j] != 2) {
        //checks to see if that letter is not already green and matches some other letter 
          colorForLetter[j] = 1;
          answerTemp.getLetterCell(k).setLetter(("-").charAt(0));
          //For repetition of letters, breakk for loop
          break;
        }
      }
    }
      //print the letters and their corresponding colors
      for (int m = 0; m < wordLength; m++) {
        if (colorForLetter[m] == 0) {
          this.getLetterCell(m).setColor("grey");
        }
        else if (colorForLetter[m] == 1) {
          this.getLetterCell(m).setColor("yellow");
        }
        else if (colorForLetter[m] == 2) {
          this.getLetterCell(m).setColor("green");
        }
      }
    }

/**
  * Method that checks if the guessed word is correct
  * Preconditions: answer is a WordRow object
  * Postconditions: returns true if the guess matches with the WordRow entered in the parameter
  * @ param answer: WordRow object that is compared to the guess
  */
  public boolean isCorrect(WordRow answer) {
    boolean correct = true;
    for (int i = 0; i < wordLength; i++) {
      if (!(this.getLetterCell(i).equals(answer.getLetterCell(i)))) {
        correct = false;
      }
    }
    return correct;
  }
  
/**
  * Method that returns the information that is printed onto the console
  * Postconditions: returns the word with its corresponding colors
  * @return: String of a colored word
  */
  public String toString() {
    String returnValue = "";
    for (int i = 0; i < wordLength; i++) {
      returnValue += getLetterCell(i).toString();
    }
    return returnValue;
  }
  
}