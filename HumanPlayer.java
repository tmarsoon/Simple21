package simple21;

import java.util.Scanner;

/**
 * Represents a human player in a game of Simple 21.
 */
public class HumanPlayer {
	
	/** 
	 * The name of the player.
	 */
    String name;
    
    /**
     * The player's one hidden card (a value from 1 - 10).
     */
    private int hiddenCard = 0;
    
    /** 
     * The sum of the player's cards, not counting the hidden card. 
     */
    private int sumOfVisibleCards = 0;
    
    /**
     * Flag indicating if the player has passed (asked for no more cards).
     */
    boolean passed = false;
    
    /**
     * Constructs a human player with the given name.
     * @param name of the user.
     */
    public HumanPlayer(String name) {
        this.name = name;
    }
    
    /**
     * Asks the Human player whether to take another card and uses the given scanner to prompt for a response.
     * @param human This human player
     * @param player1 Another (computer) player
     * @param player2 Another (computer) player
     * @param player3 Another (computer) player
     * @param scanner To use for scanning for human input
     * @return true if this human player wants another card
     */
    public boolean offerCard(HumanPlayer human, ComputerPlayer player1, ComputerPlayer player2, ComputerPlayer player3, Scanner scanner) {
    	boolean response = true;
    	
    	this.showCards(this, player1, player2, player3);
    	
    	response = this.getYesOrNoToQuestion("Take another card?", scanner);
    	if (!response) this.passed = true;
    	
    	return response;
    }
    
    /**
     * Prints the sum of all of this Human's cards, and the sum of each of the other (computer) players' 
     * visible cards.
     * @param human This human player
     * @param player1 Another (computer) player
     * @param player2 Another (computer) player
     * @param player3 Another (computer) player
     */
    public void showCards(HumanPlayer human, ComputerPlayer player1, ComputerPlayer player2, ComputerPlayer player3) {
    	// Students: your code goes here.
    	/** @param humanPoints, player1Points, player2Points, player3Points get the total scores of both visible and hidden values
    	 */
    	int humanPoints = human.getScore();
    	int player1Points = player1.getScore();
    	int player2Points = player2.getScore();
    	int player3Points = player3.getScore();
    	System.out.println("\n" + human.name + " has " + humanPoints + " total point(s).");
    	System.out.println("Player 1 has " + player1Points + " visible points(s).");
    	System.out.println("Player 2 has " + player2Points + " visible points(s).");
    	System.out.println("Player 3 has " + player3Points + " visible points(s).");
    }
    
    /**
     * Displays the given question and prompts for user input using the given scanner.
     * @param question to ask
     * @param scanner to use for user input
     * @return true if the user inputs 'y'
     */
    public boolean getYesOrNoToQuestion(String question, Scanner scanner) {
    	boolean response = true;
    	
    	String answer;

    	System.out.println();
        System.out.print(question + " ");
        
        while(true) {
	        answer = scanner.next();
	        
	        if (answer.toLowerCase().charAt(0) == 'y') {
	        	response = true;
	        	break;
	        } else if (answer.toLowerCase().charAt(0) == 'n') {
	        	response = false;
	        	break;
	        }
        }

        return response;
    }
    
    /**    
     * Puts the specified card in this human's hand as the hidden card.
     * Prints a message saying that the card is being taken, and prints the value of the hidden card.
     * @param card being taken
     */
    public void takeHiddenCard(int card) {
    	// Students: your code goes here.
    	// Assign the card value to the hiddenCard variable
    	hiddenCard = card;
    	//here i'm doing formatting to match the output of our example output in the assignment sheet
    	System.out.println("\n" + name + " takes a hidden card." + "\n" + " (It's a " + card + ")");
    }
    
    
    /**
     * Adds the given card to the sum of the visible cards for this human player.
     * Prints a message saying that the card is being taken.
     * @param card being taken
     */
    public void takeVisibleCard(int card) { 
    	// Students: your code goes here.
    	/**any visible card that is drawn is going to be added to the 
    	 * @param sumOfVisibleCards
    	 */
    	sumOfVisibleCards += card;
    	System.out.println(name + " takes " + card);
    	
    	
    
    }

    /**
     * Returns the total sum of this player's cards, not counting the hidden card. 
     * @return sumOfVisibleCards
     */
    public int getSumOfVisibleCards() { 
    	// Students: your code goes here.
    	/**
    	 * @rturns the sumOfVisibleCards
    	 */
    	return sumOfVisibleCards;
    }
    
    /**
     * Return this player's total score (the total of all this human player's cards).
     * That is to say, the sum of the visible cards + the hidden card.
     * @return total score 
     */
    public int getScore() { 
    	// Students: your code goes here.
    	/** 
    	 * @returns sumOfVisibleCards & hiddenCards will return the total score. 
    	 */
    	return sumOfVisibleCards + hiddenCard;
    }
}
