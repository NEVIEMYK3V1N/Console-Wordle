# Main.java
# author Kevin, Derek, Dan
# since May 17, 2022

@version 5 - May 17

This is a mimic of wordle game

@Preconditions: This game can only be run with a guest.txt file with at least the initial variables
  The username of the account the player wishes to create is a valid name for a txt file

This is a game where the player attempts to guess a random word from an existing word dictionary. The program then compares the player guess with the generated word. The player wins the game if successfully the guess matches the word, and loses if the player runs out of attempts or time.

  Word length can vary from 4-6, default 5
  The player has 1-20 (default 6) attempts to guess the word
  The player has a time limit of their choice (>0) to guess the word (default unlimited)
  
The player can play the game as a guest, where if they win a game, the number of trials and time they used will be stored in the guest.txt file only and calculated averages from other games will be displayed for comparison.

The player can also play the game under an existing txt file that matches the inputted username and password.
  In this case, the number of trials and time used will be stored in the player's personal file and calculated averages from the player's other games will be displayed in addition to as a guest.

If the player wins the game, the program ends after displaying averages and updating the txt files.

If the player loses the game, the programs ends after displaying the correct word.

The player would be able to play the game as designed by following the navigation displayed in the console.

The exe folder contains the executable Jar file of the game which can be directly run.
    IMPORTANT: guest.txt and all other user data files MUST be included in the same folder!