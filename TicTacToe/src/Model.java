import javax.swing.JButton;

public class Model {
	public JButton player; // The button choice of player from 1-9
	public JButton computer; // The button choice of the computer from 1-9
	public String winner;
	public final String playerS; // The sign of the player 
	public final String computerS; // The sign of the computer
	private static final String X = "X";
	private static final String O = "O";
	public static int roundsPlayed; // Total number of rounds played is 9
	
	/**
	 * The constructor sets the player to its choice
	 * Then, initializes the computer for the other choice
	 * Sets winner to empty string
	 * */
	public Model(String choice) {
		this.playerS = choice;
		
		if (choice.equals(Model.X))
			this.computerS = Model.O;
		else
			this.computerS = Model.X;
		
		this.winner = "";
		Model.roundsPlayed  = 0;
	}
	
	/**
	 * 1 Move of a player or a computer
	 * Precondition, the bt variable is bigger than 0 and less than 10 and is a reference from the Block class
	 * */
	public boolean playerMove (Block b, JButton bt) {
		if (b.isEmpty(bt)) {
			b.setSign(bt, playerS);
			Model.roundsPlayed ++;
		//	System.out.println(Model.roundsPlayed);
			return true;
		}
		
		return false;
	}
	
	/**
	 * 1 Move of the computer
	 * Currently picks by order of magnitude
	 * */
	public boolean computerMove (Block b) {
		JButton dest = bestComputerMove(b);
		if (dest.getText() != "Nothing") {
			b.setSign(dest, computerS);
			Model.roundsPlayed ++;
			return true;
		}
		
		for (int i = 0; i < 9; i ++) {
			if (b.isEmpty(b.buttons[i])) {
				b.setSign(b.buttons[i], computerS);
				Model.roundsPlayed ++;
			//	System.out.println(Model.roundsPlayed);
				return true;
			}
		}
		return false;
	}
	
	
	/**
	 * Returns the button that will win the computer the game
	 * */
	private JButton bestComputerMove (Block b) {	
		if (Model.roundsPlayed > 4) {
			// Return the button that will win the computer a row
			for (int i = 0; i < b.buttons.length; i += 3) {
				if (b.buttons[i].getText() == computerS && b.buttons[i+1].getText() == computerS &&
						b.isEmpty(b.buttons[i+2]))
					return b.buttons[i+2];
			
				if (b.buttons[i].getText() == computerS && b.isEmpty(b.buttons[i+1]) &&
						b.buttons[i+2].getText() == computerS)
					return b.buttons[i+1];
				
				if (b.isEmpty(b.buttons[i]) && b.buttons[i+1].getText() == computerS &&
						b.buttons[i+2].getText() == computerS)
					return b.buttons[i];
			}
		
			// Returns the button that will win the computer a column
			for (int i = 0; i < b.buttons.length / 3; i ++) {
				if (b.buttons[i].getText() == computerS && b.buttons[i+3].getText() == computerS &&
						b.isEmpty(b.buttons[i+6]))
					return b.buttons[i+6];
			
				if (b.buttons[i].getText() == computerS && b.isEmpty(b.buttons[i+3]) &&
						b.buttons[i+6].getText() == computerS)
					return b.buttons[i+3];
			
				if (b.isEmpty(b.buttons[i]) && b.buttons[i+3].getText() == computerS &&
						b.buttons[i+6].getText() == computerS)
					return b.buttons[i];
			}
		
			// Returns the button that will win the computer a first diagonal
			if (b.buttons[0].getText() == computerS && b.buttons[4].getText() == computerS &&
					b.isEmpty(b.buttons[8]))
				return b.buttons[8];
			if (b.buttons[0].getText() == computerS && b.isEmpty(b.buttons[4]) &&
					b.buttons[8].getText() == computerS)
				return b.buttons[4];
			if (b.isEmpty(b.buttons[0]) && b.buttons[4].getText() == computerS &&
					b.buttons[8].getText() == computerS)
				return b.buttons[0];
		
			// Returns the button that will win the computer a second diagonal
			if (b.buttons[2].getText() == computerS && b.buttons[4].getText() == computerS &&
					b.isEmpty(b.buttons[6]))
				return b.buttons[6];
			if (b.buttons[0].getText() == computerS && b.isEmpty(b.buttons[4]) &&
					b.buttons[8].getText() == computerS)
				return b.buttons[4];
			if (b.isEmpty(b.buttons[0]) && b.buttons[4].getText() == computerS &&
					b.buttons[8].getText() == computerS)
				return b.buttons[0];
		}// if
		
		// Less than 5 moves or no current win
		for (int i = 0; i < b.buttons.length - 1; i ++) {
			if (b.buttons[i].getText() == computerS && b.buttons[i+1].getText() == "")
				return b.buttons[i+1];
		}
		
		for (int i = 0; i < b.buttons.length * 2 / 3; i ++) {
			if (b.buttons[i].getText() == computerS && b.buttons[i+3].getText() == "")
				return b.buttons[i+3];
		} 
		
		JButton nothing = new JButton("Nothing");
		return nothing;
	}
	
	/**
	 * Returns the sign of the player
	 * */
	public String getPlayerS () {
		return this.playerS;
	}
	
	/**
	 * Returns the computer's sign
	 * */
	public String getComputerS() {
		return this.computerS;
	}
	
	/**
	 * Returns the winner of this round
	 * */
	public String getWinner () { 
		return this.winner;
	}
	
	/**
	 * Sets winner to the current winner of the game
	 * */
	public void setWinner (String winner) {
		this.winner = winner;
	}
	
	public void setPlayer(JButton b) {
		player = b;
	}
	
	public void setComputer (JButton b) {
		computer = b;
	}
	
	public void resetRoundsPlayed () {
		Model.roundsPlayed  = 0;
	}
}
