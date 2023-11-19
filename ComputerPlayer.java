package simple21;

/**
 * Represents a computer player in this simplified version of the "21" card game.
 */
public class ComputerPlayer {

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
     * Constructs a computer player with the given name.
     * @param name of the user.
     */
    public ComputerPlayer (String name) {
        this.name = name;
    }
    
    /**
     * Decides whether to take another card. In order to make this decision, this player considers 
     * their own total points (sum of visible cards + hidden card). 
     * This player may also consider other players' sum of visible cards, but not the value 
     * of other players' hidden cards.
     * @param human The other human player
     * @param player1 Another (computer) player
     * @param player2 Another (computer) player
     * @param player3 Another (computer) player
     * @return true if this player wants another card
     */
    public boolean offerCard(HumanPlayer human, ComputerPlayer player1, ComputerPlayer player2, ComputerPlayer player3) { 
    	// Students: your code goes here.
    	//Referenced "Head First Java" by Mike McGrath to understand how to use the math methods below.
    	/**
    	 * Typically in blackjack, if a player has 17, it's a good idea to stay which is what I do here.
    	 * However, if another player has more then 14, it's best for the opponent to draw considering we also dont know what the hidden card value is. 
    	 * @param minOpponentScore is a math function I grabbed from the textbook that basically determines the lowest score and if a player is less 
    	 * then his components, take. I didn't stop it at 21 becuase I want to allow for busting. 
    	 */
    	
    	int currentPlayerScore = getSumOfVisibleCards(); // Use sum of visible cards only

        // Calculate the scores of all other players using their visible cards
        int humanScore = human.getSumOfVisibleCards();
        int player1Score = player1.getSumOfVisibleCards();
        int player2Score = player2.getSumOfVisibleCards();
        int player3Score = player3.getSumOfVisibleCards();
        int minOpponentScore = Math.min(humanScore, Math.min(player1Score, Math.min(player2Score, player3Score)));
        // This is my strategy:
        //If the visible points of the cards is greater than 14 but lower then their opponents under 21 including the hidden cards, take. 
        if (currentPlayerScore > 14) {
            // Determine the lowest score among opponents
            // Check if the current player's score is lower than the lowest opponent score
            
            if (getScore() < minOpponentScore) {
                return true; // Draw another card
            } else {
                passed = true; // Player passes
                return false;
            }
        } else {
            return true; // Player's score is less than 17, so draw another card
        }
    }
    
    
    
    /**    
     * Puts the specified card in this player's hand as the hidden card.
     * Prints a message saying that the card is being taken, but does not print the value of the hidden card.
     * @param card being taken
     */
    public void takeHiddenCard(int card) {
    	// Students: your code goes here.
    	  hiddenCard = card;
          System.out.println(name + " takes a hidden card.");
    }
    
    /**
     * Adds the given card to the sum of the visible cards for this player.
     * Prints a message saying that the card is being taken.
     * @param card being taken
     */
    public void takeVisibleCard(int card) { 
    	// Students: your code goes here.
    	 sumOfVisibleCards += card;
         System.out.println(name + " takes " + card);
    }

    /**
     * Returns the total sum of this player's cards, not counting the hidden card. 
     * @return sumOfVisibleCards
     */
    public int getSumOfVisibleCards() { 
    	// Students: your code goes here.
    	 return sumOfVisibleCards;
    }
    
    /**
     * Return this player's total score (the total of all this player's cards).
     * That is to say, the sum of the visible cards + the hidden card.
     * @return total score 
     */
    public int getScore() { 
    	// Students: your code goes here.
    	return sumOfVisibleCards + hiddenCard;
    }
}
