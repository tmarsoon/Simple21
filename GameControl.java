//Tony Marsalla
//Student I.D. 50053305
//References used: "Head First Java" by Mike McGrath
package simple21;

import java.util.Scanner;
import java.util.Random;

/**
 * This is a simplified version of a common card game, "21". 
 */
public class GameControl {
    
	/**
	 * Human player.
	 */
    HumanPlayer human;
    
    /**
     * Computer player.
     */
    ComputerPlayer player1;
    
    /**
     * Computer player.
     */
    ComputerPlayer player2;
    
    /**
     * Computer player.
     */
    ComputerPlayer player3;
    
    /** 
     * A random number generator to be used for returning random "cards" in a card deck.
     * */
    Random random = new Random();
      
    /**
     * The main method just creates a GameControl object and calls its run method.
     * @param args Not used.
     */
    public static void main(String args[]) {    
    	new GameControl().run();
    }
    
    /**
     * Prints a welcome method, then calls methods to perform each of the following actions:
     * - Create the players (one of them a Human)
     * - Deal the initial two cards to each player
     * - Control the play of the game
     * - Print the final results
     */
    public void run() {
    	
        Scanner scanner = new Scanner(System.in);
        
        // Students: your code goes here.
        // Printing welcome message
        System.out.println("Welcome to Simple 21!" + "\n" + "You'll play against 3 other players (computers).");
        System.out.println("Try to get as close to 21 as possible, without going over.");
        
        //asking the user what their name is
        System.out.print("What is your name: ");
        String playersName = scanner.nextLine();
        //creating the player base on the users input
        createPlayers(playersName);
        
        //Create card deal function
        deal();
        //after the first deal, this is to print the hands of the players
        human.showCards(human, player1, player2, player3);
        //control the play of the game
        controlPlay(scanner);
        
        printResults();
        //print the final results
        printWinner();
        
        scanner.close();
    }
    
    /**
     * Creates one human player with the given humansName, and three computer players with hard-coded names.
     * @param humansName for human player
     */
    public void createPlayers(String humansName) {
       // Students: your code goes here.
    	//this will take in the user input for their name
    	human = new HumanPlayer(humansName);
    	//create 3 players
    	player1 = new ComputerPlayer("Player 1");
    	player2 = new ComputerPlayer("Player 2");
    	player3 = new ComputerPlayer("Player 3");
    	
    }
    
    /**
     * Deals two "cards" to each player, one hidden, so that only the player who gets it knows what it is, 
     * and one face up, so that everyone can see it. (Actually, what the other players see is the total 
     * of each other player's cards, not the individual cards.)
     */
    public void deal() { 
        // Students: your code goes here.
    	//deal each player a card that is face up and face down
    	//human
    	human.takeHiddenCard(nextCard());
    	human.takeVisibleCard(nextCard());
    
    	//player1
    	player1.takeHiddenCard(nextCard());
    	player1.takeVisibleCard(nextCard());
    	
    	//player2
    	player2.takeHiddenCard(nextCard());
    	player2.takeVisibleCard(nextCard());
    	
    	//player3
    	player3.takeHiddenCard(nextCard());
    	player3.takeVisibleCard(nextCard());
    }
    
    /**
     * Returns a random "card", represented by an integer between 1 and 10, inclusive. 
     * The odds of returning a 10 are four times as likely as any other value (because in an actual
     * deck of cards, 10, Jack, Queen, and King all count as 10).
     * 
     * Note: The java.util package contains a Random class, which is perfect for generating random numbers.
     * @return a random integer in the range 1 - 10.
     */
    public int nextCard() { 
    	// Students: your code goes here.
    	//generate a random number from 1-10. We have to add 1 because we don't want 0 being generated. 
    	//Taking into account jack, queen, and queen
    	int randomNumber = random.nextInt(13) + 1;
    	//Ill consider all numbers above 10 as a face card.
    	if (randomNumber > 10) {
    		//same value as a 10
    		return 10;
    	} else {
    		return randomNumber;
    	}
    }

    /**
     * Gives each player in turn a chance to take a card, until all players have passed. Prints a message when 
     * a player passes. Once a player has passed, that player is not given another chance to take a card.
     * @param scanner to use for user input
     */
    public void controlPlay(Scanner scanner) { 
        // Students: your code goes here.
    	/** This controls the flow of the game and allows each player to either take or pass.
    	 * @param scanner, the scanner object is for reading user input.
    	 * Boolean flag variables @param allPlayersHavePassed & @param displayComputerCards are used to display each players points after a take/pass
    	 * has been applied to each player. The points will only call displayComputerCards when the user input is equivalent to 'y'. If 'n' is pressed, the 
    	 * points will not display and the computer players will complete their take/pass round prior to the game being over. allPlayersHavePassed will be true once
    	 * each player has passed.
    	*/
    	boolean allPlayersHavePassed = false;
    	boolean displayComputerCards = false;
    	while (!allPlayersHavePassed){
    		if (!human.passed) {
    			System.out.print("\n" + "Take another card? ");
    			String input = scanner.nextLine();
    			if (input.equalsIgnoreCase("y")) {
    				//drawn another card
    				 human.takeVisibleCard(nextCard());
    				 displayComputerCards = true;
    			} else {
    				human.passed=true;
    				System.out.println(human.name + " passes.");
    			}
    		}
    		if (!player1.passed) {
    			if (player1.getScore() <= 17) {
					player1.takeVisibleCard(nextCard());
    		} else {
    			player1.passed=true;
    			System.out.println(player1.name + " passes. ");
    		}
    }
    		if (!player2.passed) {
    			if (player2.getScore() <= 17) {
					player2.takeVisibleCard(nextCard());
    		} else {
    			player2.passed = true;
    			System.out.println(player2.name + " passes. ");
    		}}
    		if (!player3.passed) {
    			if (player3.getScore() <= 17) {
					player3.takeVisibleCard(nextCard());
    		} else {
    			player3.passed = true;
    			System.out.println(player3.name + " passes. ");
    		}}
    		 if (displayComputerCards) {
    	     human.showCards(human, player1, player2, player3);
    	     displayComputerCards = false; // Reset the flag
    	        }
    		
    		allPlayersHavePassed = checkAllPlayersHavePassed();
    		}
    }
    /**
     * Checks if all players have passed.
     * @return true if all players have passed
     */
    public boolean checkAllPlayersHavePassed() {
    	// Students: your code goes here.
    	/**
    	 * @return the passing of each player which will then result in the print results function being called. 
    	 */
    	return human.passed && player1.passed && player2.passed && player3.passed;
    }
    
    /**
     * Prints a summary at the end of the game.
     * Displays how many points each player had, and if applicable, who won.
     */
    public void printResults() { 
        // Students: your code goes here.
    	//Once everyone has passed, this code is executed printing the total points of each player.
    	System.out.println("\n" + "Game over.");
    	int humanTotal = human.getScore();
        int player1Total = player1.getScore();
        int player2Total = player2.getScore();
        int player3Total = player3.getScore();
        
        System.out.println(human.name + " has " + humanTotal + " total point(s).");
        System.out.println(player1.name + " has " + player1Total + " total point(s).");
        System.out.println(player2.name + " has " + player2Total + " total point(s).");
        System.out.println(player3.name + " has " + player3Total + " total point(s).");
    }

    /**
     * Determines who won the game, and prints the results.
     */
    public void printWinner() { 
        // Students: your code goes here.
    	/**
    	 * @param humanNoBust, player1Bust, player2Bust, player3Bust will be used to test that as long as each player isn't at 22 or above
    	 * @param maxScore is set to -1 because(although not possible)its lower than any other score that a player can get. This allows me to   use this variable
    	 * to store the highest value (under or equal to 21) if it exist as it grabs the highest number during iteration. This allows a leader to be determined
    	 * compared to all other players.
    	 * @param winners is an array I used to print the winner because we have 4 possible winners. In addition, @param winnercount helps
    	 * the print out determine if its a tie when more then one winner exist. 
    	 */
       
        int humanTotal = human.getScore();
        int player1Total = player1.getScore();
        int player2Total = player2.getScore();
        int player3Total = player3.getScore();

        boolean humanNoBust = humanTotal <= 21;
        boolean player1NoBust = player1Total <= 21;
        boolean player2NoBust = player2Total <= 21;
        boolean player3NoBust = player3Total <= 21;

        //4 players can win
        String[] winners = new String[4]; 
        //counter for the number of winners
        int winnerCount = 0; 
        //Initializing max score to be used to print the points value of the winner.
        int maxScore = -1;
        // Determine the winner(s) for each player and also update maxScore
        if (humanNoBust && humanTotal >= maxScore) {
            if (humanTotal > maxScore) {
                maxScore = humanTotal;
                winnerCount = 1; // Reset the winner count if a new max score is found
                winners[0] = human.name;
            } else {
                winners[winnerCount++] = human.name;
            }
        }
        if (player1NoBust && player1Total >= maxScore) {
            if (player1Total > maxScore) {
                maxScore = player1Total;
                winnerCount = 1;
                winners[0] = player1.name;
            } else {
                winners[winnerCount++] = player1.name;
            }
        }
        if (player2NoBust && player2Total >= maxScore) {
            if (player2Total > maxScore) {
                maxScore = player2Total;
                winnerCount = 1;
                winners[0] = player2.name;
            } else {
                winners[winnerCount++] = player2.name;
            }
        }
        if (player3NoBust && player3Total >= maxScore) {
            if (player3Total > maxScore) {
                maxScore = player3Total;
                winnerCount = 1;
                winners[0] = player3.name;
            } else {
                winners[winnerCount++] = player3.name;
            }
        }

        // If the winnerCount is 0, or in other words if everyone goes over 21, its a bust and there is no winner.
        if (winnerCount == 0) {
            System.out.println("No winner. Everyone busts!");
        //if there is a tie at 21 or lower then 21 (but higher then everyone else without busting) it's a tie. 
        } else if (winnerCount > 1) {
            System.out.print("Tie, nobody wins.");
        } else {
            System.out.println(winners[0] + " wins with " + maxScore + " point(s).");
        }
    }
        }
    
    	
    	
    	
    
        
            
        
    

    

