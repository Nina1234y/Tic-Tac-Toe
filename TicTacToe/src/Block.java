import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

 
@SuppressWarnings("serial")
public class Block extends JFrame{
	JButton[] buttons;
	
	JButton close;
	JButton reset;
	JLabel label;
	final static private int HEIGHT = 500;
	final static private int WIDTH = 400;
	
	/***/
	public Block (ActionListener listener) {
		super("Tic Tac Toe");	// Calls the super constructor JFrame
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		createButtons(listener);
		createLabel();
		this.setMinimumSize(new Dimension(Block.WIDTH, Block.HEIGHT));
		this.setMaximumSize(new Dimension(Block.WIDTH, Block.HEIGHT));
        this.setLayout(new GridLayout(5, 1, 1, 1));
	}
	
	/**
	 * Initializes and defines the buttons to empty values.
	 * Set the size of each button to be 1/9 of the panel.
	 */
	private void createButtons (ActionListener l) {
		buttons = new JButton[9];
		
		for (int i = 0; i < 9; i ++) {
			buttons[i] = new JButton("");
			buttons[i].addActionListener(l);
			buttons[i].setBackground(Color.lightGray);
			buttons[i].setOpaque(true);
		}
		
		close = new JButton("Close");
		close.addActionListener(l);
		reset = new JButton("Reset");
		reset.addActionListener(l);
		
		JPanel helper;
		
		for (int i = 0; i < 9; i += 3) {
			helper = new JPanel(new GridLayout(1,3,1,0));
			helper.add(buttons[i]);
			helper.add(buttons[i+1]);
			helper.add(buttons[i+2]);
			this.add(helper);
		}
		
		helper = new JPanel (new GridLayout(1,2, 1, 0));
		helper.add(reset);
		helper.add(close);
		this.add(helper);
		reset.setFont(new Font("Arial", Font.PLAIN, 20));
		reset.setBackground(Color.LIGHT_GRAY);
		reset.setOpaque(true);
		reset.setBorderPainted(false);
		close.setFont(new Font("Arial", Font.PLAIN, 20));
		close.setBackground(Color.YELLOW);
		close.setOpaque(true);
		close.setBorderPainted(false);
	}
	
	/**
	 * Initializes the label
	 * */
	public void createLabel() {
		label = new JLabel("The winner is: ...");
		label.setFont(new Font("Arial", Font.PLAIN, 20));
		JPanel helper = new JPanel (new GridLayout(1,1));
		helper.add(label);
		this.add(helper);
	}
	
	/**
	 * Sets the label with the string of the winner
	 * */
	public void setLabel (String winner) {
		label.setText("The winner is: " + winner);
	}
	
	/**
	 * Checks if the current Button is an empty string or already taken.
	 * If the Button has already been used - return false;
	 * If the Button hasn't been used - return true;
	 */
	public boolean isEmpty(JButton b) {
		if (b.getText().equals(""))
			return true;
		return false;
	}
	
	/**
	 * Checks if all the buttons have been used up
	 * */
	public boolean isSomeEmpty () {
		for (int i = 0; i < 9; i ++) {
			if (buttons[i].getText() == "")
				return true;
		}
		
		return false;
	}
	
	/**
	 * Sets the sign at in the button b. Adjusts the color based on the string st
	 * */
	public void setSign (JButton b, String st) {
		b.setText(st);
		b.setFont(new Font("Arial", Font.PLAIN, 30));
		
		if (st == "X") {
			b.setBackground(Color.GREEN);
			b.setForeground(Color.BLACK);
		}
		else {
			b.setBackground(Color.BLACK);
			b.setForeground(Color.GREEN);
		}
		
		b.setOpaque(true);
	}
	
	/**
	 * Check if there is a win for the current sign str
	 * A win is a strike of a row, a column or a diagonal
	 * Returns true if there is a winner
	 * Returns false if there is no winner
	 * */
	public boolean isWin (String str) {
		
		for (int i = 0; i < 9; i +=3 ) {
			if (buttons[i].getText() == str && buttons[i+1].getText() == str && buttons[i+2].getText() == str) {
				buttons[i].setBackground(Color.RED);
				buttons[i+1].setBackground(Color.RED);
				buttons[i+2].setBackground(Color.RED);
				return true;
			}
		}
		
		for (int i = 0; i < 3; i ++ ) {
			if (buttons[i].getText() == str && buttons[i+3].getText() == str && buttons[i+6].getText() == str) {
				buttons[i].setBackground(Color.RED);
				buttons[i+3].setBackground(Color.RED);
				buttons[i+6].setBackground(Color.RED);
				return true;
			}
		}
		
		if (buttons[0].getText() == str && buttons[4].getText() == str && buttons[8].getText() == str) {
			buttons[0].setBackground(Color.RED);
			buttons[4].setBackground(Color.RED);
			buttons[8].setBackground(Color.RED);
			return true;
		}
		if (buttons[2].getText() == str && buttons[4].getText() == str && buttons[6].getText() == str) {
			buttons[2].setBackground(Color.RED);
			buttons[4].setBackground(Color.RED);
			buttons[6].setBackground(Color.RED);
			return true;
		}
		
		return false;
	}
	
	/**
	 * Resets all the components and restarts the game
	 * */
	public void reset (ActionListener l) {
		for (int i = 0; i < 9; i ++) {
			buttons[i].setText("");
			buttons[i].setBackground(Color.lightGray);
		}
		
		label.setText("The winner is: ");
		
	}
	
	/*
	public static void main(String[] args) {
        Block view = new Block(null);
        view.setVisible(true);
        System.out.print(view.isEmpty(view.buttons[4]));
        view.setX(view.buttons[4]);
        view.setLabel("player");
        System.out.println(view.isEmpty(view.buttons[4]));
        view.setO(view.buttons[3]);
        view.setX(view.buttons[0]);
        view.setX(view.buttons[8]);
        System.out.println(view.isWin("O"));
        System.out.println(view.isWin("X"));
        
    }
	*/
}
