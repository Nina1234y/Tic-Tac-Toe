import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class Controller implements ActionListener{

	public Block block;
	public Model model;
	boolean winner = false;
	
	public Controller (String st) {
		block = new Block(this);
		model = new Model(st);
	}
	
	/**
	 * Sets the value of block
	 * */
	public Block getBlock () {
		return block;
	}
	
	/**
	 * Sets the value of model
	 * */
	public Model getModel () {
		return model;
	}

	
	/**
	 * Process an event initialized by the user clicking on a button
	 * */
	@Override
	public void actionPerformed(ActionEvent e) {

		JButton player = new JButton();
		if (e.getActionCommand() == "Close") {
			block.setVisible(false);
			block.dispose();
			System.exit(0);
		}
		else if(e.getActionCommand() == "Reset") {
			block.reset(this);
			model.setWinner("");
			model.resetRoundsPlayed();
			winner = false;
		}
		else if (e.getActionCommand() == "X" || e.getActionCommand() == "O") {
			
		}
		else if (e.getSource().getClass() != player.getClass())
			throw new IllegalArgumentException();
		else {
		player = (JButton) e.getSource();
		model.setPlayer(player);
		Boolean ok = false;
		
		if (Model.roundsPlayed < 8 && !winner) {
			while (!ok){
				ok = model.playerMove(block, player);
			}
			if (block.isWin(model.playerS)) {
				winner = true;
				block.setLabel(Util.PLAYER);
			}
			
			ok = false;
			while (!ok && !winner) {
				ok = model.computerMove(block);
			}
			if (block.isWin(model.computerS)) {
				winner = true;
				block.setLabel(Util.COMPUTER);
			}
		}
		else {
			while (!ok && !winner){
				ok = model.playerMove(block, player);
			}
			if (block.isWin(model.playerS)) {
				winner = true;
				block.setLabel(Util.PLAYER);
			}
		}
		}
	}
}
