/**
* CreateFile.java
* @author Kevin
* @since May 17, 2022
* This class creates a basic user object for every account
*/

public class User{
  private String username;
  private boolean status;
  
  private String password;
  private double pastGameMean;
  private double pastTimeMean;    
  private int numOfGames;
  private int[] trialDistribution; 
    //{#1stTrialWin,#2ndTrialWin...#20thTrialtWin}
  private long[] timeDistribution;    
    //{time1, time2, ...}

/**
  * Constructor that takes a newUsername and newPassword
  * Preconditions: parameters are not null nor only spaces
  * Postconditions: the user object is created with username, password and others as default values
  * @param newUsername: not null nor only spaces
  * @param newPassword: not null nor only spaces
  */
  public User (String newUsername, String newPassword){
    this.username = newUsername;
    this.password = newPassword;
    this.status = false;
    this.pastGameMean = 0;
    this.pastTimeMean = 0.0;
    this.numOfGames = 0;
    this.trialDistribution = new int[20];  
    this.timeDistribution = new long[1];
  }
  
/**
  * Constructor that takes a newUsername and newPassword
  * Preconditions: parameters are not null and valid
  * Postconditions: the user object is created with username, password and others input values
  * @param newUsername: not null nor only spaces
  * @param newPassword: not null nor only spaces
  * @param existMean: >= 0
  * @param exsitTimeMean: >= 0
  * @param exsitNum: >= 0
  * @param existTrial: not null and every data >= 0
  * @param existTime: not null and every data >= 0
  */
  public User(String existUsername, String existPassword, double existMean, double existTimeMean, int existNum, int[] existTrial, long[] existTime){
    this.username = existUsername;
    this.password = existPassword;
    this.pastGameMean = existMean;
    this.pastTimeMean = existTimeMean;
    this.numOfGames = existNum;
    this.trialDistribution = existTrial;
    this.timeDistribution = existTime;
    this.status = true;
  }

/**
  *Getter method that returns the username
  */
  public String getUsername(){
    return (this.username);
  }

/**
  *Getter method that returns the password
  */
  public String getPassword(){
    return (this.password);
  }

  /**
  *Getter method that returns the status
  */
  public boolean getStatus(){
    return (this.status);
  }

/**
  *Getter method that returns the past game mean
  */
  public double getPastMean(){
    return (this.pastGameMean);
  }

/**
  *Getter method that returns the past time mean
  */
  public double getPastTimeMean(){
    return (this.pastTimeMean);
  }   //

/**
  *Getter method that returns the number of games
  */
  public int getNumOfGames(){
    return (this.numOfGames);
  }

/**
  *Getter method that returns the trial distribution
  */
  public int[] getTrialDistribution(){
    return (this.trialDistribution);
  }

/**
  *Getter method that returns the time distribution
  */
  public long[] getTimeDistribution(){
    return (this.timeDistribution);
  }  //

/**
  *Setter method that sets the username to the parameter
  *Preconditions: parameter is not null nor only spaces
  *Postconditions: username is changed to parameter
  *@param changedUN: new username
  */
  public void setUsername(String changedUN){
    this.username = changedUN;
  }

  /**
  *Setter method that sets the password to the parameter
  *Preconditions: parameter is not null nor only spaces
  *Postconditions: password is changed to parameter
  *@param changedPW: new password
  */
  public void setPassword(String changedPW){
    this.password = changedPW;
  }

  /**
  *Setter method that sets the status to the parameter
  *Preconditions: parameter is not null
  *Postconditions: status is changed to parameter
  *@param changedStatus: new status
  */
  public void setStatus(boolean changedStatus){
    this.status = changedStatus;
  }

/**
  *Method that calculates the past average trials
  *Postconditions: pastGameMean is set to the calculated average
  */
  public void calculatePastGameMean(){
    int sum = 0;
    for(int i = 0; i < this.trialDistribution.length && this.trialDistribution[i] != 0; i++){
      sum += (i+1) * this.trialDistribution[i];
    }
    this.pastGameMean = (double)sum/this.numOfGames; 
  }

/**
  *Method that calculates the past average time
  *Postconditions: pastTimeMean is set to the calculated average
  */
  public void calculatePastTimeMean(){
    long sum = 0;
    for (long val:this.timeDistribution){
      sum+=val;
    }
    this.pastTimeMean = sum/(double)this.numOfGames; 
  }    //

/**
  *Method that changes the past average tries to the new average calculated after a new trial is added
  *Preconditions: parameter >= 0
  *Postconditions: pastGameMean is set to the new calculated average
  *@param newTrial: the number of tries took to win the newest game
  */  
  public void updatePastMean(int newTrial){
    this.pastGameMean = (this.pastGameMean*this.numOfGames + newTrial)/(this.numOfGames+1);
  }

/**
  *Method that changes the past average time to the new average calculated after a new time is added
  *Preconditions: parameter >= 0
  *Postconditions: pastTimeMean is set to the new calculated average
  *@param newTrial: the time took to win the newest game
  */  
  public void updatePastTimeMean(long newTime){
    this.pastTimeMean = (this.pastTimeMean*this.numOfGames + newTime)/(this.numOfGames+1);
  }    
  
/**
  *Method that adds 1 to the number of games
  */
  public void updateNumOfGames(){
    this.numOfGames += 1;
  }

/**
  *Method that changes the trial distribution to the newest after a new trial is added
  *Preconditions: parameter >= 0
  *Postconditions: the according number of trials in the array is incremented by 1
  *@param newTrial: the number of tries took to win the newest game
  */
  public void updateTrialDistribution(int newTrial){
    this.trialDistribution[newTrial-1] += 1;
  }

/**
  *Method that changes the time distribution to the newest after a new time is added
  *Preconditions: parameter >= 0
  *Postconditions: the new time is added to the array
  *@param newTrial: the time took to win the newest game
  */
  public void updateTimeDistribution(long newTime){
    //if the time distribution is not default of a new account (ie 0)
    if (this.timeDistribution[0] != 0){
      //the updated array that timeDistribution will be set to
      long[] temp = new long[this.timeDistribution.length + 1]; 
      for (int i = 0; i < this.timeDistribution.length; i++){
        temp[i] = this.timeDistribution[i];
      }
      temp[this.timeDistribution.length] = newTime;
      this.timeDistribution = temp;
    }
    //if the time disribution is default {0}
    else{
      //change {0} to {newTime}
      this.timeDistribution[0] = newTime;
    }
  }

}

